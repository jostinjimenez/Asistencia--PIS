package Modulo.Matricula.Vista;

import ModuloMatricula.Views.UtilVista.Util_VistaLinked_Matricula;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import model.Asignatura;
import model.catalogo.EstadoMatricula;
import modelLuis.controller.ControllerCursa;
import modelLuis.controller.ControllerMatricula;
import modelLuis.tablas.ModelTableMateria;
import modelLuis.tablas.ModelTableMatriculas;


import tda_listas.ListaEnlazada;

public class Frm_Matricula extends javax.swing.JFrame {

    ControllerMatricula a = new ControllerMatricula();
    private static ModelTableMateria z = new ModelTableMateria();
    private static ListaEnlazada<Asignatura> n = new ListaEnlazada<>();
    ControllerCursa c = new ControllerCursa();
    ListaEnlazada<Integer> ids2 = new ListaEnlazada<>();
    Integer id = 0;
    ModelTableMatriculas matriculas = new ModelTableMatriculas();

    public Frm_Matricula() {
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
    }

    public static void cargarMaterias(Asignatura asignatura) {
        n.add(asignatura);
        z.setAsignaturas(n);
        tabla.setModel(z);
        tabla.updateUI();
    }

    private void limpiar() {

        cargarTabla();
        txtCarrera.setText(" ");
        try {
            Util_VistaLinked_Matricula.cargaEstudiante(cbxEstudiante);
            Util_VistaLinked_Matricula.cargaPeriodoAcademico(cbxPeriodoAcademico);
            Util_VistaLinked_Matricula.cargaDocente(cbxDocente);
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtFecha.setText(a.getMatricula().generarFecha());
        txtFecha.setEnabled(false);

    }

    public Boolean validar() {
        return !txtFecha.getText().trim().isEmpty()
                && !txtCarrera.getText().trim().isEmpty();
    }

    public Boolean validar2() {
        return !txtParalelo.getText().trim().isEmpty();
    }

    public void guadarCursa() {
        if (validar2()) {
            try {
                for (Asignatura asig : n) {
                    c.getAsistencia().setIdDocente(Util_VistaLinked_Matricula.getComboDocente(cbxDocente).getId());
                    c.getAsistencia().setParalelo(txtParalelo.getText());
                    c.getAsistencia().setIdMatricula(id);
                    c.getAsistencia().setIdAsignatura(asig.getId());

                    int id_cursa = c.generarID();
                    c.getAsistencia().setId(id_cursa);
                    if (c.saved()) {

                        JOptionPane.showMessageDialog(null, "Se guardó correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
                        ids2.add(id_cursa);
                        System.out.println(id_cursa);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception e) {
                System.out.println(e + "Error");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
        }

    }

    private void cargarVista() {
        a.setIndex(tbl1.getSelectedRow());
        if (a.getIndex() < 0) {
            JOptionPane.showMessageDialog(null, "No se puede guardar correctamente");
        } else {
            try {
                a.setAsistencia(matriculas.getMatriculas().get(a.getIndex()));
                txtCarrera.setText(a.getMatricula().getCarrera());
                txtFecha.setText(a.getMatricula().getFechaMatricula());
            } catch (Exception e) {
                System.out.println(e + "Errooor");
            }

        }
    }

    private void modificarMatricula() {
        try {
            a.getMatricula().setId_cursas(ids2);
            if (a.update1(a.getIndex())) {
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
                EstadoMatricula es = EstadoMatricula.MATRICULADO;
                String criterio = cbxEstado.getSelectedItem().toString();
                if (criterio.equalsIgnoreCase("Matriculado")) {
                    es = EstadoMatricula.MATRICULADO;
                } else if (criterio.equalsIgnoreCase("Reprobado")) {
                    es = EstadoMatricula.REPROBADO;
                } else if (criterio.equalsIgnoreCase("Aprobado")) {
                    es = EstadoMatricula.APROBADO;
                } else if (criterio.equalsIgnoreCase("Retirado")) {
                    es = EstadoMatricula.RETIRADO;
                }
                String cicloStr = cbxCiclo.getSelectedItem().toString();
                int ciclo = Integer.parseInt(cicloStr);
                a.getMatricula().setCarrera(txtCarrera.getText());
                a.getMatricula().setCiclo(ciclo);
                a.getMatricula().setEstado(es);
                a.getMatricula().setFechaMatricula(txtFecha.getText());
                a.getMatricula().setIdEstudiante(Util_VistaLinked_Matricula.getComboEstudiante(cbxEstudiante).getId());
                a.getMatricula().setIdPeriodoAcademico(Util_VistaLinked_Matricula.getComboPeriodo(cbxPeriodoAcademico).getId());
                id = a.generarID();
                a.getMatricula().setId(id);
                ListaEnlazada<Integer> ids = new ListaEnlazada();
                ids.add(0);
                a.getMatricula().setId_cursas(ids);
                if (a.saved()) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Se guardó correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                System.out.println(e + "Error");
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
        txtFecha = new javax.swing.JTextField();
        cbxCiclo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCarrera = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxEstudiante = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbxPeriodoAcademico = new javax.swing.JComboBox<>();
        cbxEstado = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtParalelo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        cbxDocente = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        menu_Admin1 = new plantilla.components.Menu_Admin();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(21, 21, 21));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Fecha:");
        roundPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));
        roundPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 130, -1));

        cbxCiclo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        roundPanel1.add(cbxCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 130, -1));

        jLabel2.setText("Ciclo:");
        roundPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel3.setText("Carrera:");
        roundPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));
        roundPanel1.add(txtCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 140, -1));

        jLabel4.setText("Estado Matricula:");
        roundPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, -1, -1));

        jLabel5.setText("Estudiante:");
        roundPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, -1, -1));

        cbxEstudiante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel1.add(cbxEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 160, -1));

        jLabel6.setText("Periodo  Academico:");
        roundPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, -1));

        cbxPeriodoAcademico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel1.add(cbxPeriodoAcademico, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 140, -1));

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matriculado", "Reprobado", "Aprobado", "Retirado" }));
        roundPanel1.add(cbxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 140, -1));

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 190, 130, -1));

        jLabel7.setText("Paralelo:");
        roundPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));
        roundPanel1.add(txtParalelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 130, -1));

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
        jScrollPane1.setViewportView(tabla);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 710, 110));

        jButton2.setText("Cargar Materias");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 480, 140, -1));

        cbxDocente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel1.add(cbxDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 140, -1));

        jLabel9.setText("Docente:");
        roundPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, -1, -1));

        jButton3.setText("Guardar Paralelo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 440, 140, -1));

        jButton4.setText("Modificar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 270, 130, -1));

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

        roundPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 710, 90));

        jButton6.setText("Listar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 230, 130, -1));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 1040, 620));
        bg_panel.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        guardar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new Frm_BuscarMateria().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        guadarCursa();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        modificarMatricula();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        cargarVista();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            new Frm_Matricula().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JComboBox<String> cbxCiclo;
    private javax.swing.JComboBox<String> cbxDocente;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JComboBox<String> cbxEstudiante;
    private javax.swing.JComboBox<String> cbxPeriodoAcademico;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private plantilla.components.Menu_Admin menu_Admin1;
    private plantilla.swing.RoundPanel roundPanel1;
    private static javax.swing.JTable tabla;
    private javax.swing.JTable tbl1;
    private javax.swing.JTextField txtCarrera;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtParalelo;
    // End of variables declaration//GEN-END:variables

}
