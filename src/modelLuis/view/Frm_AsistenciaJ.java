package modelLuis.view;

import ModuloMatricula.Controller.ControllerMatricula;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import model.Asignatura;
import model.Asistencia;
import model.Cursa;
import model.Estudiante;
import model.Matricula;
import model.catalogo.TipoFalta;
import modelLuis.controller.ControllerAsignatura;
import modelLuis.controller.ControllerAsistencia;
import modelLuis.controller.ControllerCursa;
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
    Integer id = 0;

    public Frm_AsistenciaJ() {
        initComponents();
        try {
            cargarCombos();
        } catch (Exception e) {
            System.out.println(e + "Errros");
        }
        limpiar();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void cargarCombos() throws VacioExceptions {
        ControllerCursa cc = new ControllerCursa();
        ControllerAsignatura ca = new ControllerAsignatura();
        ControllerMatricula cm = new ControllerMatricula();
        ListaEnlazada<Asignatura> listaAsig = new ListaEnlazada<>();
        ListaEnlazada<Cursa> listaCursa = new ListaEnlazada<>();
        ListaEnlazada<Matricula> listMatr = new ListaEnlazada<>();
        listaCursa = cc.busquedaBinaria(cc.list_All(), id.toString(), "id_docente", "quicksort", 0);
        for (Cursa cursa : listaCursa) {
            Matricula matricula = cm.busquedaBinaria2(cm.list_All(), cursa.getMatricula_id().toString(), "id");
            Asignatura asig = ca.busquedaBinaria2(ca.list_All(), cursa.getAsignatura_id().toString(), "id", 0);
            listaAsig.add(asig);
            listMatr.add(matricula);
        }
        Util_VistaLinked1_Asistencia.setListaMatr(listMatr);
        Util_VistaLinked1_Asistencia.setListaAsig(listaAsig);
        Util_VistaLinked1_Asistencia.setListaCursa(listaCursa);
    }

    private void limpiar() {

        try {
            Util_VistaLinked1_Asistencia.cargaCiclos(cbxCiclos);
            Util_VistaLinked1_Asistencia.cargaHorario(cbxHorario);
            Util_VistaLinked1_Asistencia.cargaAsig(cbxAsig);
            Util_VistaLinked1_Asistencia.cargaParalelos(cbxParalelos);
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
        String paralelo = Util_VistaLinked1_Asistencia.getComboCursa(cbxParalelos).getParalelo();
        ListaEnlazada<Cursa> listCur = cd.busquedaBinaria(cd.list_All(), paralelo, "paralelo", "quicksort", 0);

        Integer ciclo = Util_VistaLinked1_Asistencia.getComboMatricula(cbxCiclos).getCiclo();
        String nciclo = ciclo.toString();
        ListaEnlazada<Matricula> lista1 = c.busquedaBinaria(c.list_All(), nciclo, "ciclo", "quicksort", 0);
        for (Cursa cursa : listCur) {
            for (Matricula matricula : lista1) {
                if (cursa.getMatricula_id().equals(matricula.getId())) {
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
        cbxCiclos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbxAsig = new javax.swing.JComboBox<>();
        cbxParalelos = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        roundPanel3 = new plantilla.swing.RoundPanel();
        txtNombre = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxHorario = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsis = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnGuardarTem = new javax.swing.JButton();
        btnActualizarTab = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        menu_Docente1 = new plantilla.components.Menu_Docente();
        headerUser1 = new plantilla.components.HeaderUser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg_panel.setBackground(new java.awt.Color(225, 233, 243));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Paralelo:");
        roundPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 6, -1, -1));

        cbxCiclos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        cbxCiclos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCiclosItemStateChanged(evt);
            }
        });
        roundPanel2.add(cbxCiclos, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 28, 150, -1));

        jLabel2.setText("Ciclo:");
        roundPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 6, -1, -1));

        cbxAsig.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel2.add(cbxAsig, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 28, 136, -1));

        cbxParalelos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel2.add(cbxParalelos, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 88, 136, -1));

        jLabel6.setText("Asignaturas:");
        roundPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 66, -1, -1));

        roundPanel1.add(roundPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 440, 130));

        jLabel3.setText("Tema de la clase:");

        jLabel4.setText("Fecha de la clase:");

        cbxHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel1.add(roundPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 460, 130));

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

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 160, 130, 30));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 130, 30));

        btnGuardarTem.setText("Guardar Tematica");
        btnGuardarTem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTemActionPerformed(evt);
            }
        });
        roundPanel1.add(btnGuardarTem, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 130, 30));

        btnActualizarTab.setText("Actualizar Tabla");
        btnActualizarTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarTabActionPerformed(evt);
            }
        });
        roundPanel1.add(btnActualizarTab, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 130, 30));

        btnListar.setText("Listar");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnListar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, 130, 30));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 1030, 620));
        bg_panel.add(menu_Docente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 680));

        headerUser1.setBackground(new java.awt.Color(246, 246, 246));
        bg_panel.add(headerUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 1030, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxCiclosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCiclosItemStateChanged

    }//GEN-LAST:event_cbxCiclosItemStateChanged

    private void buttonMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMenu1ActionPerformed
        modificar();
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarTemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTemActionPerformed
        guardarTematica();
    }//GEN-LAST:event_btnGuardarTemActionPerformed

    private void btnActualizarTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarTabActionPerformed
        try {
            actualizarTabla();
        } catch (VacioExceptions ex) {

        }
    }//GEN-LAST:event_btnActualizarTabActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        try {
            listar();
        } catch (VacioExceptions ex) {

        }
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
    }//GEN-LAST:event_btnModificarActionPerformed

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
    private javax.swing.JButton btnActualizarTab;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarTem;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbxAsig;
    private javax.swing.JComboBox<String> cbxCiclos;
    private javax.swing.JComboBox<String> cbxHorario;
    private javax.swing.JComboBox<String> cbxParalelos;
    private plantilla.components.HeaderUser headerUser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private plantilla.components.Menu_Docente menu_Docente1;
    private plantilla.swing.RoundPanel roundPanel1;
    private plantilla.swing.RoundPanel roundPanel2;
    private plantilla.swing.RoundPanel roundPanel3;
    private javax.swing.JTable tblAsis;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

}
