package Transiciones;

import java.util.HashSet;

/**
 *
 * @author usuario
 */
public class TransicionAFND {
    private int origen;
    private HashSet<Integer> destinos;
    private char simbolo;

    public TransicionAFND(int origen, HashSet<Integer> destino, char simbolo) {
        this.origen = origen;
        this.destinos = destino;
        this.simbolo = simbolo;
    }

    public int getOrigen() {
        return origen;
    }

    public HashSet<Integer> getDestinos() {
        return destinos;
    }

    public char getSimbolo() {
        return simbolo;
    }
    
    public String toString()
    {
        return ("f("+origen+", "+ simbolo+") -> "+destinos +"\n");
    }
}
