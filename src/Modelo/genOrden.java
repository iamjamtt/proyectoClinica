package Modelo;

public class genOrden {
    private int dato;
    private int cont = 1;
    private String num = "";

    public void generar(int dato) {
        this.dato = dato;

        if (this.dato < 5) {
            int can = cont + this.dato;
            num = "0" + can;
        }

    }

    public String serie() {
        return this.num;
    }
}
