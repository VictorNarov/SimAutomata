/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transiciones;

import java.util.HashSet;

/**
 *
 * @author usuario
 */
public class TransicionL {
    private int origen;
    private HashSet<Integer> destinos;

    public TransicionL(int origen, HashSet<Integer> destinos) {
        this.origen = origen;
        this.destinos = destinos;
    }

    public int getOrigen() {
        return origen;
    }

    public HashSet<Integer> getDestinos() {
        return destinos;
    }
    
    public String toString()
    {
        return ("f("+origen+", L) -> "+destinos +"\n");
    }
    
}
