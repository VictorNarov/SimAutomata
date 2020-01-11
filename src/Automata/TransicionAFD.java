package Automata;

/**
 *
 * @author usuario
 */
public class TransicionAFD {
    private String estadoO;
    private String estadoD;
    private char simbolo;
    
    public TransicionAFD(String e1, char simbolo, String e2) {
        this.estadoO = e1;
        this.estadoD = e2;
        this.simbolo = simbolo;
    }

    public String getEstadoO() {
        return estadoO;
    }

    public String getEstadoD() {
        return estadoD;
    }

    public char getSimbolo() {
        return simbolo;
    }
    
    @Override
    public String toString() {
        return ("f(" +this.estadoO + "," + this.simbolo + ") --> " + this.estadoD);
    }
    
    
}
