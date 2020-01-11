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
import Automata.TransicionAFD;
import Principal.Proceso;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author usuario
 */
public class AFD implements Cloneable, Proceso {
    public HashSet<String> estadosFinales;
    private HashSet<TransicionAFD> transiciones;
    
    public AFD() {
        this.transiciones = new HashSet();
        this.estadosFinales = new HashSet();
    }
    
    public void agregarTransicion(String e1, char simbolo, String e2) {
        this.transiciones.add(new TransicionAFD(e1, simbolo, e2));
    }
    
    public void agregarTransicion(TransicionAFD trans) {
        this.transiciones.add(trans);
    }
    
    public String getTransicion(String estado, char simbolo) {
        for(TransicionAFD t : this.transiciones) {
            if(t.getEstadoO().equals(estado) && t.getSimbolo() == simbolo)
                return t.getEstadoD();
        }
        
        return "";
    }
    
    @Override
    public boolean esFinal(String estado) {
        return this.estadosFinales.contains(estado);
    }
    
    @Override
    public boolean reconocer(String cadena) {
        char[] simbolo = cadena.toCharArray();
        String estado="q0";
        
        for (int i = 0; i < simbolo.length; i++) {
            estado = getTransicion(estado,simbolo[i]);
        }
        
        return esFinal(estado);
    }
    
    @Override
    public String toString() {
        String mensaje="";
        HashSet<String> estados = new HashSet();
        
        mensaje+="ESTADOS\n";
        
        for(TransicionAFD t : this.transiciones)
        {
            estados.add(t.getEstadoO());
            estados.add(t.getEstadoD());
        }
        
        for(String e : estados)
        {
            mensaje += e + "\n";
        }
        
        mensaje+="\nTRANSICIONES:\n";
        for(TransicionAFD t : this.transiciones) {
            mensaje += t + "\n";
        }
        
        return mensaje;
    }
    
    public static AFD pedir() {
        return null;
    }
    
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
        
        if(automata.reconocer("101"))
            System.out.println("RECONOCIDO");
        else
            System.out.println("NO RECONOCIDO");
    }
}
