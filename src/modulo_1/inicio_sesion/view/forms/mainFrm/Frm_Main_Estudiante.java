package modulo_1.inicio_sesion.view.forms.mainFrm;

import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import java.io.File;
import modulo_1.inicio_sesion.controller.CuentaController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Frm_Main_Estudiante extends javax.swing.JFrame {

    CuentaController cc;
    File foto;

    public Frm_Main_Estudiante(CuentaController cc) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.cc = cc;

        labelCorreo.setText(cc.getCuenta().getCorreo());
        txtFoto.setVisible(false);
    }

    // Metodos
    private void filechooserFoto() {
        JFileChooser fileChoosser = new JFileChooser();
        fileChoosser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Multimedia", "jpg", "png", "jpeg");
        fileChoosser.addChoosableFileFilter(filter);
        int i = fileChoosser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            foto = fileChoosser.getSelectedFile();
            txtFoto.setText(foto.getAbsolutePath());
            fotoUsuario.setIcon(new ImageIcon(foto.getAbsolutePath()));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        menu_Estudiante1 = new plantilla.components.Menu_Estudiante();
        header2 = new plantilla.components.Header();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        bg_panel.setBackground(new java.awt.Color(21, 21, 21));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        bg_panel.add(menu_Estudiante1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 680));

        header2.setBackground(new java.awt.Color(234, 238, 243));
        bg_panel.add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 1040, -1));

        roundPanel1.setBackground(new java.awt.Color(234, 238, 243));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelInfomacion.setBackground(new java.awt.Color(255, 255, 255));
        panelInfomacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fotoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plantilla/img/user.png"))); // NOI18N
        panelInfomacion.add(fotoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 220, 160));

        jSeparator1.setBackground(new java.awt.Color(90, 90, 90));
        jSeparator1.setForeground(new java.awt.Color(223, 223, 223));
        panelInfomacion.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 290, 10));

        labelCorreo.setFont(new java.awt.Font("Dubai Light", 0, 24)); // NOI18N
        labelCorreo.setForeground(new java.awt.Color(0, 0, 0));
        labelCorreo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelInfomacion.add(labelCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 290, 30));

        jSeparator2.setBackground(new java.awt.Color(90, 90, 90));
        jSeparator2.setForeground(new java.awt.Color(223, 223, 223));
        panelInfomacion.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 290, 10));

        btnActualizarFoto.setBackground(new java.awt.Color(249, 248, 248));
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

        txtFoto.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelInfomacion.add(txtFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        roundPanel1.add(panelInfomacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 360, 350));

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Información personal");
        roundPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        roundPanel1.add(roundPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 560, 570));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 1040, 630));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

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
            UIManager.setLookAndFeel(new FlatNordIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JButton btnActualizarFoto;
    private com.raven.swing.ImageAvatar fotoUsuario;
    private plantilla.components.Header header2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelCorreo;
    private plantilla.components.Menu_Estudiante menu_Estudiante1;
    private plantilla.swing.RoundPanel panelInfomacion;
    private plantilla.swing.RoundPanel roundPanel1;
    private plantilla.swing.RoundPanel roundPanel2;
    private javax.swing.JTextField txtFoto;
    // End of variables declaration//GEN-END:variables

}
