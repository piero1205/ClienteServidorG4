/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Jason
 */
public class Usuarios {
    private String nombre;
    private String rol;
    private String correo;
    private String contra;

    public Usuarios() {
        this.nombre="";
        this.rol="";
        this.correo="";
        this.contra="";        
    }
    
    
    

    public Usuarios(String nombre, String rol, String correo, String contra) {
        this.nombre = nombre;
        this.rol = rol;
        this.correo = correo;
        this.contra = contra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
    
    
}
