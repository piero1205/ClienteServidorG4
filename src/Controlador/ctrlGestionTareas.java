/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Proyecto;
import Modelo.Sentencias;
import Modelo.Tarea;
import Modelo.Usuarios;
import Vista.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jason
 */
public class ctrlGestionTareas implements ActionListener{
    private final Usuarios modelo;
    private final Sentencias consultas;
    private final frmInicioSesion inicioSesion;
    private final frmCrearCuenta crearCuenta;
    private final frmAdministradorhub adminHub;
    private final frmRegistrarProyectoAdmin registrarProyecto;
    private final frmRegistrarTarea registrarTarea;
    
    public ctrlGestionTareas(Usuarios modelo
                            , Sentencias consultas, frmInicioSesion inicioSesion1, frmCrearCuenta crearCuenta1
                            , frmRegistrarProyectoAdmin registrarProyecto
                            , frmAdministradorhub adminHub
                            , frmRegistrarTarea regTarea) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.inicioSesion = inicioSesion1;
        this.crearCuenta=crearCuenta1;
        this.registrarProyecto=registrarProyecto;
        this.adminHub = adminHub;
        this.registrarTarea = regTarea;
        this.inicioSesion.btnIniciarSesión.addActionListener(this);
        this.inicioSesion.btnCrearCuenta.addActionListener(this);
        this.crearCuenta.btnCrearCuenta.addActionListener(this);
        
        this.adminHub.btnRegistrarProyecto.addActionListener(this);
        this.adminHub.btnRegistrarTarea.addActionListener(this);
        
        this.registrarProyecto.btnCrearProyecto.addActionListener(this);
        this.registrarTarea.btnCrearTarea.addActionListener(this);
        this.adminHub.btnAsignarTarea.addActionListener(this);
        
    }
    
    public void iniciar() {
        inicioSesion.setTitle("Administrador de Proyectos");
        inicioSesion.setLocationRelativeTo(null);
    }
    
    public void actionPerformed(ActionEvent e) {
        //iniciar sesion
        if(e.getSource()== inicioSesion.btnIniciarSesión){
            
            String rol=consultas.loginUsuario(inicioSesion.txtInicioUsuario.getText(), inicioSesion.txtInicioContra.getText());
            if (rol != null) {
                if (rol.equalsIgnoreCase("admin")) {
                    JOptionPane.showMessageDialog(null, "Sesión Iniciada como Admin");
                    adminHub.setVisible(true);
                    adminHub.setLocationRelativeTo(null);
                } else if (rol.equalsIgnoreCase("colaborador")) {
                    JOptionPane.showMessageDialog(null, "Sesión Iniciada como Colaborador");
                    frmColaboradorhub frmColaborador = new frmColaboradorhub();
                    frmColaborador.setVisible(true);
                    frmColaborador.setLocationRelativeTo(null);
                }
            } else {
                
            }

        }
        
        if(e.getSource()==inicioSesion.btnCrearCuenta){
            crearCuenta.setVisible(true);
            crearCuenta.setLocationRelativeTo(null);
        }
        
        if(e.getSource()==crearCuenta.btnCrearCuenta){
            modelo.setNombre((crearCuenta.txtCrearNombre.getText()));
            modelo.setRol(crearCuenta.cmbRolCrearCuenta.getSelectedItem().toString());
            modelo.setCorreo(crearCuenta.txtCrearCorreo.getText());
            modelo.setContra(crearCuenta.txtCrearContra.getText());
            
            if (consultas.agregarUsuario(modelo)) {
                JOptionPane.showMessageDialog(null, "Cuenta Creada");
                 frmInicioSesion frminicioSesionAgregar=new frmInicioSesion();
                 frminicioSesionAgregar.setVisible(true);
                 frminicioSesionAgregar.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar");
                
            }
        }
        
        //Registrar Proyecto
        
        if(e.getSource()==adminHub.btnRegistrarProyecto ){
            registrarProyecto.setVisible(true);
            registrarProyecto.setLocationRelativeTo(null);
        }
        
        if(e.getSource()== registrarProyecto.btnCrearProyecto ){
            String nom = registrarProyecto.txtCrearNombre.getText();
            Double cost;
            try{
                cost = Double.parseDouble(registrarProyecto.txtCrearCostos.getText());
            } catch ( Exception exp) {
                JOptionPane.showMessageDialog(null, "El valor '"+registrarProyecto.txtCrearCostos.getText()+"' no es un costo valido...");
                return;
            }
            
            Proyecto nuevoP = new Proyecto(nom,cost);
            
            if (consultas.agregarProyecto(nuevoP) ) {
                JOptionPane.showMessageDialog(null, "Proyecto Creado!");
                 frmInicioSesion frminicioSesionAgregar=new frmInicioSesion();
                 frminicioSesionAgregar.setVisible(true);
                 frminicioSesionAgregar.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar el proyecto...");
                
            }
            
        }
        
        
        
        //Registrar Tarea
        
        if(e.getSource()==adminHub.btnRegistrarTarea ){
            registrarTarea.setVisible(true);
            registrarTarea.setLocationRelativeTo(null);
        }
        
        if(e.getSource()== registrarTarea.btnCrearTarea ){
            String nom = registrarTarea.txtNombreProyecto.getText();
            String est = registrarTarea.txtEstado.getText();
            String com = registrarTarea.txtComentario.getText();

            int id;
            try{
                id = Integer.parseInt(registrarTarea.txtIdTarea.getText());
            } catch ( Exception exp) {
                JOptionPane.showMessageDialog(null, "El valor '"+registrarTarea.txtIdTarea.getText()+"' no es un ID valido...");
                return;
            }
            LocalDate fechaV;
            try{
                fechaV = LocalDate.parse(registrarTarea.txtFecha.getText());
            } catch ( Exception exp) {
                JOptionPane.showMessageDialog(null, "El valor '"+registrarTarea.txtIdTarea.getText()+"' no tiene el formato de fecha requerido...");
                return;
            }
            
            Tarea tarea = new Tarea(id, nom, est, com, fechaV);
            
            if (consultas.agregarTarea(tarea) ) {
                JOptionPane.showMessageDialog(null, "Tarea Creada!");
                 frmInicioSesion frminicioSesionAgregar=new frmInicioSesion();
                 frminicioSesionAgregar.setVisible(true);
                 frminicioSesionAgregar.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar la tarea...");
                
            }
            
        }
    }
    
}
