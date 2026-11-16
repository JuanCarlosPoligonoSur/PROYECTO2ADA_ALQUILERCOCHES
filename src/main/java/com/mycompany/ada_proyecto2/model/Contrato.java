/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ada_proyecto2.model;

/**
 *
 * @author juancarlos
 */

import java.time.LocalDateTime;

public class Contrato {
    
    private Integer idContrato;
    private LocalDateTime fechaRecogidaPropuesta;
    private LocalDateTime fechaEntregaPropuesta;
    private LocalDateTime firma;
    private String dniCliente;
    private String vin;
    private String nombreCliente;
    private String apellidosCliente;
    private String matricula;
    private String marca;
    private String modelo;

    //COnstructor vacio
    public Contrato() {
    }
    
    // Getter y setter id contrato
    public Integer getIdContrato() { 
        return idContrato; 
    }
    
    public void setIdContrato(Integer idContrato) { 
        this.idContrato = idContrato; 
    }
    
    //Getter y setter fecha recogida propuesta
    public LocalDateTime getFechaRecogidaPropuesta() { 
        return fechaRecogidaPropuesta; 
    }
    
    public void setFechaRecogidaPropuesta(LocalDateTime fechaRecogidaPropuesta) { 
        this.fechaRecogidaPropuesta = fechaRecogidaPropuesta; 
    }
    
    //Getter y setter fecha entrega propuesta
    public LocalDateTime getFechaEntregaPropuesta() { 
        return fechaEntregaPropuesta; 
    }
    
    public void setFechaEntregaPropuesta(LocalDateTime fechaEntregaPropuesta) { 
        this.fechaEntregaPropuesta = fechaEntregaPropuesta; 
    }
    
    //Getter y setter fecha de firma
    public LocalDateTime getFirma() { 
        return firma;
    }
    
    public void setFirma(LocalDateTime firma) { 
        this.firma = firma; 
    }
    
    //Getter y Setter dni Cliente
    public String getDniCliente() { 
        return dniCliente;
    }
    public void setDniCliente(String dniCliente) { 
        this.dniCliente = dniCliente; 
    }
    
    //Getter y setter del n de bastidor vin
    public String getVin() { 
        return vin; 
    }
    public void setVin(String vin) { 
        this.vin = vin;
    }
    
    //Getter y setter nombre del cliente
    public String getNombreCliente() { 
        return nombreCliente;
    
    }
    
    public void setNombreCliente(String nombreCliente) { 
        this.nombreCliente = nombreCliente;
    }
    
    //Getter y setter apellidos del cliente
    public String getApellidosCliente() { 
        return apellidosCliente; 
    }
    public void setApellidosCliente(String apellidosCliente) { 
        this.apellidosCliente = apellidosCliente; 
    }
    
    //Getter y setter matrícula
    public String getMatricula() { 
        return matricula;
    }
    public void setMatricula(String matricula) { 
        this.matricula = matricula;
    }
    
    //Getter y setter Marca 
    public String getMarca() { 
        return marca;
    }
    public void setMarca(String marca) { 
        this.marca = marca;
    }
    
    //Getter y setter modelo vehícilo
    public String getModelo() { 
        return modelo;
    }
    public void setModelo(String modelo) { 
        this.modelo = modelo;
    }
}
    

