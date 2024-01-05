package modelLuis.view;

import plantilla.forms.*;
import com.formdev.flatlaf.FlatDarkLaf;
import java.lang.System.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import model.Asistencia;
import model.Cursa;
import model.Estudiante;
import model.Matricula;
import model.catalogo.TipoFalta;
import modelLuis.controller.ControllerAsistencia;
import modelLuis.controller.ControllerCursa;
import modelLuis.controller.ControllerMatricula;
import modelLuis.controller.ControllerTematica;
import modelLuis.tablas.ModelTableAsistencia;
import modelLuis.tablas.ModelTableAsistencia2;
import modelLuis.view.util.Util_VistaLinked1_Asistencia;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class Frm_AsistenciaJ extends javax.swing.JFrame {

    ControllerMatricula c = new ControllerMatricula();
    ControllerCursa cd = new ControllerCursa();
    ModelTableAsistencia md = new ModelTableAsistencia();
    ControllerAsistencia ca = new ControllerAsistencia();
    ListaEnlazada<Cursa> mat = new ListaEnlazada<>();
    ListaEnlazada<Asistencia> list = new ListaEnlazada<>();
    ControllerTematica ct = new ControllerTematica();
    ListaEnlazada<Matricula> result = new ListaEnlazada<>();
    ModelTableAsistencia2 ad = new ModelTableAsistencia2();
    Integer idtematica;

    public Frm_AsistenciaJ() {
        initComponents();
        limpiar();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void limpiar() {
        try {
            Util_VistaLinked1_Asistencia.cargaHorario(cbxHorario);
        } catch (Exception e) {
            e.printStackTrace();
        }

        txtFecha.setText(ct.getTematica().generarFecha());
        txtFecha.setEnabled(false);
    }

    private void actualizarTabla() throws VacioExceptions {
        ListaEnlazada<Estudiante> df = md.getEstudiantes();
        ad.setEstudiantes(df);
        ad.setAsistencias(list);
        ad.setMatriculas(result);
        tblAsis.setModel(ad);
        tblAsis.updateUI();
        ad.fireTableDataChanged();

    }

    public Boolean validar() {
        return !txtFecha.getText().trim().isEmpty()
                && !txtNombre.getText().trim().isEmpty();
    }

    public void modificar() {
        int fila = tblAsis.getSelectedRow();
        TipoFalta a = (TipoFalta) this.tblAsis.getValueAt(fila, 2);
        ca.getAsistencia().setFalta(a);
        System.out.println(a + "gagag");
        ca.setIndex(fila);
        if (ca.update1(ca.getIndex())) {
            JOptionPane.showMessageDialog(null, "Se ha modificado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void guardar() {
        if (validar()) {
            try {
                for (Cursa cursa : mat) {
                    ca.getAsistencia().setIdCursa(cursa.getId());
                    ca.getAsistencia().setIdHorario(Util_VistaLinked1_Asistencia.getComboHorario(cbxHorario).getId());
                    ca.getAsistencia().setIdTematica(idtematica);
                    ca.getAsistencia().setId(ca.generatedId());
                    ca.getAsistencia().setFalta(TipoFalta.ASISTIO);
                    if (ca.saved()) {
                        list.add(ca.getAsistencia());
                        JOptionPane.showMessageDialog(null, "Se guardó correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
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

    public void guardarTematica() {
        if (validar()) {
            try {
                ct.getTematica().setFecha(txtFecha.getText());
                ct.getTematica().setNombre(txtNombre.getText());
                idtematica = ct.generatedId();
                ct.getTematica().setId(idtematica);
                if (ct.saved()) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Se guardo correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                System.out.println(e + "Errooor");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
        }
    }

    public void listar() throws VacioExceptions {

        String paralelo = txtParalelo.getText();
        ListaEnlazada<Cursa> listCur = cd.busquedaBinaria(cd.list_All(), paralelo, "paralelo", "quicksort", 0);
        Integer ciclo = cbxCiclos.getSelectedIndex() + 1;
        String nciclo = ciclo.toString();
        ListaEnlazada<Matricula> lista1 = c.busquedaBinaria(c.list_All(), nciclo, "ciclo", "quicksort", 0);
        for (Cursa cursa : listCur) {
            for (Matricula matricula : lista1) {
                if (cursa.getIdMatricula().equals(matricula.getId())) {
                    result.add(matricula);
                    mat.add(cursa);
                    break;
                }
            }
        }
        md.setMatriculas(result);
        tblAsis.setModel(md);
        tblAsis.updateUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        roundPanel2 = new plantilla.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        txtParalelo = new javax.swing.JTextField();
        cbxCiclos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        buttonMenu2 = new com.raven.swing.ButtonMenu();
        buttonMenu4 = new com.raven.swing.ButtonMenu();
        roundPanel3 = new plantilla.swing.RoundPanel();
        txtNombre = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxHorario = new javax.swing.JComboBox<>();
        buttonMenu5 = new com.raven.swing.ButtonMenu();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsis = new javax.swing.JTable();
        buttonMenu1 = new com.raven.swing.ButtonMenu();
        buttonMenu3 = new com.raven.swing.ButtonMenu();
        menu_Docente1 = new plantilla.components.Menu_Docente();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(21, 21, 21));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Paralelo:");

        cbxCiclos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        cbxCiclos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCiclosItemStateChanged(evt);
            }
        });

        jLabel2.setText("Ciclo:");

        buttonMenu2.setText("Buscar Lista");
        buttonMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMenu2ActionPerformed(evt);
            }
        });

        buttonMenu4.setText("           Asistencia");
        buttonMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMenu4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonMenu2, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(txtParalelo)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addComponent(cbxCiclos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(buttonMenu4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtParalelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxCiclos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonMenu4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        roundPanel1.add(roundPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 440, -1));

        jLabel3.setText("Tema de la clase:");

        jLabel4.setText("Fecha de la clase:");

        cbxHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonMenu5.setText("      Guardar Tematica");
        buttonMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMenu5ActionPerformed(evt);
            }
        });

        jLabel5.setText("Horario:");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addComponent(cbxHorario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buttonMenu5, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonMenu5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        roundPanel1.add(roundPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 460, 140));

        tblAsis.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblAsis);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 910, 320));

        buttonMenu1.setBackground(new java.awt.Color(102, 102, 102));
        buttonMenu1.setText("       Modificar Tabla");
        buttonMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMenu1ActionPerformed(evt);
            }
        });
        roundPanel1.add(buttonMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 160, -1));

        buttonMenu3.setText("        Actualizar Tabla");
        buttonMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMenu3ActionPerformed(evt);
            }
        });
        roundPanel1.add(buttonMenu3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 160, -1));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 1040, 620));
        bg_panel.add(menu_Docente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxCiclosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCiclosItemStateChanged

    }//GEN-LAST:event_cbxCiclosItemStateChanged

    private void buttonMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonMenu1ActionPerformed

    private void buttonMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMenu2ActionPerformed
        try {
            listar();
        } catch (VacioExceptions ex) {

        }
    }//GEN-LAST:event_buttonMenu2ActionPerformed

    private void buttonMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMenu3ActionPerformed
        try {
            actualizarTabla();
        } catch (VacioExceptions ex) {

        }
    }//GEN-LAST:event_buttonMenu3ActionPerformed

    private void buttonMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMenu4ActionPerformed
        guardar();
    }//GEN-LAST:event_buttonMenu4ActionPerformed

    private void buttonMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMenu5ActionPerformed
      guardarTematica();
    }//GEN-LAST:event_buttonMenu5ActionPerformed

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
            new Frm_AsistenciaJ().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private com.raven.swing.ButtonMenu buttonMenu1;
    private com.raven.swing.ButtonMenu buttonMenu2;
    private com.raven.swing.ButtonMenu buttonMenu3;
    private com.raven.swing.ButtonMenu buttonMenu4;
    private com.raven.swing.ButtonMenu buttonMenu5;
    private javax.swing.JComboBox<String> cbxCiclos;
    private javax.swing.JComboBox<String> cbxHorario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private plantilla.components.Menu_Docente menu_Docente1;
    private plantilla.swing.RoundPanel roundPanel1;
    private plantilla.swing.RoundPanel roundPanel2;
    private plantilla.swing.RoundPanel roundPanel3;
    private javax.swing.JTable tblAsis;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtParalelo;
    // End of variables declaration//GEN-END:variables

}
