package Automata;

import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author usuario
 */
public class TransicionAFND {
    private String origen;
    private HashSet<String> destinos;
    private char simbolo;

    public TransicionAFND(String origen, char simbolo, HashSet<String> destino) {
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
    
    @Override
    public String toString()
    {
        String mensaje = " " + this.origen + " '" + this.simbolo + "'";
        
        for (String valor: this.destinos) {
            mensaje += " " + valor;
        }
        
        return mensaje;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.origen);
        hash = 71 * hash + Objects.hashCode(this.destinos);
        hash = 71 * hash + this.simbolo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TransicionAFND other = (TransicionAFND) obj;
        if (this.simbolo != other.simbolo) {
            return false;
        }
        if (!Objects.equals(this.origen, other.origen)) {
            return false;
        }
        if (!Objects.equals(this.destinos, other.destinos)) {
            return false;
        }
        return true;
    }
    
    
}
