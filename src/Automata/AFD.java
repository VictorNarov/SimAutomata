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
