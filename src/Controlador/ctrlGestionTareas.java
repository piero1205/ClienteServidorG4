/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Sentencias;
import Modelo.Usuarios;
import Vista.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

/**
 *
 * @author Jason
 */
public class ctrlGestionTareas implements ActionListener{
    private final Usuarios modelo;
    private final Sentencias consultas;
    private final frmInicioSesion inicioSesion;
    private final frmCrearCuenta crearCuenta;
    
    public ctrlGestionTareas(Usuarios modelo, Sentencias consultas, frmInicioSesion inicioSesion1, frmCrearCuenta crearCuenta1) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.inicioSesion = inicioSesion1;
        this.crearCuenta=crearCuenta1;
        this.inicioSesion.btnIniciarSesión.addActionListener(this);
        this.inicioSesion.btnCrearCuenta.addActionListener(this);
        this.crearCuenta.btnCrearCuenta.addActionListener(this);
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
                    frmAdministradorhub frmAdmin = new frmAdministradorhub();
                    frmAdmin.setVisible(true);
                    frmAdmin.setLocationRelativeTo(null);
                } else if (rol.equalsIgnoreCase("colaborador")) {
                    JOptionPane.showMessageDialog(null, "Sesión Iniciada como Admin");
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
    }
    
    public DefaultTableModel generarReporte(String nombreProyecto){
        
        String columnas[] = {
            "Proyecto", "Tarea", "Estado", "Responsable", "Vencimiento", "Riesgo", "Costo", "Comentario"          
        };
        
        DefaultTableModel m = new DefaultTableModel(null, columnas);
        
        try{
            ResultSet rs = consultas.consultarProyecto(nombreProyecto);
            
            while (rs.next()) {
                Object fila[] = {
                    rs.getString("proyecto"),
                    rs.getString("tarea"),
                    rs.getString("estado"),
                    rs.getString("responsable"),
                    rs.getString("vencimiento"),
                    rs.getString("riesgo"),
                    rs.getString("costo"),
                    rs.getString("comentario")                                  
                };
                m.addRow(fila);          
            }                     
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error en generar reporte");
        }
        return m;                    
    }     
}
