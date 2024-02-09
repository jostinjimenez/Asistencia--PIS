package ModuloMatricula.Views;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;

public class Frm_Prueba extends javax.swing.JFrame {

    public Frm_Prueba() {
        initComponents();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        roundPanel2 = new plantilla.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        roundPanel7 = new plantilla.swing.RoundPanel();
        roundPanel3 = new plantilla.swing.RoundPanel();
        roundPanel4 = new plantilla.swing.RoundPanel();
        jLabel3 = new javax.swing.JLabel();
        roundPanel5 = new plantilla.swing.RoundPanel();
        jLabel4 = new javax.swing.JLabel();
        roundPanel6 = new plantilla.swing.RoundPanel();
        jLabel5 = new javax.swing.JLabel();
        roundPanel9 = new plantilla.swing.RoundPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(225, 233, 243));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 1020, 550));

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel2.add(jLabel2);
        roundPanel2.add(roundPanel7);

        roundPanel3.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel2.add(roundPanel3);

        bg_panel.add(roundPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 1020, 60));

        roundPanel4.setBackground(new java.awt.Color(255, 102, 102));
        roundPanel4.add(jLabel3);

        roundPanel5.setBackground(new java.awt.Color(255, 102, 102));
        roundPanel5.add(jLabel4);

        roundPanel4.add(roundPanel5);

        roundPanel6.setBackground(new java.awt.Color(255, 102, 102));
        roundPanel6.add(jLabel5);

        roundPanel4.add(roundPanel6);

        bg_panel.add(roundPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 60));

        roundPanel9.setBackground(new java.awt.Color(255, 255, 255));
        bg_panel.add(roundPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 220, 550));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        }
        catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Frm_Prueba().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private plantilla.swing.RoundPanel roundPanel1;
    private plantilla.swing.RoundPanel roundPanel2;
    private plantilla.swing.RoundPanel roundPanel3;
    private plantilla.swing.RoundPanel roundPanel4;
    private plantilla.swing.RoundPanel roundPanel5;
    private plantilla.swing.RoundPanel roundPanel6;
    private plantilla.swing.RoundPanel roundPanel7;
    private plantilla.swing.RoundPanel roundPanel9;
    // End of variables declaration//GEN-END:variables

}
