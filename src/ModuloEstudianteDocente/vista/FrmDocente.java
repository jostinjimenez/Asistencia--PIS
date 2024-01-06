/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloEstudianteDocente.vista;

import ModuloEstudianteDocente.controlador.DocenteControlador;
import ModuloEstudianteDocente.vista.tablas.ModeloTablaDocente;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import model.Docente;

/**
 *
 * @author LENOVO
 */
public class FrmDocente extends javax.swing.JFrame {

    private DocenteControlador docenteControlador = new DocenteControlador();
    private ModeloTablaDocente modeloDocente = new ModeloTablaDocente();
    private Integer fila = -1;

    /**
     * Creates new form FrmDocete
     */
    public FrmDocente() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarTabla();
    }

    private void cargarTabla() {
        modeloDocente.setDocente(docenteControlador.list_All());
        tblDocente.setModel(modeloDocente);
        tblDocente.updateUI();
    }

    private void limpiar() {

        txtNombresDoc.setText(" ");
        txtFechaNacim.setText(" ");
        txtCorreoPersonal.setText(" ");
        txtDNI.setText(" ");
        txtTelefn.setText(" ");
        txtCodigoEmp.setText(" ");
        txtAniosExpe.setText(" ");
        txtGradoAcademico.setText(" ");

        docenteControlador.setDocente(null);
        cargarTabla();
        fila = -1;
        tblDocente.clearSelection();
    }

    private Boolean validar() {
        return !txtNombresDoc.getText().trim().isEmpty()
                  && !txtFechaNacim.getText().trim().isEmpty()
                  && !txtCorreoPersonal.getText().trim().isEmpty()
                  && !txtDNI.getText().trim().isEmpty()
                  && !txtTelefn.getText().trim().isEmpty()
                  && !txtCodigoEmp.getText().trim().isEmpty()
                  && !txtAniosExpe.getText().trim().isEmpty()
                  && !txtGradoAcademico.getText().trim().isEmpty();

    }

    public void guardar() {
        if (validar()) {
            try {
                Docente docte = new Docente();
                docte.setNombre(txtNombresDoc.getText());
                docte.setFecha_nacimiento(txtFechaNacim.getText());
                docte.setCorreo_personal(txtCorreoPersonal.getText());
                docte.setDni(txtDNI.getText());
                docte.setTelefono(txtTelefn.getText());
                docte.setCodigo_empleado(txtCodigoEmp.getText());
                int aniosExperiencia = Integer.parseInt(txtAniosExpe.getText());
                docte.setAnios_experiencia(aniosExperiencia);
                docte.setGrado_academico(txtGradoAcademico.getText());
                docte.setGrado_academico(txtGradoAcademico.getText());

                if (fila != -1) {
                    docte.setId(docenteControlador.getDocente().getId());
                    docenteControlador.update(docte, fila);
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Docente actualizado correctamente",
                              "Mensaje",
                              JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    docte.setId(docenteControlador.generarID());
                    docenteControlador.save(docte);
                    limpiar();

                    JOptionPane.showMessageDialog(null, "Docente registrado correctamente",
                              "Mensaje",
                              JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(),
                          "Error",
                          JOptionPane.ERROR_MESSAGE);

            }
        }
        else {
            JOptionPane.showMessageDialog(null,
                      "Por favor llene todos los campos",
                      "Error",
                      JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizar() {
        int fila = tblDocente.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                      "Seleccione una fila",
                      "Error",
                      JOptionPane.ERROR_MESSAGE);
        }
        else {
            try {
                this.fila = fila;

                docenteControlador.setDocente(modeloDocente.getDocente().get(fila));
                txtNombresDoc.setText(docenteControlador.getDocente().getNombre());
                txtFechaNacim.setText(docenteControlador.getDocente().getFecha_nacimiento());
                txtCorreoPersonal.setText(docenteControlador.getDocente().getCorreo_personal());
                txtDNI.setText(docenteControlador.getDocente().getDni());
                txtTelefn.setText(docenteControlador.getDocente().getTelefono());
                txtCodigoEmp.setText(docenteControlador.getDocente().getCodigo_empleado());
                txtAniosExpe.setText(String.valueOf(docenteControlador.getDocente().getAnios_experiencia()));
                txtGradoAcademico.setText(docenteControlador.getDocente().getGrado_academico());

            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                          "Error al cargar los datos",
                          "Error",
                          JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        roundPanel2 = new plantilla.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        roundPanel6 = new plantilla.swing.RoundPanel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocente = new javax.swing.JTable();
        roundPanel5 = new plantilla.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Jlablsss = new javax.swing.JLabel();
        jlabeljhgfgh = new javax.swing.JLabel();
        txtNombresDoc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Jlabel111111 = new javax.swing.JLabel();
        txtFechaNacim = new javax.swing.JTextField();
        txtCorreoPersonal = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        txtTelefn = new javax.swing.JTextField();
        txtAniosExpe = new javax.swing.JTextField();
        txtGradoAcademico = new javax.swing.JTextField();
        txtCodigoEmp = new javax.swing.JTextField();
        menu_Admin1 = new plantilla.components.Menu_Admin();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Agregar Docente");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(jLabel1)
                .addContainerGap(639, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.add(roundPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 1020, 80));

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel6.setBackground(new java.awt.Color(51, 51, 51));

        btnGuardar.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(204, 204, 204));
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        roundPanel1.add(roundPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, 180, 260));

        tblDocente.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblDocente);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 980, 230));

        roundPanel5.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombres: ");
        roundPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 16, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha de nacimiento:");
        roundPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Correo:");
        roundPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        Jlablsss.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Jlablsss.setForeground(new java.awt.Color(255, 255, 255));
        Jlablsss.setText("Grado Academico: ");
        roundPanel5.add(Jlablsss, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jlabeljhgfgh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlabeljhgfgh.setForeground(new java.awt.Color(255, 255, 255));
        jlabeljhgfgh.setText("Años Experiencia:");
        roundPanel5.add(jlabeljhgfgh, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        txtNombresDoc.setBackground(new java.awt.Color(204, 204, 204));
        txtNombresDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresDocActionPerformed(evt);
            }
        });
        roundPanel5.add(txtNombresDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 230, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cedula: ");
        roundPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Telefono:");
        roundPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        Jlabel111111.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Jlabel111111.setForeground(new java.awt.Color(255, 255, 255));
        Jlabel111111.setText("Codigo Empleado:");
        roundPanel5.add(Jlabel111111, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        txtFechaNacim.setBackground(new java.awt.Color(204, 204, 204));
        roundPanel5.add(txtFechaNacim, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 230, -1));

        txtCorreoPersonal.setBackground(new java.awt.Color(204, 204, 204));
        roundPanel5.add(txtCorreoPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 231, -1));

        txtDNI.setBackground(new java.awt.Color(204, 204, 204));
        roundPanel5.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 231, -1));

        txtTelefn.setBackground(new java.awt.Color(204, 204, 204));
        roundPanel5.add(txtTelefn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 231, -1));

        txtAniosExpe.setBackground(new java.awt.Color(204, 204, 204));
        roundPanel5.add(txtAniosExpe, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 231, -1));

        txtGradoAcademico.setBackground(new java.awt.Color(204, 204, 204));
        roundPanel5.add(txtGradoAcademico, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 230, -1));

        txtCodigoEmp.setBackground(new java.awt.Color(204, 204, 204));
        roundPanel5.add(txtCodigoEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 231, -1));

        roundPanel1.add(roundPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 480, 260));

        jPanel1.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 1020, 530));
        jPanel1.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 230, 620));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombresDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresDocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresDocActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        actualizar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        //eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatNordIJTheme());
        }
        catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FrmDocente().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jlabel111111;
    private javax.swing.JLabel Jlablsss;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabeljhgfgh;
    private plantilla.components.Menu_Admin menu_Admin1;
    private plantilla.swing.RoundPanel roundPanel1;
    private plantilla.swing.RoundPanel roundPanel2;
    private plantilla.swing.RoundPanel roundPanel5;
    private plantilla.swing.RoundPanel roundPanel6;
    private javax.swing.JTable tblDocente;
    private javax.swing.JTextField txtAniosExpe;
    private javax.swing.JTextField txtCodigoEmp;
    private javax.swing.JTextField txtCorreoPersonal;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtFechaNacim;
    private javax.swing.JTextField txtGradoAcademico;
    private javax.swing.JTextField txtNombresDoc;
    private javax.swing.JTextField txtTelefn;
    // End of variables declaration//GEN-END:variables
}
