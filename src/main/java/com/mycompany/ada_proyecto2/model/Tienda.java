/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ada_proyecto2.model;

/**
 *
 * @author juancarlos
 */
public class Tienda {

    private Integer idTienda;
    private String nombre;
    private String direccion;
    private String telefono;

    //Cosntructor vac
    public Tienda() {
    }
    
    //Constructor
    public Tienda(Integer idTienda, String nombre, String direccion, String telefono) {
        this.idTienda = idTienda;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    
    //Getter y setter id tienda
    public Integer getIdTienda() { 
        return idTienda;
    }
    public void setIdTienda(Integer idTienda) { 
        this.idTienda = idTienda; 
    }
    
      //Getter y setter nombre tienda
    public String getNombre() { 
        return nombre;
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    
    //Getter y setter direccion
    public String getDireccion() { 
        return direccion; 
    }
    public void setDireccion(String direccion) { 
        this.direccion = direccion; 
    }
    
    //Getter y setter telefono tienda
    public String getTelefono() { 
        return telefono;
    }
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }
}

