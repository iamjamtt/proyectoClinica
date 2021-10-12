package Entidad;

public class persona {
    int idp;
    String dnip;
    String nombrep;
    String apellidop;
    String sexop;
    String emailp;
    String direccionp;
    String telefonop;
    String fecharegistrop;
    int estadop;

    public persona() {
    }

    public persona(int idp, String dnip, String nombrep, String apellidop, String sexop, String emailp, String direccionp, String telefonop, String fecharegistrop, int estadop) {
        this.idp = idp;
        this.dnip = dnip;
        this.nombrep = nombrep;
        this.apellidop = apellidop;
        this.sexop = sexop;
        this.emailp = emailp;
        this.direccionp = direccionp;
        this.telefonop = telefonop;
        this.fecharegistrop = fecharegistrop;
        this.estadop = estadop;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public String getDnip() {
        return dnip;
    }

    public void setDnip(String dnip) {
        this.dnip = dnip;
    }

    public String getNombrep() {
        return nombrep;
    }

    public void setNombrep(String nombrep) {
        this.nombrep = nombrep;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getSexop() {
        return sexop;
    }

    public void setSexop(String sexop) {
        this.sexop = sexop;
    }

    public String getEmailp() {
        return emailp;
    }

    public void setEmailp(String emailp) {
        this.emailp = emailp;
    }

    public String getDireccionp() {
        return direccionp;
    }

    public void setDireccionp(String direccionp) {
        this.direccionp = direccionp;
    }

    public String getTelefonop() {
        return telefonop;
    }

    public void setTelefonop(String telefonop) {
        this.telefonop = telefonop;
    }

    public String getFecharegistrop() {
        return fecharegistrop;
    }

    public void setFecharegistrop(String fecharegistrop) {
        this.fecharegistrop = fecharegistrop;
    }

    public int getEstadop() {
        return estadop;
    }

    public void setEstadop(int estadop) {
        this.estadop = estadop;
    }
    
    
}
