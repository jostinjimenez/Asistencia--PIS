package modulo_1.inicio_sesion.view.forms.mainFrm;

import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import modulo_1.inicio_sesion.controller.CuentaController;
import modulo_1.inicio_sesion.controller.PersonaController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.Objects;
import java.util.UUID;

import static modulo_1.inicio_sesion.view.util.Utiles.copiarArchivo;
import static modulo_1.inicio_sesion.view.util.Utiles.extension;

public class Frm_Main_Admin extends javax.swing.JFrame {

    CuentaController cc;
    File foto = null;

    public Frm_Main_Admin(CuentaController cc) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.cc = cc;

        labelCorreo.setText(cc.getCuenta().getCorreo());
        txtFoto.setVisible(false);
        fotoUsuario.setIcon(new ImageIcon("multimedia/" + cc.getPersona(cc.getCuenta().getIdPersona()).getFoto()));

    }

    public Frm_Main_Admin() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        txtFoto.setVisible(false);
        fotoUsuario.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/user.png"))));

    }

    private void filechooserFoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Multimedia", "jpg", "png", "jpeg");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                foto = fileChooser.getSelectedFile();
                if (foto != null) {
                    String imagePath = foto.getAbsolutePath();
                    txtFoto.setText(imagePath);
                    if (new File(imagePath).exists()) {
                        ImageIcon imageIcon = new ImageIcon(imagePath);
                        fotoUsuario.setIcon(imageIcon);
                        fotoUsuario.updateUI();
                        String uuid = UUID.randomUUID().toString();
                        copiarArchivo(foto, new File("multimedia/" + uuid + "." + extension(foto.getName())));

                        PersonaController pc = new PersonaController();
                        pc.setPersona(cc.getPersona(cc.getCuenta().getIdPersona()));
                        pc.getPersona().setFoto(uuid + "." + extension(foto.getName()));
                        if (pc.update()) {
                            JOptionPane.showMessageDialog(null, "Foto actualizada", "Información", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo actualizar la foto", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró la imagen", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una imagen", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // Metodos
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        panelInfomacion = new plantilla.swing.RoundPanel();
        fotoUsuario = new com.raven.swing.ImageAvatar();
        jSeparator1 = new javax.swing.JSeparator();
        labelCorreo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnActualizarFoto = new javax.swing.JButton();
        txtFoto = new javax.swing.JTextField();
        roundPanel2 = new plantilla.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        menu_Admin2 = new plantilla.components.Menu_Admin();
        header1 = new plantilla.components.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(21, 21, 21));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(21, 21, 21));
        roundPanel1.setForeground(new java.awt.Color(49, 135, 164));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelInfomacion.setBackground(new java.awt.Color(51, 51, 51));
        panelInfomacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelInfomacion.add(fotoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 220, 160));

        jSeparator1.setBackground(new java.awt.Color(90, 90, 90));
        jSeparator1.setForeground(new java.awt.Color(223, 223, 223));
        panelInfomacion.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 290, 10));

        labelCorreo.setFont(new java.awt.Font("Dubai Light", 0, 24)); // NOI18N
        labelCorreo.setForeground(new java.awt.Color(255, 255, 255));
        labelCorreo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelInfomacion.add(labelCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 290, 30));

        jSeparator2.setBackground(new java.awt.Color(90, 90, 90));
        jSeparator2.setForeground(new java.awt.Color(223, 223, 223));
        panelInfomacion.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 290, 10));

        btnActualizarFoto.setBackground(new java.awt.Color(51, 51, 51));
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

        txtFoto.setBackground(new java.awt.Color(51, 51, 51));
        txtFoto.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelInfomacion.add(txtFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        roundPanel1.add(panelInfomacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, 360, 350));

        roundPanel2.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Información personal");
        roundPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        roundPanel1.add(roundPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 590, 570));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 1040, 630));
        bg_panel.add(menu_Admin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 680));
        bg_panel.add(header1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void btnActualizarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarFotoActionPerformed
        filechooserFoto();
    }//GEN-LAST:event_btnActualizarFotoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {

    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkFlatIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Frm_Main_Admin().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JButton btnActualizarFoto;
    private com.raven.swing.ImageAvatar fotoUsuario;
    private plantilla.components.Header header1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelCorreo;
    private plantilla.components.Menu_Admin menu_Admin2;
    private plantilla.swing.RoundPanel panelInfomacion;
    private plantilla.swing.RoundPanel roundPanel1;
    private plantilla.swing.RoundPanel roundPanel2;
    private javax.swing.JTextField txtFoto;
    // End of variables declaration//GEN-END:variables

}
