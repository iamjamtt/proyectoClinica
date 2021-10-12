package Entidad;

public class asistente {
    int ida;
    String coda;
    int estadoa;
    int idp;

    public asistente() {
    }

    public asistente(int ida, String coda, int estadoa, int idp) {
        this.ida = ida;
        this.coda = coda;
        this.estadoa = estadoa;
        this.idp = idp;
    }

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public String getCoda() {
        return coda;
    }

    public void setCoda(String coda) {
        this.coda = coda;
    }

    public int getEstadoa() {
        return estadoa;
    }

    public void setEstadoa(int estadoa) {
        this.estadoa = estadoa;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }
    
    
}
