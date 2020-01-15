package Principal;

import Automata.AFD;
import Automata.AFND;
import Automata.TransicionAFD;
import Automata.TransicionAFND;
import Automata.TransicionL;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FranBeltrán
 */
public class Fichero {

    private Path ruta, salida;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private String estado_inicial;
    private HashSet<String> estados_finales;
    private HashSet<TransicionAFD> transiciones_AFD;
    private HashSet<TransicionAFND> transiciones_AFND;
    private HashSet<TransicionL> transiciones_L;
    private HashSet<String> conjuntoEstados = new HashSet();
    private HashSet<String> conjuntoSimbolos = new HashSet();

    public Fichero(String nombreFichero) {
        this.estados_finales = new HashSet();
        this.transiciones_AFD = new HashSet();
        this.transiciones_AFND = new HashSet();
        this.transiciones_L = new HashSet();
        this.conjuntoSimbolos = new HashSet();
        this.conjuntoEstados = new HashSet();
        this.ruta = Paths.get(nombreFichero);
    }

    public void procesarAFD() throws IOException {
        try ( Scanner scanner = new Scanner(ruta, ENCODING.name())) {
            // Saltamos los estados
            String linea = scanner.nextLine();
            String valores[] = linea.split(" ");

            for (int i = 1; i < valores.length; i++) {
                conjuntoEstados.add(valores[i]);
            }

            // Leemos el estado inicial
            linea = scanner.nextLine();
            valores = linea.split(" ");

            estado_inicial = valores[1];
            System.out.println("ESTADO INICIAL LEIDO: " + valores[1]);

            // Leemos los estados finales
            linea = scanner.nextLine();
            valores = linea.split(" ");

            for (int i = 1; i < valores.length; i++) {
                estados_finales.add(valores[i]);
                System.out.println("ESTADO FINAL LEIDO: " + valores[i]);
            }

            linea = scanner.nextLine();
            linea = scanner.nextLine();

            while (scanner.hasNextLine() && !linea.equals("FIN")) {
                transiciones_AFD.add(procesarLineaAFD(linea));
                linea = scanner.nextLine();
            }
        }
    }

    public void procesarAFND() throws IOException {
        try ( Scanner scanner = new Scanner(ruta, ENCODING.name())) {
            // Saltamos los estados
            String linea = scanner.nextLine();
            String valores[] = linea.split(" ");

            for (int i = 1; i < valores.length; i++) {
                conjuntoEstados.add(valores[i]);
            }

            // Leemos el estado inicial
            linea = scanner.nextLine();
            valores = linea.split(" ");

            estado_inicial = valores[1];
            System.out.println("ESTADO INICIAL LEIDO: " + valores[1]);

            // Leemos los estados finales
            linea = scanner.nextLine();
            valores = linea.split(" ");

            for (int i = 1; i < valores.length; i++) {
                estados_finales.add(valores[i]);
                System.out.println("ESTADO FINAL LEIDO: " + valores[i]);
            }

            linea = scanner.nextLine();
            linea = scanner.nextLine();

            while (scanner.hasNextLine() && !linea.equals("TRANSICIONES_L:")) {
                this.transiciones_AFND.add(procesarLineaAFND(linea));
                linea = scanner.nextLine();
            }

            linea = scanner.nextLine();

            while (scanner.hasNextLine() && !linea.equals("FIN")) {
                transiciones_L.add(procesarLineaL(linea));
                linea = scanner.nextLine();
            }
        }
    }

    private TransicionAFD procesarLineaAFD(String contenido) {
        String valores[] = contenido.split(" ");

        this.conjuntoSimbolos.add(valores[2].split("'")[1]); //Separar las comillas simples

        TransicionAFD temp = new TransicionAFD(valores[1], valores[2].charAt(1), valores[3]);

        return temp;
    }

