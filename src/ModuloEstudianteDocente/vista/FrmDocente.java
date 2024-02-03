/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloEstudianteDocente.vista;

import ModuloEstudianteDocente.controlador.DocenteController;
import ModuloEstudianteDocente.vista.tablas.ModeloTablaDocente;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import modulo_1.inicio_sesion.controller.CuentaController;

/**
 * @author LENOVO
 */
public class FrmDocente extends javax.swing.JFrame {

    private DocenteController docenteController = new DocenteController();
    private ModeloTablaDocente modeloDocente = new ModeloTablaDocente();
    private Integer fila = -1;
    private CuentaController cc;

    /**
     * Creates new form FrmDocete
     */
    public FrmDocente() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarTabla();
    }

    public FrmDocente(CuentaController cc) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarTabla();
        this.cc = cc;
    }

    private void cargarTabla() {
        modeloDocente.setDocente(docenteController.list_All());
        tblDocente.setModel(modeloDocente);
        tblDocente.updateUI();
    }

    private void limpiar() {

        txtNombresDoc.setText(" ");
        txtFechaNacim.setDate(null);
        txtCorreoPersonal.setText(" ");
        txtDNI.setText(" ");
        txtTelefn.setText(" ");
        txtCodigoEmp.setText(" ");
        txtAniosExpe.setText(" ");
        txtGradoAcademico.setText(" ");

        docenteController.setDocente(null);
        cargarTabla();
        fila = -1;
        tblDocente.clearSelection();
    }

    private Boolean validar() {
        return !txtNombresDoc.getText().trim().isEmpty()
                && !txtFechaNacim.getDate().toString().isEmpty()
                && !txtCorreoPersonal.getText().trim().isEmpty()
                && !txtDNI.getText().trim().isEmpty()
                && !txtTelefn.getText().trim().isEmpty()
                && !txtCodigoEmp.getText().trim().isEmpty()
                && !txtAniosExpe.getText().trim().isEmpty()
                && !txtApellidos.getText().trim().isEmpty()
                && !txtGradoAcademico.getText().trim().isEmpty();

    }

    public void guardar() {
        if (validar()) {
            try {
                docenteController.getDocente().setNombre(txtNombresDoc.getText());
                docenteController.getDocente().setApellido(txtApellidos.getText());
                docenteController.getDocente().setFecha_nacimiento(txtFechaNacim.getDate());
                docenteController.getDocente().setCorreo_personal(txtCorreoPersonal.getText());
                docenteController.getDocente().setDni(txtDNI.getText());
                docenteController.getDocente().setTelefono(txtTelefn.getText());
                docenteController.getDocente().setCodigo_empleado(txtCodigoEmp.getText());
                int aniosExperiencia = Integer.parseInt(txtAniosExpe.getText());
                docenteController.getDocente().setAnios_experiencia(aniosExperiencia);
                docenteController.getDocente().setGrado_academico(txtGradoAcademico.getText());
                docenteController.getDocente().setIdRol(3);
                docenteController.getDocente().setActivo(true);
                docenteController.getDocente().setFoto("user.png");

                if (fila != -1) {
                    docenteController.getDocente().setId(docenteController.getDocente().getId());
                    docenteController.update(docenteController.getDocente(), fila);
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Docente actualizado correctamente",
                            "Mensaje",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (docenteController.save()) {
                        cc.getCuenta().setCorreo(generarCorreoInst());
                        cc.getCuenta().setClave(txtDNI.getText());
                        cc.getCuenta().setIdPersona(cc.generarID());
                        if (cc.save()) {
                            System.out.println("Cuenta registrada correctamente");
                        } else {
                            System.out.println("Error al registrar la cuenta");
                        }
                        limpiar();
                        JOptionPane.showMessageDialog(null, "Docente registrado correctamente",
                                "Mensaje",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al registrar el docente",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Por favor llene todos los campos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generarCorreoInst() {
        String nombre = txtNombresDoc.getText().contains(" ") ? txtNombresDoc.getText().substring(0, txtNombresDoc.getText().indexOf(" ")) : txtNombresDoc.getText();
        String apellido = txtApellidos.getText().contains(" ") ? txtApellidos.getText().substring(0, txtApellidos.getText().indexOf(" ")) : txtApellidos.getText();
        return nombre.toLowerCase() + "." + apellido.toLowerCase() + "@unl.edu.ec";
    }

    private void actualizar() {
        int fila = tblDocente.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione una fila",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                this.fila = fila;

                docenteController.setDocente(modeloDocente.getDocente().get(fila));
                txtNombresDoc.setText(docenteController.getDocente().getNombre());
                txtApellidos.setText(docenteController.getDocente().getApellido());
                //txtFechaNacim.setText(docenteControlador.getDocente().getFecha_nacimiento());
                txtCorreoPersonal.setText(docenteController.getDocente().getCorreo_personal());
                txtDNI.setText(docenteController.getDocente().getDni());
                txtTelefn.setText(docenteController.getDocente().getTelefono());
                txtCodigoEmp.setText(docenteController.getDocente().getCodigo_empleado());
                txtAniosExpe.setText(String.valueOf(docenteController.getDocente().getAnios_experiencia()));
                txtGradoAcademico.setText(docenteController.getDocente().getGrado_academico());

            } catch (Exception e) {
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
        roundPanel1 = new plantilla.swing.RoundPanel();
        roundPanel6 = new plantilla.swing.RoundPanel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocente = new javax.swing.JTable();
        roundPanel5 = new plantilla.swing.RoundPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Jlablsss = new javax.swing.JLabel();
        jlabeljhgfgh = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Jlabel111111 = new javax.swing.JLabel();
        txtCorreoPersonal = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        txtTelefn = new javax.swing.JTextField();
        txtAniosExpe = new javax.swing.JTextField();
        txtGradoAcademico = new javax.swing.JTextField();
        txtCodigoEmp = new javax.swing.JTextField();
        txtNombresDoc = new javax.swing.JTextField();
        txtFechaNacim = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        menu_Admin1 = new plantilla.components.Menu_Admin();
        header2 = new plantilla.components.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        roundPanel1.add(roundPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 60, 180, 260));

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

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 980, 230));

        roundPanel5.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        txtNombresDoc.setBackground(new java.awt.Color(204, 204, 204));
        txtNombresDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresDocActionPerformed(evt);
            }
        });
        roundPanel5.add(txtNombresDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 230, -1));

        txtFechaNacim.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFechaNacim.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        roundPanel5.add(txtFechaNacim, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 230, 30));

        roundPanel1.add(roundPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 480, 260));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nombres: ");
        roundPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Apellidos");
        roundPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 70, -1));

        txtApellidos.setBackground(new java.awt.Color(204, 204, 204));
        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });
        roundPanel1.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, 230, -1));

        jPanel1.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 1030, 630));
        jPanel1.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 680));
        jPanel1.add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 1030, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

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

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatNordIJTheme());
        } catch (Exception ex) {
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
    private plantilla.components.Header header2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabeljhgfgh;
    private plantilla.components.Menu_Admin menu_Admin1;
    private plantilla.swing.RoundPanel roundPanel1;
    private plantilla.swing.RoundPanel roundPanel5;
    private plantilla.swing.RoundPanel roundPanel6;
    private javax.swing.JTable tblDocente;
    private javax.swing.JTextField txtAniosExpe;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCodigoEmp;
    private javax.swing.JTextField txtCorreoPersonal;
    private javax.swing.JTextField txtDNI;
    private com.toedter.calendar.JDateChooser txtFechaNacim;
    private javax.swing.JTextField txtGradoAcademico;
    private javax.swing.JTextField txtNombresDoc;
    private javax.swing.JTextField txtTelefn;
    // End of variables declaration//GEN-END:variables
}
