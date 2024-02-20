/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloEstudianteDocente.vista;

import Controller.Academico.EstudianteController;
import ModuloEstudianteDocente.vista.modals.modal_Estudiante;
import ModuloEstudianteDocente.vista.tablas.ModeloTablaEstudiante;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import Controller.Login.CuentaController;
import modulo_1.inicio_sesion.view.util.HeaderRenderer;

import static Controller.Util.Utilidades.ajustarColumnas;

/**
 *
 * @author LENOVO
 */
public class FrmEstudiante extends javax.swing.JFrame {

    private EstudianteController estudianteControlador = new EstudianteController();
    private ModeloTablaEstudiante modeloEstudiante = new ModeloTablaEstudiante();
    CuentaController cc;
    private Integer fila = -1;

    /**
     * Creates new form FrmEstudiante
     */
    public FrmEstudiante() {
        super();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarTabla();
        tblEstudiante.getSelectionModel().addListSelectionListener(e -> {
            btnEliminar.setEnabled(true);
            btnEditar.setEnabled(true);
        });
    }

    public FrmEstudiante(CuentaController cc) {
        super();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarTabla();
        this.cc = cc;
        tblEstudiante.getSelectionModel().addListSelectionListener(e -> {
            btnEliminar.setEnabled(true);
            btnEditar.setEnabled(true);
        });
    }

    public void cargarTabla() {
        modeloEstudiante.setEstudiantes(estudianteControlador.list_All());
        tblEstudiante.setModel(modeloEstudiante);
        tblEstudiante.updateUI();
        ajustarColumnas(tblEstudiante);

        TableRowSorter<ModeloTablaEstudiante> trs = new TableRowSorter<>(modeloEstudiante);
        tblEstudiante.setRowSorter(trs);
        tblEstudiante.getTableHeader().setReorderingAllowed(false);
        tblEstudiante.getTableHeader().setResizingAllowed(false);
        tblEstudiante.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        tblEstudiante.setRowHeight(30);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblEstudiante.getColumnCount(); i++) {
            tblEstudiante.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }


    public void eliminar() {
        int fila = tblEstudiante.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione una fila",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                estudianteControlador.delete(fila);
                JOptionPane.showMessageDialog(null, "Estudiante eliminado correctamente",
                        "Mensaje",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Error al eliminar el estudiante: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        menu_Admin1 = new plantilla.components.Menu_Admin();
        roundPanel5 = new plantilla.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstudiante = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbxCriterio = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        header2 = new plantilla.components.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(225, 233, 243));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 680));

        roundPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblEstudiante.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tblEstudiante.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblEstudiante);

        roundPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 980, 320));

        btnNuevo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnNuevo.setText("Agregar Estudiante");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        roundPanel5.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 180, 30));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setText("Estudiantes");
        roundPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        btnEliminar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        roundPanel5.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 560, 60, 50));

        btnEditar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        roundPanel5.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 560, 60, 50));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Buscar");
        roundPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 100, 60, 20));

        cbxCriterio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "DNI" }));
        cbxCriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriterioItemStateChanged(evt);
            }
        });
        roundPanel5.add(cbxCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 130, 160, -1));

        txtBuscar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        roundPanel5.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 180, 280, -1));

        jPanel1.add(roundPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 1040, 620));

        header2.setBackground(new java.awt.Color(246, 246, 246));
        jPanel1.add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 1040, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, -4, 1290, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        modal_Estudiante nu = new modal_Estudiante(this, true);
        nu.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        modal_Estudiante nu = new modal_Estudiante(this, true, modeloEstudiante, tblEstudiante);
        nu.setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void cbxCriterioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCriterioItemStateChanged

    }//GEN-LAST:event_cbxCriterioItemStateChanged

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            //buscar();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if (txtBuscar.getText().isEmpty()) {
            cargarTabla();
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatNordIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FrmEstudiante().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cbxCriterio;
    private plantilla.components.Header header2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private plantilla.components.Menu_Admin menu_Admin1;
    private plantilla.swing.RoundPanel roundPanel5;
    private javax.swing.JTable tblEstudiante;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
