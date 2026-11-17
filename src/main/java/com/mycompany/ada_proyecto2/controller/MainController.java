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
import javafx.fxml.FXML;

import java.io.IOException;

public class MainController {
    
    
    
    //Boton para ir a pantalla gestion Contratos
    @FXML
    private void abrirGestionContratos() throws IOException {
        App.setRoot("gestion_contratos");
    }

    //Botón abrir Consultar contratos
    @FXML
    private void abrirConsultarContratos() throws IOException {
        App.setRoot("consultar_contratos");
    }

    //Boton abrir pantalla editar vehiculo
    @FXML
    private void abrirEditarVehiculo() throws IOException {
        App.setRoot("editar_vehiculo");
    }
    
    
    //Boton abrir pantalla localizar vehiculo

    @FXML
    private void abrirLocalizarVehiculo() throws IOException {
        App.setRoot("localizar_vehiculo");
    }
}
