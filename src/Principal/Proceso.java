/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

/**
 *
 * @author usuario
 */
public interface Proceso {
    public abstract boolean esFinal(String estado); // True si estado es un estado final
    public abstract boolean reconocer(String cadena); // True si la cadena es reconocida
    public abstract String toString(); // Muestra las transiciones y estados finales
}
