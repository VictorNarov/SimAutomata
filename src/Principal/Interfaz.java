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

import Automata.AFND;
import Automata.AFD;
import Automata.TransicionAFD;
import Automata.TransicionAFND;
import Automata.TransicionL;
import java.awt.Frame;
import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Principal.panelEstados;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author victo
 */
public class Interfaz extends javax.swing.JFrame {

    private AFD afd = new AFD();
    private AFND afnd = new AFND();

    private HashSet<String> cjtoEstados = new HashSet();
    private HashSet<String> cjtoSimbolos = new HashSet();
    private DefaultTableModel modeloTT;

    //VARIABLES SIMULACION PASO A PASO
    private int indiceCadena = 0; //Indice para simular paso a paso
    private Stack<String> ultimoEstado = new Stack(); //pila de estados
    private String estadoActual = "";
    private Stack<HashSet<String>> ultimosEstados = new Stack(); //pila de cjtos de estados
    private HashSet<String> estadosActuales = new HashSet();

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        modeloTT = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloTT.setColumnIdentifiers(new Object[]{"ESTADOS/SIMBOLOS"});
        tablaTransicion.setModel(modeloTT);
        botonDestinos.setVisible(false); //Por defecto empieza en AFD y no necesita multples destinos

        labelIteracion.setVisible(false);
        labelFin.setVisible(false);
        labelResultado.setVisible(false);

