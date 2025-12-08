/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Proyecto;
import Modelo.Riesgo;
import Modelo.Sentencias;
import Modelo.Tarea;
import Modelo.Usuarios;
import Vista.*;
import java.awt.Color;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jason
 */
public class ctrlGestionTareas implements ActionListener{
    
    ArrayList<Proyecto> proyectos;
    
    private final Usuarios modelo;
    private final Sentencias consultas;
    private final frmInicioSesion inicioSesion;
    private final frmCrearCuenta crearCuenta;
    private final frmAdministradorhub adminHub;
    private final frmRegistrarProyectoAdmin registrarProyecto;
    private final frmRegistrarTarea registrarTarea;
    private final frmCostosAdmin consultarCosto;
    private final frmRegistrarRiesgo registrarRiesgo;
    private final frmAsignarTareaAdmin asignarTareaForm;
    private final frmActualizarTareaColaborador actTarea; 
    private final frmColaboradorhub colaboradorHub;
    
    public ctrlGestionTareas(Usuarios modelo
                            , Sentencias consultas
                            , frmInicioSesion inicioSesion1
                            , frmCrearCuenta crearCuenta1
                            , frmRegistrarProyectoAdmin registrarProyecto
                            , frmAdministradorhub adminHub
                            , frmRegistrarTarea regTarea
                            , frmCostosAdmin consultarCosto
                            , frmRegistrarRiesgo regRiesgo
                            , frmAsignarTareaAdmin asignarTareaAdmin
                            , frmActualizarTareaColaborador actTarea
                            , frmColaboradorhub colaboradorHub) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.proyectos = consultas.cargarProyectos();
        
        this.inicioSesion = inicioSesion1;
        this.crearCuenta=crearCuenta1;
        this.registrarProyecto=registrarProyecto;
        this.adminHub = adminHub;
        this.registrarTarea = regTarea;
        this.registrarRiesgo = regRiesgo;
        this.consultarCosto= consultarCosto;
        this.asignarTareaForm = asignarTareaAdmin;
        
        this.actTarea=actTarea; 
        this.colaboradorHub=colaboradorHub;
        
        this.asignarTareaForm = asignarTareaAdmin;
        this.inicioSesion.btnIniciarSesión.addActionListener(this);
        this.inicioSesion.btnCrearCuenta.addActionListener(this);
        this.crearCuenta.btnCrearCuenta.addActionListener(this);
        
        this.adminHub.btnRegistrarProyecto.addActionListener(this);
        this.adminHub.btnRegistrarTarea.addActionListener(this);
        this.adminHub.btnRegRiesgo.addActionListener(this);
        
        this.registrarProyecto.btnCrearProyecto.addActionListener(this);
        this.registrarTarea.btnCrearTarea.addActionListener(this);
        this.registrarRiesgo.btnCrearRiesgo.addActionListener(this);
        
        this.adminHub.btnEvaluarCostos.addActionListener(this);
        this.consultarCosto.btnConsultarCostoAdmin.addActionListener(this);
        this.adminHub.btnAsignarTarea.addActionListener(this);
        
        this.registrarProyecto.btnCrearProyecto.addActionListener(this);
        this.registrarTarea.btnCrearTarea.addActionListener(this);
        this.asignarTareaForm.btnAsignar.addActionListener(this);
        
        
        this.registrarRiesgo.txtImpacto.addActionListener(this);
        
