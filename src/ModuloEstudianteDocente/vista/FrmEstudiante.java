/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloEstudianteDocente.vista;

import ModuloEstudianteDocente.controlador.EstudianteController;
import ModuloEstudianteDocente.vista.modals.modal_Estudiante;
import ModuloEstudianteDocente.vista.tablas.ModeloTablaEstudiante;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import modulo_1.inicio_sesion.controller.CuentaController;

/**
 *
 * @author LENOVO
 */
public class FrmEstudiante extends javax.swing.JFrame {

    private EstudianteController estudianteControlador = new EstudianteController();
    private ModeloTablaEstudiante modeloEstudiante = new ModeloTablaEstudiante();
    private CuentaController cc;
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
    }

    public FrmEstudiante(CuentaController cc) {
        super();
        initComponents();

        this.setLocationRelativeTo(null);
        this.setResizable(false);

        cargarTabla();
        this.cc = cc;
    }

    public void cargarTabla() {
        modeloEstudiante.setEstudiante(estudianteControlador.list_All());
        tblEstudiante.setModel(modeloEstudiante);
        tblEstudiante.updateUI();
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
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        header2 = new plantilla.components.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 680));

        roundPanel5.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        roundPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 980, 270));

        btnNuevo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnNuevo.setText("Agregar Estudiante");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        roundPanel5.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 180, 30));

        btnEliminar.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        roundPanel5.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 560, 123, 50));

        btnModificar.setBackground(new java.awt.Color(204, 204, 204));
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        roundPanel5.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 560, 123, 43));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setText("Estudiantes");
        roundPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        txtBuscar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        roundPanel5.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 210, 240, 20));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Buscar");
        roundPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 180, 60, 20));

        jPanel1.add(roundPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 1040, 630));
        jPanel1.add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 1040, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, -4, 1290, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modal_Estudiante nu = new modal_Estudiante(this, true, modeloEstudiante, tblEstudiante);
        nu.setVisible(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        modal_Estudiante nu = new modal_Estudiante(this, true);
        nu.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

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
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
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
