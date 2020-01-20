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
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxStylesheet;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Clase ManejaGrafo. Se encarga de facilitar el uso del grafo.
 *
 * @author Víctor M. Rodríguez y Fran J. Beltrán
 */
public class ManejaGrafo {

    private Grafo grafo = new Grafo();
    Object parent;
    mxStylesheet stylesheet = grafo.getStylesheet();
    ArrayList<String> estados = new ArrayList<>();
    ArrayList<Object> objEstados = new ArrayList<>();

    /**
     * Genera la representación grafica del AFD
     *
     * @param automata Automata pasado por parametro
     * @param cjtoEstados Conjunto de estados
     * @return objeto mxGraphComponent para su representación gráfica
     * @throws Exception
     */
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
            //INSERTAR TRANSICIONES
            for (TransicionAFD t : automata.getTransiciones()) {
                grafo.insertEdge(parent, null, "   " + t.getSimbolo(), objEstados.get(estados.indexOf(t.getEstadoO())), objEstados.get(estados.indexOf(t.getEstadoD())), "rounded=1");
            }
            
            //AJUSTES ESTÉTICOS EN EL GRAFO
            mxHierarchicalLayout layout = new mxHierarchicalLayout(grafo);
            layout.setInterRankCellSpacing(50.0);
            layout.setIntraCellSpacing(50.0);
            layout.setDisableEdgeStyle(false);
            layout.execute(grafo.getDefaultParent());

        } finally {
            grafo.getModel().endUpdate();
        }

        //AJUSTAR Y CENTRAR EN LA VENTANA
        double width = grafo.getGraphBounds().getWidth();
        double height = grafo.getGraphBounds().getHeight();

        if (width > 560) {
            width = 500;
        }

        if (height > 460) {
            height = 440;
        }
        grafo.getModel().setGeometry(grafo.getDefaultParent(), new mxGeometry(250 - (width) / 2, 220 - (height) / 2, 0, 0));

        return new mxGraphComponent(grafo);

    }

    /**
     * Genera la representación gráfica del AFND
     *
     * @param automata Automata pasado por parametro
     * @param cjtoEstados Conjunto de estados
     * @return objeto mxGraphComponent para su representación gráfica
     * @throws Exception
     */
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

            //AJUSTES ESTÉTICOS EN EL GRAFO
            mxHierarchicalLayout layout = new mxHierarchicalLayout(grafo);
            layout.setInterRankCellSpacing(50.0);
            layout.setIntraCellSpacing(50.0);
            layout.setDisableEdgeStyle(false);
            layout.execute(grafo.getDefaultParent());

        } finally {
            grafo.getModel().endUpdate();
        }
        //AJUSTAR Y CENTRAR EN LA VENTANA
        double width = grafo.getGraphBounds().getWidth();
        double height = grafo.getGraphBounds().getHeight();

        if (width > 560) {
            width = 500;
        }

        if (height > 460) {
            height = 440;
        }
        grafo.getModel().setGeometry(grafo.getDefaultParent(), new mxGeometry(250 - (width) / 2, 220 - (height) / 2, 0, 0));
        return new mxGraphComponent(grafo);
    }

    /**
     * Genera la representación gráfica del AFD dada una situación de estados
     * activos
     *
     * @param automata
     * @param cjtoEstados
     * @param estadoActivo
     * @return objeto mxGraphComponent para su representación gráfica
     * @throws java.lang.Exception si algo sale mal, se lanzará
     */
    public mxGraphComponent simularAFD(AFD automata, HashSet<String> cjtoEstados, String estadoActivo) throws Exception {
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
            try {
                grafo.setCellStyles(mxConstants.STYLE_FILLCOLOR, "green", new Object[]{objEstados.get(estados.indexOf(estadoActivo))});
            } catch (Exception ex) {
                throw new Exception("Error: caracter no reconocido!");
            }

            //INSERTAR LAS TRANSICIONES
            for (TransicionAFD t : automata.getTransiciones()) {
                grafo.insertEdge(parent, null, "   " + t.getSimbolo(), objEstados.get(estados.indexOf(t.getEstadoO())), objEstados.get(estados.indexOf(t.getEstadoD())), "rounded=1");
            }

            //AJUSTES ESTÉTICOS DEL GRAFO
            mxHierarchicalLayout layout = new mxHierarchicalLayout(grafo);
            layout.setInterRankCellSpacing(50.0);
            layout.setIntraCellSpacing(50.0);
            layout.setDisableEdgeStyle(false);
            layout.execute(grafo.getDefaultParent());

        } finally {
            grafo.getModel().endUpdate();
        }
        //AJUSTAR Y CENTRAR EN LA VENTANA
        double width = grafo.getGraphBounds().getWidth();
        double height = grafo.getGraphBounds().getHeight();

        if (width > 560) {
            width = 500;
        }

        if (height > 460) {
            height = 440;
        }
        grafo.getModel().setGeometry(grafo.getDefaultParent(), new mxGeometry(250 - (width) / 2, 220 - (height) / 2, 0, 0));
        return new mxGraphComponent(grafo);
    }

    /**
     * Genera la representación gráfica del AFND dada una situación de estados
     * activos
     *
     * @param automata
     * @param cjtoEstados
     * @param estadosActivos
     * @return objeto mxGraphComponent para su representación gráfica
     */
    public mxGraphComponent simularAFND(AFND automata, HashSet<String> cjtoEstados, HashSet<String> estadosActivos) {
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
            for (String estadoActivo : estadosActivos) {
                grafo.setCellStyles(mxConstants.STYLE_FILLCOLOR, "green", new Object[]{objEstados.get(estados.indexOf(estadoActivo))});
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
            
            //AJUSTES ESTÉTICOS EN EL GRAFO
            mxHierarchicalLayout layout = new mxHierarchicalLayout(grafo);
            layout.setInterRankCellSpacing(50.0);
            layout.setIntraCellSpacing(50.0);
            layout.setDisableEdgeStyle(false);
            layout.execute(grafo.getDefaultParent());

        } finally {
            grafo.getModel().endUpdate();
        }
        //AJUSTAR Y CENTRAR EN LA VENTANA
        double width = grafo.getGraphBounds().getWidth();
        double height = grafo.getGraphBounds().getHeight();

        if (width > 560) {
            width = 500;
        }

        if (height > 460) {
            height = 440;
        }
        grafo.getModel().setGeometry(grafo.getDefaultParent(), new mxGeometry(250 - (width) / 2, 220 - (height) / 2, 0, 0));
        return new mxGraphComponent(grafo);
    }

}
