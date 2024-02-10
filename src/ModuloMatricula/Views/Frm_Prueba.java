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
        menu_Admin1 = new plantilla.components.Menu_Admin();
        header1 = new plantilla.components.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(225, 233, 243));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 1000, 610));
        bg_panel.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 220, -1));

        header1.setBackground(new java.awt.Color(255, 255, 255));
        bg_panel.add(header1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 1000, -1));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

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
    private plantilla.components.Header header1;
    private plantilla.components.Menu_Admin menu_Admin1;
    private plantilla.swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables

}
