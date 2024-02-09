package ModuloMatricula.Views;

import Modelo.ControllerCarrera.ControllerCarrera;
import Modelo.ControllerCarrera.Tabla.ModeloTablaCarrera;
import Modelo.ControllerCarrera.Tabla.ModeloTablaCarreras;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import model.Carrera;
import tda_listas.exceptions.VacioExceptions;

public class Frm_BuscarCarreras extends javax.swing.JFrame {

    ModeloTablaCarrera m = new ModeloTablaCarrera();
    ModeloTablaCarreras y = new ModeloTablaCarreras();
    ControllerCarrera a = new ControllerCarrera();

    public Frm_BuscarCarreras() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargar();
    }

    public void cargar() {
        m.setLista(a.list_All());
        tabla.setModel(m);
        tabla.updateUI();
    }

    private void cerrarVentana() {
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        bg_panel = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        rbutton1 = new javax.swing.JRadioButton();
        rbutton2 = new javax.swing.JRadioButton();
        txtBusqueda = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        menu_Admin1 = new plantilla.components.Menu_Admin();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(21, 21, 21));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 880, 270));

        buttonGroup1.add(rbutton1);
        rbutton1.setText("Nombre");
        rbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbutton1ActionPerformed(evt);
            }
        });
        roundPanel1.add(rbutton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        buttonGroup1.add(rbutton2);
        rbutton2.setText("Codigo");
        rbutton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbutton2ActionPerformed(evt);
            }
        });
        roundPanel1.add(rbutton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });
        roundPanel1.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 350, -1));

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setText("Busqueda de Carrera:");
        roundPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, -1, -1));

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 170, -1));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 1040, 630));
        bg_panel.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 620));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 1) {
            try {
                Carrera p = m.getCarrera(tabla.getSelectedRow());
                Frm_Maatricula.cargarCarreras(p);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void rbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbutton1ActionPerformed

    }//GEN-LAST:event_rbutton1ActionPerformed

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        if (rbutton1.isSelected()) {
            String text = txtBusqueda.getText();
            try {
                y.setCarrera(a.busquedaBinaria2(a.list_All(), text, "nombre", 0));
            } catch (VacioExceptions ex) {
            
            }
            tabla.setModel(y);
            tabla.updateUI();
        } else {
            if (rbutton2.isSelected()) {
                String text = txtBusqueda.getText();
                try {
                    y.setCarrera(a.busquedaBinaria2(a.list_All(), text, "codigo", 0));
                } catch (VacioExceptions ex) {
                }
                tabla.setModel(y);
                tabla.updateUI();
            }
        }
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void rbutton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbutton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbutton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cerrarVentana();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Frm_BuscarCarreras().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private plantilla.components.Menu_Admin menu_Admin1;
    private javax.swing.JRadioButton rbutton1;
    private javax.swing.JRadioButton rbutton2;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables

}
