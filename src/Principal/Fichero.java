package Principal;

import Transiciones.TransicionAFD;
import Transiciones.TransicionAFND;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author VSPC-Infernov4
 */
public class Fichero {
    private final Path ruta;
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private HashSet<Integer> estado_inicial, estados_finales, transiciones;
    private HashSet<TransicionAFD> transiciones_AFD;
    private HashSet<TransicionAFND> transiciones_AFND;
    
    public Fichero(String nombreFichero) {
        this.estado_inicial = new HashSet();
        this.estados_finales = new HashSet();
        this.transiciones = new HashSet();
        this.transiciones_AFD = new HashSet();
        this.transiciones_AFND = new HashSet();
        this.ruta = Paths.get(nombreFichero);
    }
    
    public void procesarAFD() throws IOException {
        try ( Scanner scanner = new Scanner(ruta, ENCODING.name()) ) {
            // Saltamos los estados
            scanner.nextLine();
            
            // Leemos el estado inicial
            String linea = scanner.nextLine();
            String valores[] = linea.split(" ");
            System.out.println(valores[1].charAt(1));
            estado_inicial.add(Character.getNumericValue(valores[1].charAt(1)));
            
            // Leemos los estados finales
            linea = scanner.nextLine();
            valores = linea.split(" ");
            
            for (int i = 1; i < valores.length; i++) {
                System.out.println(Character.getNumericValue(valores[i].charAt(i)));
                estados_finales.add(Character.getNumericValue(valores[i].charAt(i)));
            }
            
            linea = scanner.nextLine();
            linea = scanner.nextLine();
            
            while( scanner.hasNextLine() && !linea.equals("FIN") ) {
                transiciones_AFD.add(procesarLineaAFD(linea));
                linea = scanner.nextLine();
            }
        }
    }
    
    public TransicionAFD procesarLineaAFD(String contenido) {
        String valores[] = contenido.split(" ");
        TransicionAFD temp = new TransicionAFD(Character.getNumericValue(valores[1].charAt(1)), valores[2].charAt(1), Character.getNumericValue(valores[3].charAt(1)));
        
        System.out.println(temp);
        
        return temp;
    }
    
    public static void main(String[] args) throws IOException {
        Fichero temp = new Fichero("D://1.txt");
        
        temp.procesarAFD();
    }
}
