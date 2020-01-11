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
 *
 * @author usuario
 */
public class TransicionL {
    private String origen;
    private HashSet<String> destinos;

    public TransicionL(String origen, HashSet<String> destinos) {
        this.origen = origen;
        this.destinos = destinos;
    }

    public String getOrigen() {
        return origen;
    }

    public HashSet<String> getDestinos() {
        return destinos;
    }
    
    @Override
    public String toString()
    {
        return ("f("+origen+", L) -> "+destinos +"\n");
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.origen);
        hash = 59 * hash + Objects.hashCode(this.destinos);
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
