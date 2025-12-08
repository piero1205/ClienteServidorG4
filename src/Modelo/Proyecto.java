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
    private int id;
    private String nombre;
    private String descripcion;
    private double costo;
    private ArrayList <Tarea> tareas;
    private ArrayList <Riesgo> riesgos;

    public Proyecto(int id, String nombre, String descripcion, double costo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        tareas = new ArrayList<>();
        riesgos = new ArrayList<>();
    }

    public Proyecto(double costo) {
        this.costo = costo;
        tareas = new ArrayList<>();
        riesgos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
            this.id = proyecto.getId();
            this.nombre = proyecto.getNombre();
            this.descripcion = proyecto.getDescripcion();
            this.costo = proyecto.getCosto();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    public boolean eliminarProyecto(int idProyecto) {
        try {
            if (idProyecto <= 0) {
                return false;
            }
            if (this.id == idProyecto) {
                this.id = 0;
                this.nombre = "";
                this.descripcion = "";
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
    
    public boolean actualizarProyecto(int idProyecto, Proyecto proyectoActualizado) {
        try {
            if (idProyecto <= 0 || proyectoActualizado == null) {
                return false;
            }
            if (this.id == idProyecto) {
                this.nombre = proyectoActualizado.getNombre() != null ? proyectoActualizado.getNombre() : this.nombre;
                this.descripcion = proyectoActualizado.getDescripcion() != null ? proyectoActualizado.getDescripcion() : this.descripcion;
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
