package Modelo;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class limiteDigitos extends PlainDocument{
    
    private int limite;
    
    public limiteDigitos(int cant){
        super();
        this.limite = cant;
    }
    
    public void insertString(int compensacion, String str, AttributeSet attr) throws BadLocationException{
        if(str == null) return;
        if(getLength()+str.length() <= limite){
            super.insertString(compensacion, str, attr);
        }
    }
}
