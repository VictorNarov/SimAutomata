package Automata;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author usuario
 */
public class AFND {

    private HashSet<String> estadosFinales;
    private String estadoInicial;
    private HashSet<TransicionAFND> transiciones;
    private HashSet<TransicionL> transicionesL;

    public AFND() {
        this.estadosFinales = new HashSet();
        this.transiciones = new HashSet();
        this.transicionesL = new HashSet();
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public void setEstadosFinales(HashSet<String> estadosFinales) {
        this.estadosFinales = estadosFinales;
    }

    public void setTransiciones(HashSet<TransicionAFND> transiciones) {
        this.transiciones = transiciones;
    }

    public void setTransicionesL(HashSet<TransicionL> transicionesL) {
        this.transicionesL = transicionesL;
    }

    
    public void agregarTransicion(String e1, char simbolo, HashSet e2) {
        this.transiciones.add(new TransicionAFND(e1, simbolo,e2));
    }

    public void agregarTransicion(TransicionAFND trans) {
        this.transiciones.add(trans);
    }

    public void agregarTransicionL(String e1, HashSet e2) {
        this.transicionesL.add(new TransicionL(e1, e2));
    }

    public void agregarTransicionL(TransicionL trans) {
        this.transicionesL.add(trans);
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public HashSet<TransicionAFND> getTransiciones() {
        return transiciones;
    }

    public HashSet<TransicionL> getTransicionesL() {
        return transicionesL;
    }

    
    public HashSet<String> getEstadosFinales() {
        return estadosFinales;
    }

    
    public HashSet<String> getTransicion(String estado, char simbolo) {
        for (TransicionAFND t : this.transiciones) {
            if (t.getOrigen().equals(estado) && t.getSimbolo() == simbolo) {
                return t.getDestinos();
            }
        }

        return new HashSet<>();
    }

    public HashSet<String> getTransicion(HashSet<String> macroestado, char simbolo) {
        ArrayList<String> temp = new ArrayList();

        for (String estado : macroestado) {
            for (String estado2 : this.getTransicion(estado, simbolo)) {
                temp.add(estado2);
            }
        }

        HashSet<String> solucion = new HashSet();

        for (int i = 0; i < temp.size(); i++) {
            solucion.add(temp.get(i));
        }

        return solucion;
    }

    public HashSet<String> getTransicionL(String estado) {
        for (TransicionL sol : this.transicionesL) {
            if (sol.getOrigen().equals(estado)) {
                return sol.getDestinos();
            }
        }

        return new HashSet();
    }

    private boolean esFinal(String estado) {
        return this.estadosFinales.contains(estado);
    }

    public boolean esFinal(HashSet<String> macroestado) {
        for (String estado : macroestado) {
            if (this.esFinal(estado)) {
                return true;
            }
        }

        return false;
    }

    public HashSet<String> L_clausura(String estado) {
        HashSet<String> solucion = new HashSet<>(); 
        solucion.add(estado);                   //Añadimos el estado actual
        
        transicionesL.forEach((transicionL) -> { //Recorremos las L-Transiciones
                if (transicionL.getOrigen().equals(estado)) { //con origen ese estado
                    transicionL.getDestinos().forEach((estado_destino) -> {
                        //Y añadimos a la solucion todos los estados de la LC del destino
                        solucion.addAll(L_clausura(estado_destino));
                    });
                }
        });

        return solucion;
    }

    public HashSet<String> L_clausura(HashSet<String> estados) { //Devuelve el cjto de estados CL(estados)
        HashSet<String> solucion = new HashSet();

        estados.forEach((estado) -> {
            HashSet<String> valores = L_clausura(estado);

            for (String valor : valores) {
                solucion.add(valor);
            }

        });

        return solucion;
    }

    public boolean reconocer(String cadena) {
        char[] simbolo = cadena.toCharArray();
        HashSet<String> estado = new HashSet();
        estado.add(this.getEstadoInicial());
        estado = L_clausura(estado);

        for (int i = 0; i < simbolo.length; i++) {

            estado = getTransicion(estado, simbolo[i]);

            for (String estado_accesible : L_clausura(estado)) {
                estado.add(estado_accesible);
            }

        }

        return esFinal(estado);
    }
    
    public void eliminarSimbolo(char s) {
        for (TransicionAFND t : this.transiciones) {
            if (t.getSimbolo() == s) {
                this.transiciones.remove(t);
            }
        }
    }

    public void eliminarEstado(String e) //Eliminar las  transiciones que usan ese estado
    {
        //Elimina las transiciones con origen e
        HashSet<TransicionAFND> eliminar = new HashSet();
        for (TransicionAFND t : this.transiciones) {
            if (t.getOrigen().equals(e)) {
                eliminar.add(t); //Eliminar transicion
            }
            //Elimina las ocurrencias en los destinos de la transicion
            HashSet<String> eliminarDestino = new HashSet();
            for (String estado : t.getDestinos()) {
                if (estado.equals(e)) {
                    eliminarDestino.add(e); //Eliminar estado del destino
                }
            }
            t.getDestinos().removeAll(eliminarDestino);
        }
        this.transiciones.removeAll(eliminar);

        //Elimina las transiciones L con origen e
        HashSet<TransicionL> eliminarTL = new HashSet();
        for (TransicionL t : this.transicionesL) {
            if (t.getOrigen().equals(e)) {
                eliminarTL.add(t);
            }

            //Elimina las ocurrencias en los destinos de la transicion L
            HashSet<String> eliminarDestinoL = new HashSet();
            for (String estado : t.getDestinos()) {
                if (estado.equals(e)) {
                    eliminarDestinoL.add(e); //Eliminar estado del destino
                }
            }
            t.getDestinos().removeAll(eliminarDestinoL);
        }
        this.transicionesL.removeAll(eliminarTL);
    }

    public void eliminarTransicion(TransicionAFND t)
    {
        this.transiciones.remove(t);
    }
    
    public void eliminarTransicionL(TransicionL t)
    {
        this.transicionesL.remove(t);
    }
    
    @Override
    public String toString() {
        String mensaje = "";
        HashSet<String> estados = new HashSet();

        mensaje += "\nESTADOS:\n";

        for (TransicionAFND t : this.transiciones) {
            estados.add(t.getOrigen());
        }

        for (String e : estados) {
            mensaje += e + "\n";
        }
        
        mensaje+="ESTADO INICIAL: "+this.estadoInicial+"\n";
        mensaje+="ESTADOS FINALES: \n";
        for(String e : estadosFinales)
        {
            mensaje+=e;
        }
        mensaje += "\nTRANSICIONES:";
        for (TransicionAFND t : this.transiciones) {
            mensaje += t;
        }
        
        mensaje += "\n\nTRANSICIONES_L:";
        for (TransicionL t : this.transicionesL) {
            mensaje += t;
        }

        return mensaje;
    }

//    public static void main(String[] args) {
//        AFND automata = new AFND();
//
//        automata.setEstadoInicial("q0");
//
//        automata.agregarTransicion("q0", 'a', new HashSet<String>() {
//            {
//                add("q1");
//            }
//        });
//        automata.agregarTransicion("q0", 'b', new HashSet<String>() {
//            {
//                add("q1");
//            }
//        });
//
//        automata.agregarTransicion("q1", 'a', new HashSet<String>() {
//            {
//                add("q2");
//            }
//        });
//        automata.agregarTransicion("q1", 'b', new HashSet<String>() {
//            {
//                add("q1");
//            }
//        });
//
//        automata.agregarTransicion("q2", 'a', new HashSet<String>() {
//            {
//                add("q0");
//            }
//        });
//        automata.agregarTransicion("q2", 'b', new HashSet<String>() {
//            {
//                add("q4");
//            }
//        });
//
//        automata.agregarTransicion("q3", 'a', new HashSet<String>() {
//            {
//                add("q4");
//            }
//        });
//        automata.agregarTransicion("q3", 'b', new HashSet<String>() {
//            {
//                add("q1");
//            }
//        });
//
//        automata.setEstadosFinales(new HashSet<String>() {
//            {
//                add("q4");
//            }
//        });
//
//        automata.agregarTransicionL("q0", new HashSet<String>() {
//            {
//                add("q2");
//            }
//        });
//        automata.agregarTransicionL("q1", new HashSet<String>() {
//            {
//                add("q3");
//            }
//        });
//
//        if (automata.reconocer("aa")) {
//            System.out.println("RECONOCIDA");
//        } else {
//            System.out.println("NO RECONOCIDA");
//        }
//    }
}
