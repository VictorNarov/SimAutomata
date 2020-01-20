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

/**
 * Interface que implementan los AFD y AFND
 * @author Víctor M. Rodríguez y Fran J. Beltrán
 */
public interface Proceso {
    public abstract boolean esFinal(String estado); // True si estado es un estado final
    public abstract boolean reconocer(String cadena) throws Exception; // True si la cadena es reconocida
    public abstract String toString(); // Muestra las transiciones y estados finales
}
