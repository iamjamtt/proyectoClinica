package Modelo;

import DB.database;
import Entidad.paciente;
import Entidad.persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class pacienteDao {
    PreparedStatement ps;
    ResultSet rs;
    
    DB.database con = new database();
    Connection acceso;
    
    //Aqui hacemos una consulta para mostrar los datos del paciente en una tabla
    public DefaultTableModel consultarPacienteInnerJoin(String b){
        String []titulos={"ID","DNI","NRO HISTORIA","NOMBRE","APELLIDO","SEXO"};
        DefaultTableModel m = new DefaultTableModel(null, titulos);
        Object[] o = new Object[6];
        
        String sql = "SELECT pa.idpa,p.dnip,pa.nrohistoriapa,p.nombrep,p.apellidop,p.sexop FROM paciente pa INNER JOIN persona p ON pa.idp=p.idp WHERE pa.estadopa=1 AND p.estadop=1 AND (p.dnip LIKE '%" + b + "%' OR pa.nrohistoriapa LIKE '%" + b +"%')";
   
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                o[0] = rs.getInt(1);
                o[1] = rs.getString(2);
                o[2] = rs.getString(3);
                o[3] = rs.getString(4);
                o[4] = rs.getString(5);
                o[5] = rs.getString(6);
                
                m.addRow(o);
            }
        } catch (Exception e) {
            System.out.println("error consultar datos del paciente para mostrar en la tabla: " + e);
        }

        return m;
    }
    
    //consulta para encontrar el maximo nro de historia para aumentarle 1 en el sistema
    public String nroHistoria(){
        String serie = "";
        String sql = "SELECT max(nrohistoriapa) FROM paciente";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                serie = rs.getString(1);
            }
        } catch (Exception e) {
        }
        
        return serie;
    }
    
    //aqui ingresamos al paciente
    public int addPaciente(Object[] o) {
        int r = 0;
        String sql = "INSERT INTO persona(dnip,nombrep,apellidop,sexop,emailp,direccionp,telefonop,fecharegistrop,estadop) VALUES(?,?,?,?,?,?,?,?,?)";
        
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
            System.out.println("error al ingresar paciente " + e);
        }
        
        return r;
    }
    
    //buscar el id de la persona recien ingresada para vincularlo con el paciente
    public int idPersona(){
        int id = 0;
        String sql = "SELECT max(idp) FROM persona WHERE estadop=1";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        
        return id;
    }
    
    //ingresamos datos en la tabla paciente
    public int addPaciente2(Object[] o) {
        int r = 0;
        String sql = "INSERT INTO paciente(nrohistoriapa,fechanapa,estadopa,idp) VALUES(?,?,?,?)";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al ingresar paciente 2 " + e);
        }
        
        return r;
    }
    
    //actualizamos la tabla persona
    public int updatePersonaPaciente(Object[] o) {
        int r = 0;
        String sql = "UPDATE persona SET dnip=?,nombrep=?,apellidop=?,sexop=?,emailp=?,direccionp=?,telefonop=? WHERE idp=?";
        
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
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error actualizar persona paciente " + e);
        }
        
        return r;
    }
    
    //buscar el id de la persona para poder modificar los datos 
    public int buscaridPersona(int idpa){
        int id = 0;
        String sql = "SELECT idp FROM paciente WHERE estadopa=1 AND idpa="+idpa;
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("error al buscar el idp de la tabla paciente: " + e);
        }
        
        return id;
    }
    
    //actualizamos la tabla paciente
    public int updatePaciente(Object[] o) {
        int r = 0;
        String sql = "UPDATE paciente SET fechanapa=? WHERE idpa=?";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error actualizar paciente " + e);
        }
        
        return r;
    }
    
    //aqui obtenemos todos los datos persona para poder modificarlos
    public Entidad.persona datosPersona(int idp, String dnip){
        Entidad.persona ep = new persona();
        
        String sql = "";
        
        if(dnip == null){
            sql = "SELECT * FROM persona WHERE idp=? AND estadop=1";
        }else if(idp == 0){
            sql = "SELECT * FROM persona WHERE dnip=? AND estadop=1";
        }
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            if(dnip == null){
                ps.setInt(1, idp);
            }else if(idp == 0){
                ps.setString(1, dnip);
            }
            rs = ps.executeQuery();
            while(rs.next()){
                ep.setIdp(rs.getInt(1));
                ep.setDnip(rs.getString(2));
                ep.setNombrep(rs.getString(3));
                ep.setApellidop(rs.getString(4));
                ep.setSexop(rs.getString(5));
                ep.setEmailp(rs.getString(6));
                ep.setDireccionp(rs.getString(7));
                ep.setTelefonop(rs.getString(8));
                ep.setFecharegistrop(rs.getString(9));
                ep.setEstadop(rs.getInt(10));
            }
        } catch (Exception e) {
            System.out.println("error al obtener datos de persona:  " + e);
        }
        
        return ep;
    }
    
    //aqui obtenemos todos los datos paciente para poder modificarlos
    public Entidad.paciente datosPaciente(int idpa, int idp){
        Entidad.paciente epa = new paciente();
        
        String sql = "";
        
        if(idp == 0){
            sql = "SELECT * FROM paciente WHERE idpa=? AND estadopa=1";
        }else if(idpa == 0){
            sql = "SELECT * FROM paciente WHERE idp=? AND estadopa=1";
        }
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            if(idp == 0){
                ps.setInt(1, idpa);
            }else if(idpa == 0){
                ps.setInt(1, idp);
            }
            rs = ps.executeQuery();
            while(rs.next()){
                epa.setIdpa(rs.getInt(1));
                epa.setNrohistoriapa(rs.getString(2));
                epa.setFechanapa(rs.getString(3));
                epa.setEstadopa(rs.getInt(4));
                epa.setIdp(rs.getInt(5));
            }
        } catch (Exception e) {
            System.out.println("error al obtener datos de paciente:  " + e);
        }
        
        return epa;
    }
    
    //aqui actualizamos el esatdo de la persona
    public int deletePersonaPaciente(int idp) {
        int r = 0;
        String sql = "UPDATE persona SET estadop=2 WHERE idp=?";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, idp);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error eliminar persona paciente " + e);
        }
        
        return r;
    }
    
    //actualizamos el estado de la tabla paciente
    public int deletePaciente(int idpa) {
        int r = 0;
        String sql = "UPDATE paciente SET estadopa=2 WHERE idpa=?";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, idpa);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error eliminar paciente " + e);
        }
        
        return r;
    }
    
    //aqui obtnemos el id del paciente ingresando su numero de historia
    public int idpa(String nro){
        int serie = 0; 
        String sql = "SELECT idpa FROM paciente WHERE estadopa=1 AND nrohistoriapa=?";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, nro);
            rs = ps.executeQuery();
            while(rs.next()){
                serie = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("erro al obtener idpa del paciente: " + e);
        }
        
        return serie;
    }
    
    //buscar a la persona para vailar sus campos (si existe o no)
    public int buscarPersonaPaciente(String dnip){
        int id = 0;
        String sql = "SELECT pa.idpa FROM paciente pa INNER JOIN persona p ON pa.idp=p.idp WHERE estadopa=1 AND estadop=1 AND dnip=?";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, dnip);
            rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("error al buscar el ver si existe persona paciente: " + e);
        }
        
        return id;
    }
    
    //buscar a la persona para vailar sus campos (si existe o no el dni)
    public String buscarPersonaPacienteDni(String dnip){
        String dni = "";
        String sql = "SELECT p.dnip FROM paciente pa INNER JOIN persona p ON pa.idp=p.idp WHERE estadopa=1 AND estadop=1 AND dnip=?";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, dnip);
            rs = ps.executeQuery();
            while(rs.next()){
                dni = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("error al buscar el ver si existe dni persona paciente: " + e);
        }
        
        return dni;
    }
}