        this.colaboradorHub.btnhubAct.addActionListener(this); 
        this.actTarea.btnActualizar.addActionListener(this);
        llenarTablaAdmin();
        llenarTablaColaborador();
    }
    
    public void iniciar() {
        inicioSesion.setTitle("Administrador de Proyectos");
        inicioSesion.setLocationRelativeTo(null);
    }
    
        public void llenarTablaColaborador(){
        DefaultTableModel modelo=(DefaultTableModel) colaboradorHub.tablaColaborador.getModel();
        ArrayList<Tarea> listaTareas=consultas.recorridoTareas();
        String datos[]=new String[4];
        int i=0;
        DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/mm/yyyy");
       for(Tarea juguete:listaTareas){
           datos[0]=listaTareas.get(i).getNombre();
           datos[1]=String.valueOf(listaTareas.get(i).getId());
           datos[2]=listaTareas.get(i).getFechaVencimiento().toString();
           datos[3]=listaTareas.get(i).getResponsable();
           i++;
           modelo.addRow(datos);//add datos a la lista
       }
       colaboradorHub.tablaColaborador.setModel(modelo);//incluir en la tabla
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
                    colaboradorHub.setVisible(true);
                    colaboradorHub.setLocationRelativeTo(null); 
                }
            } else {
                
            }

        }
        
        if(e.getSource()==registrarRiesgo.txtImpacto){
            
            String[] colores = {"Verde", "Amarillo", "Rojo"};
            JComboBox<String> combo = registrarRiesgo.txtImpacto;
            
            
            String s = (String) combo.getSelectedItem();
            if (s == null) {
                combo.setForeground(Color.BLACK);
            } else {
                s = s.trim().toLowerCase();
                if (s.equals("bajo")) {
                    combo.setForeground(new Color(0, 128, 0));
                } else if (s.equals("medio")) {
                    combo.setForeground(new Color(255, 165, 0));
                } else if (s.equals("alto")) {
                    combo.setForeground(Color.RED);
                } else {
                    combo.setForeground(Color.BLACK);
                }
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
            
            if ( consultas.agregarProyecto(nuevoP) ) {
                JOptionPane.showMessageDialog(null, "Proyecto Creado!");
                 frmInicioSesion frminicioSesionAgregar=new frmInicioSesion();
                 frminicioSesionAgregar.setVisible(true);
                 frminicioSesionAgregar.setLocationRelativeTo(null);
                 proyectos.add(nuevoP);
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
                 buscarProyecto(nom).insertarTarea(tarea);
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar la tarea...");
                
            }
            
        }
        
        //Registrar Riesgo
        
        if(e.getSource()==adminHub.btnRegRiesgo ){
            registrarRiesgo.setVisible(true);
            registrarRiesgo.setLocationRelativeTo(null);
        }
        
        if(e.getSource()== registrarRiesgo.btnCrearRiesgo ){
            String proy = registrarRiesgo.txtNombreProyecto.getText();
            String imp = registrarRiesgo.txtImpacto.getSelectedItem().toString();
            String desc = registrarRiesgo.txtComentario.getText();
            
            Riesgo riesgo = new Riesgo(desc,imp);
            
            if (consultas.agregarRiesgo(proy, riesgo) ) {
                JOptionPane.showMessageDialog(null, "Riesgo Creado!");
                 frmInicioSesion frminicioSesionAgregar=new frmInicioSesion();
                 frminicioSesionAgregar.setVisible(true);
                 frminicioSesionAgregar.setLocationRelativeTo(null);
                 buscarProyecto(proy).insertarRiesgo(riesgo);
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar el riesgo...");
                
            }
            
        }
        
        //Evaluar costos
        if(e.getSource()==adminHub.btnEvaluarCostos ){
            consultarCosto.setVisible(true);
            consultarCosto.setLocationRelativeTo(null);
        }
        if(e.getSource()== consultarCosto.btnConsultarCostoAdmin){
            
            Proyecto p = buscarProyecto(this.consultarCosto.txtProyectoConsultarCostoAdmin.getText());
            if(p == null){
                JOptionPane.showMessageDialog(null, "Proyecto "+this.consultarCosto.txtProyectoConsultarCostoAdmin.getText()+" no existe ");
            } else {
                this.consultarCosto.txtMostrarCosto.setText( String.valueOf( p.getCosto() ));
            }
        
        }
        
        // actualizar
            if (e.getSource()==colaboradorHub.btnhubAct){ 
            actTarea.setVisible(true);
            actTarea.setLocationRelativeTo(null);
            System.err.println("presionado"); 
        }
        
        if (e.getSource()==actTarea.btnActualizar){ 
         int id=Integer.parseInt(actTarea.txtTareaEditar.getText());
        String comentario=actTarea.txtComentarioTarea.getText(); 
        String estado=actTarea.cmbEstadoTarea.getSelectedItem().toString(); 
        boolean completado=consultas.ActualizarTareaColaborador(id, estado, comentario); 
        if (completado==true){ 
            JOptionPane.showMessageDialog(null, "Tarea Actualizada Correctamente"); 
            llenarTablaColaborador();
        }else{ 
            JOptionPane.showMessageDialog(null, "Tarea no Actualizada");
         }
        
    }
      //asignar colaborador
        if (e.getSource()==adminHub.btnAsignarTarea){
          asignarTareaForm.setVisible(true);
          asignarTareaForm.setLocationRelativeTo(actTarea);
      }  
       
        if(e.getSource()==asignarTareaForm.btnAsignar){
            String colaborador=asignarTareaForm.txtColaboradorAsignar.getText();
            int id=Integer.parseInt(asignarTareaForm.txtTareaAsignar.getText());
            boolean completado=consultas.asignarTarea(id, colaborador);
        if (completado==true){ 
            JOptionPane.showMessageDialog(null, "Responable Actualizado Correctamente"); 
            llenarTablaAdmin();
        }else{ 
            JOptionPane.showMessageDialog(null, "Responsable no Actualizado");
         }
        }
        
    }
    
   
    public Proyecto buscarProyecto(String nombreProyecto) {
        if (proyectos == null || nombreProyecto == null) {
            return null;
        }
        for (Proyecto p : proyectos) {
            if (p.getNombre().equalsIgnoreCase(nombreProyecto)) {
                return p;
            }
        }
        return null;
    }
    
    // Llenar Cronograma
    
    public void llenarTablaAdmin() {
        JTable tablaAdmin = this.adminHub.tablaAdmin;
        if (tablaAdmin == null) return;

        // Define encabezados en el mismo orden que obtenerProyectoTareas()
        String[] columnas = {
            "Proyecto",
            "Costo",
            "Tarea",
            "Estado",
            "Responsable",
            "Fecha Vencimiento",
            "Comentarios"
        };

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 1: return Double.class; // Costo
                    case 2: return Integer.class; // ID Tarea
                    default: return String.class;
                }
            }
        };

        List<Object[]> filas = consultas.obtenerCronograma();
        for (Object[] fila : filas) {
            modelo.addRow(fila);
        }

        tablaAdmin.setModel(modelo);

    }
    

    



    
}

