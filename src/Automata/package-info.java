/*
 * Copyright (C) 2020 victo
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
/**
 * El paquete Autómata permite la creación de Autómatas Finitos Deterministas (AFD.java) haciendo uso de las TransicionesAFD 
 * para poder procesar cadenas de entrada mediante el método reconocer(String cadena). 
 * Asimismo, en el paquete Autómata, encontramos la clase AFND.java para la creación de los no deterministas,
 * haciendo uso de las TransicionesAFND y TransicionesL (lambda).
 * La diferencia entre las Transiciones AFD y AFND radica en que estas últimas permiten al autómata 
 * transitar de un estado origen a un conjunto de estados  destino, y no solamente a un estado, como nos restringe el AFD.
 */
package Automata;


