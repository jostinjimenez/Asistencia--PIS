package ModuloMatricula.Views;

import Controller.Academico.ControllerMatricula;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import modelLuis.tablas.ModelTableMatriculas;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import modulo_1.inicio_sesion.view.util.HeaderRenderer;

import java.util.Objects;

import static modulo_1.inicio_sesion.view.util.Utiles.cargarPeriodo;
import static modulo_1.inicio_sesion.view.util.Utiles.getComboPeriodo;

public class Frm_Maatricula extends javax.swing.JFrame {

    ControllerMatricula a = new ControllerMatricula();
    ModelTableMatriculas matriculas = new ModelTableMatriculas();

    public Frm_Maatricula() {
        initComponents();
        limpiar();
        cargarTabla();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

    }

    public void cargarTabla() {
        matriculas.setMatriculas(a.list_All());
        tbl1.setModel(matriculas);
        tbl1.updateUI();

        TableRowSorter<ModelTableMatriculas> trs = new TableRowSorter<>(matriculas);
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

        cargarTabla();
        try {
            cargarPeriodo(cbxPeriodoAcademico);
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtFecha.setDate(null);
    }

    public Boolean validar() {
        return !txtFecha.getDate().toString().isEmpty() && !txtIdCarrera.getText().trim().isEmpty() && !txtIdEstudiante.getText().trim().isEmpty();
    }
    

    private void cargarVista() {
        a.setIndex(tbl1.getSelectedRow());
        if (a.getIndex() < 0) {
            JOptionPane.showMessageDialog(null, "No se puede guardar correctamente");
        } else {
            try {
                a.setAsistencia(matriculas.getMatriculas().get(a.getIndex()));
                txtFecha.setDate(a.getMatricula().getFechamatricula());
            } catch (Exception e) {
                System.out.println(e + "Errooor");
            }

        }
    }

    private void modificarMatricula() {
        try {
            if (a.update()) {
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
                a.getMatricula().setCiclo(Integer.valueOf(Objects.requireNonNull(cbxCiclo.getSelectedItem()).toString()));
                a.getMatricula().setEstado_matricula((cbxEstado.getSelectedItem()).toString());
                a.getMatricula().setFechamatricula(txtFecha.getDate());
                a.getMatricula().setPeriodoacademico_id(getComboPeriodo(cbxPeriodoAcademico).getId());

                a.getMatricula().setEstudiante_id(Integer.parseInt(txtIdEstudiante.getText()));
                a.getMatricula().setCarrera_id(Integer.parseInt(txtIdCarrera.getText()));

                if (a.save() > 0) {
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
        jLabel1 = new javax.swing.JLabel();
        cbxCiclo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbxPeriodoAcademico = new javax.swing.JComboBox<>();
        cbxEstado = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        txtFecha = new com.toedter.calendar.JDateChooser();
        txtCarrera = new javax.swing.JTextField();
        txtEstudiante = new javax.swing.JTextField();
        btnCargarCarrera = new javax.swing.JButton();
        btnCargarEstudiante = new javax.swing.JButton();
        txtIdEstudiante = new javax.swing.JTextField();
        txtIdCarrera = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        menu_Admin1 = new plantilla.components.Menu_Admin();
        header2 = new plantilla.components.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg_panel.setBackground(new java.awt.Color(225, 233, 243));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setText("Fecha:");
        roundPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, -1, -1));

        cbxCiclo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxCiclo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        roundPanel1.add(cbxCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 150, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setText("Ciclo:");
        roundPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setText("Carrera:");
        roundPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setText("Estado Matricula:");
        roundPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setText("Estudiante:");
        roundPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setText("Periodo  Academico:");
        roundPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, -1, -1));

        cbxPeriodoAcademico.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxPeriodoAcademico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel1.add(cbxPeriodoAcademico, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, 210, -1));

        cbxEstado.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MATRICULADO", "APROBADO", "RETIRADO" }));
        roundPanel1.add(cbxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 210, 210, -1));

        jButton1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 120, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 570, 50, -1));

        tbl1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
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
        jScrollPane2.setViewportView(tbl1);

        roundPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 910, 200));

        jButton6.setText("Listar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 580, 130, -1));
        roundPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 150, -1));

        txtCarrera.setEditable(false);
        txtCarrera.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        roundPanel1.add(txtCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 230, -1));

        txtEstudiante.setEditable(false);
        txtEstudiante.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        roundPanel1.add(txtEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 230, -1));

        btnCargarCarrera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscarUsu.png"))); // NOI18N
        btnCargarCarrera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCargarCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarCarreraActionPerformed(evt);
            }
        });
        roundPanel1.add(btnCargarCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, -1, -1));

        btnCargarEstudiante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscarUsu.png"))); // NOI18N
        btnCargarEstudiante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCargarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarEstudianteActionPerformed(evt);
            }
        });
        roundPanel1.add(btnCargarEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, -1, -1));

        txtIdEstudiante.setEditable(false);
        roundPanel1.add(txtIdEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 50, -1));

        txtIdCarrera.setEditable(false);
        roundPanel1.add(txtIdCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 50, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 102, 102));
        jLabel7.setText("Agregar Matriculas");
        roundPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 1030, 620));
        bg_panel.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 680));

        header2.setBackground(new java.awt.Color(255, 255, 255));
        bg_panel.add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 1030, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarEstudianteActionPerformed
        buscar_Estudiante mc = new buscar_Estudiante(this, true);
        mc.setVisible(true);
    }//GEN-LAST:event_btnCargarEstudianteActionPerformed

    private void btnCargarCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarCarreraActionPerformed
        buscar_Carrera mc = new buscar_Carrera(this, true, "Frm_Maatricula" );
        mc.setVisible(true);
    }//GEN-LAST:event_btnCargarCarreraActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        cargarVista();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        modificarMatricula();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        guardar();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            new Frm_Maatricula().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JButton btnCargarCarrera;
    private javax.swing.JButton btnCargarEstudiante;
    private javax.swing.JComboBox<String> cbxCiclo;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JComboBox<String> cbxPeriodoAcademico;
    private plantilla.components.Header header2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private plantilla.components.Menu_Admin menu_Admin1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tbl1;
    protected javax.swing.JTextField txtCarrera;
    protected javax.swing.JTextField txtEstudiante;
    private com.toedter.calendar.JDateChooser txtFecha;
    protected javax.swing.JTextField txtIdCarrera;
    protected javax.swing.JTextField txtIdEstudiante;
    // End of variables declaration//GEN-END:variables

}
