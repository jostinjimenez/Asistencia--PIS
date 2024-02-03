package ModuloEstudianteDocente.vista.modals;

import ModuloEstudianteDocente.controlador.EstudianteController;
import ModuloEstudianteDocente.vista.FrmEstudiante;
import ModuloEstudianteDocente.vista.tablas.ModeloTablaEstudiante;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modulo_1.inicio_sesion.controller.CuentaController;

public class modal_Estudiante extends javax.swing.JDialog {

    public modal_Estudiante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setupListeners();

        cbxTituloBach.addItem("Si");
        cbxTituloBach.addItem("No");
    }

    public modal_Estudiante(java.awt.Frame parent, boolean modal, ModeloTablaEstudiante modeloTablaEstudiante, JTable tablaEstudiantes) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setupListeners();

        cargarVista(modeloTablaEstudiante, tablaEstudiantes);

        btnGuardar.addActionListener(e -> {
            actualizar(tablaEstudiantes, modeloTablaEstudiante);
        });

        cbxTituloBach.addItem("Si");
        cbxTituloBach.addItem("No");
    }

    // Variables
    private EstudianteController estudianteControlador = new EstudianteController();
    private CuentaController cc = new CuentaController();
    private Integer fila = -1;

    // Metodos
    public void cargarVista(ModeloTablaEstudiante mte, JTable jTable1) {
        estudianteControlador.setIndex(jTable1.getSelectedRow());
        if (estudianteControlador.getIndex() < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                //isEditing = true;
                fila = estudianteControlador.getIndex();
                estudianteControlador.setEstudiante(mte.getEstudiante().get(fila));
                txtNombres.setText(estudianteControlador.getEstudiante().getNombre());
                txtApellidos.setText(estudianteControlador.getEstudiante().getApellido());
                txtFechaNacim.setDate(cc.getPersona(cc.getCuenta().getId()).getFecha_nacimiento());
                txtCorreo.setText(estudianteControlador.getEstudiante().getCorreo_personal());
                txtCedula.setText(estudianteControlador.getEstudiante().getDni());
                txtTelefono.setText(estudianteControlador.getEstudiante().getTelefono());
                txtEtnia.setText(estudianteControlador.getEstudiante().getEtnia());
                String seleccion = estudianteControlador.getEstudiante().getTitulo_bachiller() ? "Si" : "No";
                cbxTituloBach.setSelectedItem(seleccion);
                txtDireccion.setText(estudianteControlador.getEstudiante().getDireccion());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    private Boolean validar() {
        return !txtApellidos.getText().trim().isEmpty()
                && !txtFechaNacim.getDate().toString().isEmpty()
                && !txtCorreo.getText().trim().isEmpty()
                && !txtCedula.getText().trim().isEmpty()
                && !txtTelefono.getText().trim().isEmpty()
                && !txtEtnia.getText().trim().isEmpty()
                && !txtDireccion.getText().trim().isEmpty()
                && !txtNombres.getText().trim().isEmpty();

    }

    public void guardar() {
        if (validar()) {
            try {
                estudianteControlador.getEstudiante().setNombre(txtNombres.getText());
                estudianteControlador.getEstudiante().setApellido(txtApellidos.getText());
                estudianteControlador.getEstudiante().setFecha_nacimiento(txtFechaNacim.getDate());
                estudianteControlador.getEstudiante().setCorreo_personal(txtCorreo.getText());
                estudianteControlador.getEstudiante().setDni(txtCedula.getText());
                estudianteControlador.getEstudiante().setTelefono(txtTelefono.getText());
                estudianteControlador.getEstudiante().setEtnia(txtEtnia.getText());
                String seleccion = cbxTituloBach.getSelectedItem().toString();
                Boolean tituloBachiller = seleccion.equals("Si");
                estudianteControlador.getEstudiante().setTitulo_bachiller(tituloBachiller);
                String valorGuardar = tituloBachiller ? "Si" : "No";
                estudianteControlador.getEstudiante().setDireccion(txtDireccion.getText());
                estudianteControlador.getEstudiante().setIdRol(2);
                estudianteControlador.getEstudiante().setActivo(true);
                estudianteControlador.getEstudiante().setFoto("user.png");

                if (fila != -1) {
                    estudianteControlador.getEstudiante().setId(estudianteControlador.getEstudiante().getId());
                    estudianteControlador.update();

                    JOptionPane.showMessageDialog(null, "Estudiante actualizado correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } else {
                    if (estudianteControlador.save()) {
                        cc.getCuenta().setCorreo(generarCorreoInst());
                        cc.getCuenta().setClave(txtCedula.getText());
                        cc.getCuenta().setIdPersona(cc.generarID());
                        cc.save();
                        
                        ((FrmEstudiante) getParent()).cargarTabla();

                        JOptionPane.showMessageDialog(null, "Estudiante registrado correctamente",
                                "Mensaje",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
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
        String nombre = txtNombres.getText().contains(" ") ? txtNombres.getText().substring(0, txtNombres.getText().indexOf(" ")) : txtNombres.getText();
        String apellido = txtApellidos.getText().contains(" ") ? txtApellidos.getText().substring(0, txtApellidos.getText().indexOf(" ")) : txtApellidos.getText();
        return nombre.toLowerCase() + "." + apellido.toLowerCase() + "@unl.edu.ec";
    }

    private void actualizar(JTable tblEstudiante, ModeloTablaEstudiante modeloEstudiante) {
        int fila = tblEstudiante.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione una fila",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                this.fila = fila;

                estudianteControlador.setEstudiante(modeloEstudiante.getEstudiante().get(fila));
                txtNombres.setText(estudianteControlador.getEstudiante().getNombre());
                txtApellidos.setText(estudianteControlador.getEstudiante().getApellido());
                //txtFechaNac.setText(estudianteControlador.getEstudiante().getFecha_nacimiento());
                txtCorreo.setText(estudianteControlador.getEstudiante().getCorreo_personal());
                txtCedula.setText(estudianteControlador.getEstudiante().getDni());
                txtTelefono.setText(estudianteControlador.getEstudiante().getTelefono());
                txtEtnia.setText(estudianteControlador.getEstudiante().getEtnia());
                String seleccion = cbxTituloBach.getSelectedItem().toString();
                Boolean tituloBachiller = seleccion.equals("Si");
                cbxTituloBach.setSelectedItem(seleccion);

                txtDireccion.setText(estudianteControlador.getEstudiante().getDireccion());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Error al cargar los datos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
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
        jLabel7 = new javax.swing.JLabel();
        Jlabelll = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtEtnia = new javax.swing.JTextField();
        cbxTituloBach = new javax.swing.JComboBox<>();
        txtDireccion = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        txtFechaNacim = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nombres: ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha de nacimiento:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Correo Personal:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cedula: ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Telefono:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, -1, -1));

        Jlabel.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Jlabel.setForeground(new java.awt.Color(255, 255, 255));
        Jlabel.setText("Etnia:");
        jPanel1.add(Jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Titulo de bachiller: ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, -1, -1));

        Jlabelll.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Jlabelll.setForeground(new java.awt.Color(255, 255, 255));
        Jlabelll.setText("Direccion:");
        jPanel1.add(Jlabelll, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, -1, -1));

        txtNombres.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombres.setForeground(new java.awt.Color(0, 0, 0));
        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });
        jPanel1.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 230, -1));

        txtCorreo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 231, -1));

        txtCedula.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCedula.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 231, -1));

        txtTelefono.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 120, 231, -1));

        txtEtnia.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtEtnia.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtEtnia, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 160, 231, -1));

        cbxTituloBach.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxTituloBach.setForeground(new java.awt.Color(0, 0, 0));
        cbxTituloBach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTituloBachActionPerformed(evt);
            }
        });
        jPanel1.add(cbxTituloBach, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, 230, -1));

        txtDireccion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, 230, -1));

        btnGuardar.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 330, 110, 30));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Agregar Estudiante");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Apellidos:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, -1, -1));

        txtApellidos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtApellidos.setForeground(new java.awt.Color(0, 0, 0));
        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });
        jPanel1.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 230, -1));

        txtFechaNacim.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFechaNacim.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jPanel1.add(txtFechaNacim, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 230, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

    private void cbxTituloBachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTituloBachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTituloBachActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
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
            java.util.logging.Logger.getLogger(modal_Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modal_Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modal_Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modal_Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                modal_Estudiante dialog = new modal_Estudiante(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbxTituloBach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEtnia;
    private com.toedter.calendar.JDateChooser txtFechaNacim;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
