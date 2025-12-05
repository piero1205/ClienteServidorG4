/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jason
 */
public class Sentencias extends Conexion {
        
    public boolean agregarUsuario(Usuarios usuario) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO usuarios (nombre, rol, correo, contra) VALUES (?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getRol());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getContra());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public String loginUsuario(String nombre, String contra) {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = getConexion();

    String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contra = ?";

    try {
        ps = con.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.setString(2, contra);
        rs = ps.executeQuery();

        if (rs.next()) {
            
            return rs.getString("rol");
        } else {
            return null;
        }

    } catch (SQLException e) {
        System.err.println(e);
        return null;

    } finally {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
    
        
    public ArrayList<Usuarios> recorridoUsuarios(){
        Connection con=getConexion();
        ArrayList<Usuarios> listaUsuarios=new ArrayList<>();
        String sql="SELECT nombre, rol, correo, contra from usuarios";
        
        try{
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                String nombre=rs.getString("nombre");
                String rol=rs.getString("rol");
                String correo=rs.getString("correo");
                String contra=rs.getString("contra");
                
                listaUsuarios.add(new Usuarios(nombre, rol, correo, contra));
                        
            }
        }catch (SQLException e){
             Logger.getLogger(Sentencias.class.getName()).log(Level.SEVERE, null,e);
        }
        return listaUsuarios;
    }
    
    
}
