package modulo_1.usuario_rol.view;

import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import modulo_1.usuario_rol.controller.PersonaController;
import modulo_1.usuario_rol.view.tablas.ModeloTablaPersona;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

public class Frm_Usuarios extends javax.swing.JFrame {

    public Frm_Usuarios() {
        initComponents();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarTabla();

        jTable1.getSelectionModel().addListSelectionListener(e -> {
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        });

        btnEliminar.addActionListener(e -> eliminarRegistro());
    }

    ModeloTablaPersona mtp = new ModeloTablaPersona();
    PersonaController pc = new PersonaController();

    // Metodos
    public void eliminarRegistro() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                pc.setPersona(mtp.getPersonas().get(selectedRow));
                if (pc.delete(selectedRow)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                    mtp.getPersonas().delete(selectedRow);
                    mtp.fireTableDataChanged();
                    jTable1.updateUI();
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
        mtp.setPersonas(pc.getPersonas());
        jTable1.setModel(mtp);
        jTable1.updateUI();

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        menu1 = new plantilla.components.Menu();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCriterio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(21, 21, 21));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        bg_panel.add(menu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 620));

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Usuarios");
        roundPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Buscar");
        roundPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 70, 60, 30));

        txtCriterio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCriterio.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        roundPanel1.add(txtCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 110, 240, 30));

        jTable1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 1000, 360));

        btnNuevo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        roundPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 110, 30));

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

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 1040, 620));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        NuevoUsuario nu = new NuevoUsuario(this, true, mtp, jTable1);
        nu.setVisible(true);
        mtp.fireTableDataChanged();
        jTable1.updateUI();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        NuevoUsuario nu = new NuevoUsuario(this, true);
        nu.setVisible(true);
        mtp.fireTableDataChanged();
        jTable1.updateUI();
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
            new Frm_Usuarios().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private plantilla.components.Menu menu1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTextField txtCriterio;
    // End of variables declaration//GEN-END:variables

}
