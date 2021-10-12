package Entidad;

public class detalleconsulta {
    int idd;
    int idco;
    int idme;
    String descripciond;

    public detalleconsulta() {
    }

    public detalleconsulta(int idd, int idco, int idme, String descripciond) {
        this.idd = idd;
        this.idco = idco;
        this.idme = idme;
        this.descripciond = descripciond;
    }

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public int getIdco() {
        return idco;
    }

    public void setIdco(int idco) {
        this.idco = idco;
    }

    public int getIdme() {
        return idme;
    }

    public void setIdme(int idme) {
        this.idme = idme;
    }

    public String getDescripciond() {
        return descripciond;
    }

    public void setDescripciond(String descripciond) {
        this.descripciond = descripciond;
    }
    
    
}
