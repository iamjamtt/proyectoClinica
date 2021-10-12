package Modelo;

import DB.database;
import Entidad.asistente;
import Entidad.medico;
import Entidad.persona;
import Entidad.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userDao {
    PreparedStatement ps;
    ResultSet rs;
    
    DB.database con = new database();
    Connection acceso;
    
    //Sirve para validar los campos al iniciar sesion en el sistema
    public Entidad.user ValidarUser(String username, String pass){
        Entidad.user eu = new user();
        
        String sql = "SELECT * FROM user WHERE username=? AND password=? AND estadou=1";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while(rs.next()){
                eu.setIdu(rs.getInt(1));
                eu.setUsername(rs.getString(2));
                eu.setPassword(rs.getString(3));
                eu.setEstadou(rs.getInt(4));
                eu.setIdp(rs.getInt(5));
            }
        } catch (Exception e) {
            System.out.println("error al validad user del login:  " + e);
        }
        
        return eu;
    }
    
    //obtengo los datos de la tabla asistente de acuerdo al id ded la persona vinvulada a ella
    public Entidad.asistente validarAsistente(int idp){
        Entidad.asistente ea = new asistente();
        
        String sql = "SELECT * FROM asistente WHERE idp=? AND estadoa=1";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, idp);
            rs = ps.executeQuery();
            while(rs.next()){
                ea.setIda(rs.getInt(1));
                ea.setCoda(rs.getString(2));
                ea.setEstadoa(rs.getInt(3));
                ea.setIdp(rs.getInt(4));
            }
        } catch (Exception e) {
            System.out.println("error al validad user asistente del login:  " + e);
        }
        
        return ea;
    }
    
    //obtengo los datos de la tabla medico de acuerdo a su id de la persona vinculada a ella
    public Entidad.medico validarMedico(int idp){
        Entidad.medico em = new medico();
        
        String sql = "SELECT * FROM medico WHERE idp=? AND estadom=1";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, idp);
            rs = ps.executeQuery();
            while(rs.next()){
                em.setIdm(rs.getInt(1));
                em.setCodm(rs.getString(2));
                em.setIde(rs.getInt(3));
                em.setEstadom(rs.getInt(4));
                em.setIdp(rs.getInt(5));
            }
        } catch (Exception e) {
            System.out.println("error al validad user medico del login:  " + e);
        }
        
        return em;
    }
    
    //obtengo los datos de la tabla persona de acuerdo al id persona ingresada al sistema
    public Entidad.persona validarPersona(int idp){
        Entidad.persona ep = new persona();
        
        String sql = "SELECT * FROM persona WHERE idp=? AND estadop=1";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, idp);
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
                ep.setEstadop(rs.getInt(9));
            }
        } catch (Exception e) {
            System.out.println("error al validad persona del login:  " + e);
        }
        
        return ep;
    }
}
