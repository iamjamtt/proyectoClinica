package Entidad;

public class user {
    int idu;
    String username;
    String password;
    int estadou;
    int idp;

    public user() {
    }

    public user(int idu, String username, String password, int estadou, int idp) {
        this.idu = idu;
        this.username = username;
        this.password = password;
        this.estadou = estadou;
        this.idp = idp;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstadou() {
        return estadou;
    }

    public void setEstadou(int estadou) {
        this.estadou = estadou;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }
    
    
}
