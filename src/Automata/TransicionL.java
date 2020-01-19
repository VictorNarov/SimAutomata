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

import java.util.HashSet;
import java.util.Objects;

/**
 * Clase TransicionL Los estados son cadenas de caracteres (String).
 *
 * @author Víctor M. Rodríguez y Fran J. Beltrán
 */
public class TransicionL {

    private String origen;
    private HashSet<String> destinos;

    /**
     * Crea la transición con los parámetros indicados
     *
     * @param origen Estado origen
     * @param destinos Conjunto de estados
     */
    public TransicionL(String origen, HashSet<String> destinos) {
        this.origen = origen;
        this.destinos = destinos;
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
     * Permite la representación en texto de la transicion
     *
     * @return
     */
    @Override
    public String toString() {
        String mensaje = " " + this.origen;

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
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.origen);
        hash = 59 * hash + Objects.hashCode(this.destinos);
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
        final TransicionL other = (TransicionL) obj;
        if (!Objects.equals(this.origen, other.origen)) {
            return false;
        }
        if (!Objects.equals(this.destinos, other.destinos)) {
            return false;
        }
        return true;
    }

}
