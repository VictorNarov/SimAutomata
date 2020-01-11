/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automata;

import java.util.HashSet;

/**
 *
 * @author usuario
 */
public class TransicionL {
    private String origen;
    private HashSet<String> destinos;

    public TransicionL(String origen, HashSet<String> destinos) {
        this.origen = origen;
        this.destinos = destinos;
    }

    public String getOrigen() {
        return origen;
    }

    public HashSet<String> getDestinos() {
        return destinos;
    }
    
    public String toString()
    {
        return ("f("+origen+", L) -> "+destinos +"\n");
    }
    
}