        this.setLocationRelativeTo(null); //Centrar ventana en la pantalla
    }

    private void vaciarTabla() {
        while (modeloTT.getRowCount() > 0) {
            modeloTT.removeRow(0);
        }
    }

    public void actualizarTabla() {
        //TODO: editar nombre estados, si es incial -> delante y si es final * delante
        vaciarTabla();
        Object[] simbolos = cjtoSimbolos.toArray();
        ArrayList<Object> columna = new ArrayList();

        //System.out.println("ESTOY PILLANDO "+Arrays.toString(simbolos));
        columna.add("ESTADOS/SIMBOLOS");
        for (int i = 0; i < cjtoSimbolos.size(); i++) {
            columna.add(simbolos[i]);
            //System.out.println("ESTOY PILLANDO "+Arrays.toString(columna));
        }
        //Si es AFND le tenemos que añadir la columna de simbolo LAMBDA
        if (tipoAFND.isSelected()) {
            columna.add("L");
        }
        modeloTT.setColumnIdentifiers(columna.toArray()); //Añadimos la cabecera de la tabla

        for (String e : cjtoEstados) // Por cada estado
        {
            columna = new ArrayList<>(); //Reiniciamos la columna
            columna.add(e);

            if (tipoAFD.isSelected()) {
                for (int j = 0; j < cjtoSimbolos.size(); j++) // Rellena cada columna segun el simbolo de entrada 
                //System.out.println("VOY A VER SI EXISTE LA T: "+e+", "+ (tablaTransicion.getColumnName(j + 1)).charAt(0));
                {
                    columna.add(afd.getTransicion(e, (tablaTransicion.getColumnName(j + 1)).charAt(0)));  // Obteniendo la transicion del AFD
                }
            } else //TODO
            {
                for (int j = 0; j < cjtoSimbolos.size() + 1; j++) //Tiene que contar con la columna L
                {
                    if ((tablaTransicion.getColumnName(j + 1)).equals("L")) {
                        columna.add(afnd.getTransicionL(e));
                    } else {
                        columna.add(afnd.getTransicion(e, (tablaTransicion.getColumnName(j + 1)).charAt(0)));
                    }
                }
            }

            modeloTT.addRow(columna.toArray());
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTransicion = new javax.swing.JTable();
        botonSimular = new javax.swing.JButton();
        comboEjemplo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        botonAddEstado = new javax.swing.JButton();
        textEstado = new javax.swing.JTextField();
        textSimbolo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboOrigen = new javax.swing.JComboBox<>();
        comboDestino = new javax.swing.JComboBox<>();
        botonAddSimbolo = new javax.swing.JButton();
        botonAddT = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        comboEstadoI = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        comboSimbolo = new javax.swing.JComboBox<>();
        tipoAFD = new javax.swing.JRadioButton();
        tipoAFND = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        textCadena = new javax.swing.JTextField();
        botonCargar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        botonEliminarT = new javax.swing.JButton();
        botonEstadosF = new javax.swing.JButton();
        labelEstadoI = new javax.swing.JLabel();
        labelEstadosF = new javax.swing.JLabel();
        botonEliminarSimbolo = new javax.swing.JButton();
        botonEliminarEstado = new javax.swing.JButton();
        botonDestinos = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        botonGuardar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        botonSiguiente = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        textEstadoActual = new javax.swing.JTextField();
        textSimboloActual = new javax.swing.JTextField();
        botonAnterior = new javax.swing.JButton();
        botonIniciar = new javax.swing.JButton();
        labelIteracion = new javax.swing.JLabel();
        labelFin = new javax.swing.JLabel();
        labelResultado = new javax.swing.JLabel();
        botonGenerar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SimAutomata - Víctor Roríguez y Fran Beltrán ");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocation(new java.awt.Point(0, 0));

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jLabel1.setText("SimAutomata - Víctor Rodríguez y Fran Beltrán - 2019");

        tablaTransicion.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        tablaTransicion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaTransicion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaTransicion.setName("Tabla de Transiciones"); // NOI18N
        tablaTransicion.setRowHeight(30);
        tablaTransicion.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(tablaTransicion);
        if (tablaTransicion.getColumnModel().getColumnCount() > 0) {
            tablaTransicion.getColumnModel().getColumn(0).setResizable(false);
        }

        botonSimular.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        botonSimular.setText("SIMULAR");
        botonSimular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSimularActionPerformed(evt);
            }
        });

        comboEjemplo.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        comboEjemplo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir" }));

        jLabel2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel2.setText("1) Definir símbolos de entrada");

        jLabel3.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel3.setText("2) Definir conjunto de estados");

        botonAddEstado.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        botonAddEstado.setText("Añadir");
        botonAddEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAddEstadoActionPerformed(evt);
            }
        });

        textEstado.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        textEstado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textEstado.setText("q0");

        textSimbolo.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        textSimbolo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textSimbolo.setText("a");
        textSimbolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textSimboloActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel4.setText("Cargar ejemplo");

        jLabel5.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel5.setText("Cargar fichero");

        jLabel6.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel6.setText("3) Definir conjunto transiciones");

        comboOrigen.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        comboOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Origen" }));

        comboDestino.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        comboDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Destino" }));

        botonAddSimbolo.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        botonAddSimbolo.setText("Añadir");
        botonAddSimbolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAddSimboloActionPerformed(evt);
            }
        });

        botonAddT.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        botonAddT.setText("Añadir");
        botonAddT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAddTActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel7.setText("4) Definir estado inicial");

        comboEstadoI.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        comboEstadoI.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "q0" }));
        comboEstadoI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEstadoIActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel8.setText("5) Definir estados finales");

        comboSimbolo.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        comboSimbolo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Símbolo" }));

        buttonGroup1.add(tipoAFD);
        tipoAFD.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        tipoAFD.setSelected(true);
        tipoAFD.setText("AFD");
        tipoAFD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoAFDActionPerformed(evt);
            }
        });

        buttonGroup1.add(tipoAFND);
        tipoAFND.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        tipoAFND.setText("AFND");
        tipoAFND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoAFNDActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel9.setText("0) Tipo de Autómata");

        textCadena.setFont(new java.awt.Font("Rockwell", 0, 36)); // NOI18N
        textCadena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCadenaActionPerformed(evt);
            }
        });

        botonCargar.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        botonCargar.setText("Cargar");
        botonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel10.setText("Eliminar transiciones seleccionadas");

        botonEliminarT.setText("Eliminar");
        botonEliminarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarTActionPerformed(evt);
            }
        });

        botonEstadosF.setText("Estados");
        botonEstadosF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEstadosFActionPerformed(evt);
            }
        });

        labelEstadoI.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        labelEstadoI.setText("Estado incial: NO SLECCIONADO");

        labelEstadosF.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        labelEstadosF.setText("Estados finales: NO SELECCIONADOS ");

        botonEliminarSimbolo.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        botonEliminarSimbolo.setText("Eliminar");
        botonEliminarSimbolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarSimboloActionPerformed(evt);
            }
        });

        botonEliminarEstado.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        botonEliminarEstado.setText("Eliminar");
        botonEliminarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarEstadoActionPerformed(evt);
            }
        });

        botonDestinos.setText("Destinos");
        botonDestinos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDestinosActionPerformed(evt);
            }
        });

        botonLimpiar.setText("Limpiar");
        botonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel11.setText("LIMPIAR TODO");

        jLabel12.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel12.setText("Guardar Fichero");

        botonGuardar.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        botonGuardar.setText("Guardar");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel13.setText("Cadena a reconocer");

        jLabel14.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel14.setText("Simular paso a paso");

        jLabel15.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel15.setText("Estado actual");

        botonSiguiente.setText("Siguiente");
        botonSiguiente.setEnabled(false);
        botonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSiguienteActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel16.setText("Símbolo a leer");

        textEstadoActual.setEditable(false);

        textSimboloActual.setEditable(false);

        botonAnterior.setText("Anterior");
        botonAnterior.setEnabled(false);
        botonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnteriorActionPerformed(evt);
            }
        });

        botonIniciar.setText("Iniciar");
        botonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIniciarActionPerformed(evt);
            }
        });

        labelIteracion.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        labelIteracion.setText("Iteración: 0");

        labelFin.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        labelFin.setText("FIN DE CADENA");

        labelResultado.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        labelResultado.setText("RECONOCIDA");

        botonGenerar.setText("GENERAR GRAFICA");
        botonGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGenerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonEliminarT))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(comboOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(comboSimbolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(botonEstadosF)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelEstadosF))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(comboEstadoI, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(labelEstadoI))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(7, 7, 7)
                                                .addComponent(comboDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(botonDestinos)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(botonAddT))))
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(textCadena, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botonSimular, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel13)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelIteracion)))
                                .addGap(0, 39, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tipoAFD)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tipoAFND))
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(textSimbolo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botonAddSimbolo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botonEliminarSimbolo)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(botonCargar))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(comboEjemplo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonLimpiar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textSimboloActual, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(botonAnterior))
                            .addComponent(textEstadoActual, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botonSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botonIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelFin)
                            .addComponent(labelResultado))
                        .addGap(70, 88, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonAddEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonEliminarEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonGenerar)
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(tipoAFD)
                                    .addComponent(tipoAFND))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(textSimbolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(botonAddSimbolo)
                                    .addComponent(botonEliminarSimbolo))
                                .addGap(7, 7, 7)
                                .addComponent(jLabel3)
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(botonAddEstado)
                                    .addComponent(textEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(botonEliminarEstado)
                                    .addComponent(botonGenerar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(botonCargar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboEjemplo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(botonGuardar)
                                    .addComponent(jLabel12))
                                .addGap(86, 86, 86)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonAddT)
                            .addComponent(comboSimbolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonDestinos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(comboEstadoI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEstadoI))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(botonEstadosF)
                            .addComponent(labelEstadosF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textCadena, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonSimular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(labelIteracion))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(textEstadoActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonIniciar)
                            .addComponent(labelFin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(textSimboloActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonSiguiente)
                            .addComponent(botonAnterior)
                            .addComponent(labelResultado)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(botonEliminarT)
                        .addComponent(botonLimpiar)
                        .addComponent(jLabel11))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textSimboloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSimboloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textSimboloActionPerformed

    private void comboEstadoIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEstadoIActionPerformed
        if (tipoAFD.isSelected()) {
            afd.setEstadoInicial(comboEstadoI.getSelectedItem().toString());
        } else {
            afnd.setEstadoInicial(comboEstadoI.getSelectedItem().toString());
        }
        labelEstadoI.setText("Estado inicial: " + comboEstadoI.getSelectedItem().toString());
    }//GEN-LAST:event_comboEstadoIActionPerformed

    private void botonAddEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAddEstadoActionPerformed
        cjtoEstados.add(textEstado.getText());
        comboOrigen.addItem(textEstado.getText());
        comboDestino.addItem(textEstado.getText());
        comboEstadoI.addItem(textEstado.getText());

        actualizarTabla();
    }//GEN-LAST:event_botonAddEstadoActionPerformed

    private void tipoAFDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoAFDActionPerformed
        this.afd = new AFD(); //Reseteamos el AFD
        comboSimbolo.removeItem("LAMBDA");
        botonDestinos.setVisible(false);
        comboDestino.setVisible(true);
        actualizarTabla();
    }//GEN-LAST:event_tipoAFDActionPerformed

    private void tipoAFNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoAFNDActionPerformed
        this.afnd = new AFND(); //Reseteamos el AFND
        comboSimbolo.addItem("LAMBDA");
        botonAddT.setVisible(false);
        comboDestino.setVisible(false);
        botonDestinos.setVisible(true);
        actualizarTabla();
    }//GEN-LAST:event_tipoAFNDActionPerformed

    private void botonAddSimboloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAddSimboloActionPerformed
        cjtoSimbolos.add(textSimbolo.getText());
        comboSimbolo.addItem(textSimbolo.getText());
        actualizarTabla();
    }//GEN-LAST:event_botonAddSimboloActionPerformed

    private void botonAddTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAddTActionPerformed
        if (tipoAFD.isSelected()) //Obtenemos la transicion de los valores del comboBox
        {
            TransicionAFD t = new TransicionAFD(comboOrigen.getSelectedItem().toString(), comboSimbolo.getSelectedItem().toString().charAt(0), comboDestino.getSelectedItem().toString());
            afd.agregarTransicion(t);
            System.out.println("AÑADIDA TRANSICION:" + t);
        }
