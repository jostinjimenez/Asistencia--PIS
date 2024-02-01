package modulo_1.inicio_sesion.view.forms;

import ModuloEstudianteDocente.controlador.ControladorEstudiante;
import ModuloEstudianteDocente.controlador.DocenteControlador;
import modulo_1.inicio_sesion.view.util.TextPrompt;
import modulo_1.inicio_sesion.controller.CuentaController;
import modulo_1.inicio_sesion.controller.PersonaController;

import javax.swing.*;

import modulo_1.inicio_sesion.view.tablas.ModeloTablaCuenta;

public class NuevoUsuario extends javax.swing.JDialog {

    public NuevoUsuario(java.awt.Frame parent, boolean modal, ModeloTablaCuenta mtp, JTable jTable1) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        pc.setIndex(-1);
        cc.setIndex(-1);

        cargarVista(mtp, jTable1);
        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> this.dispose());
    }

    public NuevoUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarPh();

        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> this.dispose());
    }

    //Variables
    private PersonaController pc = new PersonaController();
    private CuentaController cc = new CuentaController();
    private ControladorEstudiante ec = new ControladorEstudiante();
    private DocenteControlador dc = new DocenteControlador();
    private boolean isEditing = false;

    //Metodos
    public void cargarVista(ModeloTablaCuenta mtp, JTable jTable1) {
        pc.setIndex(jTable1.getSelectedRow());
        if (pc.getIndex() < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                isEditing = true;
                cc.setCuenta(mtp.getCuentas().get(cc.getIndex()));
                txtNombre.setText(cc.getPersona(cc.getCuenta().getId()).getNombre());
                txtApellido.setText(cc.getPersona(cc.getCuenta().getId()).getApellido());
                txtDni.setText(cc.getPersona(cc.getCuenta().getId()).getDni());
                txtCorreoPersonal.setText(cc.getPersona(cc.getCuenta().getId()).getCorreo_personal());
                txtFechaNac.setText(cc.getPersona(cc.getCuenta().getId()).getFecha_nacimiento());
                txtTelefono.setText(cc.getPersona(cc.getCuenta().getId()).getTelefono());
                txtDni.setEnabled(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    public void cargarPh() {
        TextPrompt tp = new TextPrompt("Nombre", txtNombre);
        TextPrompt tp1 = new TextPrompt("Apellido", txtApellido);
        TextPrompt tp2 = new TextPrompt("DNI", txtDni);
        TextPrompt tp4 = new TextPrompt("Correo Personal", txtCorreoPersonal);
        TextPrompt tp5 = new TextPrompt("Fecha de Nacimiento", txtFechaNac);
        TextPrompt tp6 = new TextPrompt("Telefono", txtTelefono);
    }

    public Boolean validar() {
        return !txtNombre.getText().trim().isEmpty()
                && !txtApellido.getText().trim().isEmpty()
                && !txtDni.getText().trim().isEmpty()
                && !txtCorreoPersonal.getText().trim().isEmpty()
                && !txtFechaNac.getText().trim().isEmpty()
                && !txtTelefono.getText().trim().isEmpty();
    }

    public void guardar() {
        if (validar()) {
            if (isEditing) {
                updatePersona();
            } else {
                saveUsuario();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveUsuario() {
        pc.getPersona().setNombre(txtNombre.getText());
        pc.getPersona().setApellido(txtApellido.getText());
        pc.getPersona().setDni(txtDni.getText());
        pc.getPersona().setCorreo_personal(txtCorreoPersonal.getText());
        pc.getPersona().setFecha_nacimiento(txtFechaNac.getText());
        pc.getPersona().setTelefono(txtTelefono.getText());
        pc.getPersona().setActivo(true);
        pc.getPersona().setIdRol(1);
        pc.getPersona().setFoto("/plantilla/img/user.png");

        if (pc.save()) {
            cc.getCuenta().setCorreo(generarCorreoInst());
            cc.getCuenta().setClave(txtDni.getText());
            cc.getCuenta().setIdPersona(cc.generarID());
            cc.save();

            JOptionPane.showMessageDialog(null, "Se guardó correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void updatePersona() {
        if (pc.update()) {
            JOptionPane.showMessageDialog(null, "Se actualizó correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generarCorreoInst() {
        String nombre = txtNombre.getText().contains(" ") ? txtNombre.getText().substring(0, txtNombre.getText().indexOf(" ")) : txtNombre.getText();
        String apellido = txtApellido.getText().contains(" ") ? txtApellido.getText().substring(0, txtApellido.getText().indexOf(" ")) : txtApellido.getText();
        return nombre.toLowerCase() + "." + apellido.toLowerCase() + "@unl.edu.ec";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtCorreoPersonal = new javax.swing.JTextField();
        txtFechaNac = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCancelar.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 110, 30));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Nuevo Administrador");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

        txtNombre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 270, 30));

        txtDni.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtDni.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 270, 30));

        txtTelefono.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 270, 30));

        txtApellido.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 270, 30));

        txtCorreoPersonal.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCorreoPersonal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(txtCorreoPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 270, 30));

        txtFechaNac.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtFechaNac.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(txtFechaNac, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, 270, 30));

        btnGuardar.setBackground(new java.awt.Color(102, 102, 255));
        btnGuardar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 110, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {

    }

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                NuevoUsuario dialog = new NuevoUsuario(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCorreoPersonal;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtFechaNac;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
