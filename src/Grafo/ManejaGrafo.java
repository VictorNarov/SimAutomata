/*
 * Copyright (C) 2020 victo
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
package Grafo;

import Automata.AFD;
import Automata.AFND;
import Automata.TransicionAFD;
import Automata.TransicionAFND;
import Automata.TransicionL;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxStylesheet;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author victo
 */
public class ManejaGrafo {

    private Grafo grafo = new Grafo();
    Object parent;
    mxStylesheet stylesheet = grafo.getStylesheet();
    ArrayList<String> estados = new ArrayList<>();
    ArrayList<Object> objEstados = new ArrayList<>();

    public mxGraphComponent generarAFD(AFD automata, HashSet<String> cjtoEstados) throws Exception {
        objEstados.clear();
        estados.clear();
        grafo = new Grafo();

        estados = new ArrayList<>(cjtoEstados);

        try {
            //Añadir los estados al grafo
            for (String estado : estados) {
                if (automata.getEstadosFinales().contains(estado)) {
                    objEstados.add(grafo.insertVertex(parent, null, estado, 100, 200, 50, 50, "ESTADOFINAL"));
                } else if (automata.getEstadoInicial().equals(estado)) {
                    objEstados.add(grafo.insertVertex(parent, null, estado, 100, 200, 50, 50, "ESTADOINICIAL"));
                } else {
                    objEstados.add(grafo.insertVertex(parent, null, estado, 100, 200, 50, 50, "ESTADO"));
                }
            }

            for (TransicionAFD t : automata.getTransiciones()) {
                grafo.insertEdge(parent, null, "   " + t.getSimbolo(), objEstados.get(estados.indexOf(t.getEstadoO())), objEstados.get(estados.indexOf(t.getEstadoD())), "rounded=1");
            }

            mxHierarchicalLayout layout = new mxHierarchicalLayout(grafo);

            layout.setInterRankCellSpacing(50.0);
            layout.setIntraCellSpacing(50.0);
            layout.setDisableEdgeStyle(false);
            layout.execute(grafo.getDefaultParent());

        } finally {
            grafo.getModel().endUpdate();
        }

        return new mxGraphComponent(grafo);

//        double width = grafo.getGraphBounds().getWidth();
//        double height = grafo.getGraphBounds().getHeight();
//        
//        if(width>487){
//            width = 500;
//        }
//        
//        if(height> 440){
//            height = 440;
//        }
//        grafo.getModel().setGeometry(grafo.getDefaultParent(), new mxGeometry(250-(width)/2, 220-(height)/2,0, 0));


//        double width = grafo.getGraphBounds().getWidth();
//        double height = grafo.getGraphBounds().getHeight();
//
//        if (width > 560) {
//            width = 560;
//        }
//
//        if (height > 450) {
//            height = 450;
//        }
//        grafo.getModel().setGeometry(grafo.getDefaultParent(), new mxGeometry(280 - (width) / 2, 225 - (height) / 2, 0, 0));
    }

    public mxGraphComponent generarAFND(AFND automata, HashSet<String> cjtoEstados) throws Exception {
        objEstados.clear();
        estados.clear();
        grafo = new Grafo();
        estados = new ArrayList<>(cjtoEstados);
        try {

            //Añadir los estados al grafo
            for (String estado : estados) {
                if (automata.getEstadosFinales().contains(estado)) {
                    objEstados.add(grafo.insertVertex(parent, null, estado, 100, 200, 50, 50, "ESTADOFINAL"));
                } else if (automata.getEstadoInicial().equals(estado)) {
                    objEstados.add(grafo.insertVertex(parent, null, estado, 100, 200, 50, 50, "ESTADOINICIAL"));
                } else {
                    objEstados.add(grafo.insertVertex(parent, null, estado, 100, 200, 50, 50, "ESTADO"));
                }
            }
            //Añadimos las transiciones que consumen simbolo
            if (!automata.getTransiciones().isEmpty()) {
                for (TransicionAFND t : automata.getTransiciones()) {
                    for (String estadoDestino : t.getDestinos()) {
                        grafo.insertEdge(parent, null, t.getSimbolo(), objEstados.get(estados.indexOf(t.getOrigen())), objEstados.get(estados.indexOf(estadoDestino)), "rounded=1");
                    }
                }
            }

            //Añadimos las transiciones lambda
            if (!automata.getTransicionesL().isEmpty()) {
                for (TransicionL tl : automata.getTransicionesL()) {    //Por cada transicion lambda
                    for (String estadoDestino : tl.getDestinos()) //y por cada destino de esa T-L
                    {
                        grafo.insertEdge(parent, null, "L", objEstados.get(estados.indexOf(tl.getOrigen())), objEstados.get(estados.indexOf(estadoDestino)), "rounded=1");
                    }
                }
            }

            mxHierarchicalLayout layout = new mxHierarchicalLayout(grafo);

            layout.setInterRankCellSpacing(50.0);
            layout.setIntraCellSpacing(50.0);
            layout.setDisableEdgeStyle(false);
            layout.execute(grafo.getDefaultParent());

        } finally {
            grafo.getModel().endUpdate();
        }

        return new mxGraphComponent(grafo);
    }
    
