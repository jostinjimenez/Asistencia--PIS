/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View.Academico.modals;

import Controller.Academico.ControllerAsistencia;
import Controller.tda_listas.ListaEnlazada;
import View.Tablas.ModelTableAsistenciaEstudiante;
import View.Tablas.ModeloTablaCursosEstudiantes;
import View.Tablas.ModeloTablaDetalleAsisEstudiante;
import View.Util.HeaderRenderer;
import model.Asignatura;
import model.Asistencia;
import model.Cuenta;
import model.Cursa;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.sql.SQLOutput;

public class Detalle_Asistencia extends javax.swing.JDialog {

    Cursa c;
    Asignatura a;
    Cuenta cuenta;
    ModeloTablaDetalleAsisEstudiante mtae = new ModeloTablaDetalleAsisEstudiante();
    ControllerAsistencia ca = new ControllerAsistencia();

    public Detalle_Asistencia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    public Detalle_Asistencia(java.awt.Frame parent, boolean modal, Cursa c, Asignatura a, Cuenta cuenta) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.c = c;
        this.a = a;
        this.cuenta = cuenta;
        cargarDatos();
        cargarTabla();
    }

    public void cargarDatos(){
        lblAsignatura.setText(a.getNombre());
//        lblTotalAsistencias.setText(c.getAsistencias().toString());
//        faltasInjusti.setText(c.getFaltas_injustificadas().toString());
//        lblFaltasJusti.setText(c.getFaltas_justificadas().toString());
//        lblPorcentajeF.setText(c.getPorcentaje_faltas().toString());
    }

    public void cargarTabla(){
        ListaEnlazada<Asistencia> asistenciasPorEstudiante = ca.asistenciasPorEstudiante(cuenta.getPersona_id(), c.getId());
        mtae.setAsistencias(asistenciasPorEstudiante);
        jTable1.setModel(mtae);
        jTable1.updateUI();

        TableRowSorter<ModeloTablaDetalleAsisEstudiante> trs = new TableRowSorter<>(mtae);
        jTable1.setRowSorter(trs);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setDefaultRenderer(new HeaderRenderer());

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblAsignatura = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        faltasInjusti = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTotalAsistencias = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        lblPorcentajeF = new javax.swing.JLabel();
        lblFaltasJusti = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAsignatura.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        lblAsignatura.setForeground(new java.awt.Color(99, 134, 170));
        lblAsignatura.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAsignatura.setText("Asignatura");
        jPanel1.add(lblAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 380, -1));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Faltas Injustificadas:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Detalle de Asistencia");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 800, 30));

        faltasInjusti.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        faltasInjusti.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(faltasInjusti, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Total de Asistencias:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        lblTotalAsistencias.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblTotalAsistencias.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(lblTotalAsistencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, -1));

        jTable1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 750, 270));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(51, 51, 51));
        jSeparator2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 800, 30));

        lblPorcentajeF.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblPorcentajeF.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(lblPorcentajeF, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, -1, -1));

        lblFaltasJusti.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lblFaltasJusti.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(lblFaltasJusti, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, -1, -1));

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Faltas Justificadas:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, -1, -1));

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Porcentaje Faltas:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Detalle_Asistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Detalle_Asistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Detalle_Asistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Detalle_Asistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Detalle_Asistencia dialog = new Detalle_Asistencia(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel faltasInjusti;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAsignatura;
    private javax.swing.JLabel lblFaltasJusti;
    private javax.swing.JLabel lblPorcentajeF;
    private javax.swing.JLabel lblTotalAsistencias;
    // End of variables declaration//GEN-END:variables
}
