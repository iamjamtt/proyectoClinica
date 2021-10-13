package Modelo;

import DB.database;
import Entidad.especialidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class especialidadDao {
    PreparedStatement ps;
    ResultSet rs;
    
    DB.database con = new database();
    Connection acceso;
    
    //en esta consulta lo que se hace es obtener el id ingresando el nombre de la especialidad
    public int idEspecialidad(String nombree){
        int ide = 0;
        String sql = "SELECT ide FROM especialidad WHERE nombree=?";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, nombree);
            rs = ps.executeQuery();
            while(rs.next()){
                ide = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("error al consultar id especialidad: " + e);
        }
        
        return ide;
    }
    
    //aqui obetenemos todos los datos de la tabla especialidad y lo retornamos en la entidad especialidad
    public Entidad.especialidad datosEspecialidad(int ide){
        Entidad.especialidad ee = new especialidad();
        
        String sql = "SELECT * FROM especialidad WHERE ide=?";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, ide);
            rs = ps.executeQuery();
            while(rs.next()){
                ee.setIde(rs.getInt(1));
                ee.setNombree(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("error al obtener datos de especialidad:  " + e);
        }
        
        return ee;
    }
}

