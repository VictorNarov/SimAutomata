package Automata;

import java.util.HashSet;

/**
 * Clase Autómata Finito No Determinista. Los estados son cadenas de caracteres
 * (String). Los símbolos de entrada son caracteres (Character) así como la
 * palabra vacía lambda (L). No resulta necesario implementar un conjunto de
 * estados del autómata para su funcionamiento. Tan solo necesita un conjunto de
 * símbolos de entrada y de transiciones de estados, así como un estado inicial
 * y un conjunto de estados finales.
 *
 * @author Víctor M. Rodríguez y Fran J. Beltrán
 */
public class AFND implements Cloneable, Proceso {

    private HashSet<String> estadosFinales;
    private String estadoInicial = "";
    private HashSet<TransicionAFND> transiciones;
    private HashSet<TransicionL> transicionesL;

    /**
     * Constructor
     */
    public AFND() {
        this.estadosFinales = new HashSet();
        this.transiciones = new HashSet();
        this.transicionesL = new HashSet();
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
     * Establece el conjunto de transiciones pasado por parámetro en el autómata
     *
     * @param transiciones
     */
    public void setTransiciones(HashSet<TransicionAFND> transiciones) {
        this.transiciones = transiciones;
    }

    /**
     * Establece el conjunto de lambda-transiciones pasado por parámetro en el
     * autómata
     *
     * @param transicionesL
     */
    public void setTransicionesL(HashSet<TransicionL> transicionesL) {
        this.transicionesL = transicionesL;
    }

    /**
     * Agrega la transición formada en los parámetros al autómata
     *
     * @param e1 Estado origen
     * @param simbolo Símbolo de entrada
     * @param e2 Estado destino
     */
    public void agregarTransicion(String e1, char simbolo, HashSet e2) {
        this.transiciones.add(new TransicionAFND(e1, simbolo, e2));
    }

    /**
     * Agrega la transición pasada por parámetro al autómata
     *
     * @param trans
     */
    public void agregarTransicion(TransicionAFND trans) {
        this.transiciones.add(trans);
    }

    /**
     * Agrega la lambda-transición pasada por parámetro al autómata
     *
     * @param e1 Estado origen
     * @param e2 Estaod destino
     */
    public void agregarTransicionL(String e1, HashSet e2) {
        this.transicionesL.add(new TransicionL(e1, e2));
    }

    /**
     * Agrega la lambda-transición pasada por parámetro al autómata
     *
     * @param trans
     */
    public void agregarTransicionL(TransicionL trans) {
        this.transicionesL.add(trans);
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
     * Obtiene el conjunto de transiciones del autómata
     *
     * @return
     */
    public HashSet<TransicionAFND> getTransiciones() {
        return transiciones;
    }

    /**
     * Obtiene el conjunto de lambda-transiciones del autómata
     *
     * @return
     */
    public HashSet<TransicionL> getTransicionesL() {
        return transicionesL;
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
     * Obtiene el estado destino de la transición pasada por parámetro
     *
     * @param estado Estado origen
     * @param simbolo Símbolo de entrada
     * @return Estado destino
     */
    public HashSet<String> getTransicion(String estado, char simbolo) {
        for (TransicionAFND t : this.transiciones) {
            if (t.getOrigen().equals(estado) && t.getSimbolo() == simbolo) {
                return t.getDestinos();
            }
        }

        return new HashSet<>(); //Si no se encuentra
    }

    /**
     * Devuelve el conjunto de estados destino a los que evoluciona el autómata
     * dado un conjunto de estados origen
     *
     * @param macroestado Conjunto de estados origen
     * @param simbolo Símbolo de entrada
     * @return Conjunto de estados destino
     */
    public HashSet<String> getTransicion(HashSet<String> macroestado, char simbolo) {
        HashSet<String> solucion = new HashSet();

        for (String estado : macroestado) {
            for (String estado2 : this.getTransicion(estado, simbolo)) {
                solucion.add(estado2);
            }
        }

        return solucion;
    }

    /**
     * Obtiene el estado destino al que evoluciona el autómata con una lambda-t
     * desde el estado origen pasado por parámetro
     *
     * @param estado Estado origen
     * @return Estado destino
     */
    public HashSet<String> getTransicionL(String estado) {
        for (TransicionL sol : this.transicionesL) {
            if (sol.getOrigen().equals(estado)) {
                return sol.getDestinos();
            }
        }

        return new HashSet();
    }

    /**
     * Devuelve verdadero si el estado pasado por parámetro es un estado final o
     * de aceptación
     *
     * @param estado
     * @return
     */
    public boolean esFinal(String estado) {
        return this.estadosFinales.contains(estado);
    }

    /**
     * Devuelve verdadero si alguno de los estados pasados por parámetro es un
     * estado final o de aceptación
     *
     * @param macroestado Conjunto de estados a evaluar
     * @return
     */
    public boolean esFinal(HashSet<String> macroestado) {
        for (String estado : macroestado) {
            if (this.esFinal(estado)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Devuelve la lambda-clausura del estado pasado por parámetro Conjunto de
     * estados destino a los que evoluciona el autómata con lambda-transiciones
     * Desde el estado origen pasado por parámetro
     *
     * @param estado Estado origen
     * @return Conjunto de estados destino
     */
    public HashSet<String> L_clausura(String estado) {
        HashSet<String> solucion = new HashSet<>();
        solucion.add(estado);                   //Añadimos el estado actual

        transicionesL.forEach((transicionL) -> { //Recorremos las L-Transiciones
            if (transicionL.getOrigen().equals(estado)) { //con origen ese estado
                transicionL.getDestinos().forEach((estado_destino) -> {      
                    //Y añadimos a la solucion todos los estados de la LC del destino
                    if(!estado_destino.equals(transicionL.getOrigen()))//Evitamos un bucle infinito
                        solucion.addAll(L_clausura(estado_destino));
                });
            }
        });

        return solucion;
    }

    /**
     * Devuelve la lambda-clausura de un conjunto de estados pasados por
     * parámetro Conjunto de estados destino a los que evoluciona el autómata
     * con lambda-transiciones
     *
     * @param estados Conjunto de estados origen
     * @return Conjutno de estados destino
     */
    public HashSet<String> L_clausura(HashSet<String> estados) { //Devuelve el cjto de estados CL(estados)
        HashSet<String> solucion = new HashSet();

        estados.forEach((estado) -> {
            HashSet<String> valores = L_clausura(estado); //Aplicamos la clausura a cada estado del conjunto
            
            for (String valor : valores) {
                solucion.add(valor); //Añadimos cada destino obtenido
            }

        });

        return solucion;
    }

    /**
     * Simula el funcionamiento del autómata Recorre la cadena de entrada y
     * evoluciona el estado actual según lo definido en las transiciones del
     * autómata Si el estado al que evolucion al leer el último símbolo de
     * entrada de la cadena es un estado final o de aceptación, la cadena es
     * reconocida
     *
     * @param cadena Símbolos de entrada a reconocer por el autómata
     * @return verdadero si la cadena es reconocida por el autómata (pertenece a
     * su lenguaje formado)
     * @throws java.lang.Exception Si el autómata no es válid
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
        HashSet<String> estado = new HashSet();
        estado.add(this.getEstadoInicial());
        estado = L_clausura(estado); //Partimos de la clausura del estado inicial

        for (int i = 0; i < simbolo.length; i++) {
            
            estado = getTransicion(estado, simbolo[i]); //Primero evolucionamos consumiendo simbolo
            
            for (String estado_accesible : L_clausura(estado)) {
                estado.add(estado_accesible); //Añadimos la clausura de todos los estados destino
            }
            
            if (estado.isEmpty()) {
                throw new Exception("Error: transicion con caracter '" + simbolo[i] + "' no válida!");
            }

        }

        return esFinal(estado);
    }

    /**
     * Elimina las transiciones que usan el símbolo pasado por parámetro
     *
     * @param s
     */
    public void eliminarSimbolo(char s) {
        for (TransicionAFND t : this.transiciones) {
            if (t.getSimbolo() == s) {
                this.transiciones.remove(t);
            }
        }
    }

    /**
     * Elimina las transiciones que usan el estado pasado por parámetro
     *
     * @param e
     */
    public void eliminarEstado(String e) //
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

    /**
     * Elimina la transición pasada por parámetro del autómata
     *
     * @param t
     */
    public void eliminarTransicion(TransicionAFND t) {
        this.transiciones.remove(t);
    }

    /**
     * Elimina la lambda-transición pasada por parámetro del autómata
     *
     * @param t
     */
    public void eliminarTransicionL(TransicionL t) {
        this.transicionesL.remove(t);
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

        mensaje += "\nESTADOS:";

        for (TransicionAFND t : this.transiciones) {
            estados.add(t.getOrigen());
        }

        for (String e : estados) {
            mensaje += e + "\n";
        }

        mensaje += "\nESTADO INICIAL: " + this.estadoInicial;
        mensaje += "\nESTADOS FINALES: ";
        for (String e : estadosFinales) {
            mensaje += e+" ";
        }
        mensaje += "\nTRANSICIONES:\n";
        for (TransicionAFND t : this.transiciones) {
            mensaje += t+"\n";
        }

        mensaje += "\nTRANSICIONES_L:\n";
        for (TransicionL t : this.transicionesL) {
            mensaje += t+"\n";
        }

        return mensaje;
    }

    /**
     * Devuelve una copia del autómata Se crean nuevos objetos, no se clonan las
     * referencias
     *
     * @return
     */
    @Override
    public Object clone() {
        AFND copia = null;
        try {
            copia = (AFND) super.clone(); //Hace una copia binaria de los objetos
        } catch (CloneNotSupportedException ex) {
            System.out.println("Clone no soportado");
        }
        //Como el clone de HashSet hace solo una copia superficial, tenemos que copia a mano los elementos
        copia.estadosFinales = new HashSet<String>();
        for (String estado : this.estadosFinales) {
            copia.estadosFinales.add(estado);
        }

        copia.transiciones = new HashSet<TransicionAFND>();
        for (TransicionAFND t : this.transiciones) {
            copia.transiciones.add(t);
        }

        copia.transicionesL = new HashSet<TransicionL>();
        for (TransicionL tl : this.transicionesL) {
            copia.transicionesL.add(tl);
        }

        return copia;
    }
}
