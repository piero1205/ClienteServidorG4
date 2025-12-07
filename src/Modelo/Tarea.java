/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author Carrillo Díaz
 */
public class Tarea {
    private int id;
    private String nombre;
    private String estado;
    private LocalDate fechaVencimiento;

    public Tarea(int id, String nombre, String estado, LocalDate fechaVencimiento) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.fechaVencimiento = fechaVencimiento;
    }

    public Tarea(String estado, LocalDate fechaVencimiento) {
        this.estado = estado;
        this.fechaVencimiento = fechaVencimiento;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
