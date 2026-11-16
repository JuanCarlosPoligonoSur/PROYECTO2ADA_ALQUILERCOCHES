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
import com.mycompany.ada_proyecto2.model.Contrato;
import com.mycompany.ada_proyecto2.service.ContratoService;
import com.mycompany.ada_proyecto2.util.AlertUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleObjectProperty;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ConsultarContratoController {


    //Inicio objeto contratoService
    private final ContratoService contratoService = new ContratoService();
    
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    
    @FXML private TableView<Contrato> tablaContratos;
    @FXML private TableColumn<Contrato, Integer> colIdContrato;
    @FXML private TableColumn<Contrato, String> colDniCliente;
    @FXML private TableColumn<Contrato, String> colNombreCliente;
    @FXML private TableColumn<Contrato, String> colApellidosCliente;
    @FXML private TableColumn<Contrato, String> colVin;
    @FXML private TableColumn<Contrato, String> colMatricula;
    @FXML private TableColumn<Contrato, String> colMarca;
    @FXML private TableColumn<Contrato, String> colModelo;
    @FXML private TableColumn<Contrato, LocalDateTime> colFechaRecogida;
    @FXML private TableColumn<Contrato, LocalDateTime> colFechaEntrega;
    @FXML private TableColumn<Contrato, LocalDateTime> colFirma;

    @FXML private TextField txtIdContrato;
    @FXML private TextField txtDniCliente;
    @FXML private TextField txtVin;
    @FXML private TextField txtFechaRecogida;
    @FXML private TextField txtFechaEntrega;

    
    //Tabla FXML
    @FXML
    private void initialize() {
        colIdContrato.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getIdContrato()));
        colDniCliente.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getDniCliente()));
        colNombreCliente.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getNombreCliente()));
        colApellidosCliente.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getApellidosCliente()));
        colVin.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getVin()));
        colMatricula.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getMatricula()));
        colMarca.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getMarca()));
        colModelo.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getModelo()));
        colFechaRecogida.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getFechaRecogidaPropuesta()));
        colFechaEntrega.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getFechaEntregaPropuesta()));
        colFirma.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getFirma()));

        tablaContratos.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                cargarContratoEnFormulario(newValue);
            }
        });

        cargarTabla();
    }

    //Boton para buscar contrato
    @FXML
    private void buscarContrato() {
        String texto = txtIdContrato.getText() == null ? "" : txtIdContrato.getText().trim();
        if (texto.isBlank()) {
            AlertUtils.showError("Debes introducir un ID del contrato para buscarlo.");
            return;
        }
        try {
            Integer id = Integer.parseInt(texto);
            Optional<Contrato> contrato = contratoService.consultarPorId(id);
            if (contrato.isPresent()) {
                cargarContratoEnFormulario(contrato.get());
                AlertUtils.showInfo("Contrato encontrado.");
            } else {
                AlertUtils.showError("No existe ningún contrato con ese identificador.");
            }
        } catch (NumberFormatException e) {
            AlertUtils.showError("El ID del contrato debe ser un número entero.");
        } catch (SQLException e) {
            AlertUtils.showError("No se pudo buscar el contrato: " + e.getMessage());
        }
    }

    
    Botón para  guardar los cambios
    @FXML
    private void guardarCambios() {
        try {
            Contrato contrato = new Contrato();
            contrato.setIdContrato(Integer.valueOf(txtIdContrato.getText().trim()));
            contrato.setDniCliente(txtDniCliente.getText().trim());
            contrato.setVin(txtVin.getText().trim());
            contrato.setFechaRecogidaPropuesta(parseFecha(txtFechaRecogida.getText()));
            contrato.setFechaEntregaPropuesta(parseFecha(txtFechaEntrega.getText()));
            contratoService.actualizar(contrato);
            AlertUtils.showInfo("Cambios guardados correctamente.");
            cargarTabla();
        } catch (NumberFormatException e) {
            AlertUtils.showError("El ID del contrato debe ser un número entero.");
        } catch (IllegalArgumentException | SQLException e) {
            AlertUtils.showError(e.getMessage());
        }
    }
     
    Botó
    @FXML
    private void firmarContrato() {
        String texto = txtIdContrato.getText() == null ? "" : txtIdContrato.getText().trim();
        if (texto.isBlank()) {
            AlertUtils.showError("Debes seleccionar o buscar un contrato antes de firmarlo.");
            return;
        }
        try {
            int idContrato = Integer.parseInt(texto);
            if (!AlertUtils.confirm("¿Confirmas que quieres firmar este contrato? Después no se podrá borrar.")) {
                return;
            }
            contratoService.firmarContrato(idContrato);
            AlertUtils.showInfo("Contrato firmado correctamente.");
            cargarTabla();
        } catch (NumberFormatException e) {
            AlertUtils.showError("El ID del contrato debe ser un número entero.");
        } catch (IllegalArgumentException | SQLException e) {
            AlertUtils.showError(e.getMessage());
        }
    }

    //Boton para borrar el contrato no firmado
    @FXML
    private void borrarContrato() {
        String texto = txtIdContrato.getText() == null ? "" : txtIdContrato.getText().trim();
        if (texto.isBlank()) {
            AlertUtils.showError("Debes seleccionar o buscar un contrato antes de borrarlo.");
            return;
        }
        try {
            int idContrato = Integer.parseInt(texto);
            if (!AlertUtils.confirm("¿Seguro que quieres borrar este contrato?")) {
                return;
            }
            contratoService.eliminarContrato(idContrato);
            AlertUtils.showInfo("Contrato eliminado correctamente.");
            limpiarFormulario();
            cargarTabla();
        } catch (NumberFormatException e) {
            AlertUtils.showError("El ID del contrato debe ser un número entero.");
        } catch (SQLException e) {
            AlertUtils.showError(e.getMessage());
        }
    }

    
    //Boton para volver al menu de inicio
    @FXML
    private void volverAlMenu() throws IOException {
        App.setRoot("inicio");
    }
    
    
    //Metodo para cargar la tabla de contratos
    private void cargarTabla() {
        try {
            tablaContratos.setItems(FXCollections.observableArrayList(contratoService.listarContratos()));
        } catch (SQLException e) {
            AlertUtils.showError("No se pudieron cargar los contratos: " + e.getMessage());
        }
    }
    
    
    
    //Para cargar el contato en el formulario
    private void cargarContratoEnFormulario(Contrato contrato) {
        txtIdContrato.setText(String.valueOf(contrato.getIdContrato()));
        txtDniCliente.setText(contrato.getDniCliente());
        txtVin.setText(contrato.getVin());
        txtFechaRecogida.setText(formatearFecha(contrato.getFechaRecogidaPropuesta()));
        txtFechaEntrega.setText(formatearFecha(contrato.getFechaEntregaPropuesta()));
    }
    
    
    
    //Limpiar el formulario
    private void limpiarFormulario() {
        txtIdContrato.clear();
        txtDniCliente.clear();
        txtVin.clear();
        txtFechaRecogida.clear();
        txtFechaEntrega.clear();
    }
    
    
    
    //Metodo para verificar la recogida de fecha
    private LocalDateTime parseFecha(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("Todos los campos de fecha son obligatorios.");
        }
        return LocalDateTime.parse(valor, FORMATTER);
    }

    //Metodo par adarle formato a la fecha 
    private String formatearFecha(LocalDateTime fecha) {
        return fecha == null ? "" : fecha.format(FORMATTER);
    }
}
