/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package crudmvc;

import Controlador.ctrlGestionTareas;
import Modelo.Sentencias;
import Modelo.Usuarios;
import Vista.*;

/**
 *
 * @author Jason
 */
public class ProyectoCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Usuarios mod=new Usuarios();
        Sentencias modC=new Sentencias();
        frmInicioSesion frmINI=new frmInicioSesion();
        frmCrearCuenta frmCC=new frmCrearCuenta();
        
        ctrlGestionTareas ctrl=new ctrlGestionTareas(mod, modC, frmINI,frmCC);
        ctrl.iniciar();
        frmINI.setVisible(true);     
    }
    
}
