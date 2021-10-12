package Entidad;

public class consulta {
    int idco;
    String codco;
    String fechaco;
    int idm;
    int idpa;
    int estadoco;
    String descripcionco;

    public consulta() {
    }

    public consulta(int idco, String codco, String fechaco, int idm, int idpa, int estadoco, String descripcionco) {
        this.idco = idco;
        this.codco = codco;
        this.fechaco = fechaco;
        this.idm = idm;
        this.idpa = idpa;
        this.estadoco = estadoco;
        this.descripcionco = descripcionco;
    }

    public int getIdco() {
        return idco;
    }

    public void setIdco(int idco) {
        this.idco = idco;
    }

    public String getCodco() {
        return codco;
    }

    public void setCodco(String codco) {
        this.codco = codco;
    }

    public String getFechaco() {
        return fechaco;
    }

    public void setFechaco(String fechaco) {
        this.fechaco = fechaco;
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public int getIdpa() {
        return idpa;
    }

    public void setIdpa(int idpa) {
        this.idpa = idpa;
    }

    public int getEstadoco() {
        return estadoco;
    }

    public void setEstadoco(int estadoco) {
        this.estadoco = estadoco;
    }

    public String getDescripcionco() {
        return descripcionco;
    }

    public void setDescripcionco(String descripcionco) {
        this.descripcionco = descripcionco;
    }
    
    
}
