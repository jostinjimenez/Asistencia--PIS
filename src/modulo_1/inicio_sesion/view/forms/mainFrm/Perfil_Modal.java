package modulo_1.inicio_sesion.view.forms.mainFrm;

import ModuloEstudianteDocente.controlador.EstudianteController;
import ModuloEstudianteDocente.controlador.DocenteController;
import model.Docente;
import model.Estudiante;
import model.Persona;
import modulo_1.inicio_sesion.controller.CuentaController;
import modulo_1.inicio_sesion.controller.PersonaController;

import java.io.File;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import static modulo_1.inicio_sesion.view.util.Utiles.*;

public class Perfil_Modal extends javax.swing.JDialog {

    File foto = null;
    CuentaController cc;

    public Perfil_Modal(java.awt.Frame parent, boolean modal, CuentaController cc) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.cc = cc;

        cargarDatos();
    }

    public Perfil_Modal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

    }

    private void cargarDatos() {
        labelCorreo.setText(cc.getCuenta().getCorreo_institucional());
        txtFoto.setVisible(false);
        fotoUsuario.setIcon(new ImageIcon("multimedia/" + cc.getPersona(cc.getCuenta().getPersona_id()).getFoto()));
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

    private void updateFoto(String newFileName) {
        try {
            EstudianteController ce = new EstudianteController();
            PersonaController pc = new PersonaController();
            DocenteController dc = new DocenteController();

            Persona p = cc.getPersona(cc.getCuenta().getPersona_id());

            pc.setPersona(p);
            pc.getPersona().setFoto(newFileName);

            if (pc.update()) {
                System.out.println("Perfil " + p.getRol_id() + " actualizado");

                switch (p.getRol_id()) {
                    case 2:
                        Estudiante estudiante = (Estudiante) p;
                        estudiante.setFoto(newFileName);
                        ce.setEstudiante(estudiante);
                        if (ce.update()) {
                            System.out.println("Foto del estudiante actualizada");
                        } else {
                            mostrarError("No se pudo actualizar la foto del estudiante");
                        }
                        break;
                    case 3:
                        Docente docente = (Docente) p;
                        docente.setFoto(newFileName);
                        dc.setDocente(docente);
                        if (dc.update()) {
                            System.out.println("Foto del docente actualizada");
                        } else {
                            mostrarError("No se pudo actualizar la foto del docente");
                        }
                        break;
                }
            } else {
                mostrarError("No se pudo actualizar la foto");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
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
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();

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

        jPanel1.add(panelInfomacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 360, 350));

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Contraseña:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 270, -1, -1));

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Correo personal:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, -1, -1));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Telefono:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, -1, -1));

        btnGuardar.setBackground(new java.awt.Color(102, 102, 255));
        btnGuardar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 420, 110, 30));

        btnCancelar.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 420, 110, 30));

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Información personal");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jTextField1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, 240, -1));

        jLabel11.setBackground(new java.awt.Color(51, 51, 51));
        jLabel11.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Telefono:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, -1, -1));

        jTextField3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, 240, -1));

        jTextField4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, 240, -1));

        jTextField5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 240, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 969, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarFotoActionPerformed
        filechooserFoto();
    }//GEN-LAST:event_btnActualizarFotoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel labelCorreo;
    private plantilla.swing.RoundPanel panelInfomacion;
    private javax.swing.JTextField txtFoto;
    // End of variables declaration//GEN-END:variables
}
