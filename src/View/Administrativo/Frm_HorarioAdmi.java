package View.Administrativo;

import View.Academico.modals.buscar_Asignatura;
import View.Academico.modals.buscar_Carrera;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import model.Horario;
import Controller.Administrativo.ControllerHorario;
import View.Tablas.ModelTableHorario;
import View.Util.HeaderRenderer;
import Controller.tda_listas.ListaEnlazada;

public class Frm_HorarioAdmi extends javax.swing.JFrame {

    ControllerHorario a = new ControllerHorario();
    private ModelTableHorario mt1 = new ModelTableHorario();

    public Frm_HorarioAdmi() {
        initComponents();
        limpiar();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

    }

    private void limpiar() {
//        cargarTabla();
        try {
//            Util_VistaLinked1_Asistencia.cargaMaterias(cbxAsistencia);
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtHoraI.setText("");
        txtHoraF.setText("");
    }

    public void cargarTabla() {
        ListaEnlazada<Horario>lista = a.buscarHorario(txtIdAsignatura.getText());
        mt1.setHorarios(lista);
        tblM.setModel(mt1);
        tblM.updateUI();

        TableRowSorter<ModelTableHorario> trs = new TableRowSorter<>(mt1);
        tblM.setRowSorter(trs);
        tblM.getTableHeader().setReorderingAllowed(false);
        tblM.getTableHeader().setResizingAllowed(false);
        tblM.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        tblM.setRowHeight(30); //tamaño de las celdas
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblM.getColumnCount(); i++) {
            tblM.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    private void guardarHorario() {

        try {
            a.getAsistencia().setDia(cbxDia.getSelectedItem().toString());
            a.getAsistencia().setHoraFin(txtHoraF.getText());
            a.getAsistencia().setHoraInicio(txtHoraI.getText());
            a.getAsistencia().setAsignatura_id(Integer.parseInt(txtIdAsignatura.getText()));
            if (a.save()) {
                JOptionPane.showMessageDialog(null, "Se guardó correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println(e + "Error");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timePicker1 = new com.raven.swing.TimePicker();
        timePicker2 = new com.raven.swing.TimePicker();
        bg_panel = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblM = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        cbxDia = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCarrera = new javax.swing.JTextField();
        btnCargarCarrera = new javax.swing.JButton();
        txtIdCarrera = new javax.swing.JTextField();
        txtHoraI = new javax.swing.JTextField();
        txtHoraF = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtAsignaturas = new javax.swing.JTextField();
        txtIdAsignatura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        menu_Admin2 = new plantilla.components.Menu_Admin();
        header1 = new plantilla.components.Header();

        timePicker1.setDisplayText(txtHoraI);

        timePicker2.setDisplayText(txtHoraF);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(225, 233, 243));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setForeground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblM.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblM);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 960, 220));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Hora In:");
        roundPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Hora Fin:");
        roundPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Dia:");
        roundPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 170, -1, -1));

        jButton6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        jButton6.setText("Guardar Horario");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, -1, -1));

        cbxDia.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO" }));
        roundPanel1.add(cbxDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 160, 230, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reloj.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 40, 40));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setText("Carrera:");
        roundPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        txtCarrera.setEditable(false);
        txtCarrera.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarreraActionPerformed(evt);
            }
        });
        roundPanel1.add(txtCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 230, 30));

        btnCargarCarrera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscarCarrera.png"))); // NOI18N
        btnCargarCarrera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCargarCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarCarreraActionPerformed(evt);
            }
        });
        roundPanel1.add(btnCargarCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, -1, 40));

        txtIdCarrera.setEditable(false);
        roundPanel1.add(txtIdCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 50, -1));
        roundPanel1.add(txtHoraI, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 230, 30));
        roundPanel1.add(txtHoraF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 230, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reloj.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 40, 40));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscarAsig.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 100, 40, 40));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setText("Asignatura:");
        roundPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, 90, 20));

        txtAsignaturas.setEditable(false);
        txtAsignaturas.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        roundPanel1.add(txtAsignaturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 230, 30));

        txtIdAsignatura.setEditable(false);
        txtIdAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdAsignaturaActionPerformed(evt);
            }
        });
        roundPanel1.add(txtIdAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, 50, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 153, 255));
        jLabel2.setText("Administracion de Horarios");
        roundPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        btnEliminar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 560, 60, 50));

        btnEditar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 560, 60, 50));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 1040, 620));
        bg_panel.add(menu_Admin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 680));

        header1.setBackground(new java.awt.Color(255, 255, 255));
        bg_panel.add(header1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        guardarHorario();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        timePicker2.showPopup(this, 620, 130);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCargarCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarCarreraActionPerformed
        buscar_Carrera mc = new buscar_Carrera(this, true, "Frm_HorarioAdmi");
        mc.setVisible(true);
    }//GEN-LAST:event_btnCargarCarreraActionPerformed

    private void txtCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarreraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarreraActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        timePicker1.showPopup(this, 620, 130);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (txtIdCarrera.getText() != null && !txtIdCarrera.getText().isEmpty()) {
            buscar_Asignatura mc = new buscar_Asignatura(this, true, Integer.parseInt(txtIdCarrera.getText()), "Frm_HorarioAdmi");
            mc.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una carrera");
        }
        cargarTabla();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtIdAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdAsignaturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdAsignaturaActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

    }//GEN-LAST:event_btnEditarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Frm_HorarioAdmi().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JButton btnCargarCarrera;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cbxDia;
    private plantilla.components.Header header1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private plantilla.components.Menu_Admin menu_Admin2;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tblM;
    private com.raven.swing.TimePicker timePicker1;
    private com.raven.swing.TimePicker timePicker2;
    public javax.swing.JTextField txtAsignaturas;
    public javax.swing.JTextField txtCarrera;
    private javax.swing.JTextField txtHoraF;
    private javax.swing.JTextField txtHoraI;
    public javax.swing.JTextField txtIdAsignatura;
    public javax.swing.JTextField txtIdCarrera;
    // End of variables declaration//GEN-END:variables

}
