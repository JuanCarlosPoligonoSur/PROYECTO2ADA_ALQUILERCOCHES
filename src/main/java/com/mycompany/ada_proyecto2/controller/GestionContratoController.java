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
import com.mycompany.ada_proyecto2.dao.ClienteDAO;
import com.mycompany.ada_proyecto2.model.Contrato;
import com.mycompany.ada_proyecto2.service.ContratoService;
import com.mycompany.ada_proyecto2.util.AlertUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class GestionContratoController {
    
    
    
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final ContratoService contratoService = new ContratoService();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @FXML private TextField txtIdContrato;
    @FXML private TextField txtDniCliente;
    @FXML private TextField txtVin;
    @FXML private TextField txtFechaRecogida;
    @FXML private TextField txtFechaEntrega;
    @FXML private Button btnGuardarContrato;
    @FXML private Button btnVerificarDni;
    @FXML private Button btnVolver;

    @FXML
    private void initialize() {
        txtFechaRecogida.setPromptText("yyyy-MM-dd HH:mm");
        txtFechaEntrega.setPromptText("yyyy-MM-dd HH:mm");
    }
    
    //Boton guardar contrato
    @FXML
    private void guardarContrato() {
        try {
            Contrato contrato = new Contrato();
            contrato.setIdContrato(Integer.valueOf(txtIdContrato.getText().trim()));
            contrato.setDniCliente(txtDniCliente.getText().trim());
            contrato.setVin(txtVin.getText().trim());
            contrato.setFechaRecogidaPropuesta(parseFecha(txtFechaRecogida.getText()));
            contrato.setFechaEntregaPropuesta(parseFecha(txtFechaEntrega.getText()));
            contratoService.guardar(contrato);
            AlertUtils.showInfo("Contrato guardado correctamente.");
            limpiarFormulario();
        } catch (NumberFormatException e) {
            AlertUtils.showError("El ID del contrato debe ser un número entero.");
        } catch (DateTimeParseException e) {
            AlertUtils.showError("El formato de fecha debe ser yyyy-MM-dd HH:mm.");
        } catch (IllegalArgumentException | SQLException e) {
            AlertUtils.showError(e.getMessage());
        }
    }

    
    //Boton verificar dni
    @FXML
    private void verificarDni() {
        String dni = txtDniCliente.getText() == null ? "" : txtDniCliente.getText().trim();
        if (dni.isBlank()) {
            AlertUtils.showError("Debes introducir un DNI antes de comprobarlo.");
            return;
        }
        try {
            boolean existe = clienteDAO.existsByDni(dni);
            if (existe) {
                AlertUtils.showInfo("El cliente está registrado");
            } else {
                AlertUtils.showInfo("El DNI no está registrado");
            }
        } catch (SQLException e) {
            AlertUtils.showError("No se pudo comprobar el DNI en la base de datos: " + e.getMessage());
        }
    }

    
    //Boton volver al menu
    @FXML
    private void volverAlMenu() throws IOException {
        App.setRoot("inicio");
    }
    
    
    
    
//Metodo para comprobar fecha
    private LocalDateTime parseFecha(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("La fecha es obligatoria.");
        }
        return LocalDateTime.parse(texto, FORMATTER);
    }

    
    //Metodo para limpiar formulario
    private void limpiarFormulario() {
        txtIdContrato.clear();
        txtDniCliente.clear();
        txtVin.clear();
        txtFechaRecogida.clear();
        txtFechaEntrega.clear();
    }
}
