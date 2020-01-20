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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Autómata Finito Determinista. Los estados son cadenas de caracteres
 * (String). Los símbolos de entrada son caracteres (Character). No resulta
 * necesario implementar un conjunto de estados del autómata para su
 * funcionamiento. Tan solo necesita un conjunto de símbolos de entrada y de
 * transiciones de estados, así como un estado inicial y un conjunto de estados
 * finales.
 *
 * @author Víctor M. Rodríguez y Fran J. Beltrán
 * 
 */
public class AFD implements Cloneable, Proceso {

    private HashSet<String> estadosFinales;
    private String estadoInicial = "";
    private HashSet<TransicionAFD> transiciones;

    /**
     * Constructor
     */
    public AFD() {
        this.transiciones = new HashSet();
        this.estadosFinales = new HashSet();
    }

    /**
     * Agrega la transición al autómata
     *
     * @param e1 Estado origen
     * @param simbolo Símbolo de entrada
     * @param e2 Estado destino
     */
    public void agregarTransicion(String e1, char simbolo, String e2) {
        this.transiciones.add(new TransicionAFD(e1, simbolo, e2));
    }

    /**
     * Agrega la transición pasada por parámetro al autómata
     *
     * @param trans
     */
    public void agregarTransicion(TransicionAFD trans) {
        this.transiciones.add(trans);
    }

    /**
     * Obtiene el estado destino de una transición del autómata
     *
     * @param estado Estado origen
     * @param simbolo Símbolo de entrada
     * @return Estado destino
     */
    public String getTransicion(String estado, char simbolo) {
        for (TransicionAFD t : this.transiciones) {
            if (t.getEstadoO().equals(estado) && t.getSimbolo() == simbolo) {
                return t.getEstadoD();
            }
        }

        return "";
    }

    /**
     * Obtiene el estado inicial del autómata
     *
     * @return
     */
    public String getEstadoInicial() {
        return estadoInicial;
    }

    /**
     * Establece el estado inicial del autómata
     *
     * @param estadoInicial
     */
    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    /**
     * Establece los estados finales del autómata (1...N)
     *
     * @param estadosFinales
     */
    public void setEstadosFinales(HashSet<String> estadosFinales) {
        this.estadosFinales = estadosFinales;
    }

    /**
     * Obtiene el conjunto de estados finales del autómata
     *
     * @return
     */
    public HashSet<String> getEstadosFinales() {
        return estadosFinales;
    }

    /**
     * Obtiene el conjunto de transiciones del autómata
     *
     * @return
     */
    public HashSet<TransicionAFD> getTransiciones() {
        return transiciones;
    }

    /**
     * Agrega el estado final pasado por parámetro al autómata
     *
     * @param estadoFinal
     */
    public void addEstadoFinal(String estadoFinal) {
        this.estadosFinales.add(estadoFinal);
    }

    /**
     * Devuelve verdadero si el estado final pasado por parámetro pertenece al
     * conjunto de estados finales del autómata
     *
     * @param estado
     * @return
     */
    @Override
    public boolean esFinal(String estado) {
        return this.estadosFinales.contains(estado);
    }

    /**
     * Elimina el símbolo de entrada pasado por parámetro del autómata
     *
     * @param s símbolo de entrada a eliminar
     */
    public void eliminarSimbolo(char s) //Elimina las transiciones que usan ese simbolo
    {
        for (TransicionAFD t : this.transiciones) {
            if (t.getSimbolo() == s) {
                this.transiciones.remove(t);
            }
        }
    }

    /**
     * Elimina el estado pasado por parámetro del autómata
     *
     * @param e Estado a eliminar
     */
    public void eliminarEstado(String e) //Eliminar las transiciones que usan ese estado
    {
        HashSet<TransicionAFD> eliminar = new HashSet();
        for (TransicionAFD t : this.transiciones) {
            if (t.getEstadoO().equals(e) || t.getEstadoD().equals(e)) //Si aparece en el origen o destino de una transicion
            {
                eliminar.add(t);    //La eliminamos
            }
        }
        this.transiciones.removeAll(eliminar);
    }

