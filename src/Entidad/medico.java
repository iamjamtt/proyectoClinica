package Entidad;

public class medico {
    int idm;
    String codm;
    int ide;
    int estadom;
    int idp;

    public medico() {
    }

    public medico(int idm, String codm, int ide, int estadom, int idp) {
        this.idm = idm;
        this.codm = codm;
        this.ide = ide;
        this.estadom = estadom;
        this.idp = idp;
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public String getCodm() {
        return codm;
    }

    public void setCodm(String codm) {
        this.codm = codm;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public int getEstadom() {
        return estadom;
    }

    public void setEstadom(int estadom) {
        this.estadom = estadom;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }
    
    
}
