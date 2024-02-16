/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloEstudianteDocente.vista;

import ModuloEstudianteDocente.controlador.DocenteController;
import ModuloEstudianteDocente.vista.modals.modal_Docente;
import ModuloEstudianteDocente.vista.tablas.ModeloTablaDocente;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.Cuenta;
import model.Docente;
import moduloAsignaturas.view.tablas.ModeloTablaAsignaturas;
import modulo_1.inicio_sesion.controller.CuentaController;
import modulo_1.inicio_sesion.controller.PersonaController;
import modulo_1.inicio_sesion.view.tablas.ModeloTablaCuenta;
import modulo_1.inicio_sesion.view.util.HeaderRenderer;
import tda_listas.ListaEnlazada;

import java.sql.*;
import java.util.Objects;

import static modulo_1.inicio_sesion.controller.util.Utilidades.ajustarColumnas;

/**
 * @author LENOVO
 */
public class FrmDocente extends javax.swing.JFrame {

    private DocenteController dc = new DocenteController();
    private ModeloTablaDocente modeloDocente = new ModeloTablaDocente();
    private Integer fila = -1;
    private CuentaController cc;

    /**
     * Creates new form FrmDocete
     */
    public FrmDocente() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarTabla();
    }

    public FrmDocente(CuentaController cc) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarTabla();
        this.cc = cc;
    }

    private void buscar() {
        String criterio = Objects.requireNonNull(cbxCriterio.getSelectedItem()).toString().toLowerCase();
        String texto = txtBuscar.getText();

        try {
            if (texto.isEmpty()) {
                modeloDocente.setDocentes(dc.list_All());
            } else {
                if (criterio.equalsIgnoreCase("nombre")) {
                    modeloDocente.setDocentes(dc.buscarPorNombre(texto));
                } else if (criterio.equalsIgnoreCase("apellido")) {
                    modeloDocente.setDocentes(dc.buscarPorApellido(texto));
                } else if (criterio.equalsIgnoreCase("dni")) {
                    Docente docente = dc.buscarPorDni(texto);
                    if (docente != null) {
                        ListaEnlazada<Docente> docentes = new ListaEnlazada<>();
                        docentes.add(docente);
                        modeloDocente.setDocentes(docentes);
                    } else {
                        modeloDocente.setDocentes(new ListaEnlazada<>());
                    }
                }
            }
            modeloDocente.fireTableDataChanged();
            tblDocente.setModel(modeloDocente);
            tblDocente.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void cargarTabla() {
        modeloDocente.setDocentes(dc.list_All());
        tblDocente.setModel(modeloDocente);
        tblDocente.updateUI();
        ajustarColumnas(tblDocente);


        tblDocente.getTableHeader().setReorderingAllowed(false);
        tblDocente.getTableHeader().setResizingAllowed(false);
        tblDocente.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        tblDocente.setRowHeight(30);

<<<<<<< HEAD
        dc.setDocente(null);
        cargarTabla();
        fila = -1;
        tblDocente.clearSelection();
    }

    private Boolean validar() {
        return !txtNombresDoc.getText().trim().isEmpty()
                && !txtFechaNacim.getDate().toString().isEmpty()
                && !txtCorreoPersonal.getText().trim().isEmpty()
                && !txtDNI.getText().trim().isEmpty()
                && !txtTelefn.getText().trim().isEmpty()
                && !txtCodigoEmp.getText().trim().isEmpty()
                && !txtAniosExpe.getText().trim().isEmpty()
                && !txtApellidos.getText().trim().isEmpty()
                && !txtGradoAcademico.getText().trim().isEmpty();

    }

    public void guardar() {
        if (validar()) {
            try {
                // Crear y configurar la persona
                PersonaController pc = new PersonaController();
                pc.getPersona().setNombre(txtNombresDoc.getText());
                pc.getPersona().setApellido(txtApellidos.getText());
                pc.getPersona().setFecha_nacimiento(txtFechaNacim.getDate());
                pc.getPersona().setCorreo_personal(txtCorreoPersonal.getText());
                pc.getPersona().setDni(txtDNI.getText());
                pc.getPersona().setTelefono(txtTelefn.getText());
                pc.getPersona().setRol_id(2);
                pc.getPersona().setFoto("user.png");

                // Guardar la persona y obtener el ID generado
                Integer idGenerado = pc.save();

                if (idGenerado != null) {
                    // Configurar el estudiante con el ID de la persona
                    dc.getDocente().setGrado_academico(txtGradoAcademico.getText());
                    dc.getDocente().setAnios_experiencia(Integer.parseInt(txtAniosExpe.getText()));
                    dc.getDocente().setCodigo_empleado(txtCodigoEmp.getText());
                    dc.getDocente().setId(idGenerado);

                    // Guardar el docente
                    if (dc.save()) {
                        // Configurar y guardar la cuenta
                        cc.getCuenta().setCorreo_institucional(generarCorreoInst());
                        String claveCifrada = cc.cifrar(txtDNI.getText(), 10);
                        cc.getCuenta().setClave(claveCifrada);
                        cc.getCuenta().setPersona_id(idGenerado);
                        cc.save();
                    }
                    JOptionPane.showMessageDialog(null, "Docente guardado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar la persona", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al guardar el Docente", "Error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
=======
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblDocente.getColumnCount(); i++) {
            tblDocente.getColumnModel().getColumn(i).setCellRenderer(tcr);
>>>>>>> master
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

        jPanel1 = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocente = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbxCriterio = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        menu_Admin1 = new plantilla.components.Menu_Admin();
        header2 = new plantilla.components.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(225, 233, 243));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDocente.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tblDocente.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblDocente);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 980, 290));

        btnEliminar.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 560, 123, 38));

        btnModificar.setBackground(new java.awt.Color(204, 204, 204));
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 560, 123, 43));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setText("Docentes");
        roundPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        btnNuevo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnNuevo.setText("Agregar Docente");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        roundPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 180, 30));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Buscar");
        roundPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 100, 60, 20));

        cbxCriterio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Apellido", "DNI" }));
        cbxCriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriterioItemStateChanged(evt);
            }
        });
        roundPanel1.add(cbxCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 130, 160, -1));

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

        jPanel1.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 1040, 620));
        jPanel1.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 680));

        header2.setBackground(new java.awt.Color(246, 246, 246));
        jPanel1.add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 1040, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        modal_Docente nu = new modal_Docente(this, true);
        nu.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void cbxCriterioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCriterioItemStateChanged

    }//GEN-LAST:event_cbxCriterioItemStateChanged

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
            new FrmDocente().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cbxCriterio;
    private plantilla.components.Header header2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private plantilla.components.Menu_Admin menu_Admin1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tblDocente;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