    /**
     * Elimina la transición pasada por parámetro
     *
     * @param t Transición a eliminar
     */
    public void eliminarTransicion(TransicionAFD t) {
        this.transiciones.remove(t);
    }

    /**
     * Simula el funcionamiento del autómata Recorre la cadena de entrada y
     * evoluciona el estado actual según lo definido en las transiciones del
     * autómata Si el estado al que evolucion al leer el último símbolo de
     * entrada de la cadena es un estado final, la cadena es reconocida
     *
     * @param cadena Símbolos de entrada a reconocer por el autómata
     * @return verdadero si la cadena es reconocida por el autómata (pertenece a
     * su lenguaje formado)
     * @throws java.lang.Exception Si el autómata no es válido
     */
    @Override
    public boolean reconocer(String cadena) throws Exception {
        //CONTROL DE EXCEPCIONES
        if (this.estadoInicial.equals("")) {
            throw new Exception("Error: no ha indicado ningún estado inicial!");
        }
        if (this.getEstadosFinales().isEmpty()) {
            throw new Exception("Error: no ha indicado ningún estado final!");
        }
        
        char[] simbolo = cadena.toCharArray();
        String estado = this.getEstadoInicial();

        for (int i = 0; i < simbolo.length; i++) {
            estado = getTransicion(estado, simbolo[i]);
            if (estado.equals("")) {
                throw new Exception("Error: transicion con caracter '" + simbolo[i] + "' no válida!");
            }
        }

        return esFinal(estado);
    }

    /**
     * Permite la representación en texto del autómata
     *
     * @return
     */
    @Override
    public String toString() {
        String mensaje = "";
        HashSet<String> estados = new HashSet();

        mensaje += "ESTADOS\n";

        for (TransicionAFD t : this.transiciones) {
            estados.add(t.getEstadoO());
            estados.add(t.getEstadoD());
        }

        mensaje += "ESTADO INICIAL: " + this.estadoInicial + "\n";
        mensaje += "ESTADOS FINALES: \n";
        for (String e : estadosFinales) {
            mensaje += e;
        }

        for (String e : estados) {
            mensaje += e + "\n";
        }

        mensaje += "\nTRANSICIONES:\n";
        for (TransicionAFD t : this.transiciones) {
            mensaje += t + "\n";
        }

        return mensaje;
    }

    /**
     * Simula un AFD
     *
     * @param args
     */
    public static void main(String[] args) {
        AFD automata = new AFD();

        automata.estadosFinales.add("q1");

        automata.agregarTransicion("q0", '1', "q0");
        automata.agregarTransicion("q0", '0', "q2");
        automata.agregarTransicion("q2", '0', "q2");
        automata.agregarTransicion("q2", '1', "q1");
        automata.agregarTransicion("q1", '0', "q1");
        automata.agregarTransicion("q1", '1', "q1");

        System.out.println(automata);

        automata.setEstadoInicial("q0");

        try {
            if (automata.reconocer("101")) {
                System.out.println("RECONOCIDO");
            } else {
                System.out.println("NO RECONOCIDO");
            }
        } catch (Exception ex) {
            Logger.getLogger(AFD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Devuelve una copia del autómata. Se crean nuevos objetos, no se clonan
     * las referencias
     *
     * @return
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        AFD copia = null;
        try {
            copia = (AFD) super.clone(); //Hace una copia binaria de los objetos
        } catch (CloneNotSupportedException ex) {
            System.out.println("Clone no soportado");
        }
        //Como el clone de HashSet hace solo una copia superficial, tenemos que copia a mano los elementos
        copia.estadosFinales = new HashSet<>();
        for (String estado : this.estadosFinales) {
            copia.estadosFinales.add(estado);
        }

        copia.transiciones = new HashSet<TransicionAFD>();
        for (TransicionAFD t : this.transiciones) {
            copia.transiciones.add(t);
        }

        return copia;
    }
}
