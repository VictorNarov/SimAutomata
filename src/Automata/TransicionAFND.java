package Automata;

import java.util.HashSet;

/**
 *
 * @author usuario
 */
public class TransicionAFND {
    private String origen;
    private HashSet<String> destinos;
    private char simbolo;

    public TransicionAFND(String origen, HashSet<String> destino, char simbolo) {
        this.origen = origen;
        this.destinos = destino;
        this.simbolo = simbolo;
    }

    public String getOrigen() {
        return origen;
    }

    public HashSet<String> getDestinos() {
        return destinos;
    }

    public char getSimbolo() {
        return simbolo;
    }
    
    public String toString()
    {
        return ("\nf("+origen+", "+ simbolo+") -> "+destinos);
    }
}
