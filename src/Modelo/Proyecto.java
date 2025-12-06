/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Carrillo Díaz
 */
public class Proyecto {
    private double costo;
    private Tarea tarea;
    private Riesgo riesgo;

    public Proyecto(double costo, Tarea tarea, Riesgo riesgo) {
        this.costo = costo;
        this.tarea = tarea;
        this.riesgo = riesgo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public Riesgo getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(Riesgo riesgo) {
        this.riesgo = riesgo;
    }
    
    
}
