package modulo_1.usuario_rol.view;

import modulo_1.inicio_sesion.view.util.TextPrompt;
import modulo_1.usuario_rol.controller.CuentaController;
import modulo_1.usuario_rol.controller.PersonaController;

import javax.swing.*;

import static modulo_1.usuario_rol.view.util.Utiles.cargaRol;

public class NuevoUsuario extends javax.swing.JDialog {

    public NuevoUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargaRol(cbxRol);
        cargarPh();
    }

    //Variables
    private PersonaController pc = new PersonaController();
    private CuentaController cc = new CuentaController();

    //Metodos
    public void cargarPh() {
        TextPrompt tp = new TextPrompt("Nombre", txtNombre);
        TextPrompt tp1 = new TextPrompt("Apellido", txtApellido);
        TextPrompt tp2 = new TextPrompt("DNI", txtDni);
        TextPrompt tp3 = new TextPrompt("Correo Institucional", txtCorreoInstitucional);
        TextPrompt tp4 = new TextPrompt("Correo Personal", txtCorreoPersonal);
        TextPrompt tp5 = new TextPrompt("Fecha de Nacimiento", txtFechaNac);
        TextPrompt tp6 = new TextPrompt("Telefono", txtTelefono);
    }

    public Boolean validar() {
        return !txtNombre.getText().trim().isEmpty() &&
                !txtApellido.getText().trim().isEmpty() &&
                !txtDni.getText().trim().isEmpty() &&
                !txtCorreoInstitucional.getText().trim().isEmpty() &&
                !txtCorreoPersonal.getText().trim().isEmpty() &&
                !txtFechaNac.getText().trim().isEmpty() &&
                !txtTelefono.getText().trim().isEmpty();
    }

    public void guardar() {
        if (validar()) {
            // Guardar Persona
            pc.getPersona().setNombre(txtNombre.getText().trim());
            pc.getPersona().setApellido(txtApellido.getText().trim());
            pc.getPersona().setDni(txtDni.getText().trim());
            pc.getPersona().setCorreo_personal(txtCorreoPersonal.getText().trim());
            pc.getPersona().setFecha_nacimiento(txtFechaNac.getText().trim());
            pc.getPersona().setTelefono(txtTelefono.getText().trim());
            pc.getPersona().setIdRol(cbxRol.getSelectedIndex() + 1);

            // Guardar Cuenta
            cc.getCuenta().setClave(txtDni.getText().trim());
            cc.getCuenta().setCorreo(txtCorreoInstitucional.getText().trim());
            cc.getCuenta().setIdPersona(pc.getPersona().getId());

            if (pc.save() && cc.save()) {
                JOptionPane.showMessageDialog(null, "Se guardó correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(null, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCorreoInstitucional = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtCorreoPersonal = new javax.swing.JTextField();
        txtFechaNac = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        cbxRol = new javax.swing.JComboBox<>();

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
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 110, 30));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Datos Nuevo Usuario");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        txtNombre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 270, 30));

        txtCorreoInstitucional.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCorreoInstitucional.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(txtCorreoInstitucional, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 270, 30));

        txtDni.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtDni.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 270, 30));

        txtTelefono.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 270, 30));

        txtApellido.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 270, 30));

        txtCorreoPersonal.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCorreoPersonal.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(txtCorreoPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 270, 30));

        txtFechaNac.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtFechaNac.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        jPanel1.add(txtFechaNac, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 220, 270, 30));

        btnGuardar.setBackground(new java.awt.Color(102, 102, 255));
        btnGuardar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, 110, 30));

        cbxRol.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxRol.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(cbxRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, 270, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

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
    private javax.swing.JComboBox<String> cbxRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCorreoInstitucional;
    private javax.swing.JTextField txtCorreoPersonal;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtFechaNac;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
