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

import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Clase Grafo. Hereda de mxGraph.
 * @author Víctor M. Rodríguez y Fran J. Beltrán
 */
public class Grafo extends mxGraph {

    /**
     * Crea el objeto grafo, que hereda de mxGraph y establece su configuración
     * estética
     */
    public Grafo() {
        super();

        Hashtable<String, Object> estiloEstado = new Hashtable<>();
        estiloEstado.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        estiloEstado.put(mxConstants.STYLE_FONTSIZE, 20);

        Hashtable<String, Object> estiloEFinal = new Hashtable<String, Object>();
        estiloEFinal.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_DOUBLE_ELLIPSE);
        estiloEFinal.put(mxConstants.STYLE_FONTSIZE, 20);
        estiloEFinal.put(mxConstants.STYLE_FILLCOLOR, "black");

        Hashtable<String, Object> estiloEInicial = new Hashtable<String, Object>();
        estiloEInicial.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_DOUBLE_ELLIPSE);
        estiloEInicial.put(mxConstants.STYLE_FONTSIZE, 20);
        estiloEInicial.put(mxConstants.STYLE_FILLCOLOR, "white");

        Map<String, Object> edgeStyle = new HashMap<String, Object>();
        edgeStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        edgeStyle.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
        edgeStyle.put(mxConstants.STYLE_STROKECOLOR, "#000000");
        edgeStyle.put(mxConstants.STYLE_FONTCOLOR, "#000000");
        stylesheet.setDefaultEdgeStyle(edgeStyle);

        stylesheet.putCellStyle("ESTADOFINAL", estiloEFinal);
        stylesheet.putCellStyle("ESTADO", estiloEstado);
        stylesheet.putCellStyle("ESTADOINICIAL", estiloEInicial);

        this.setStylesheet(stylesheet);

        this.getModel().beginUpdate();
        this.setCellsLocked(true);
        this.setVertexLabelsMovable(false);
        this.setEdgeLabelsMovable(false);
    }

}
