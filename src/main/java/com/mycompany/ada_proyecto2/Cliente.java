
package com.mycompany.ada_proyecto2;

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
    private String nTarjeta;
    
    
    //Constructor completo
    public Cliente(String dniCliente, String nombre,String apellidos, String direccion, String telefono,String nTarjeta){
        this.dniCliente=dniCliente;
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.direccion=direccion;
        this.telefono=telefono;
        this.nTarjeta=nTarjeta;
   
    }
    
    //Constructor vacío
    public Cliente(){
    }
    
    //Getter y Setter DniCliente
    public String getDniCliente(){
        return dniCliente;
    }
    
    public void setDniCliente(String dniCliente){
        this.dniCliente=dniCliente;
    }
   
    //Getter y Setter Nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Getter y Setter apellidos
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

    //Getter y setter teléfono
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    //Getter y setter tarjeta
    public String getNTarjeta() {
        return nTarjeta;
    }

    public void setNTarjeta(String nTarjeta) {
        this.nTarjeta = nTarjeta;
    }
 
    
}
    

   
    