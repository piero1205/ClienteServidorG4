/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Carrillo Díaz
 */
public class Proyecto {
    private String nombre;
    private double costo;
    private ArrayList <Tarea> tareas;
    private ArrayList <Riesgo> riesgos;

    public Proyecto( String nombre, double costo) {
        this.nombre = nombre;
        this.costo = costo;
        tareas = new ArrayList<>();
        riesgos = new ArrayList<>();
    }

    public Proyecto(double costo) {
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

    public boolean asignarTarea(Tarea t) {
        if (t == null) {
            return false;
        }
        for (Tarea ex : tareas) {
            if (ex.getId() == t.getId()) {
                return false;
            }
        }
        if (t.getEstado() == null || t.getEstado().isEmpty()) {
            t.setEstado("Asignada");
        }
        tareas.add(t);
        return true;
    }
    
    public void calcularCosto(){
        double costo = 0.0d;
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getFechaVencimiento().isAfter(LocalDate.now())) {
                costo += this.costo * 0.10;
            }
        }
        this.costo = costo;
    }
    
    public void recordatorio (){
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getFechaVencimiento().isBefore(LocalDate.now())) {
                // Recordatorio: tarea vencida -> no mostrar diálogo
                System.out.println("Recordatorio: La tarea " + tareas.get(i).getNombre() + " se vence el dia: " + tareas.get(i).getFechaVencimiento());
            }
        }
        
    }
    
    public boolean agregarProyecto(Proyecto proyecto) {
        if (proyecto == null || proyecto.getNombre() == null || proyecto.getNombre().isEmpty()) {
            // Nombre inválido
            return false;
        }
        try {
            this.nombre = proyecto.getNombre();
            this.costo = proyecto.getCosto();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminarProyecto(String nombreProyecto) {
        try {
            if (nombreProyecto != "") {
                return false;
            }
            if (this.nombre == nombre) {
                this.nombre = "";
                this.costo = 0;
                tareas.clear();
                riesgos.clear();
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    public boolean actualizarProyecto(String nombreProyecto, Proyecto proyectoActualizado) {
        try {
            if (nombreProyecto != "" || proyectoActualizado == null) {
                return false;
            }
            if (this.nombre == nombreProyecto) {
                this.nombre = proyectoActualizado.getNombre() != null ? proyectoActualizado.getNombre() : this.nombre;
                this.costo = proyectoActualizado.getCosto();
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminarTarea(int indiceTarea) {
        try {
            if (indiceTarea < 0 || indiceTarea >= tareas.size()) {
                return false;
            }
            tareas.remove(indiceTarea);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminarRiesgo(int indiceRiesgo) {
        try {
            if (indiceRiesgo < 0 || indiceRiesgo >= riesgos.size()) {
                return false;
            }
            riesgos.remove(indiceRiesgo);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
}
