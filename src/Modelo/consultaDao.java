package Modelo;

import DB.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class consultaDao {
    PreparedStatement ps;
    ResultSet rs;
    
    DB.database con = new database();
    Connection acceso;
    
    //Aqui lo que se hace es consultar a las tablas relacionadas a cita para mostrar los datos en una tabla y asi em medico pueda atender en su consultorio
    public DefaultTableModel consultaMostrarCitas(int idm, String b){
        String []titulos={"ID","PACIENTE","DNI","NRO HISTORIA","FECHA","HORA","NRO ORDEN"};
        DefaultTableModel m = new DefaultTableModel(null, titulos);
        Object[] o = new Object[7];
        
        String sql = "SELECT c.idc,p.nombrep,p.apellidop,p.dnip,pa.nrohistoriapa,c.fechac,c.horac,c.ordenc FROM cita c INNER JOIN paciente pa ON c.idpa=pa.idpa INNER JOIN persona p ON pa.idp=p.idp WHERE c.estadoc=1 AND pa.estadopa=1 AND p.estadop=1 AND c.idm=? AND c.fechac=?";
   
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, idm);
            ps.setString(2, b);
            rs = ps.executeQuery();
            while(rs.next()){
                o[0] = rs.getInt(1);
                o[1] = rs.getString(2) + " " + rs.getString(3);
                o[2] = rs.getString(4);
                o[3] = rs.getString(5);
                o[4] = rs.getString(6);
                o[5] = rs.getString(7);
                o[6] = rs.getString(8);
                
                m.addRow(o);
            }
        } catch (Exception e) {
            System.out.println("error consultar datos de la cita para mostrar en la tabla: " + e);
        }

        return m;
    }
    
    //aqui me lo que me hace es obtener los datos del medicamento y cargarlo en un combo
    public void cargarComboMedicamentos(JComboBox cbo, String var){
        
        String sql = "SELECT nombreme FROM medicamentos WHERE codme LIKE '%" + var + "%' AND nombreme LIKE '%" + var + "%'";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            cbo.removeAllItems();
            cbo.addItem("Seleccione");
            
            while(rs.next()){
                cbo.addItem(rs.getString(1));
            }
            
        } catch (Exception e) {
            System.out.println("Error en combo medicamentos: " + e);
        }
    }
    
    //aqui lo que se hace es devolverme el id del medicamento ingresando su nombre
    public int idme(String nombreme){
        int serie = 0; 
        String sql = "SELECT idme FROM medicamentos WHERE nombreme=?";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, nombreme);
            rs = ps.executeQuery();
            while(rs.next()){
                serie = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("erro al obtener idme del medicamento: " + e);
        }
        
        return serie;
    }
    
    //aqui se ingresan los datos de medicamentos a la db
    public int addConsulta(Object[] o) {
        int r = 0;
        String sql = "INSERT INTO consulta(codco,fechaco,idm,idpa,estadoco,descripcionco) VALUES(?,?,?,?,?,?)";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, o[5]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al ingresar consulta " + e);
        }
        
        return r;
    }
    
    //aqui consultamos el codigo maximo de la consulta para poder generar uno mayor para cada consulta se se vaya a tratar
    public String consultarCodConsulta(){
        String serie = "";
        String sql = "SELECT max(codco) FROM consulta";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                serie = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("error en consiltar codigo de consulta " + e);
        }
        
        return serie;
    }
    
    //aqui agregamos los detalles de la consulta que vienen hacer los medicamentos 
    public int addDetalleConsulta(Object[] o) {
        int r = 0;
        String sql = "INSERT INTO detalleconsulta(idco,idme,descripciond) VALUES(?,?,?)";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al ingresar detalle consulta " + e);
        }
        
        return r;
    }
    
    //aqui obtenemos el id recien ingresado de la tabla consulta para poder mandarlo como valor en la tabla detalleconsulta
    public int obtIdConsulta(){
        int id = 0;
        
        String sql = "SELECT MAX(idco) FROM consulta";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("error en obtner idco ultimo " + e);
        }
        
        return id;
    }
    
    //una ves que se haya realizado la consulta, lo que se hace en la tabla cita es ponerlo en modo "cita finalizada"
    //1=activo      2=desactivo     3=citaconcluida
    public int finalizarCita(int idc) {
        int r = 0;
        String sql = "UPDATE cita SET estadoc=3 WHERE idc=?";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, idc);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al finalizar cita " + e);
        }
        
        return r;
    }
    
    //esta consulta es para mostra datos en una tabla de las consultas realizadas 
    public DefaultTableModel consultaMostrarConsultas(String nrohistoriapa){
        String []titulos={"ID","CODIGO","DNI","PACIENTE","FECHA","SINTOMA"};
        DefaultTableModel m = new DefaultTableModel(null, titulos);
        Object[] o = new Object[7];
        
        String sql = "SELECT c.idco,c.codco,p.dnip,p.nombrep,p.apellidop,c.fechaco,c.descripcionco FROM consulta c INNER JOIN paciente pa ON c.idpa=pa.idpa INNER JOIN persona p ON pa.idp=p.idp WHERE c.estadoco=1 AND pa.estadopa=1 AND p.estadop=1 AND (pa.nrohistoriapa=? OR p.dnip=?)";
   
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, nrohistoriapa);
            ps.setString(2, nrohistoriapa);
            rs = ps.executeQuery();
            while(rs.next()){
                o[0] = rs.getInt(1);
                o[1] = rs.getString(2);
                o[2] = rs.getString(3);
                o[3] = rs.getString(4) + " " + rs.getString(5);
                o[4] = rs.getString(6);
                o[5] = rs.getString(7);
                
                m.addRow(o);
            }
        } catch (Exception e) {
            System.out.println("error consultar datos de la consulta para mostrar en la tabla: " + e);
        }

        return m;
    }
    
}
