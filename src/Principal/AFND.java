package Principal;

import Transiciones.TransicionAFND;
import Transiciones.TransicionL;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author usuario
 */
public class AFND {

    private HashSet<Integer> estadosFinales;
    private HashSet<TransicionAFND> transiciones;
    private HashSet<TransicionL> transicionesL;

    public AFND() {
        this.estadosFinales = new HashSet();
        this.transiciones = new HashSet();
        this.transicionesL = new HashSet();
    }

    public void setEstadosFinales(HashSet<Integer> estadosFinales) {
        this.estadosFinales = estadosFinales;
    }

    public void setTransiciones(HashSet<TransicionAFND> transiciones) {
        this.transiciones = transiciones;
    }

    public void setTransicionesL(HashSet<TransicionL> transicionesL) {
        this.transicionesL = transicionesL;
    }

    public void agregarTransicion(int e1, char simbolo, HashSet e2) {
        this.transiciones.add(new TransicionAFND(e1, e2, simbolo));
    }

    public void agregarTransicionL(int e1, HashSet e2) {
        this.transicionesL.add(new TransicionL(e1, e2));
    }

    private HashSet<Integer> transicion(int estado, char simbolo) {
        for (TransicionAFND t : this.transiciones) {
            if (t.getOrigen() == estado && t.getSimbolo() == simbolo) {
                return t.getDestinos();
            }
        }

        return new HashSet<Integer>();
    }

    public HashSet<Integer> transicion(HashSet<Integer> macroestado, char simbolo) {
        ArrayList<Integer> temp = new ArrayList();

        for (int estado : macroestado) {
            for (int estado2 : this.transicion(estado, simbolo)) {
                temp.add(estado2);
            }
        }

        HashSet<Integer> solucion = new HashSet();

        for (int i = 0; i < temp.size(); i++) {
            solucion.add(temp.get(i));
        }

        return solucion;
    }

    public HashSet<Integer> transicionL(int estado) {
        for (TransicionL sol : this.transicionesL) {
            if (sol.getOrigen() == estado) {
                return sol.getDestinos();
            }
        }

        return new HashSet();
    }

    private boolean esFinal(int estado) {
        return this.estadosFinales.contains(estado);
    }

    public boolean esFinal(HashSet<Integer> macroestado) {
        for (int estado : macroestado) {
            if (this.esFinal(estado)) {
                return true;
            }
        }

        return false;
    }

    private HashSet<Integer> L_clausura(int estado) {
        HashSet solucion = new HashSet();
        solucion.add(estado);

        transicionesL.forEach((f) -> {
            if (f.getOrigen() == estado) {
                for (int valor : f.getDestinos()) {
                    solucion.add(valor);
                }
            }
        });

        return solucion;
    }

    private HashSet<Integer> L_clausura(HashSet<Integer> estados) { //Devuelve el cjto de estados CL(estados)
        HashSet<Integer> solucion = new HashSet();

        estados.forEach((estado) -> {
            HashSet<Integer> valores = L_clausura(estado);

            for (int valor : valores) {
                solucion.add(valor);
            }

        });

        return solucion;
//        for(int estado : estados) {
//            solucion.add(estado);
//            while(transicionL() != (new int[] {-1}))
//            {
//                solucion.add(transicionL(solucion.get(i)));
//                i++;
//            }
//                    
//            int[] estados = this.transicionL(estado);
//            
//        }
    }

    public boolean reconocer(String cadena) {
        char[] simbolo = cadena.toCharArray();
        HashSet<Integer> estadoInicial = new HashSet();
        estadoInicial.add(0);
        estadoInicial = L_clausura(estadoInicial);

        for (int i = 0; i < simbolo.length; i++) {

            estadoInicial = transicion(estadoInicial, simbolo[i]);

            for (int estado : L_clausura(estadoInicial)) {
                estadoInicial.add(estado);
            }

        }

        return esFinal(estadoInicial);
    }

    public static void main(String[] args) {
        AFND automata = new AFND();

        HashSet<Integer> estados = new HashSet();
        for (int i = 0; i < 5; i++) {
            estados.add(i);
        }

        automata.agregarTransicion(0, 'a', new HashSet<Integer>() {
            {
                add(1);
            }
        });
        automata.agregarTransicion(0, 'b', new HashSet<Integer>() {
            {
                add(1);
            }
        });

        automata.agregarTransicion(1, 'a', new HashSet<Integer>() {
            {
                add(2);
            }
        });
        automata.agregarTransicion(1, 'b', new HashSet<Integer>() {
            {
                add(1);
            }
        });

        automata.agregarTransicion(2, 'a', new HashSet<Integer>() {
            {
                add(0);
            }
        });
        automata.agregarTransicion(2, 'b', new HashSet<Integer>() {
            {
                add(4);
            }
        });

        automata.agregarTransicion(3, 'a', new HashSet<Integer>() {
            {
                add(4);
            }
        });
        automata.agregarTransicion(3, 'b', new HashSet<Integer>() {
            {
                add(1);
            }
        });

        automata.setEstadosFinales(new HashSet<Integer>() {
            {
                add(4);
            }
        });

        automata.agregarTransicionL(0, new HashSet<Integer>() {
            {
                add(2);
            }
        });
        automata.agregarTransicionL(1, new HashSet<Integer>() {
            {
                add(3);
            }
        });

        if (automata.reconocer("b")) {
            System.out.println("RECONOCIDA");
        } else {
            System.out.println("NO RECONOCIDA");
        }

//            System.out.println(automata.transicionesL);
//            System.out.println(automata.L_clausura(0));
    }
}
