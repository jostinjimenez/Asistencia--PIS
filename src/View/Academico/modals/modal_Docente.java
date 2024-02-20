package View.Academico.modals;

import Controller.Academico.DocenteController;
import View.Academico.FrmDocente;
import View.Tablas.ModeloTablaDocente;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Controller.Login.CuentaController;
import Controller.Administrativo.PersonaController;

public class modal_Docente extends javax.swing.JDialog {

    public modal_Docente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setupListeners();

        btnGuardar.addActionListener(e -> {
            guardar();
        });
    }

    public modal_Docente(java.awt.Frame parent, boolean modal, ModeloTablaDocente modeloTablaDocente, JTable tablaEstudiantes) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setupListeners();
        //cargarVista(modeloTablaDocente, tablaEstudiantes);

        btnGuardar.addActionListener(e -> {
            actualizar(tablaEstudiantes, modeloTablaDocente);
        });
    }

    // Variables
    private DocenteController dc = new DocenteController();
    private PersonaController pc = new PersonaController();
    private CuentaController cc = new CuentaController();
    private Integer fila = -1;

    // Metodos
//    public void cargarVista(ModeloTablaDocente mte, JTable jTable1) {
//        dc.setIndex(jTable1.getSelectedRow());
//        if (dc.getIndex() < 0) {
//            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
//        } else {
//            try {
//                //isEditing = true;
//                fila = dc.getIndex();
//                dc.setDocente(mte.getDocentes().get(fila));
//                pc.setPersona(mte.get().get(fila));
//                txtNombres.setText(pc.getPersona().getNombre());
//                txtApellidos.setText(pc.getPersona().getApellido());
//                txtFechaNacim.setDate(pc.getPersona().getFecha_nacimiento());
//                txtCorreo.setText(pc.getPersona().getCorreo_personal());
//                txtCedula.setText(pc.getPersona().getDni());
//                txtTelefono.setText(pc.getPersona().getTelefono());
//
//                txtCodigoEmpleado.setText(dc.getEstudiante().getEtnia());
//                String seleccion = dc.getEstudiante().getTitulo_bachiller() ? "Si" : "No";
//                cbxTituloBach.setSelectedItem(seleccion);
//                txtExperiencia.setText(dc.getEstudiante().getCalle_direccion());
//                txtNacionalidad.setText(dc.getEstudiante().getNacionalidad());
//
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                System.out.println(e.getMessage());
//                throw new RuntimeException(e);
//            }
//        }
//    }

    private Boolean validar() {
        return !txtApellidos.getText().trim().isEmpty()
                && !txtFechaNacim.getDate().toString().isEmpty()
                && !txtCorreo.getText().trim().isEmpty()
                && !txtCedula.getText().trim().isEmpty()
                && !txtTelefono.getText().trim().isEmpty()
                && !txtCodigoEmpleado.getText().trim().isEmpty()
                && !txtExperiencia.getText().trim().isEmpty()
                && !txtNombres.getText().trim().isEmpty();

    }

    public void guardar() {
        if (validar()) {
            try {
                // Crear y configurar la persona
                PersonaController pc = new PersonaController();
                pc.getPersona().setNombre(txtNombres.getText());
                pc.getPersona().setApellido(txtApellidos.getText());
                pc.getPersona().setFecha_nacimiento(txtFechaNacim.getDate());
                pc.getPersona().setCorreo_personal(txtCorreo.getText());
                pc.getPersona().setDni(txtCedula.getText());
                pc.getPersona().setTelefono(txtTelefono.getText());
                pc.getPersona().setRol_id(3);
                pc.getPersona().setFoto("user.png");

                // Guardar la persona y obtener el ID generado
                Integer idGenerado = pc.save();

                if (idGenerado != null) {
                    // Configurar el docente con el ID de la persona
                    dc.getDocente().setId(idGenerado);
                    dc.getDocente().setExperiencia(Integer.valueOf(txtExperiencia.getText()));
                    dc.getDocente().setGrado_academico(txtGradoAcademico.getText());
                    dc.getDocente().setCodigo_empleado(txtCodigoEmpleado.getText());

                    // Guardar el docente
                    if (dc.save()) {
                        // Configurar y guardar la cuenta
                        cc.getCuenta().setCorreo_institucional(generarCorreoInst());
                        cc.getCuenta().setClave(txtCedula.getText());
                        cc.getCuenta().setPersona_id(idGenerado);
                        cc.save();
                        JOptionPane.showMessageDialog(null, "Docente guardado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        ((FrmDocente) getParent()).cargarTabla();
                        this.dispose();
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al guardar el docente", "Error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generarCorreoInst() {
        String nombre = txtNombres.getText().contains(" ") ? txtNombres.getText().substring(0, txtNombres.getText().indexOf(" ")) : txtNombres.getText();
        String apellido = txtApellidos.getText().contains(" ") ? txtApellidos.getText().substring(0, txtApellidos.getText().indexOf(" ")) : txtApellidos.getText();
        return nombre.toLowerCase() + "." + apellido.toLowerCase() + "@unl.edu.ec";
    }

    private void actualizar(JTable tblEstudiante, ModeloTablaDocente modeloTablaDocente) {
//        int fila = tblEstudiante.getSelectedRow();
//        if (fila < 0) {
//            JOptionPane.showMessageDialog(null,
//                    "Seleccione una fila",
//                    "Error",
//                    JOptionPane.ERROR_MESSAGE);
//        } else {
//            try {
//                this.fila = fila;
//                dc.set(modeloTablaDocente.getEstudiantes().get(fila));
//                pc.setPersona(modeloTablaDocente.getPersonas().get(fila));
//                pc.getPersona().setNombre(txtNombres.getText());
//                pc.getPersona().setApellido(txtApellidos.getText());
//                pc.getPersona().setFecha_nacimiento(txtFechaNacim.getDate());
//                pc.getPersona().setCorreo_personal(txtCorreo.getText());
//                pc.getPersona().setDni(txtCedula.getText());
//                pc.getPersona().setTelefono(txtTelefono.getText());
//                pc.getPersona().setRol_id(2);
//                pc.getPersona().setFoto("user.png");
//
//                if (pc.update()){
//                    dc.getEstudiante().setEtnia(txtCodigoEmpleado.getText());
//                    String seleccion = cbxTituloBach.getSelectedItem().toString();
//                    Boolean tituloBachiller = seleccion.equals("Si");
//                    dc.getEstudiante().setTitulo_bachiller(tituloBachiller);
//                    dc.getEstudiante().setCalle_direccion(txtExperiencia.getText());
//                    dc.getEstudiante().setNacionalidad(txtGradoAcademico.getText());
//                    if (dc.update()){
//                        JOptionPane.showMessageDialog(null, "Estudiante actualizado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
//                        this.dispose();
//                    }
//                }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null,
//                        "Error al cargar los datos",
//                        "Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        }
    }

    private void setupListeners() {
        txtCedula.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || txtCedula.getText().length() >= 10) {
                    e.consume();
                }
            }
        });
        txtTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || txtTelefono.getText().length() >= 10) {
                    e.consume();
                }
            }
        });
        txtCodigoEmpleado.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetterOrDigit(c) || txtCodigoEmpleado.getText().length() >= 11) {
                    e.consume();
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Jlabel = new javax.swing.JLabel();
        Jlabelll = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtFechaNacim = new com.toedter.calendar.JDateChooser();
        txtCorreo = new javax.swing.JTextField();
        txtExperiencia = new javax.swing.JTextField();
        txtCodigoEmpleado = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Jlabelll1 = new javax.swing.JLabel();
        txtGradoAcademico = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Nombres: ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Fecha de nacimiento:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Correo Personal:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Cedula: ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Telefono:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, -1, -1));

        Jlabel.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Jlabel.setForeground(new java.awt.Color(51, 51, 51));
        Jlabel.setText("Codigo docente:");
        jPanel1.add(Jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        Jlabelll.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Jlabelll.setForeground(new java.awt.Color(51, 51, 51));
        Jlabelll.setText("Experiencia:");
        jPanel1.add(Jlabelll, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, -1, -1));

        txtNombres.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombres.setForeground(new java.awt.Color(0, 0, 0));
        txtNombres.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });
        jPanel1.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 230, -1));

        txtApellidos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtApellidos.setForeground(new java.awt.Color(0, 0, 0));
        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });
        jPanel1.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 90, 230, -1));

        txtCedula.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCedula.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 231, -1));

        txtTelefono.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, 231, -1));

        txtFechaNacim.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFechaNacim.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jPanel1.add(txtFechaNacim, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 230, 30));

        txtCorreo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 231, -1));

        txtExperiencia.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtExperiencia.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtExperiencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, 230, -1));

        txtCodigoEmpleado.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCodigoEmpleado.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtCodigoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 231, -1));

        btnGuardar.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 350, 110, 30));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 153, 255));
        jLabel1.setText("Agregar Docente");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Apellidos:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, -1, -1));

        Jlabelll1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Jlabelll1.setForeground(new java.awt.Color(51, 51, 51));
        Jlabelll1.setText("Grado Academico:");
        jPanel1.add(Jlabelll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, -1, -1));

        txtGradoAcademico.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtGradoAcademico.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtGradoAcademico, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 210, 230, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosActionPerformed

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
            java.util.logging.Logger.getLogger(modal_Docente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modal_Docente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modal_Docente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modal_Docente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                modal_Docente dialog = new modal_Docente(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel Jlabel;
    private javax.swing.JLabel Jlabelll;
    private javax.swing.JLabel Jlabelll1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCodigoEmpleado;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtExperiencia;
    private com.toedter.calendar.JDateChooser txtFechaNacim;
    private javax.swing.JTextField txtGradoAcademico;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
