package Transiciones;

/**
 *
 * @author usuario
 */
public class TransicionAFD {
    private int estadoO;
    private int estadoD;
    private char simbolo;
    
    public TransicionAFD(int e1, char simbolo, int e2) {
        this.estadoO = e1;
        this.estadoD = e2;
        this.simbolo = simbolo;
    }

    public int getEstadoO() {
        return estadoO;
    }

    public int getEstadoD() {
        return estadoD;
    }

    public char getSimbolo() {
        return simbolo;
    }
    
    @Override
    public String toString() {
        return ("f(" +"q"+this.estadoO + "," + this.simbolo + ") --> " + this.estadoD);
    }
    
    
}
