package modulo_1.inicio_sesion.view.forms.mainFrm;

import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;
import modulo_1.inicio_sesion.controller.CuentaController;
import javax.swing.*;


public class Frm_Main_Admin extends javax.swing.JFrame {

    CuentaController cc;

    public Frm_Main_Admin(CuentaController cc) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.cc = cc;
    }

    public Frm_Main_Admin() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }



    // Metodos
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        menu_Admin2 = new plantilla.components.Menu_Admin();
        header1 = new plantilla.components.Header();
        roundPanel2 = new plantilla.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(225, 233, 243));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        bg_panel.add(menu_Admin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 680));

        header1.setBackground(new java.awt.Color(246, 246, 246));
        bg_panel.add(header1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        roundPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Area Personal");
        roundPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        bg_panel.add(roundPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 1040, 610));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

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
    private plantilla.components.Header header1;
    private javax.swing.JLabel jLabel2;
    private plantilla.components.Menu_Admin menu_Admin2;
    private plantilla.swing.RoundPanel roundPanel2;
    // End of variables declaration//GEN-END:variables

}
