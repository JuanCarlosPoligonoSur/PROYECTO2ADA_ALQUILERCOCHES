/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ada_proyecto2.model;

/**
 *
 * @author juancarlos
 */
public class Vehiculo {

    private String vin;
    private String matricula;
    private String color;
    private Integer idModelo;
    private Integer kilometraje;
    private String marca;
    private String modelo;
    private String tipoCarnet;

    
    
    //Constructor vacio
    public Vehiculo() {
    }

    //Constructor
    public Vehiculo(String vin, String matricula, String color, Integer idModelo, Integer kilometraje, String marca, String modelo) {
        this.vin = vin;
        this.matricula = matricula;
        this.color = color;
        this.idModelo = idModelo;
        this.kilometraje = kilometraje;
        this.marca = marca;
        this.modelo = modelo;
    }

    
    //Getter y setter Vin
    public String getVin() { 
        return vin; 
    }
    public void setVin(String vin) { 
        this.vin = vin; 
    }
    
     //Getter y setter matricula
    public String getMatricula() { 
        return matricula; 
    }
    public void setMatricula(String matricula) { 
        this.matricula = matricula; 
    }
    
     //Getter y setter color
    public String getColor() { 
        return color;
    }
    public void setColor(String color) { 
        this.color = color; 
    }
    
     //Getter y setter id modelo
    public Integer getIdModelo() { 
        return idModelo; 
    }
    public void setIdModelo(Integer idModelo) { 
        this.idModelo = idModelo; 
    }
    
    // //Getter y setter km
    public Integer getKilometraje() { 
        return kilometraje; 
    }
    public void setKilometraje(Integer kilometraje) { 
        this.kilometraje = kilometraje; 
    }
    
     //Getter y setter marca
    public String getMarca() { 
        return marca; 
    }
    public void setMarca(String marca) { 
        this.marca = marca; 
    }
    
    //Getter y setter modelo
    public String getModelo() { 
        return modelo; 
    }
    public void setModelo(String modelo) { 
        this.modelo = modelo; 
    }
    
    // //Getter y setter tipo carnet
    public String getTipoCarnet() { 
        return tipoCarnet; 
    }
    public void setTipoCarnet(String tipoCarnet) { 
        this.tipoCarnet = tipoCarnet; 
    }
}
