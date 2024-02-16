package ModuloMatricula.Views;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Objects;

import model.*;
import modelLuis.view.Frm_HorarioAdmi;
import moduloAsignaturas.controller.AsignaturaController;
import moduloAsignaturas.view.tablas.ModeloTablaAsignaturas;
import modulo_1.inicio_sesion.view.util.HeaderRenderer;
import modulo_carrera.controller.CarreraController;
import modulo_carrera.view.tablas.ModeloTablaCarrera;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class buscar_Asignatura extends javax.swing.JDialog {

    AsignaturaController rc = new AsignaturaController();
    ModeloTablaAsignaturas mtc = new ModeloTablaAsignaturas();
    Integer id;

    public buscar_Asignatura(java.awt.Frame parent, boolean modal, Integer id, String nombre) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.id = id;
        if (nombre.equals("Frm_Cursas")) {
            mostrarTabla();
        } else if (nombre.equals("Frm_HorarioAdmi")) {
            mostrarTabla1();
        }

        tabla.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                try {
                    if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                        Asignatura asignatura = mtc.getAsignatura(row);
                        if (asignatura != null) {

                            if (nombre.equals("Frm_Cursas")) {
                                ((Frm_Cursas) getParent()).txtAsignaturas.setText(asignatura.getNombre());
                                ((Frm_Cursas) getParent()).txtIdAsignatura.setText(String.valueOf(asignatura.getId()));

                            } else if (nombre.equals("Frm_HorarioAdmi")) {
                                ((Frm_HorarioAdmi) getParent()).txtAsignaturas.setText(asignatura.getNombre());
                                ((Frm_HorarioAdmi) getParent()).txtIdAsignatura.setText(String.valueOf(asignatura.getId()));

                            }
                            //((Frm_Cursas) getParent()).txtAsignaturas.setText(asignatura.getNombre());
                            // ((Frm_Cursas) getParent()).txtIdAsignatura.setText(String.valueOf(asignatura.getId()));
                        }
                        dispose();
                    }
                } catch (VacioExceptions e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void mostrarTabla() {
        ListaEnlazada<Asignatura> asigMatriculas = rc.buscarAsignaturasPorMatricula(id);
        mtc.setAsignaturas(asigMatriculas);
        tabla.setModel(mtc);
        tabla.updateUI();
        mtc.fireTableDataChanged();

        TableRowSorter<ModeloTablaAsignaturas> trs = new TableRowSorter<>(mtc);
        tabla.setRowSorter(trs);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setResizingAllowed(false);
        tabla.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        tabla.setRowHeight(30);
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    public void mostrarTabla1() {
        ListaEnlazada<Asignatura> asigMatriculas = rc.buscarAsignaturasPorCarrera(id);
        mtc.setAsignaturas(asigMatriculas);
        tabla.setModel(mtc);
        tabla.updateUI();
        mtc.fireTableDataChanged();

        TableRowSorter<ModeloTablaAsignaturas> trs = new TableRowSorter<>(mtc);
        tabla.setRowSorter(trs);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setResizingAllowed(false);
        tabla.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        tabla.setRowHeight(30);
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    private void buscar() {
        String criterio = Objects.requireNonNull(cbxCriterio.getSelectedItem()).toString().toLowerCase();
        String texto = txtBusqueda.getText();

        try {
            if (texto.isEmpty()) {
                mtc.setAsignaturas(rc.getAsignaturas());
            } else {
                if (criterio.equalsIgnoreCase("nombre")) {
                    mtc.setAsignaturas(rc.busquedaBinaria(rc.list_All(), texto, "nombre"));
                } else if (criterio.equalsIgnoreCase("codigo")) {
                    Asignatura c = rc.busquedaBinaria2(rc.list_All(), texto, "codigo_materia");
                    if (c != null) {
                        mtc.setAsignaturas(new ListaEnlazada<>());
                        mtc.getAsignaturas().add(c);
                    }
                }
            }
            mtc.fireTableDataChanged();
            tabla.setModel(mtc);
            tabla.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botones = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        txtBusqueda = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        cbxCriterio = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(225, 233, 243));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 480, 230));

        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });
        roundPanel1.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 350, -1));

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setText("Busqueda de Carrera:");
        roundPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 110, -1));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));
        roundPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 520, 10));

        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Nombre", "Codigo"}));
        roundPanel1.add(cbxCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 160, -1));

        jPanel1.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 430));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked

    }//GEN-LAST:event_tablaMouseClicked

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        if (txtBusqueda.getText().isEmpty()) {
            mostrarTabla();
        }
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            buscar();
        }
    }//GEN-LAST:event_txtBusquedaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup botones;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
