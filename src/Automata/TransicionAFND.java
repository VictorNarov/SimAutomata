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