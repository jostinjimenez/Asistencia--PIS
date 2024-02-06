package modulo_1.inicio_sesion.view.forms;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import model.Cuenta;
import modulo_1.inicio_sesion.controller.CuentaController;
import modulo_1.inicio_sesion.view.forms.mainFrm.Frm_Main_Admin;
import modulo_1.inicio_sesion.view.forms.mainFrm.Frm_Main_Docente;
import modulo_1.inicio_sesion.view.forms.mainFrm.Frm_Main_Estudiante;
import modulo_1.inicio_sesion.view.util.TextPrompt;
import modulo_1.inicio_sesion.view.util.Utiles;

/**
 * Esta clase representa el formulario de inicio de sesión.
 */
public class Frm_Inicio_Sesion extends javax.swing.JFrame {

    /**
     * Constructor de Frm_Inicio_Sesion. Inicializa los componentes del
     * formulario y establece su ubicación.
     */
    public Frm_Inicio_Sesion() {
        initComponents();
        setLocationRelativeTo(null);

        this.setResizable(false);

        TextPrompt tp = new TextPrompt("Ingrese su correo institucional", txtUsuario);
        TextPrompt tp1 = new TextPrompt("Ingrese su clave", txtClave);

    }

    CuentaController cc = new CuentaController();

    /**
     * Este método se encarga de iniciar la sesión del usuario. Valida la cuenta
     * del usuario y, si es válida, identifica el rol de la persona y abre el
     * formulario correspondiente.
     */
    public void iniciarSesion() {
        String usuario = txtUsuario.getText();
        String claveCifrada = cc.cifrar(txtClave.getText(), 10);

        if (usuario.isEmpty() || claveCifrada.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        } else {
            Cuenta cuenta = cc.validarCuenta(usuario, claveCifrada);
            cc.setCuenta(cuenta);
            Utiles.setCc(cc);
            Utiles.setCuentaUsu(cuenta);
            if (cuenta != null) {
                switch (cc.getPersona(cuenta.getPersona_id()).getRol_id()) {
                    case 1 -> {
                        Frm_Main_Admin fma = new Frm_Main_Admin(cc);
                        fma.setVisible(true);
                        this.dispose();
                    }
                    case 2 -> {
                        Frm_Main_Estudiante fme = new Frm_Main_Estudiante(cc);
                        fme.setVisible(true);
                        this.dispose();
                    }
                    case 3 -> {
                        Frm_Main_Docente fmd = new Frm_Main_Docente(cc);
                        fmd.setVisible(true);
                        this.dispose();
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
        jLabel10 = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(21, 21, 21));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel2.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roundPanel2MouseClicked(evt);
            }
        });
        roundPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setBackground(new java.awt.Color(229, 229, 229));
        jSeparator2.setForeground(new java.awt.Color(21, 21, 21));
        roundPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, 330, 30));

        jLabel3.setFont(new java.awt.Font("Dubai Light", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Iniciar Sesión");
        roundPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 190, 30));

        txtClave.setBackground(new java.awt.Color(51, 51, 51));
        txtClave.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtClave.setForeground(new java.awt.Color(204, 204, 204));
        txtClave.setBorder(null);
        roundPanel2.add(txtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 330, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plantilla/img/icons8-llave-32.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        roundPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 40, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plantilla/img/Imagen1.png"))); // NOI18N
        roundPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 320, 110));

        jSeparator4.setBackground(new java.awt.Color(229, 229, 229));
        jSeparator4.setForeground(new java.awt.Color(21, 21, 21));
        roundPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 330, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plantilla/img/icons8-usuario-32.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        roundPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 40, 40));

        btnIngresar.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        roundPanel2.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 560, 110, 30));

        txtUsuario.setBackground(new java.awt.Color(51, 51, 51));
        txtUsuario.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(204, 204, 204));
        txtUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        roundPanel2.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 330, 40));

        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setText("¿Olvidaste tu contraseña? ");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        roundPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 570, -1, -1));

        jPanel1.add(roundPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, -10, 500, 720));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/modulo_1/inicio_sesion/view/forms/img/unl.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 700, 530));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        iniciarSesion();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        NuevaClave nu = new NuevaClave(this, true);
        nu.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void roundPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roundPanel2MouseClicked

    }//GEN-LAST:event_roundPanel2MouseClicked

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
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
