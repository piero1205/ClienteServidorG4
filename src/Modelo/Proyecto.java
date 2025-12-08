/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Carrillo Díaz
 */
public class Proyecto {
    private String nombre;
    private double costo;
    private ArrayList <Tarea> tareas;
    private ArrayList <Riesgo> riesgos;

    public Proyecto(String nombre, double costo) {
        this.nombre=nombre;
        this.costo = costo;
        tareas = new ArrayList<>();
        riesgos = new ArrayList<>();
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }

    public ArrayList<Riesgo> getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(ArrayList<Riesgo> riesgos) {
        this.riesgos = riesgos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public void insertarTarea(Tarea t){
        tareas.add(t);
    }
    
    public void insertarRiesgo(Riesgo r){
        riesgos.add(r);
    }
    
    public void calcularCosto(){
        double costo = 0.0d;
        for (int i=0; i<=tareas.size(); i++){
            if(tareas.get(i).getFechaVencimiento().isAfter(LocalDate.now())){
                costo += this.costo*0.10;
            }
        }
        this.costo = costo;
    }
    
    public void recordatorio (){
        for (int i=0; i<=tareas.size(); i++){
            if(tareas.get(i).getFechaVencimiento().isBefore(LocalDate.now())){
                JOptionPane.showMessageDialog(null, "Recordatorio\n---------\nLa tarea "+tareas.get(i).getComentario() +" se vence el dia: "+tareas.get(i).getFechaVencimiento());
            }
        }
        
    }
}
