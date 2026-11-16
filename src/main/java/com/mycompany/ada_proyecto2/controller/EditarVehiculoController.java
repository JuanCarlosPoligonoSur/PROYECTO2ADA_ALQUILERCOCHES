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
import com.mycompany.ada_proyecto2.dao.VehiculoDAO;
import com.mycompany.ada_proyecto2.model.Vehiculo;
import com.mycompany.ada_proyecto2.service.VehiculoService;
import com.mycompany.ada_proyecto2.util.AlertUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class EditarVehiculoController {
    private final VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private final VehiculoService vehiculoService = new VehiculoService();

    @FXML private TextField txtBusqueda;
    @FXML private TextField txtVin;
    @FXML private TextField txtMatricula;
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtKilometrajeActual;
    @FXML private TextField txtNuevoKilometraje;

    //Boton de buscar vehiculo
    @FXML
    private void buscarVehiculo() {
        String texto = txtBusqueda.getText() == null ? "" : txtBusqueda.getText().trim();
        if (texto.isBlank()) {
            AlertUtils.showError("Debes introducir un VIN o matrícula para buscar el vehículo.");
            return;
        }
        try {
            Vehiculo vehiculo = vehiculoService.buscarVehiculo(texto);
            txtVin.setText(vehiculo.getVin());
            txtMatricula.setText(vehiculo.getMatricula());
            txtMarca.setText(vehiculo.getMarca());
            txtModelo.setText(vehiculo.getModelo());
            txtKilometrajeActual.setText(String.valueOf(vehiculo.getKilometraje()));
            txtNuevoKilometraje.clear();
            AlertUtils.showInfo("Vehículo localizado correctamente.");
        } catch (IllegalArgumentException | SQLException e) {
            AlertUtils.showError(e.getMessage());
        }
    }

    //Boton para guardar los cambios km
    @FXML
    private void guardarCambios() {
        try {
            String vin = txtVin.getText() == null ? "" : txtVin.getText().trim();
            Integer actual = txtKilometrajeActual.getText() == null || txtKilometrajeActual.getText().isBlank()
                    ? null : Integer.parseInt(txtKilometrajeActual.getText().trim());
            Integer nuevo = txtNuevoKilometraje.getText() == null || txtNuevoKilometraje.getText().isBlank()
                    ? null : Integer.parseInt(txtNuevoKilometraje.getText().trim());

            vehiculoService.actualizarKilometraje(vin, nuevo, actual);
            vehiculoDAO.updateKilometraje(vin, nuevo);
            AlertUtils.showInfo("Kilometraje actualizad correctamente.");
        } catch (NumberFormatException e) {
            AlertUtils.showError("El kilometraje debe ser un número entero.");
        } catch (IllegalArgumentException | SQLException e) {
            AlertUtils.showError(e.getMessage());
        }
    }

    //Boton para volver a menu inicio
    @FXML
    private void volverAlMenu() throws IOException {
        App.setRoot("inicio");
    }
}
