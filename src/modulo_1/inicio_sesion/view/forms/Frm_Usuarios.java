package modulo_1.inicio_sesion.view.forms;

import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import model.Cuenta;
import model.Rol;
import modulo_1.inicio_sesion.view.util.HeaderRenderer;
import modulo_1.inicio_sesion.view.util.Utiles;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.Objects;

import Controller.Login.CuentaController;
import modulo_1.inicio_sesion.view.tablas.ModeloTablaCuenta;
import tda_listas.ListaEnlazada;

import static Controller.Util.Utilidades.ajustarColumnas;
import static modulo_1.inicio_sesion.view.util.Utiles.cargaRol;

/**
 * Esta clase representa el formulario de usuarios.
 */
public class Frm_Usuarios extends javax.swing.JFrame {

    /**
     * Constructor de Frm_Usuarios. Inicializa los componentes del formulario y
     * establece su ubicación.
     */
    public Frm_Usuarios() {
        initComponents();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarTabla();
        cargaRol(cbxRol);
        cbxRol.setVisible(false);
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            btnEliminar.setEnabled(true);
        });
    }

    ModeloTablaCuenta mtp = new ModeloTablaCuenta();
    CuentaController pc = new CuentaController();


    private void buscarRol() {
        String criterio = Objects.requireNonNull(cbxCriterio.getSelectedItem()).toString().toLowerCase();
        try {
            if (criterio.equalsIgnoreCase("rol")) {
                Rol rolSeleccionado = Utiles.getComboRol(cbxRol);
                if (rolSeleccionado.getNombre().equals("Todos")) {
                    mtp.setCuentas(pc.getCuentas());
                } else {
                    mtp.setCuentas(pc.buscarCuentasPorRol(rolSeleccionado.getId()));
                }
                mtp.fireTableDataChanged();
                jTable1.setModel(mtp);
                jTable1.updateUI();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscar() {
        String criterio = Objects.requireNonNull(cbxCriterio.getSelectedItem()).toString().toLowerCase();
        String texto = txtBuscar.getText();

        try {
            if (texto.isEmpty()) {
                mtp.setCuentas(pc.list_All());
            } else {
                if (criterio.equalsIgnoreCase("nombre")) {
                    mtp.setCuentas(pc.buscarCuentasPorNombre(texto));
                } else if (criterio.equalsIgnoreCase("apellido")) {
                    mtp.setCuentas(pc.buscarCuentasPorApellido(texto));
                } else if (criterio.equalsIgnoreCase("dni")) {
                    Cuenta cuenta = pc.buscarCuentaPorDni(texto);
                    if (cuenta != null) {
                        ListaEnlazada<Cuenta> cuentas = new ListaEnlazada<>();
                        cuentas.add(cuenta);
                        mtp.setCuentas(cuentas);
                    } else {
                        mtp.setCuentas(new ListaEnlazada<>());
                    }
                }
            }
            mtp.fireTableDataChanged();
            jTable1.setModel(mtp);
            jTable1.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void eliminar() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                int idPersona = (int) jTable1.getValueAt(selectedRow, 0);
                if (pc.delete(idPersona)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarTabla() {
        mtp.setCuentas(pc.list_All());
        jTable1.setModel(mtp);
        jTable1.updateUI();
        mtp.fireTableDataChanged();
        ajustarColumnas(jTable1);

        TableRowSorter<ModeloTablaCuenta> trs = new TableRowSorter<>(mtp);
        jTable1.setRowSorter(trs);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        //jTable1.setRowHeight(30); // Ajusta este valor según tus necesidades

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        cbxCriterio = new javax.swing.JComboBox<>();
        cbxRol = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        menu_Admin2 = new plantilla.components.Menu_Admin();
        header2 = new plantilla.components.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(225, 233, 243));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Usuarios");
        roundPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Buscar");
        roundPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 100, 60, 20));

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
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 1000, 310));

        btnNuevo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnNuevo.setText("Agregar Administrador");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        roundPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 200, 30));

        btnEliminar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 560, 60, 50));

        cbxCriterio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "DNI", "Rol" }));
        cbxCriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriterioItemStateChanged(evt);
            }
        });
        roundPanel1.add(cbxCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 130, 160, -1));

        cbxRol.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxRol.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxRolItemStateChanged(evt);
            }
        });
        roundPanel1.add(cbxRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 160, -1));

        txtBuscar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        roundPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 180, 280, -1));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 1040, 620));
        bg_panel.add(menu_Admin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 680));

        header2.setBackground(new java.awt.Color(246, 246, 246));
        bg_panel.add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 1040, -1));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cbxCriterioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCriterioItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (Objects.equals(cbxCriterio.getSelectedItem(), "Rol")) {
                cbxRol.setVisible(true);
                txtBuscar.setVisible(false);
            } else {
                cbxRol.setVisible(false);
                txtBuscar.setVisible(true);
            }

        }
    }//GEN-LAST:event_cbxCriterioItemStateChanged

    private void cbxRolItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxRolItemStateChanged
        buscarRol();
    }//GEN-LAST:event_cbxRolItemStateChanged

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            buscar();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if (txtBuscar.getText().isEmpty()) {
            cargarTabla();
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        NuevoUsuario nu = new NuevoUsuario(this, true);
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
            new Frm_Usuarios().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JComboBox<String> cbxRol;
    private plantilla.components.Header header2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private plantilla.components.Menu_Admin menu_Admin2;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

}
