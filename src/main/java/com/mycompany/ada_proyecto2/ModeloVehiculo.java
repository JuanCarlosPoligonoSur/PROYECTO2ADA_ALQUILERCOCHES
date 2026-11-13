/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ada_proyecto2;

/**
 *
 * @author juancarlos
 */
public class ModeloVehiculo {
    
    private int id_ModeloVehiculo;
    private String marca;
    private String nombre;
    private float cv;
    private int nPlazas;
    private String gama;
    private String foto;
    
    //Constructor vacío
    public ModeloVehiculo(){}
    
    //Constructor completo
    public ModeloVehiculo(int id_ModeloVehiculo, String marca, String nombre, float cv, int nPlazas, String gama,String foto){
        
        this.id_ModeloVehiculo=id_ModeloVehiculo;
        this.marca=marca;
        this.nombre=nombre;
        this.cv=cv;
        this.nPlazas=nPlazas;
        this.gama=gama;
        this.foto=foto;
    }
    
    //Getter y setter id modelo

    public int getId_ModeloVehiculo() {
        return id_ModeloVehiculo;
    }

    public void setId_ModeloVehiculo(int id_ModeloVehiculo) {
        this.id_ModeloVehiculo = id_ModeloVehiculo;
    }

    
    //Getter y setter marca
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    //Getter y setter nombre de la marca

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Getter y setter potencia en cv
    public float getCv() {
        return cv;
    }

    public void setCv(float cv) {
        this.cv = cv;
    }

    //Getter y setter nº de plazas
    public int getNPlazas() {
        return nPlazas;
    }

    public void setNPlazas(int nPlazas) {
        this.nPlazas = nPlazas;
    }

    public String getGama() {
        return gama;
    }
    
    //Getter y setter de gama

    public void setGama(String gama) {
        this.gama = gama;
    }
    
    //Getter y setter foto

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
  
    }
    
    
    
}
    
    
  
