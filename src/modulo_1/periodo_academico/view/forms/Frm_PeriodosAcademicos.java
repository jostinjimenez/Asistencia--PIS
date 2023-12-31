package modulo_1.periodo_academico.view.forms;

import model.PeriodoAcademico;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import modulo_1.periodo_academico.controller.PeriodoAcController;
import modulo_1.periodo_academico.view.tablas.ModeloTablaPeriodoAc;
import tda_listas.ListaEnlazada;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.event.ItemEvent;
import java.util.Objects;

public class Frm_PeriodosAcademicos extends javax.swing.JFrame {

    public Frm_PeriodosAcademicos() {
        initComponents();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarTabla();

        jTable1.getSelectionModel().addListSelectionListener(e -> {
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        });

        btnEliminar.addActionListener(e -> eliminarRegistro());

        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }

            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            public void insertUpdate(DocumentEvent e) {
               buscar();
            }
        });
    }

    ModeloTablaPeriodoAc mtpa = new ModeloTablaPeriodoAc();
    PeriodoAcController pc = new PeriodoAcController();

    // Metodos
    private void buscar() {
        String criterio = Objects.requireNonNull(cbxCriterio.getSelectedItem()).toString().toLowerCase();
        String texto = txtBuscar.getText();

        try {
            if (texto.isEmpty()) {
                mtpa.setPeriodoAcademicos(pc.getPeriodoAcademicos());
            } else {
                if (criterio.equalsIgnoreCase("anio")) {
                    Integer anio = Integer.parseInt(texto);
                    mtpa.setPeriodoAcademicos(pc.buscarAnio(pc.list_All(), anio));
                } else if (criterio.equalsIgnoreCase("fechaFin")) {
                    mtpa.setPeriodoAcademicos(pc.buscarFechaFin(pc.list_All(), texto));
                } else if (criterio.equalsIgnoreCase("fechaInicio")) {
                    mtpa.setPeriodoAcademicos(pc.buscarFechaInicio(pc.list_All(), texto));
                } else if (criterio.equalsIgnoreCase("id")) {
                    Integer id = Integer.parseInt(texto);
                    mtpa.setPeriodoAcademicos(pc.buscarId(pc.list_All(), id));
                }
            }
            mtpa.fireTableDataChanged();
            jTable1.setModel(mtpa);
            jTable1.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarRegistro() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                int idPA = (int) jTable1.getValueAt(selectedRow, 0);
                if (pc.delete(idPA)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarTabla() {
        mtpa.setPeriodoAcademicos(pc.getPeriodoAcademicos());
        mtpa.fireTableDataChanged();
        jTable1.setModel(mtpa);
        jTable1.updateUI();

        TableRowSorter<ModeloTablaPeriodoAc> trs = new TableRowSorter<>(mtpa);
        jTable1.setRowSorter(trs);
        jTable1.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        cbxCriterio = new javax.swing.JComboBox<>();
        menu_Admin1 = new plantilla.components.Menu_Admin();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(21, 21, 21));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Periodo Academico");
        roundPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Buscar");
        roundPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 70, 60, 20));

        txtBuscar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        roundPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, 240, 20));

        jTable1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 1000, 360));

        btnNuevo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnNuevo.setText("Agregar periodo Academico");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        roundPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 220, 30));

        btnEditar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 570, 110, 30));

        btnEliminar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnEliminar.setText("Dar de Baja");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 570, 110, 30));

        cbxCriterio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id", "Anio", "Fecha Inicio", "Fecha Fin" }));
        cbxCriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriterioItemStateChanged(evt);
            }
        });
        roundPanel1.add(cbxCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 150, 160, -1));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 1040, 620));
        bg_panel.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 620));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        NuevoPeriodo nu = new NuevoPeriodo(this, true, mtpa, jTable1);
        nu.setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cbxCriterioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCriterioItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //buscar();
        }
    }//GEN-LAST:event_cbxCriterioItemStateChanged

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        NuevoPeriodo nu = new NuevoPeriodo(this, true);
        nu.setVisible(true);
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatNordIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Frm_PeriodosAcademicos().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private plantilla.components.Menu_Admin menu_Admin1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

}
