package Entidad;

public class paciente {
    int idpa;
    String nrohistoriapa;
    String fechanapa;
    int estadopa;
    int idp;

    public paciente() {
    }

    public paciente(int idpa, String nrohistoriapa, String fechanapa, int estadopa, int idp) {
        this.idpa = idpa;
        this.nrohistoriapa = nrohistoriapa;
        this.fechanapa = fechanapa;
        this.estadopa = estadopa;
        this.idp = idp;
    }

    public int getIdpa() {
        return idpa;
    }

    public void setIdpa(int idpa) {
        this.idpa = idpa;
    }

    public String getNrohistoriapa() {
        return nrohistoriapa;
    }

    public void setNrohistoriapa(String nrohistoriapa) {
        this.nrohistoriapa = nrohistoriapa;
    }

    public String getFechanapa() {
        return fechanapa;
    }

    public void setFechanapa(String fechanapa) {
        this.fechanapa = fechanapa;
    }

    public int getEstadopa() {
        return estadopa;
    }

    public void setEstadopa(int estadopa) {
        this.estadopa = estadopa;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }
    
    
}
