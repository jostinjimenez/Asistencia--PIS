package View.Login.mainFrm;

import model.Cuenta;
import model.Persona;
import Controller.Login.CuentaController;
import Controller.Administrativo.PersonaController;

import java.io.File;
import java.util.UUID;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import static Controller.Util.Utilidades.getPersonaStatic;
import static View.Util.UtilVista.*;

public class Perfil_Modal extends javax.swing.JDialog {

    File foto = null;
    Cuenta cuenta;
    Persona persona;

    public Perfil_Modal(java.awt.Frame parent, boolean modal, Cuenta cuentausu) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.cuenta = cuentausu;
        txtFoto.setVisible(false);
        txtFoto.setText(persona.getFoto());
        persona = getPersonaStatic(cuenta.getPersona_id());
        cargarDatos();
    }

    CuentaController cc = new CuentaController();

    private Boolean validarClave() {
        String clave = txtClaveActual.getText();
        return cuenta.getClave().equals(clave);
    }

    private Boolean validarNuevasClaves() {
        String clave1 = txtNuevaClave.getText();
        String clave2 = txtClaveNueva2.getText();
        return clave1.equals(clave2);
    }

    private void actualizarInformacion(String newFileName) {
        updateClave();
        updateFoto(newFileName);
    }

    private void updateClave() {
        if (validarClave()) {
            try {
                cc.setCuenta(cuenta);
                if (validarNuevasClaves()) {
                    String claveNueva = txtNuevaClave.getText();
                    cuenta.setClave(claveNueva);
                    cc.update(cuenta);
                    JOptionPane.showMessageDialog(null, "Clave actualizada correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Las claves no coinciden");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La clave actual no es correcta");
        }
    }

    private void updateFoto(String newFileName) {
        PersonaController pc = new PersonaController();

        pc.setPersona(persona);
        pc.getPersona().setFoto(newFileName);
        if (pc.update()) {
            JOptionPane.showMessageDialog(null, "Foto actualizada", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la foto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Perfil_Modal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

    }

    private void cargarDatos() {
        labelCorreo.setText(cuenta.getCorreo_institucional());
        txtFoto.setVisible(false);
        fotoUsuario.setIcon(new ImageIcon("multimedia/" + persona.getFoto()));

    }

    private void filechooserFoto() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Multimedia", "jpg", "png", "jpeg");
            fileChooser.addChoosableFileFilter(filter);

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                foto = fileChooser.getSelectedFile();

                if (foto != null) {
                    String imagePath = foto.getAbsolutePath();
                    txtFoto.setText(imagePath);

                    if (new File(imagePath).exists()) {
                        String uuid = UUID.randomUUID().toString();
                        String newFileName = uuid + "." + extension(foto.getName());
                        ImageIcon imageIcon = new ImageIcon(imagePath);

                        // Actualizar la interfaz gráfica
                        fotoUsuario.setIcon(imageIcon);
                        fotoUsuario.updateUI();

                        // Copiar el archivo a la carpeta multimedia
                        copiarArchivo(foto, new File("multimedia/" + newFileName));
                        updateFoto(newFileName);

                        JOptionPane.showMessageDialog(null, "Foto actualizada", "Información", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró la imagen", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una imagen", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelInfomacion = new plantilla.swing.RoundPanel();
        jSeparator1 = new javax.swing.JSeparator();
        labelCorreo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnActualizarFoto = new javax.swing.JButton();
        txtFoto = new javax.swing.JTextField();
        fotoUsuario = new plantilla.swing.ImageAvatar();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtClaveNueva2 = new javax.swing.JPasswordField();
        txtClaveActual = new javax.swing.JPasswordField();
        txtNuevaClave = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelInfomacion.setBackground(new java.awt.Color(225, 233, 243));
        panelInfomacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setBackground(new java.awt.Color(90, 90, 90));
        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));
        panelInfomacion.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 290, 10));

        labelCorreo.setFont(new java.awt.Font("Dubai Light", 0, 24)); // NOI18N
        labelCorreo.setForeground(new java.awt.Color(51, 51, 51));
        labelCorreo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelInfomacion.add(labelCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 290, 30));

        jSeparator2.setBackground(new java.awt.Color(90, 90, 90));
        jSeparator2.setForeground(new java.awt.Color(51, 51, 51));
        panelInfomacion.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 290, 10));

        btnActualizarFoto.setBackground(new java.awt.Color(225, 233, 243));
        btnActualizarFoto.setFont(new java.awt.Font("Dubai Light", 0, 14)); // NOI18N
        btnActualizarFoto.setForeground(new java.awt.Color(49, 135, 164));
        btnActualizarFoto.setText("Actualizar foto");
        btnActualizarFoto.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnActualizarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarFotoActionPerformed(evt);
            }
        });
        panelInfomacion.add(btnActualizarFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 110, -1));

        txtFoto.setEditable(false);
        txtFoto.setBackground(new java.awt.Color(225, 233, 243));
        txtFoto.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtFoto.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelInfomacion.add(txtFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 60, -1));
        panelInfomacion.add(fotoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 190, 160));

        jPanel1.add(panelInfomacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 360, 350));

        btnGuardar.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 380, 110, 30));

        btnCancelar.setBackground(new java.awt.Color(204, 204, 204));
        btnCancelar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 380, 110, 30));

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 153, 255));
        jLabel2.setText("Información personal");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Repita la contraseña:");
        roundPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Contraseña actual:");
        roundPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel12.setBackground(new java.awt.Color(51, 51, 51));
        jLabel12.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Nueva contraseña:");
        roundPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        txtClaveNueva2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        roundPanel1.add(txtClaveNueva2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 260, -1));

        txtClaveActual.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        roundPanel1.add(txtClaveActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 260, -1));

        txtNuevaClave.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        roundPanel1.add(txtNuevaClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 260, -1));

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 153, 255));
        jLabel3.setText("Actualizar contraseña");
        roundPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        jPanel1.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 490, 280));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 969, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        actualizarInformacion(foto.getName());
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarFotoActionPerformed
        filechooserFoto();
    }//GEN-LAST:event_btnActualizarFotoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Perfil_Modal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Perfil_Modal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Perfil_Modal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Perfil_Modal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Perfil_Modal dialog = new Perfil_Modal(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarFoto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private plantilla.swing.ImageAvatar fotoUsuario;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelCorreo;
    private plantilla.swing.RoundPanel panelInfomacion;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JPasswordField txtClaveActual;
    private javax.swing.JPasswordField txtClaveNueva2;
    private javax.swing.JTextField txtFoto;
    private javax.swing.JPasswordField txtNuevaClave;
    // End of variables declaration//GEN-END:variables
}
