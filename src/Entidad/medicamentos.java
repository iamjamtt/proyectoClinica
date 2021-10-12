package Entidad;

public class medicamentos {
    int idme;
    String nombreme;
    String codme;

    public medicamentos() {
    }

    public medicamentos(int idme, String nombreme, String codme) {
        this.idme = idme;
        this.nombreme = nombreme;
        this.codme = codme;
    }

    public int getIdme() {
        return idme;
    }

    public void setIdme(int idme) {
        this.idme = idme;
    }

    public String getNombreme() {
        return nombreme;
    }

    public void setNombreme(String nombreme) {
        this.nombreme = nombreme;
    }

    public String getCodme() {
        return codme;
    }

    public void setCodme(String codme) {
        this.codme = codme;
    }
    
    
}