    public mxGraphComponent simularAFD(AFD automata, HashSet<String> cjtoEstados, String estadoActivo)
    {
        objEstados.clear();
        estados.clear();
        grafo = new Grafo();

        estados = new ArrayList<>(cjtoEstados);

        try {
            //Añadir los estados al grafo
            for (String estado : estados) {
                if (automata.getEstadosFinales().contains(estado)) {
                    objEstados.add(grafo.insertVertex(parent, null, estado, 100, 200, 50, 50, "ESTADOFINAL"));
                } else if (automata.getEstadoInicial().equals(estado)) {
                    objEstados.add(grafo.insertVertex(parent, null, estado, 100, 200, 50, 50, "ESTADOINICIAL"));
                } else {
                    objEstados.add(grafo.insertVertex(parent, null, estado, 100, 200, 50, 50, "ESTADO"));
                }
            }
        //PINTAR EL ESTADO ACTIVO
        grafo.setCellStyles(mxConstants.STYLE_FILLCOLOR, "green", new Object[]{objEstados.get(estados.indexOf(estadoActivo))}); 

 
            for (TransicionAFD t : automata.getTransiciones()) {
                grafo.insertEdge(parent, null, "   " + t.getSimbolo(), objEstados.get(estados.indexOf(t.getEstadoO())), objEstados.get(estados.indexOf(t.getEstadoD())), "rounded=1");
            }

            mxHierarchicalLayout layout = new mxHierarchicalLayout(grafo);

            layout.setInterRankCellSpacing(50.0);
            layout.setIntraCellSpacing(50.0);
            layout.setDisableEdgeStyle(false);
            layout.execute(grafo.getDefaultParent());

        } finally {
            grafo.getModel().endUpdate();
        }

            
        return new mxGraphComponent(grafo);
    }

    
        public mxGraphComponent simularAFND(AFND automata, HashSet<String> cjtoEstados, HashSet<String> estadosActivos){
        objEstados.clear();
        estados.clear();
        grafo = new Grafo();
        estados = new ArrayList<>(cjtoEstados);
        try {

            //Añadir los estados al grafo
            for (String estado : estados) {
                if (automata.getEstadosFinales().contains(estado)) {
                    objEstados.add(grafo.insertVertex(parent, null, estado, 100, 200, 50, 50, "ESTADOFINAL"));
                } else if (automata.getEstadoInicial().equals(estado)) {
                    objEstados.add(grafo.insertVertex(parent, null, estado, 100, 200, 50, 50, "ESTADOINICIAL"));
                } else {
                    objEstados.add(grafo.insertVertex(parent, null, estado, 100, 200, 50, 50, "ESTADO"));
                }
            }
            
            //PINTAR EL ESTADO ACTIVO
            for(String estadoActivo : estadosActivos)
                grafo.setCellStyles(mxConstants.STYLE_FILLCOLOR, "green", new Object[]{objEstados.get(estados.indexOf(estadoActivo))}); 

            //Añadimos las transiciones que consumen simbolo
            if (!automata.getTransiciones().isEmpty()) {
                for (TransicionAFND t : automata.getTransiciones()) {
                    for (String estadoDestino : t.getDestinos()) {
                        grafo.insertEdge(parent, null, t.getSimbolo(), objEstados.get(estados.indexOf(t.getOrigen())), objEstados.get(estados.indexOf(estadoDestino)), "rounded=1");
                    }
                }
            }

            //Añadimos las transiciones lambda
            if (!automata.getTransicionesL().isEmpty()) {
                for (TransicionL tl : automata.getTransicionesL()) {    //Por cada transicion lambda
                    for (String estadoDestino : tl.getDestinos()) //y por cada destino de esa T-L
                    {
                        grafo.insertEdge(parent, null, "L", objEstados.get(estados.indexOf(tl.getOrigen())), objEstados.get(estados.indexOf(estadoDestino)), "rounded=1");
                    }
                }
            }

            mxHierarchicalLayout layout = new mxHierarchicalLayout(grafo);

            layout.setInterRankCellSpacing(50.0);
            layout.setIntraCellSpacing(50.0);
            layout.setDisableEdgeStyle(false);
            layout.execute(grafo.getDefaultParent());

        } finally {
            grafo.getModel().endUpdate();
        }

        return new mxGraphComponent(grafo);
    }
    
}
