/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ada_proyecto2.controller;

/**
 *
 * @author juancarlos
 */


import com.mycompany.ada_proyecto2.App;
import com.mycompany.ada_proyecto2.database.DatabaseConnection;
import com.mycompany.ada_proyecto2.service.VehiculoService;
import com.mycompany.ada_proyecto2.util.AlertUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

public class LocalizarVehiculoController {
    private final VehiculoService vehiculoService = new VehiculoService();

    @FXML private TextField txtBusqueda;
    @FXML private TextArea txtResultado;

    
    //Boton buscar vehiculo
    @FXML
    private void buscarVehiculo() {
        String entrada = txtBusqueda.getText() == null ? "" : txtBusqueda.getText().trim();
        if (entrada.isBlank()) {
            AlertUtils.showError("Debes introducir un VIN o matrícula antes de buscar.");
            return;
        }
        try {
            Map<String, Object> vehiculo = vehiculoService.localizarVehiculo(entrada);
            String vin = String.valueOf(vehiculo.getOrDefault("vin", "-"));
            String matricula = String.valueOf(vehiculo.getOrDefault("matricula", "-"));
            String marca = String.valueOf(vehiculo.getOrDefault("marca", "-"));
            String modelo = String.valueOf(vehiculo.getOrDefault("modelo", "-"));
            Timestamp ultimaRecogida = (Timestamp) vehiculo.get("ultima_recogida");
            Timestamp ultimaEntrega = (Timestamp) vehiculo.get("ultima_entrega");

            StringBuilder resultado = new StringBuilder();
            resultado.append("VIN: ").append(vin).append("\n");
            resultado.append("Matrícula: ").append(matricula).append("\n");
            resultado.append("Marca: ").append(marca).append("\n");
            resultado.append("Modelo: ").append(modelo).append("\n");

            if (ultimaRecogida == null && ultimaEntrega == null) {
                resultado.append("Estado: SIN MOVIMIENTOS REGISTRADOS\n");
                resultado.append("No se puede determinar su ubicación actual.");
            } else if (ultimaEntrega != null && (ultimaRecogida == null || ultimaEntrega.toLocalDateTime().isAfter(ultimaRecogida.toLocalDateTime()))) {
                resultado.append("Estado: DISPONIBLE EN TIENDA\n");
                Map<String, Object> detalle = obtenerDetalleEntrega(vin, ultimaEntrega);
                resultado.append("Tienda: ").append(detalle.getOrDefault("tienda", "-")).append("\n");
                resultado.append("Dirección: ").append(detalle.getOrDefault("direccion", "-")).append("\n");
                resultado.append("Fecha de entrega: ").append(detalle.getOrDefault("fecha_entrega", "-")).append("\n");
                resultado.append("Último movimiento: ENTREGA");
            } else {
                resultado.append("Estado: ALQUILADO\n");
                Map<String, Object> detalle = obtenerDetalleRecogida(vin, ultimaRecogida);
                resultado.append("Cliente: ").append(detalle.getOrDefault("cliente", "-")).append("\n");
                resultado.append("DNI: ").append(detalle.getOrDefault("dni", "-")).append("\n");
                resultado.append("Tienda: ").append(detalle.getOrDefault("tienda", "-")).append("\n");
                resultado.append("Fecha de recogida: ").append(detalle.getOrDefault("fecha_recogida", "-")).append("\n");
                resultado.append("Fecha de entrega prevista: ").append(detalle.getOrDefault("fecha_entrega_prevista", "-")).append("\n");
                resultado.append("Último movimiento: RECOGIDA");
            }

            txtResultado.setText(resultado.toString());
        } catch (IllegalArgumentException | SQLException e) {
            AlertUtils.showError(e.getMessage());
        }
    }

    //Boton para volver menu de inicio
    @FXML
    private void volverAlMenu() throws IOException {
        App.setRoot("inicio");
    }

    
    //metodo para obtener detalle entrega
    private Map<String, Object> obtenerDetalleEntrega(String vin, Timestamp fechaEntrega) throws SQLException {
        String sql = "SELECT e.fecha_entrega, t.nombre AS tienda, t.direccion " +
                "FROM ENTREGA e JOIN TIENDA t ON e.id_tienda = t.id_tienda " +
                "JOIN CONTRATO c ON e.id_contrato = c.id_contrato " +
                "WHERE c.vin = ? AND e.fecha_entrega = ? LIMIT 1";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vin);
            statement.setTimestamp(2, fechaEntrega);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Map.of(
                            "tienda", rs.getString("tienda"),
                            "direccion", rs.getString("direccion"),
                            "fecha_entrega", format(rs.getTimestamp("fecha_entrega"))
                    );
                }
            }
        }
        return Map.of("tienda", "-", "direccion", "-", "fecha_entrega", "-");
    }

    //Metodo para obtener detalles de la recogida
    private Map<String, Object> obtenerDetalleRecogida(String vin, Timestamp fechaRecogida) throws SQLException {
        String sql = "SELECT r.fecha_recogida, c.fecha_entrega_propuesta, t.nombre AS tienda, cl.nombre, cl.apellidos, cl.dni_cliente " +
                "FROM RECOGIDA r JOIN TIENDA t ON r.id_tienda = t.id_tienda " +
                "JOIN CONTRATO c ON r.id_contrato = c.id_contrato " +
                "JOIN CLIENTE cl ON c.dni_cliente = cl.dni_cliente " +
                "WHERE c.vin = ? AND r.fecha_recogida = ? LIMIT 1";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vin);
            statement.setTimestamp(2, fechaRecogida);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String cliente = rs.getString("nombre") + " " + rs.getString("apellidos");
                    return Map.of(
                            "cliente", cliente,
                            "dni", rs.getString("dni_cliente"),
                            "tienda", rs.getString("tienda"),
                            "fecha_recogida", format(rs.getTimestamp("fecha_recogida")),
                            "fecha_entrega_prevista", format(rs.getTimestamp("fecha_entrega_propuesta"))
                    );
                }
            }
        }
        return Map.of("cliente", "-", "dni", "-", "tienda", "-", "fecha_recogida", "-", "fecha_entrega_prevista", "-");
    }

    private String format(Timestamp timestamp) {
        if (timestamp == null) {
            return "-";
        }
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        return localDateTime.toString().replace("T", " ");
    }
}
