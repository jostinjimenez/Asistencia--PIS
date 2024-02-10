package modulo_carrera.view.forms;

import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import model.Rol;
import modulo_1.inicio_sesion.controller.PersonaController;
import modulo_1.inicio_sesion.view.tablas.ModeloTablaPersona;
import modulo_1.inicio_sesion.view.util.Utiles;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.event.ItemEvent;
import java.util.Objects;

import modulo_1.inicio_sesion.controller.CuentaController;
import modulo_1.inicio_sesion.view.tablas.ModeloTablaCuenta;
import plantilla.AccionesCellRenderer;

import static modulo_1.inicio_sesion.view.util.Utiles.cargaRol;

/**
 * Esta clase representa el formulario de usuarios.
 */
public class Frm_Carrera extends javax.swing.JFrame {

    /**
     * Constructor de Frm_Usuarios.
     * Inicializa los componentes del formulario y establece su ubicación.
     */
    public Frm_Carrera() {
        initComponents();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarTabla();
        cargaRol(cbxRol);
        cbxRol.setVisible(false);
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        });


//        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                buscar();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                buscar();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                buscar();
//            }
//        });
    }

    ModeloTablaCuenta mtp = new ModeloTablaCuenta();
    CuentaController pc = new CuentaController();
    //ModeloTablaCuenta mtc = new ModeloTablaCuenta();

    /**
     * Este método se encarga de buscar personas por rol.
     */
//    private void buscarRol() {
//        
//        
//        
//        String criterio = Objects.requireNonNull(cbxCriterio.getSelectedItem()).toString().toLowerCase();
//        try {
//            if (criterio.equalsIgnoreCase("rol")) {
//                Rol rolSeleccionado = Utiles.getComboRol(cbxRol);
//                if (rolSeleccionado.getNombre().equals("Todos")) {
//                    mtp.setPersonas(pc.getPersonas());
//                } else {
//                    mtp.setPersonas(pc.buscarRol(pc.getPersonas(), "idRol", rolSeleccionado));
//                }
//                mtp.fireTableDataChanged();
//                jTable1.setModel(mtp);
//                jTable1.updateUI();
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }

    /**
     * Este método se encarga de buscar personas por diferentes criterios.
     */
//    private void buscar() {
//        String criterio = Objects.requireNonNull(cbxCriterio.getSelectedItem()).toString().toLowerCase();
//        String texto = txtBuscar.getText();
//
//        try {
//            if (texto.isEmpty()) {
//                mtp.setPersonas(pc.getPersonas());
//            } else {
//                if (criterio.equalsIgnoreCase("nombre")) {
//                    mtp.setPersonas(pc.buscarNombre(pc.list_All(), texto));
//                } else if (criterio.equalsIgnoreCase("apellido")) {
//                    mtp.setPersonas(pc.buscarApellido(pc.list_All(), texto));
//                } else if (criterio.equalsIgnoreCase("dni")) {
//                    mtp.setPersonas(pc.buscarDni(pc.list_All(), texto));
//                } else if (criterio.equalsIgnoreCase("id")) {
//                    Integer id = Integer.parseInt(texto);
//                    mtp.setPersonas(pc.buscarId(pc.list_All(), id));
//                }
//            }
//            mtp.fireTableDataChanged();
//            jTable1.setModel(mtp);
//            jTable1.updateUI();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }

    /**
     * Este método se encarga de eliminar un registro de la tabla.
     */
//    public void eliminarRegistro() {
//        int selectedRow = jTable1.getSelectedRow();
//        if (selectedRow >= 0) {
//            try {
//                int idPersona = (int) jTable1.getValueAt(selectedRow, 0);
//                if (pc.delete(idPersona)) {
//                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
//                    cargarTabla();
//                } else {
//                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                System.out.println(e.getMessage());
//                throw new RuntimeException(e);
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }

    /**
     * Este método se encarga de cargar la tabla con los datos de las personas.
     */
    public void cargarTabla() {
        mtp.setCuentas(pc.list_All());
        jTable1.setModel(mtp);
        jTable1.updateUI();
        mtp.fireTableDataChanged();

        TableRowSorter<ModeloTablaCuenta> trs = new TableRowSorter<>(mtp);
        jTable1.setRowSorter(trs);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable1.getTableHeader().setReorderingAllowed(false);

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
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        cbxCriterio = new javax.swing.JComboBox<>();
        cbxRol = new javax.swing.JComboBox<>();
        menu_Admin2 = new plantilla.components.Menu_Admin();
        header2 = new plantilla.components.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(225, 233, 243));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Carreras");
        roundPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Buscar");
        roundPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 100, 60, 20));

        txtBuscar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        roundPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 130, 240, 20));

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

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 1000, 310));

        btnNuevo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnNuevo.setText("Agregar carrera");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        roundPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 160, 30));

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
        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id", "Rol", "Nombre", "Apellido", "DNI" }));
        cbxCriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriterioItemStateChanged(evt);
            }
        });
        roundPanel1.add(cbxCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 180, 160, -1));

        cbxRol.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxRol.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxRolItemStateChanged(evt);
            }
        });
        roundPanel1.add(cbxRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 160, -1));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 1040, 620));
        bg_panel.add(menu_Admin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 680));

        header2.setBackground(new java.awt.Color(255, 255, 255));
        bg_panel.add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 1040, -1));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Modal_Carrera nu = new Modal_Carrera(this, true, mtp, jTable1);
        nu.setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed

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
        //buscarRol();
    }//GEN-LAST:event_cbxRolItemStateChanged

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        Modal_Carrera nu = new Modal_Carrera(this, true);
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
            new Frm_Carrera().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JButton btnEditar;
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
