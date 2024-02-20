package modelLuis.view;


import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import model.Asignatura;
import model.Asistencia;
import model.Cuenta;
import model.Cursa;
import model.Horario;
import model.Persona;
import model.Tematica;
import Controller.Academico.ControllerAsistencia;
import Controller.Administrativo.ControllerHorario;
import Controller.Academico.ControllerTematica;
import modelLuis.tablas.ModelTableAsistencia;
import modelLuis.view.util.Util_VistaLinked1_Asistencia;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import static modulo_1.inicio_sesion.view.util.Utiles.getComboAsignatura;

public class Frm_AsistenciaJ extends javax.swing.JFrame {
    ModelTableAsistencia md = new ModelTableAsistencia();
    ControllerAsistencia ca = new ControllerAsistencia();
    ListaEnlazada<Asistencia> list = new ListaEnlazada<>();
    ControllerTematica ct = new ControllerTematica();
    ListaEnlazada<Cursa> lista = new ListaEnlazada<>();
    ControllerHorario ch = new ControllerHorario();
    ListaEnlazada<Persona> personas = new ListaEnlazada<>();
    Cuenta cuenta;

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

    public Frm_AsistenciaJ(Cuenta c) {
        initComponents();
        this.cuenta = c;
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

        int ciclo = Integer.parseInt(cbxCiclos.getSelectedItem().toString());
        String n = cuenta.getPersona_id().toString();
        ListaEnlazada<Cursa> lista = ca.buscarCiclos(n, Integer.toString(ciclo));
        ListaEnlazada<Asignatura> listaAsig = new ListaEnlazada<>();
        for (Cursa cursa : lista) {
            Asignatura asignatura = ca.buscarAsig(cursa.getAsignatura_id().toString());
            listaAsig.add(asignatura);
        }
        Util_VistaLinked1_Asistencia.setListaAsig(listaAsig);
        Util_VistaLinked1_Asistencia.setListaCursa(lista);
    }

    private void cargarTabla() {
        String id = getComboAsignatura(cbxAsignaturas).getId().toString();
        String texto = cbxParalelos.getSelectedItem().toString();
        lista = ca.buscarParalelos(texto, id);
        for (Cursa cursa : lista) {
            Persona persona = ca.buscarEstudiante(cursa.getId().toString());
            personas.add(persona);
        }
        md.setLista(personas);
        tblAsis.setModel(md);
        tblAsis.updateUI();
        TableColumnModel tcm = tblAsis.getColumnModel();
        tcm.removeColumn(tblAsis.getColumn("ID"));
        tcm.removeColumn(tblAsis.getColumn("ID_CURSA"));
    }

    private void cargarHorario() throws VacioExceptions {
        ListaEnlazada<Horario> lista = ch.buscarHorario(Util_VistaLinked1_Asistencia.getComboAsig(cbxAsignaturas).getId().toString());
        Util_VistaLinked1_Asistencia.setListaHorarios(lista);
        Util_VistaLinked1_Asistencia.cargaHorario(cbxHorario);
    }

