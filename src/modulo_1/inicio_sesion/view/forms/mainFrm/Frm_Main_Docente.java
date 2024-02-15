package modulo_1.inicio_sesion.view.forms.mainFrm;

import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import modulo_1.inicio_sesion.controller.CuentaController;
import javax.swing.*;

public class Frm_Main_Docente extends javax.swing.JFrame {

    public Frm_Main_Docente() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    CuentaController cc;

    public Frm_Main_Docente(CuentaController cc) {
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
        roundPanel2 = new plantilla.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        headerUser1 = new plantilla.components.HeaderUser();
        menu_Docente2 = new plantilla.components.Menu_Docente();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        bg_panel.setBackground(new java.awt.Color(225, 233, 243));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Resumen");
        roundPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        bg_panel.add(roundPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 1030, 610));

        headerUser1.setBackground(new java.awt.Color(246, 246, 246));
        bg_panel.add(headerUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 1030, 60));
        bg_panel.add(menu_Docente2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 680));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

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
    private plantilla.components.HeaderUser headerUser1;
    private javax.swing.JLabel jLabel2;
    private plantilla.components.Menu_Docente menu_Docente2;
    private plantilla.swing.RoundPanel roundPanel2;
    // End of variables declaration//GEN-END:variables

}
