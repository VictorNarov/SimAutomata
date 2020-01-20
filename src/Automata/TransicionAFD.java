/* 
 * Copyright (C) 2020 Víctor Manuel Rodríguez Navarro
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Automata;

import java.util.Objects;

/**
 * Clase TransicionAFD. Los estados son cadenas de caracteres (String). El
 * símbolo de entrada es un caracter (Character).
 *
 * @author Víctor M. Rodríguez y Fran J. Beltrán
 * @see Automata.AFD
 * 
 */
public class TransicionAFD {

    private String estadoO;
    private String estadoD;
    private char simbolo;

    /**
     * Crea la transición con los parámetros indicados
     *
     * @param e1 Estado origen
     * @param simbolo Símbolo de entrada
     * @param e2 Estado destino
     */
    public TransicionAFD(String e1, char simbolo, String e2) {
        this.estadoO = e1;
        this.estadoD = e2;
        this.simbolo = simbolo;
    }

    /**
     * Devuelve el estado de origen de la transicion
     * @return
     */
    public String getEstadoO() {
        return estadoO;
    }

    /**
     * Devuelve el estado de destino de la transicion
     * @return
     */
    public String getEstadoD() {
        return estadoD;
    }

    /**
     * Devuelve el simbolo de la transicion
     * @return
     */
    public char getSimbolo() {
        return simbolo;
    }

    /**
     * Permite la representación en texto de la transicion 
     * @return
     */
    @Override
    public String toString() {
        return (" " + this.estadoO + " '" + this.simbolo + "' " + this.estadoD);
    }

    /**
     * Devuvelve el código hash del objeto, usado para ser comparado con otro
     * objeto en colecciones (HashSet, HashMap..)
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.estadoO);
        hash = 97 * hash + Objects.hashCode(this.estadoD);
        hash = 97 * hash + this.simbolo;
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
        final TransicionAFD other = (TransicionAFD) obj;
        if (this.simbolo != other.simbolo) {
            return false;
        }
        if (!Objects.equals(this.estadoO, other.estadoO)) { //Además nos fijamos en los estado origen y destino
            return false;
        }
        if (!Objects.equals(this.estadoD, other.estadoD)) {
            return false;
        }
        return true;
    }

}
