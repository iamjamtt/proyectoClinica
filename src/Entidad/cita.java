package Entidad;

public class cita {
    int idc;
    String fechac;
    String horac;
    int idm;
    String ordenc;
    int idpa;
    int ida;
    int estadoc;
    String fecharegistroc;
    String codc;

    public cita() {
    }

    public cita(int idc, String fechac, String horac, int idm, String ordenc, int idpa, int ida, int estadoc, String fecharegistroc, String codc) {
        this.idc = idc;
        this.fechac = fechac;
        this.horac = horac;
        this.idm = idm;
        this.ordenc = ordenc;
        this.idpa = idpa;
        this.ida = ida;
        this.estadoc = estadoc;
        this.fecharegistroc = fecharegistroc;
        this.codc = codc;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getFechac() {
        return fechac;
    }

    public String getFecharegistroc() {
        return fecharegistroc;
    }

    public String getCodc() {
        return codc;
    }

    public void setCodc(String codc) {
        this.codc = codc;
    }

    public void setFecharegistroc(String fecharegistroc) {
        this.fecharegistroc = fecharegistroc;
    }

    public void setFechac(String fechac) {
        this.fechac = fechac;
    }

    public String getHorac() {
        return horac;
    }

    public void setHorac(String horac) {
        this.horac = horac;
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public String getOrdenc() {
        return ordenc;
    }

    public void setOrdenc(String ordenc) {
        this.ordenc = ordenc;
    }

    public int getIdpa() {
        return idpa;
    }

    public void setIdpa(int idpa) {
        this.idpa = idpa;
    }

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public int getEstadoc() {
        return estadoc;
    }

    public void setEstadoc(int estadoc) {
        this.estadoc = estadoc;
    }
    
    
}
