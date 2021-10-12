package Modelo;

import DB.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class citaDao {
    PreparedStatement ps;
    ResultSet rs;
    
    DB.database con = new database();
    Connection acceso;
    
    public int orden(String fechac, int idm){
        int serie = 0;
        String sql = "SELECT count(fechac) FROM cita WHERE estadoc=1 AND fechac=? AND idm=?";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, fechac);
            ps.setInt(2, idm);
            rs = ps.executeQuery();
            while(rs.next()){
                serie = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("erro al contar cuantas orden hay por dia: " + e);
        }
        
        return serie;
    }
    
    public String maxOrden(String fechac, int idm){
        String serie = ""; 
        String sql = "SELECT MAX(ordenc) FROM cita WHERE estadoc=1 AND fechac=? AND idm=?";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, fechac);
            ps.setInt(2, idm);
            rs = ps.executeQuery();
            while(rs.next()){
                serie = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("erro al obtener la maxima orden para ingresar a las citas: " + e);
        }
        
        return serie;
    }
    
    public int addCita(Object[] o) {
        int r = 0;
        String sql = "INSERT INTO cita(fechac,horac,idm,ordenc,idpa,ida,estadoc,fecharegistroc,codc) VALUES(?,?,?,?,?,?,?,?,?)";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, o[5]);
            ps.setObject(7, o[6]);
            ps.setObject(8, o[7]);
            ps.setObject(9, o[8]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al ingresar cita " + e);
        }
        
        return r;
    }
    
    //verificar si la hora de la cita ya esta copada
    public String horaCita(String fechac, int idm, String horac){
        String serie = ""; 
        String sql = "SELECT horac FROM cita WHERE estadoc=1 AND fechac=? AND idm=? AND horac=?";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, fechac);
            ps.setInt(2, idm);
            ps.setString(3, horac);
            rs = ps.executeQuery();
            while(rs.next()){
                serie = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("erro al obtener la hora y validar al ingresar a las citas: " + e);
        }
        
        return serie;
    }
    
    public String consultarCodC(){
        String serie = "";
        String sql = "SELECT max(codc) FROM cita";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                serie = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("error en consiltar codigo e ventas " + e);
        }
        
        return serie;
    }
    
    public DefaultTableModel consultaMostrarCitas(String dnip, String fechac){
        String []titulos={"ID","CODIGO","PACIENTE","DNI","NRO HISTORIA","MEDICO","FECHA","HORA","NRO ORDEN"};
        DefaultTableModel m = new DefaultTableModel(null, titulos);
        Object[] o = new Object[9];
        
        String sql = "SELECT c.idc,c.codc,p.nombrep,p.apellidop,p.dnip,pa.nrohistoriapa,pp.nombrep,pp.apellidop,c.fechac,c.horac,c.ordenc FROM cita c INNER JOIN paciente pa ON c.idpa=pa.idpa INNER JOIN persona p ON pa.idp=p.idp INNER JOIN medico m ON c.idm=m.idm INNER JOIN persona pp ON (m.idp=pp.idp) WHERE c.estadoc=1 AND pa.estadopa=1 AND m.estadom=1 AND p.dnip LIKE '%"+dnip+"%' AND c.fechac=?";
   
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, fechac);
            rs = ps.executeQuery();
            while(rs.next()){
                o[0] = rs.getInt(1);
                o[1] = rs.getString(2);
                o[2] = rs.getString(3) + " " + rs.getString(4);
                o[3] = rs.getString(5);
                o[4] = rs.getString(6);
                o[5] = rs.getString(7) + " " + rs.getString(8);
                o[6] = rs.getString(9);
                o[7] = rs.getString(10);
                o[8] = rs.getString(11);
                
                m.addRow(o);
            }
        } catch (Exception e) {
            System.out.println("error consultar datos de la cita con full detalle para mostrar en la tabla: " + e);
        }

        return m;
    }
        
    public int deleteCita(int idc) {
        int r = 0;
        String sql = "UPDATE cita SET estadoc=2 WHERE idc=?";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, idc);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error eliminar cita " + e);
        }
        
        return r;
    }  
    
}
