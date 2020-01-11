package Principal;
import Transiciones.TransicionAFD;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author usuario
 */
public class AFD implements Cloneable, Proceso {
    public ArrayList<Integer> estadosFinales;
    private ArrayList<TransicionAFD> transiciones;
    
    public AFD() {
        this.transiciones = new ArrayList();
        this.estadosFinales = new ArrayList();
    }
    
    public void agregarTransicion(int e1, char simbolo, int e2) {
        this.transiciones.add(new TransicionAFD(e1, simbolo, e2));
    }
    
    public int transicion(int estado, char simbolo) {
        for(TransicionAFD t : this.transiciones) {
            if(t.getEstadoO() == estado && t.getSimbolo() == simbolo)
                return t.getEstadoD();
        }
        
        return -1;
    }
    
    @Override
    public boolean esFinal(int estado) {
        return this.estadosFinales.contains(estado);
    }
    
    @Override
    public boolean reconocer(String cadena) {
        char[] simbolo = cadena.toCharArray();
        int estado=0;
        
        for (int i = 0; i < simbolo.length; i++) {
            estado = transicion(estado,simbolo[i]);
        }
        
        return esFinal(estado);
    }
    
    @Override
    public String toString() {
        String mensaje="";
        HashSet<Integer> estados = new HashSet();
        
        mensaje+="ESTADOS\n";
        
        for(TransicionAFD t : this.transiciones)
        {
            estados.add(t.getEstadoO());
            estados.add(t.getEstadoD());
        }
        
        for(int e : estados)
        {
            mensaje += "q" + e + "\n";
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
        
        automata.estadosFinales.add(1);
        
        automata.agregarTransicion(0, '1', 0);
        automata.agregarTransicion(0, '0', 2);
        automata.agregarTransicion(2, '0', 2);
        automata.agregarTransicion(2, '1', 1);
        automata.agregarTransicion(1, '0', 1);
        automata.agregarTransicion(1, '1', 1);
        
        System.out.println(automata);
        
        if(automata.reconocer("101"))
            System.out.println("RECONOCIDO");
        else
            System.out.println("NO RECONOCIDO");
    }
}
