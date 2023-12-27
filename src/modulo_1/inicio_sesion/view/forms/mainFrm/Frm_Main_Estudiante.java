package modulo_1.inicio_sesion.view.forms.mainFrm;

import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import modulo_1.inicio_sesion.controller.CuentaController;

import javax.swing.*;


public class Frm_Main_Estudiante extends javax.swing.JFrame {
    CuentaController cc;

    public Frm_Main_Estudiante(CuentaController cc) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.cc = cc;
    }


    // Metodos




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        txtUsername = new javax.swing.JLabel();
        menu_Estudiante1 = new plantilla.components.Menu_Estudiante();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(21, 21, 21));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 1040, 620));

        txtUsername.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtUsername.setText("jLabel1");
        bg_panel.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 120, 20));
        bg_panel.add(menu_Estudiante1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 620));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       txtUsername.setText(cc.getPersona(cc.getCuenta().getIdPersona()).toString());
    }//GEN-LAST:event_formWindowOpened

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
    private plantilla.components.Menu_Estudiante menu_Estudiante1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JLabel txtUsername;
    // End of variables declaration//GEN-END:variables

}
