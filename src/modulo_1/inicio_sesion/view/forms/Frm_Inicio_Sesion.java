package modulo_1.inicio_sesion.view.forms;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import model.Cuenta;
import modulo_1.inicio_sesion.controller.CuentaController;
import modulo_1.inicio_sesion.view.forms.mainFrm.Frm_Main_Admin;
import modulo_1.inicio_sesion.view.forms.mainFrm.Frm_Main_Docente;
import modulo_1.inicio_sesion.view.forms.mainFrm.Frm_Main_Estudiante;
import modulo_1.inicio_sesion.view.util.TextPrompt;

public class Frm_Inicio_Sesion extends javax.swing.JFrame {

    public Frm_Inicio_Sesion() {
        initComponents();
        setLocationRelativeTo(null);

        this.setResizable(false);

        TextPrompt tp = new TextPrompt("Ingrese su correo institucional", txtUsuario);
        TextPrompt tp1 = new TextPrompt("Ingrese su clave", txtClave);
    }

    CuentaController cc = new CuentaController();

    public void iniciarSesion() {
        String usuario = txtUsuario.getText();
        String clave = txtClave.getText();

        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        } else {
            Cuenta cuenta = cc.validarCuenta(usuario, clave);
            if (cuenta != null) {
                switch (cc.identificarRolPersona(cc.getPersona(cuenta.getIdPersona()))) {
                    case 1 -> {
                        JOptionPane.showMessageDialog(null, "Bienvenido " + cc.getPersona(cuenta.getIdPersona()));
                        Frm_Main_Admin frm = new Frm_Main_Admin(cc);
                        frm.setVisible(true);
                        this.dispose();
                    }
                    case 2 -> {
                        JOptionPane.showMessageDialog(null, "Bienvenido " + cc.getPersona(cuenta.getIdPersona()));
                        Frm_Main_Estudiante mp = new Frm_Main_Estudiante(cc);
                        mp.setVisible(true);
                        this.dispose();
                    }
                    case 3 -> {
                        JOptionPane.showMessageDialog(null, "Bienvenido " + cc.getPersona(cuenta.getIdPersona()));
                        Frm_Main_Docente mp1 = new Frm_Main_Docente(cc);
                        mp1.setVisible(true);
                        this.dispose();
                    }
                    default -> {
                        JOptionPane.showMessageDialog(null, "No se pudo identificar el rol");
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        roundPanel2 = new plantilla.swing.RoundPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtUsuario = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(21, 21, 21));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setBackground(new java.awt.Color(229, 229, 229));
        jSeparator2.setForeground(new java.awt.Color(21, 21, 21));
        roundPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 330, 30));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(70, 89, 135));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Iniciar Sesion");
        roundPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 190, 30));

        txtClave.setBackground(new java.awt.Color(51, 51, 51));
        txtClave.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtClave.setForeground(new java.awt.Color(204, 204, 204));
        txtClave.setBorder(null);
        roundPanel2.add(txtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 330, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plantilla/img/icons8-llave-32.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        roundPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 40, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plantilla/img/Imagen1.png"))); // NOI18N
        roundPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 320, 110));

        jSeparator4.setBackground(new java.awt.Color(229, 229, 229));
        jSeparator4.setForeground(new java.awt.Color(21, 21, 21));
        roundPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 330, 30));

        txtUsuario.setBackground(new java.awt.Color(51, 51, 51));
        txtUsuario.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(204, 204, 204));
        txtUsuario.setBorder(null);
        txtUsuario.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtUsuarioAncestorAdded(evt);
            }

            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }

            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        roundPanel2.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 330, 40));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plantilla/img/icons8-usuario-32.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        roundPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 40, 40));

        btnIngresar.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        roundPanel2.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 450, 110, 30));

        jPanel1.add(roundPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 500, 530));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        iniciarSesion();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void txtUsuarioAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtUsuarioAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioAncestorAdded


    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Failed to initialize LaF");
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Frm_Inicio_Sesion().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private plantilla.swing.RoundPanel roundPanel2;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
