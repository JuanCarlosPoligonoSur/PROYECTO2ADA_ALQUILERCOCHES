/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ada_proyecto2.model;

/**
 *
 * @author juancarlos
 */
public class Cliente {
    private String dniCliente;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String numeroTarjetaCredito;

    public Cliente() {
    }

    public Cliente(String dniCliente, String nombre, String apellidos, String direccion, String telefono, String numeroTarjetaCredito) {
        this.dniCliente = dniCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.numeroTarjetaCredito = numeroTarjetaCredito;
    }

    //Getter y setter Dni Cliente
    public String getDniCliente() { 
        return dniCliente; 
    }
    public void setDniCliente(String dniCliente) { 
        this.dniCliente = dniCliente; 
    }
    
    //Getter y setter Nombre del cliente
    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    
    //Getter y setter Apellidos
    public String getApellidos() { 
        return apellidos; 
    }
    
    public void setApellidos(String apellidos) { 
        this.apellidos = apellidos; 
    }
    
    //Getter y Setter Dirección
    public String getDireccion() { 
        return direccion; 
    }
    public void setDireccion(String direccion) { 
        this.direccion = direccion; 
    }
    
    //Getter y Setter Telefono
    public String getTelefono() { 
        return telefono; 
    }
    
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }
    
    //Getter y setter Tarjeta
    public String getNumeroTarjetaCredito() { 
        return numeroTarjetaCredito; 
    }
    
    public void setNumeroTarjetaCredito(String numeroTarjetaCredito) { 
        this.numeroTarjetaCredito = numeroTarjetaCredito; 
    }
}
    

