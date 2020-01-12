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
package Principal;

import Automata.AFD;
import Automata.AFND;
import Automata.TransicionAFD;
import Automata.TransicionAFND;
import Automata.TransicionL;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author FranBeltrán
 */
public class Fichero {

    private final Path ruta;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private HashSet<String> estado_inicial, estados_finales;
    private HashSet<TransicionAFD> transiciones_AFD;
    private HashSet<TransicionAFND> transiciones_AFND;
    private HashSet<TransicionL> transiciones_L;
    private HashSet<String> conjuntoEstados = new HashSet();
    private HashSet<String> conjuntoSimbolos = new HashSet();

    public Fichero(String nombreFichero) {
        this.estado_inicial = new HashSet();
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

            estado_inicial.add(valores[1]);

            // Leemos los estados finales
            linea = scanner.nextLine();
            valores = linea.split(" ");

            for (int i = 1; i < valores.length; i++) {
                estados_finales.add(valores[i]);
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

            estado_inicial.add(valores[1]);

            // Leemos los estados finales
            linea = scanner.nextLine();
            valores = linea.split(" ");

            for (int i = 1; i < valores.length; i++) {
                estados_finales.add(valores[i]);
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

    public TransicionAFD procesarLineaAFD(String contenido) {
        String valores[] = contenido.split(" ");

        this.conjuntoSimbolos.add(valores[2]);
        TransicionAFD temp = new TransicionAFD(valores[1], valores[2].charAt(1), valores[3]);

        return temp;
    }

    public TransicionAFND procesarLineaAFND(String contenido) {
        String valores[] = contenido.split(" ");

        this.conjuntoSimbolos.add(valores[2]);

        HashSet<String> destinos = new HashSet();

        for (int i = 3; i < valores.length; i++) {
            destinos.add(valores[i]);
        }

        TransicionAFND temp = new TransicionAFND(valores[1], destinos, valores[2].charAt(1));

        return temp;
    }

    public TransicionL procesarLineaL(String contenido) {
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

        return temp;
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

        temp.procesarAFND();
        System.out.println(temp.generarAutomataAFND());

//        temp2.procesarAFD();
//        System.out.println(temp2.generarAutomataAFD());
    }
}
