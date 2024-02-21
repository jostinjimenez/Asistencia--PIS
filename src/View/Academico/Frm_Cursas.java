package View.Academico;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import View.Tablas.ModeloTablaCursas;
import Controller.Academico.ControllerCursa;
import View.Academico.modals.buscar_Asignatura;
import View.Academico.modals.buscar_Docente;
import View.Academico.modals.buscar_Matriculas;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import View.Util.HeaderRenderer;

import java.util.Objects;

public class Frm_Cursas extends javax.swing.JFrame {

    ControllerCursa cc = new ControllerCursa();
    ModeloTablaCursas mtc = new ModeloTablaCursas();

    public Frm_Cursas() {
        initComponents();
        limpiar();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        btnCargarAsignatura.setEnabled(false);
    }

    public void cargarTabla() {
        mtc.setCursas(cc.list_All());
        tbl1.setModel(mtc);
        tbl1.updateUI();

        TableRowSorter<ModeloTablaCursas> trs = new TableRowSorter<>(mtc);
        tbl1.setRowSorter(trs);
        tbl1.getTableHeader().setReorderingAllowed(false);
        tbl1.getTableHeader().setResizingAllowed(false);
        tbl1.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        tbl1.setRowHeight(30); // Ajusta este valor según tus necesidades

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tbl1.getColumnCount(); i++) {
            tbl1.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }


    private void limpiar() {
        txtIdMatricula.setText("");
        txtMatricula.setText("");
        txtIdDocente.setText("");
        txtDocente.setText("");
        txtIdAsignatura.setText("");
        txtAsignaturas.setText("");
        cbxParalelo.setSelectedIndex(0);

        cargarTabla();
    }

    public Boolean validar() {
        return !txtIdMatricula.getText().trim().isEmpty()
                && !txtIdDocente.getText().trim().isEmpty();
    }


    private void cargarVista() {
        cc.setIndex(tbl1.getSelectedRow());
        if (cc.getIndex() < 0) {
            JOptionPane.showMessageDialog(null, "No se puede guardar correctamente");
        } else {
            try {
                cc.setCursa(mtc.getCursas().get(cc.getIndex()));
                cbxParalelo.setSelectedItem(cc.getCursa().getParalelo());
            } catch (Exception e) {
                System.out.println(e + "Errooor");
            }

        }
    }

    private void modificarMatricula() {
        try {
            if (cc.update()) {
                JOptionPane.showMessageDialog(null, "Se ha modificado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e + "Errooor");
        }
    }

    public void guardar() {
        if (validar()) {
            try {
                cc.getCursa().setParalelo(Objects.requireNonNull(cbxParalelo.getSelectedItem()).toString());
                cc.getCursa().setAsignatura_id(Integer.valueOf(txtIdAsignatura.getText()));
                cc.getCursa().setDocente_id(Integer.valueOf(txtIdDocente.getText()));
                cc.getCursa().setMatricula_id(Integer.valueOf(txtIdMatricula.getText()));

                if (cc.save() > 0) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Se guardó correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtMatricula = new javax.swing.JTextField();
        txtDocente = new javax.swing.JTextField();
        btnCargarMatricula = new javax.swing.JButton();
        btnCargarDocente = new javax.swing.JButton();
        txtIdDocente = new javax.swing.JTextField();
        txtIdMatricula = new javax.swing.JTextField();
        cbxParalelo = new javax.swing.JComboBox<>();
        txtAsignaturas = new javax.swing.JTextField();
        txtIdAsignatura = new javax.swing.JTextField();
        btnCargarAsignatura = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        menu_Admin1 = new plantilla.components.Menu_Admin();
        header2 = new plantilla.components.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg_panel.setBackground(new java.awt.Color(225, 233, 243));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setText("Matricula:");
        roundPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("Paralelo:");
        roundPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("Docente:");
        roundPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Asignaturas:");
        roundPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, -1, -1));

        jButton1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 130, -1));

        jButton4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jButton4.setText("Modificar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 580, 130, -1));

        jButton6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jButton6.setText("Listar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 580, 130, -1));

        txtMatricula.setEditable(false);
        txtMatricula.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        roundPanel1.add(txtMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 290, -1));

        txtDocente.setEditable(false);
        txtDocente.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        roundPanel1.add(txtDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 290, -1));

        btnCargarMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscarUsu.png"))); // NOI18N
        btnCargarMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCargarMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarMatriculaActionPerformed(evt);
            }
        });
        roundPanel1.add(btnCargarMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, -1, -1));

        btnCargarDocente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscarUsu.png"))); // NOI18N
        btnCargarDocente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCargarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarDocenteActionPerformed(evt);
            }
        });
        roundPanel1.add(btnCargarDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, -1, -1));

        txtIdDocente.setEditable(false);
        roundPanel1.add(txtIdDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 50, -1));

        txtIdMatricula.setEditable(false);
        roundPanel1.add(txtIdMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 50, -1));

        cbxParalelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D" }));
        roundPanel1.add(cbxParalelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, 220, -1));

        txtAsignaturas.setEditable(false);
        txtAsignaturas.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        roundPanel1.add(txtAsignaturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 230, -1));

        txtIdAsignatura.setEditable(false);
        roundPanel1.add(txtIdAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 50, -1));

        btnCargarAsignatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscarUsu.png"))); // NOI18N
        btnCargarAsignatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCargarAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarAsignaturaActionPerformed(evt);
            }
        });
        roundPanel1.add(btnCargarAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, -1, -1));

        tbl1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tbl1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl1);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 950, 250));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 1030, 620));
        bg_panel.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 680));

        header2.setBackground(new java.awt.Color(255, 255, 255));
        bg_panel.add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 1030, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarDocenteActionPerformed
        buscar_Docente mc = new buscar_Docente(this, true);
        mc.setVisible(true);
    }//GEN-LAST:event_btnCargarDocenteActionPerformed

    private void btnCargarMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarMatriculaActionPerformed
        buscar_Matriculas mc = new buscar_Matriculas(this, true);
        mc.setVisible(true);
        if (txtIdMatricula.getText() != null && !txtIdMatricula.getText().isEmpty()) {
            btnCargarAsignatura.setEnabled(true); // Habilita el botón después de seleccionar una matrícula
        }
    }//GEN-LAST:event_btnCargarMatriculaActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        cargarVista();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        modificarMatricula();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        guardar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCargarAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarAsignaturaActionPerformed
        if (txtIdMatricula.getText() != null && !txtIdMatricula.getText().isEmpty()){
            buscar_Asignatura mc = new buscar_Asignatura(this, true, Integer.valueOf(txtIdMatricula.getText()),"Frm_Cursas");
            mc.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una matricula");
        }
    }//GEN-LAST:event_btnCargarAsignaturaActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Frm_Cursas().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JButton btnCargarAsignatura;
    private javax.swing.JButton btnCargarDocente;
    private javax.swing.JButton btnCargarMatricula;
    private javax.swing.JComboBox<String> cbxParalelo;
    private plantilla.components.Header header2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private plantilla.components.Menu_Admin menu_Admin1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tbl1;
    public javax.swing.JTextField txtAsignaturas;
    public javax.swing.JTextField txtDocente;
    public javax.swing.JTextField txtIdAsignatura;
    public javax.swing.JTextField txtIdDocente;
    public javax.swing.JTextField txtIdMatricula;
    public javax.swing.JTextField txtMatricula;
    // End of variables declaration//GEN-END:variables

}