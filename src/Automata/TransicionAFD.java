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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.estadoO);
        hash = 97 * hash + Objects.hashCode(this.estadoD);
        hash = 97 * hash + this.simbolo;
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
        final TransicionAFD other = (TransicionAFD) obj;
        if (this.simbolo != other.simbolo) {
            return false;
        }
        if (!Objects.equals(this.estadoO, other.estadoO)) {
            return false;
        }
        if (!Objects.equals(this.estadoD, other.estadoD)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