    private TransicionAFND procesarLineaAFND(String contenido) {
        String valores[] = contenido.split(" ");

        this.conjuntoSimbolos.add(valores[2].split("'")[1]); //Separar las comillas simples

        HashSet<String> destinos = new HashSet();

        for (int i = 3; i < valores.length; i++) {
            destinos.add(valores[i]);
        }

        TransicionAFND temp = new TransicionAFND(valores[1], valores[2].charAt(1), destinos);
        System.out.println("LEIDO DE FICHERO T: " + new TransicionAFND(valores[1], valores[2].charAt(1), destinos));
        return temp;
    }

    private TransicionL procesarLineaL(String contenido) {
        String valores[] = contenido.split(" ");

        this.conjuntoEstados.add(valores[1]);

        HashSet<String> destinos = new HashSet();

        for (int i = 2; i < valores.length; i++) {
            destinos.add(valores[i]);
        }

        TransicionL temp = new TransicionL(valores[1], destinos);

        return temp;

    }

    public AFD generarAutomataAFD() {
        AFD temp = new AFD();

        for (TransicionAFD valor : this.transiciones_AFD) {
            temp.agregarTransicion(valor);
        }

        temp.setEstadoInicial(estado_inicial);
        temp.setEstadosFinales(estados_finales);

        return temp;
    }

    public AFND generarAutomataAFND() {
        AFND temp = new AFND();

        for (TransicionAFND valor : this.transiciones_AFND) {
            temp.agregarTransicion(valor);
        }

        for (TransicionL valor : this.transiciones_L) {
            temp.agregarTransicionL(valor);
        }

        temp.setEstadoInicial(estado_inicial);
        temp.setEstadosFinales(estados_finales);

        return temp;
    }

    public void generarFichero(AFD automata, String nombreFichero) {
        try {
            this.salida = Paths.get(nombreFichero);
            FileWriter fw = new FileWriter(salida.toFile());
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);
            String mensaje = "ESTADOS:";

            for (String valor : this.conjuntoEstados) {
                mensaje += " " + valor;
            }

            salida.println(mensaje);
            salida.println("INICIAL: " + this.estado_inicial);
            mensaje = "FINALES: ";

            for (String valor : this.estados_finales) {
                mensaje += valor + " ";
            }

            salida.println(mensaje);
            salida.println("TRANSICIONES:");

            for (TransicionAFD valor : this.transiciones_AFD) {
                salida.println(valor);
            }

            salida.println("FIN");
            salida.close();
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generarFichero(AFND automata, String nombreFichero) {
        try {
            this.salida = Paths.get(nombreFichero);
            FileWriter fw = new FileWriter(salida.toFile());
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);
            String mensaje = "ESTADOS:";

            for (String valor : this.conjuntoEstados) {
                mensaje += " " + valor;
            }

            salida.println(mensaje);
            salida.println("INICIAL: " + this.estado_inicial);
            mensaje = "FINALES: ";

            for (String valor : this.estados_finales) {
                mensaje += valor + " ";
            }

            salida.println(mensaje);
            salida.println("TRANSICIONES:");

            for (TransicionAFND valor : this.transiciones_AFND) {
                salida.println(valor);
            }
            
            salida.println("TRANSICIONES_L:");
            
            for (TransicionL valor: this.transiciones_L) {
                salida.println(valor);
            }

            salida.println("FIN");
            salida.close();
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HashSet<String> getConjuntoEstados() {
        return conjuntoEstados;
    }

    public HashSet<String> getConjuntoSimbolos() {
        return conjuntoSimbolos;
    }

    public static void main(String[] args) throws IOException {
        Fichero temp = new Fichero("D://2.txt");
        Fichero temp2 = new Fichero("D://1.txt");

        temp2.procesarAFND();
        temp2.generarFichero(temp.generarAutomataAFND(), "D://pruebaAFND.txt");
        
        temp.procesarAFD();
        temp.generarFichero(temp2.generarAutomataAFD(), "D://pruebaAFD.txt");
    }
}