    private void limpiar() {

        try {
            Util_VistaLinked1_Asistencia.cargaAsig(cbxAsignaturas);
            Util_VistaLinked1_Asistencia.cargaParalelos(cbxParalelos);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Boolean validar() {
        return !txtFecha.getDate().toString().isEmpty()
                && !txtNombre.getText().trim().isEmpty();
    }

    public void modificar() {

        int fila = tblAsis.getSelectedRow();
        TableModel model = tblAsis.getModel();
        String value = model.getValueAt(fila, 4).toString();
        String value2 = model.getValueAt(fila, 5).toString();
        String a = model.getValueAt(fila, 3).toString();
        ca.getAsistencia().setEstado_asistencia(a);
        ca.getAsistencia().setCursa_id(Integer.parseInt(value2));
        ca.getAsistencia().setId(Integer.parseInt(value));
        if (ca.update()) {
            JOptionPane.showMessageDialog(null, "Se ha modificado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void guardar() {
        if (validar()) {
            try {
                Tematica tematica = ct.buscarTematica(txtNombre.getText());
                System.out.println(tematica.getNombre());
                System.out.println(lista.print());
                for (Cursa cursa : lista) {
                    ca.getAsistencia().setCursa_id(cursa.getId());
                    ca.getAsistencia().setHorario_id(Util_VistaLinked1_Asistencia.getComboHorario(cbxHorario).getId());
                    ca.getAsistencia().setTematica_id(tematica.getId());
                    ca.getAsistencia().setEstado_asistencia("ASISTIO");
                    ca.getAsistencia().setObservacion("Ninguna");
                    Integer i = ca.saved();
                    if (i != 0) {
                        ca.getAsistencia().setId(i);
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

    private void actualizarTabla() {
        md.setAsistencias(list);
        md.setLista(personas);
        tblAsis.setModel(md);
        tblAsis.updateUI();
    }

    public void guardarTematica() {
        if (validar()) {
            try {
                ct.getTematica().setFecha(txtFecha.getDate());
                ct.getTematica().setNombre(txtNombre.getText());
                if (ct.save()) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsis = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnGuardarTem = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        cbxHorario = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFecha = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        cbxParalelos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cbxAsignaturas = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbxCiclos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        menu_Docente1 = new plantilla.components.Menu_Docente();
        headerUser1 = new plantilla.components.HeaderUser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg_panel.setBackground(new java.awt.Color(225, 233, 243));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        roundPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 160, 30));

        btnGuardarTem.setText("Guardar Tematica");
        btnGuardarTem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTemActionPerformed(evt);
            }
        });
        roundPanel1.add(btnGuardarTem, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 160, 30));

        jButton1.setText("Actualizar Tabla");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        roundPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 160, 30));

        cbxHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel1.add(cbxHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, 220, 30));

        jLabel5.setText("Horario:");
        roundPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 150, -1, -1));
        roundPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 220, 30));

        jLabel3.setText("Tema de la clase:");
        roundPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, -1, -1));
        roundPanel1.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 220, 30));

        jLabel4.setText("Fecha de la clase:");
        roundPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, -1, -1));

        cbxParalelos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel1.add(cbxParalelos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 230, 30));

        jLabel1.setText("Paralelo:");
        roundPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        cbxAsignaturas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel1.add(cbxAsignaturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 230, 30));

        jLabel6.setText("Asignaturas:");
        roundPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        cbxCiclos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        cbxCiclos.setPreferredSize(new java.awt.Dimension(85, 22));
        cbxCiclos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCiclosItemStateChanged(evt);
            }
        });
        cbxCiclos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCiclosActionPerformed(evt);
            }
        });
        roundPanel1.add(cbxCiclos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 230, 30));

        jLabel2.setText("Ciclo:");
        roundPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

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
        try {
            cargarCombos();
            limpiar();
        } catch (VacioExceptions ex) {
            System.out.println("ERROR" + ex);
        }
    }//GEN-LAST:event_cbxCiclosItemStateChanged

    private void btnGuardarTemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTemActionPerformed
        guardarTematica();
        guardar();
        actualizarTabla();
    }//GEN-LAST:event_btnGuardarTemActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void cbxCiclosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCiclosActionPerformed


    }//GEN-LAST:event_cbxCiclosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            cargarTabla();
            cargarHorario();
        } catch (VacioExceptions ex) {
        }
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
            new Frm_AsistenciaJ().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JButton btnGuardarTem;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbxAsignaturas;
    private javax.swing.JComboBox<String> cbxCiclos;
    private javax.swing.JComboBox<String> cbxHorario;
    private javax.swing.JComboBox<String> cbxParalelos;
    private plantilla.components.HeaderUser headerUser1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private plantilla.components.Menu_Docente menu_Docente1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tblAsis;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

}
