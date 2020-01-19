package Automata;

import java.util.HashSet;
import java.util.Objects;

/**
 * Clase TransicionAFND. Los estados son cadenas de caracteres (String). El
 * símbolo de entrada es un caracter (Character).
 *
 * @author Víctor M. Rodríguez y Fran J. Beltrán
 */
public class TransicionAFND {

    private String origen;
    private HashSet<String> destinos;
    private char simbolo;

    /**
     * Crea la transición con los parámetros indicados
     *
     * @param origen Estado origen
     * @param simbolo Símbolo de entrada
     * @param destino Conjunto de estados destino
     */
    public TransicionAFND(String origen, char simbolo, HashSet<String> destino) {
        this.origen = origen;
        this.destinos = destino;
        this.simbolo = simbolo;
    }

    /**
     * Devuelve el estado de origen
     *
     * @return
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Devuelve el conjunto de estados de destino
     *
     * @return
     */
    public HashSet<String> getDestinos() {
        return destinos;
    }

    /**
     * Devuelve el simbolo de la transicion
     *
     * @return
     */
    public char getSimbolo() {
        return simbolo;
    }

    /**
     * Permite la representación en texto de la transicion
     *
     * @return
     */
    @Override
    public String toString() {
        String mensaje = " " + this.origen + " '" + this.simbolo + "'";

        for (String valor : this.destinos) {
            mensaje += " " + valor;
        }

        return mensaje;
    }

    /**
     * Devuvelve el código hash del objeto, usado para ser comparado con otro
     * objeto en colecciones (HashSet, HashMap..)
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.origen);
        hash = 71 * hash + Objects.hashCode(this.destinos);
        hash = 71 * hash + this.simbolo;
        return hash;
    }

    /**
     * Devuelve verdadero si la transición pasada por parámetro equivale a la
     * que invoca el método
     *
     * @param obj
     * @return
     */
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
