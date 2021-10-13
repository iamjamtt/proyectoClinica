package Modelo;

import DB.database;
import Entidad.especialidad;
import Entidad.medico;
import Entidad.persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class medicoDao {
    PreparedStatement ps;
    ResultSet rs;
    
    DB.database con = new database();
    Connection acceso;
    
    //consulta para mostrar todas las especialidaddes del medico en un combobox
    public void cargarComboEspecialidad(JComboBox cbo){
        Entidad.especialidad ee = new especialidad();
        
        String sql = "SELECT nombree FROM especialidad";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            cbo.addItem("Seleccione");
            
            while(rs.next()){
                cbo.addItem(rs.getString(1));
            }
            
        } catch (Exception e) {
            System.out.println("Error en combo especialidad de medico: " + e);
        }
    }
    
    //consulta para mostrar a los medicos
    public DefaultTableModel consultarMedicoInnerJoin(String nombree){
        String []titulos={"ID","CODIGO","MEDICO","EMAIL","ESPECIALIDAD"};
        DefaultTableModel m = new DefaultTableModel(null, titulos);
        Object[] o = new Object[5];
        
        String sql = "SELECT m.idm,m.codm,p.nombrep,p.apellidop,p.emailp,e.nombree FROM medico m INNER JOIN persona p ON m.idp=p.idp INNER JOIN especialidad e ON m.ide=e.ide WHERE m.estadom=1 AND p.estadop=1 AND e.nombree LIKE '%" + nombree + "%'";
   
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                o[0] = rs.getInt(1);
                o[1] = rs.getString(2);
                o[2] = rs.getString(3) + " " + rs.getString(4);;
                o[3] = rs.getString(5);
                o[4] = rs.getString(6);
                
                m.addRow(o);
            }
        } catch (Exception e) {
            System.out.println("error consultar datos del medico para mostrar en la tabla: " + e);
        }

        return m;
    }
    
    //aqui obtenemos todos los datos de persona y lo guardamos en la entidad persona
    public Entidad.persona datosPersona(int idp){
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
                ep.setFecharegistrop(rs.getString(9));
                ep.setEstadop(rs.getInt(10));
            }
        } catch (Exception e) {
            System.out.println("error al obtener datos de persona:  " + e);
        }
        
        return ep;
    }
    
    //aqui obtenemos todos los datos de la tabla medico y lo guadamos en la entidad medico
    public Entidad.medico datosMedico(int idm){
        Entidad.medico em = new medico();
        
        String sql = "SELECT * FROM medico WHERE idm=? AND estadom=1";
        
        try {
            acceso = con.conectardb();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, idm);
            rs = ps.executeQuery();
            while(rs.next()){
                em.setIdm(rs.getInt(1));
                em.setCodm(rs.getString(2));
                em.setIde(rs.getInt(3));
                em.setEstadom(rs.getInt(4));
                em.setIdp(rs.getInt(5));
            }
        } catch (Exception e) {
            System.out.println("error al obtener datos de medico:  " + e);
        }
        
        return em;
    }
}
