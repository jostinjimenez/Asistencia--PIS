package inicio_sesion.view.forms;

import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarkLaf;
import inicio_sesion.view.util.TextPrompt;
import javax.swing.UnsupportedLookAndFeelException;

public class Frm_Inicio_Sesion extends javax.swing.JFrame {

    public Frm_Inicio_Sesion() {
        initComponents();
        setLocationRelativeTo(null);

        this.setResizable(false);

        TextPrompt tp = new TextPrompt("Ingrese su correo institucional", txtUsuario);
        TextPrompt tp1 = new TextPrompt("Ingrese su clave", txtClave);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtUsuario = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        buttonMenu1 = new com.raven.swing.ButtonMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(21, 21, 21));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator2.setBackground(new java.awt.Color(229, 229, 229));
        jSeparator2.setForeground(new java.awt.Color(21, 21, 21));
        roundPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, 330, 30));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(70, 89, 135));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Iniciar Sesion");
        roundPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 190, 30));

        txtClave.setBackground(new java.awt.Color(51, 51, 51));
        txtClave.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtClave.setForeground(new java.awt.Color(204, 204, 204));
        txtClave.setBorder(null);
        roundPanel1.add(txtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 330, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inicio_sesion/view/img/icons8-llave-32.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        roundPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 40, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inicio_sesion/view/img/Imagen1.png"))); // NOI18N
        roundPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 320, 110));

        jSeparator4.setBackground(new java.awt.Color(229, 229, 229));
        jSeparator4.setForeground(new java.awt.Color(21, 21, 21));
        roundPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 330, 30));

        txtUsuario.setBackground(new java.awt.Color(51, 51, 51));
        txtUsuario.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(204, 204, 204));
        txtUsuario.setBorder(null);
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        roundPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 330, 40));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inicio_sesion/view/img/icons8-usuario-32.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        roundPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 40, 40));

        buttonMenu1.setForeground(new java.awt.Color(153, 153, 153));
        buttonMenu1.setText("Ingresar");
        buttonMenu1.setFont(new java.awt.Font("Roboto", 1, 17)); // NOI18N
        roundPanel1.add(buttonMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 450, -1, -1));

        jPanel1.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 500, 530));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        }
        catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Failed to initialize LaF");
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Frm_Inicio_Sesion().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.ButtonMenu buttonMenu1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