//        else //Debemos desplegar una lista 
//        {
//            botonAddT.setVisible(false); //Se añade directamente con la ventana de 
//            comboDestino.setVisible(false);
//            JList listaDestinos = new JList();
//            listaDestinos.setListData(this.cjtoEstados.toArray());
//            Frame ventana = new Frame();
//            ventana.setSize(100, 300);
//            ventana.add(listaDestinos);
//
//            ventana.setVisible(true);
//        }
        actualizarTabla();
    }//GEN-LAST:event_botonAddTActionPerformed

    private void botonEliminarSimboloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarSimboloActionPerformed
        this.cjtoSimbolos.remove(textSimbolo.getText());
        comboSimbolo.removeItem(textSimbolo.getText());

        if (tipoAFD.isSelected()) {
            afd.eliminarSimbolo(textSimbolo.getText().charAt(0)); //Elimina las transiciones que usan el simbolo
        } else {
            afnd.eliminarSimbolo(textSimbolo.getText().charAt(0));
        }
        actualizarTabla();
    }//GEN-LAST:event_botonEliminarSimboloActionPerformed

    private void botonEliminarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarEstadoActionPerformed
        String estado = textEstado.getText();
        this.cjtoEstados.remove(estado);
        comboEstadoI.removeItem(estado);
        comboOrigen.removeItem(estado);

        if (tipoAFD.isSelected()) {
            comboDestino.removeItem(estado);
            afd.eliminarEstado(estado);
        } else {
            afnd.eliminarEstado(estado);
        }
        actualizarTabla();
    }//GEN-LAST:event_botonEliminarEstadoActionPerformed

    private void botonEliminarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarTActionPerformed
        if (tipoAFD.isSelected()) {
            for (int i : tablaTransicion.getSelectedRows()) {
                System.out.println("Eliminando fila: " + i);
                String origen = modeloTT.getValueAt(i, 0).toString();
                String destino = modeloTT.getValueAt(i, 1).toString();

                for (int j = 1; j <= modeloTT.getColumnCount() - 1; j++) {//Por cada simbolo
                    TransicionAFD t = new TransicionAFD(origen, modeloTT.getColumnName(j).charAt(0), destino);
                    System.out.println("Eliminar transicion " + t);
                    afd.eliminarTransicion(t); //Borramos la transicion 
                }

            }
        }

        actualizarTabla();
    }//GEN-LAST:event_botonEliminarTActionPerformed

    private void botonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargarActionPerformed
        // TODO add your handling code here:
        JFileChooser selectorArchivos = new JFileChooser();
        String currentPath = Paths.get("./src/Ficheros").toAbsolutePath().normalize().toString();
        selectorArchivos.setCurrentDirectory(new File(currentPath));

        selectorArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int resultado = selectorArchivos.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = selectorArchivos.getSelectedFile();

            if ((archivo == null) || (archivo.getName().equals(""))) {
                JOptionPane.showMessageDialog(this, "Error al cargar fichero", "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE);
            }

            try {
                Fichero datos = new Fichero(archivo.getAbsolutePath());

                if (tipoAFD.isSelected()) {
                    datos.procesarAFD();
                    this.afd = datos.generarAutomataAFD();
                    this.labelEstadoI.setText("Estado inicial: " + afd.getEstadoInicial());
                    this.labelEstadosF.setText("Estados finales: " + afd.getEstadosFinales());
                    System.out.println(afd);
                } else {
                    datos.procesarAFND();
                    this.afnd = datos.generarAutomataAFND();
                    this.labelEstadoI.setText("Estado inicial: " + afnd.getEstadoInicial());
                    this.labelEstadosF.setText("Estados finales: " + afnd.getEstadosFinales());
                    System.out.println(afnd);
                }

                this.cjtoEstados = new HashSet<String>();
                this.cjtoEstados.addAll(datos.getConjuntoEstados());
                this.cjtoSimbolos = new HashSet<String>();
                this.cjtoSimbolos.addAll(datos.getConjuntoSimbolos());

                System.out.println("ESTADOS  \n" + cjtoEstados);
                System.out.println("SIMBOLOS \n" + cjtoSimbolos);
                this.actualizarTabla();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar fichero", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_botonCargarActionPerformed

    private void botonEstadosFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEstadosFActionPerformed
        panelEstados pE = new panelEstados();
        pE.setEstados(cjtoEstados);

        int res = JOptionPane.showConfirmDialog(null, pE,
                "SELECCIONAR ESTADOS", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            if (tipoAFD.isSelected()) {
                afd.setEstadosFinales(pE.getEstados());
            } else {
                afnd.setEstadosFinales(pE.getEstados());
            }

            labelEstadosF.setText("Estados finales: " + pE.getEstados());
        }

    }//GEN-LAST:event_botonEstadosFActionPerformed

    private void botonSimularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSimularActionPerformed
        if (tipoAFD.isSelected()) {
            if (afd.reconocer(textCadena.getText())) {
                JOptionPane.showConfirmDialog(rootPane, "CADENA RECONOCIDA", "RESULTADO DEL AUTOMATA", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showConfirmDialog(rootPane, "CADENA NO RECONOCIDA", "RESULTADO DEL AUTOMATA", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (afnd.reconocer(textCadena.getText())) {
                JOptionPane.showConfirmDialog(rootPane, "CADENA RECONOCIDA", "RESULTADO DEL AUTOMATA", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showConfirmDialog(rootPane, "CADENA NO RECONOCIDA", "RESULTADO DEL AUTOMATA", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_botonSimularActionPerformed

    private void botonDestinosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDestinosActionPerformed
        panelEstados pE = new panelEstados();
        pE.setEstados(cjtoEstados);

        int res = JOptionPane.showConfirmDialog(null, pE,
                "SELECCIONAR ESTADOS", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            if (comboSimbolo.getSelectedItem().equals("LAMBDA")) //Es una L-T             
            {
                TransicionL lt = new TransicionL(comboOrigen.getSelectedItem().toString(), pE.getEstados());
                afnd.agregarTransicionL(lt);
                System.out.println("AÑADIDA LAMBDA-T:" + lt);
            } else {
                TransicionAFND t = new TransicionAFND(comboOrigen.getSelectedItem().toString(), comboSimbolo.getSelectedItem().toString().charAt(0), pE.getEstados());
                afnd.agregarTransicion(t);
                System.out.println("AÑADIDA T: " + t);
            }

        }
        actualizarTabla();
    }//GEN-LAST:event_botonDestinosActionPerformed

    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarActionPerformed
        this.cjtoEstados.clear();
        this.cjtoSimbolos.clear();
        this.afd = new AFD();
        this.afnd = new AFND();
        textSimboloActual.setText("");
        textCadena.setText("");
        textEstadoActual.setText("");
        vaciarTabla();
        actualizarTabla(); //Volver a pintar la tabla vacia

    }//GEN-LAST:event_botonLimpiarActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void textCadenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCadenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCadenaActionPerformed

    private void botonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIniciarActionPerformed
        if (textCadena.getText().equals("")) //Si la cadena esta vacia
            JOptionPane.showMessageDialog(this, "Error: cadena vacia", "Error", JOptionPane.ERROR_MESSAGE);

        else if (indiceCadena == 0) //Vamos a empezar la simulacion
        {
            botonSiguiente.setEnabled(true); //activamos el boton siguiente
            labelIteracion.setVisible(true);
            labelIteracion.setText("Iteración: 1");
            String estadoI;
            if (tipoAFD.isSelected()) {
                estadoI = afd.getEstadoInicial();
                estadoActual = estadoI;
            } else {
                estadoI = afnd.getEstadoInicial();
                estadosActuales.addAll(afnd.L_clausura(estadoI));
            }

            textEstadoActual.setText(estadoI);
            textSimboloActual.setText(textCadena.getText().charAt(0) + ""); //caracter inicial
            botonIniciar.setText("Reiniciar");
        } else //Pulsa boton reiniciar
        {
            indiceCadena = 0;
            labelIteracion.setVisible(false);
            labelResultado.setVisible(false);
            labelFin.setVisible(false);
            estadoActual = "";
            estadosActuales.clear();
            ultimoEstado.clear();
            ultimosEstados.clear();
            textEstadoActual.setText("");
            textSimboloActual.setText("");
            botonIniciar.setText("Iniciar");
            botonSiguiente.setEnabled(false);
            botonAnterior.setEnabled(false);
        }
    }//GEN-LAST:event_botonIniciarActionPerformed

    private void botonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSiguienteActionPerformed
        if (indiceCadena == 0) {
            botonAnterior.setEnabled(true);
        }
        if (tipoAFD.isSelected()) {
            ultimoEstado.push(estadoActual);
            estadoActual = afd.getTransicion(estadoActual, textCadena.getText().charAt(indiceCadena));
            textEstadoActual.setText(estadoActual);
        } else {
            ultimosEstados.push(estadosActuales);
            estadosActuales = afnd.getTransicion(estadosActuales, textCadena.getText().charAt(indiceCadena));
            estadosActuales = afnd.L_clausura(estadosActuales);
            textEstadoActual.setText(estadosActuales.toString());
        }
        indiceCadena++;
        if (indiceCadena == textCadena.getText().length()) //Fin de cadena
        {
            botonSiguiente.setEnabled(false);
            labelFin.setVisible(true);
            if (tipoAFD.isSelected() && afd.esFinal(estadoActual)) {
                labelResultado.setText("RECONOCIDA");
            } else if (tipoAFD.isSelected() && !afd.esFinal(estadoActual)) {
                labelResultado.setText("NO RECONOCIDA");
            } else if (tipoAFND.isSelected() && afnd.esFinal(estadosActuales)) {
                labelResultado.setText("RECONOCIDA");
            } else {
                labelResultado.setText("NO RECONOCIDA");
            }
            labelResultado.setVisible(true);
        } 
        else
        {
            textSimboloActual.setText(textCadena.getText().charAt(indiceCadena) + "");
             labelIteracion.setText("Iteración: " + (indiceCadena + 1));
        }        
       


    }//GEN-LAST:event_botonSiguienteActionPerformed

    private void botonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnteriorActionPerformed
        indiceCadena--;
        labelResultado.setVisible(false);
        labelFin.setVisible(false);
        botonSiguiente.setEnabled(true);
        labelIteracion.setText("Iteración: "+(indiceCadena+1));

        if (tipoAFD.isSelected()) {
            estadoActual = ultimoEstado.pop();
            textEstadoActual.setText(estadoActual);
        } else {
            estadosActuales = ultimosEstados.pop();
            textEstadoActual.setText(ultimosEstados.toString());
        }
        textSimboloActual.setText(textCadena.getText().charAt(indiceCadena) + "");

        if (indiceCadena == 0)
            botonAnterior.setEnabled(false);
        
    }//GEN-LAST:event_botonAnteriorActionPerformed

    private void botonGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGenerarActionPerformed
        Grafica grafica = new Grafica();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        //JPanel panel = new JPanel();
       
        //panel.setPreferredSize(new Dimension(50,50));
        try {
            scrollPane.setViewportView(grafica.generarAFD(afd, cjtoEstados));
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, ex.getMessage(),
                "Error al generar grafica", JOptionPane.OK_OPTION,
                JOptionPane.ERROR_MESSAGE);
        }
 
        
        //final JOptionPane OptionPane = new JOptionPane(panel);
        //final JDialog dialog = OptionPane.createDialog((JFrame)null, "REPRESENTACION GRAFICA");
        JDialog dialog = new JDialog();
        dialog.getContentPane().setLayout(new BorderLayout(0, 0));
        dialog.getContentPane().add(scrollPane,BorderLayout.CENTER);
        dialog.setBounds(0, 0, 100, this.getHeight());

 
        dialog.setLocationRelativeTo(this);
        dialog.setLocation(dialog.getLocation().x+580, dialog.getLocation().y);
        dialog.setModal(false);
        dialog.setAlwaysOnTop(true);
        dialog.setTitle("REPRESENTACION GRAFICA");
        dialog.setSize(300,this.getHeight());
//        dialog.setMaximumSize(new Dimension(900,500));
        dialog.setVisible(true);

        
//        JOptionPane.showConfirmDialog(null, panel,
//                "grAFICA", JOptionPane.OK_OPTION,
//                JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_botonGenerarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAddEstado;
    private javax.swing.JButton botonAddSimbolo;
    private javax.swing.JButton botonAddT;
    private javax.swing.JButton botonAnterior;
    private javax.swing.JButton botonCargar;
    private javax.swing.JButton botonDestinos;
    private javax.swing.JButton botonEliminarEstado;
    private javax.swing.JButton botonEliminarSimbolo;
    private javax.swing.JButton botonEliminarT;
    private javax.swing.JButton botonEstadosF;
    private javax.swing.JButton botonGenerar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonIniciar;
    private javax.swing.JButton botonLimpiar;
    private javax.swing.JButton botonSiguiente;
    private javax.swing.JButton botonSimular;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboDestino;
    private javax.swing.JComboBox<String> comboEjemplo;
    private javax.swing.JComboBox<String> comboEstadoI;
    private javax.swing.JComboBox<String> comboOrigen;
    private javax.swing.JComboBox<String> comboSimbolo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelEstadoI;
    private javax.swing.JLabel labelEstadosF;
    private javax.swing.JLabel labelFin;
    private javax.swing.JLabel labelIteracion;
    private javax.swing.JLabel labelResultado;
    private javax.swing.JTable tablaTransicion;
    private javax.swing.JTextField textCadena;
    private javax.swing.JTextField textEstado;
    private javax.swing.JTextField textEstadoActual;
    private javax.swing.JTextField textSimbolo;
    private javax.swing.JTextField textSimboloActual;
    private javax.swing.JRadioButton tipoAFD;
    private javax.swing.JRadioButton tipoAFND;
    // End of variables declaration//GEN-END:variables
}
