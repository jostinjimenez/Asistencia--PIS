/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloEstudianteDocente.vista;

import ModuloEstudianteDocente.controlador.ControladorEstudiante;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import ModuloEstudianteDocente.vista.tablas.ModeloTablaEstudiante;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import java.awt.Frame;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import model.Estudiante;
import modulo_1.inicio_sesion.controller.CuentaController;
import modulo_1.inicio_sesion.controller.PersonaController;

/**
 *
 * @author LENOVO
 */
public class FrmEstudiante extends javax.swing.JFrame {

    private ControladorEstudiante estudianteControlador = new ControladorEstudiante();
    private ModeloTablaEstudiante modeloEstudiante = new ModeloTablaEstudiante();
    private CuentaController cc;
    private Integer fila = -1;

    /**
     * Creates new form FrmEstudiante
     */
    public FrmEstudiante() {
        super();
        initComponents();
        setupListeners();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cbxTituloBach.addItem("Si");
        cbxTituloBach.addItem("No");
        cargarTabla();
    }

    public FrmEstudiante(CuentaController cc) {
        super();
        initComponents();
        setupListeners();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cbxTituloBach.addItem("Si");
        cbxTituloBach.addItem("No");
        cargarTabla();
        this.cc = cc;
    }

    private void cargarTabla() {
        modeloEstudiante.setEstudiante(estudianteControlador.list_All());
        tblEstudiante.setModel(modeloEstudiante);
        tblEstudiante.updateUI();
    }

    private void limpiar() {

        txtApellidos.setText(" ");
        txtFechaNac.setText(" ");
        txtCorreo.setText(" ");
        txtCedula.setText(" ");
        txtTelefono.setText(" ");
        txtEtnia.setText(" ");
        txtDireccion.setText(" ");

        estudianteControlador.setEstudiante(null);
        cargarTabla();
        fila = -1;
        tblEstudiante.clearSelection();
    }

    private Boolean validar() {
        return !txtApellidos.getText().trim().isEmpty()
                  && !txtFechaNac.getText().trim().isEmpty()
                  && !txtCorreo.getText().trim().isEmpty()
                  && !txtCedula.getText().trim().isEmpty()
                  && !txtTelefono.getText().trim().isEmpty()
                  && !txtEtnia.getText().trim().isEmpty()
                  && !txtDireccion.getText().trim().isEmpty();

    }

    public void guardar() {
        if (validar()) {
            try {
                Estudiante est = new Estudiante();
                est.setNombre(txtApellidos.getText());
                est.setApellido(txtApellidos.getText());
                est.setFecha_nacimiento(txtFechaNac.getText());
                est.setCorreo_personal(txtCorreo.getText());
                est.setDni(txtCedula.getText());
                est.setTelefono(txtTelefono.getText());
                est.setEtnia(txtEtnia.getText());
                String seleccion = cbxTituloBach.getSelectedItem().toString();
                Boolean tituloBachiller = seleccion.equals("Si");
                est.setTitulo_bachiller(tituloBachiller);
                String valorGuardar = tituloBachiller ? "Si" : "No";
                est.setDireccion(txtDireccion.getText());
                est.setIdRol(2);

                if (fila != -1) {
                    est.setId(estudianteControlador.getEstudiante().getId());
                    estudianteControlador.update(est, fila);
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Estudiante actualizado correctamente",
                              "Mensaje",
                              JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    est.setId(estudianteControlador.generarID());
                    estudianteControlador.save(est);

                    cc.getCuenta().setCorreo(generarCorreoInst());
                    cc.getCuenta().setClave(txtCedula.getText());
                    cc.getCuenta().setEstado(true);
                    cc.getCuenta().setIdPersona(estudianteControlador.getEstudiante().getId());
                    limpiar();

                    JOptionPane.showMessageDialog(null, "Estudiante registrado correctamente",
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

    private String generarCorreoInst() {
        String nombre = txtNombres.getText().contains(" ") ? txtNombres.getText().substring(0, txtNombres.getText().indexOf(" ")) : txtNombres.getText();
        String apellido = txtApellidos.getText().contains(" ") ? txtApellidos.getText().substring(0, txtApellidos.getText().indexOf(" ")) : txtApellidos.getText();
        return nombre.toLowerCase() + "." + apellido.toLowerCase() + "@unl.edu.ec";
    }

    private void actualizar() {
        int fila = tblEstudiante.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                      "Seleccione una fila",
                      "Error",
                      JOptionPane.ERROR_MESSAGE);
        }
        else {
            try {
                this.fila = fila;

                estudianteControlador.setEstudiante(modeloEstudiante.getEstudiante().get(fila));
                txtApellidos.setText(estudianteControlador.getEstudiante().getNombre());
                txtFechaNac.setText(estudianteControlador.getEstudiante().getFecha_nacimiento());
                txtCorreo.setText(estudianteControlador.getEstudiante().getCorreo_personal());
                txtCedula.setText(estudianteControlador.getEstudiante().getDni());
                txtTelefono.setText(estudianteControlador.getEstudiante().getTelefono());
                txtEtnia.setText(estudianteControlador.getEstudiante().getEtnia());
                String seleccion = cbxTituloBach.getSelectedItem().toString();
                Boolean tituloBachiller = seleccion.equals("Si");
                cbxTituloBach.setSelectedItem(seleccion);

                txtDireccion.setText(estudianteControlador.getEstudiante().getDireccion());

            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                          "Error al cargar los datos",
                          "Error",
                          JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void eliminar() {
        int fila = tblEstudiante.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                      "Seleccione una fila",
                      "Error",
                      JOptionPane.ERROR_MESSAGE);
        }
        else {
            try {
                estudianteControlador.delete(fila);
                limpiar();
                JOptionPane.showMessageDialog(null, "Estudiante eliminado correctamente",
                          "Mensaje",
                          JOptionPane.INFORMATION_MESSAGE);
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                          "Error al eliminar el estudiante: " + e.getMessage(),
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

        txtFechaNac.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == '/') || txtFechaNac.getText().length() >= 11) {
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
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
        roundPanel3 = new plantilla.swing.RoundPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Jlabelll = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Jlabel = new javax.swing.JLabel();
        txtFechaNac = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtEtnia = new javax.swing.JTextField();
        cbxTituloBach = new javax.swing.JComboBox<>();
        txtDireccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        menu_Admin1 = new plantilla.components.Menu_Admin();
        roundPanel2 = new plantilla.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        roundPanel5 = new plantilla.swing.RoundPanel();
        roundPanel4 = new plantilla.swing.RoundPanel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstudiante = new javax.swing.JTable();
        txtApellidos = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel3.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha de nacimiento:");
        roundPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Correo Personal:");
        roundPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        Jlabelll.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Jlabelll.setForeground(new java.awt.Color(255, 255, 255));
        Jlabelll.setText("Direccion:");
        roundPanel3.add(Jlabelll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Titulo de bachiller: ");
        roundPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cedula: ");
        roundPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Telefono:");
        roundPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        Jlabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Jlabel.setForeground(new java.awt.Color(255, 255, 255));
        Jlabel.setText("Etnia:");
        roundPanel3.add(Jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        txtFechaNac.setBackground(new java.awt.Color(204, 204, 204));
        txtFechaNac.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtFechaNac.setForeground(new java.awt.Color(0, 0, 0));
        roundPanel3.add(txtFechaNac, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 230, -1));

        txtCorreo.setBackground(new java.awt.Color(204, 204, 204));
        txtCorreo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(0, 0, 0));
        roundPanel3.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 231, -1));

        txtCedula.setBackground(new java.awt.Color(204, 204, 204));
        txtCedula.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCedula.setForeground(new java.awt.Color(0, 0, 0));
        roundPanel3.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 231, -1));

        txtTelefono.setBackground(new java.awt.Color(204, 204, 204));
        txtTelefono.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(0, 0, 0));
        roundPanel3.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 231, -1));

        txtEtnia.setBackground(new java.awt.Color(204, 204, 204));
        txtEtnia.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtEtnia.setForeground(new java.awt.Color(0, 0, 0));
        roundPanel3.add(txtEtnia, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 231, -1));

        cbxTituloBach.setBackground(new java.awt.Color(204, 204, 204));
        cbxTituloBach.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxTituloBach.setForeground(new java.awt.Color(0, 0, 0));
        cbxTituloBach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTituloBachActionPerformed(evt);
            }
        });
        roundPanel3.add(cbxTituloBach, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 230, -1));

        txtDireccion.setBackground(new java.awt.Color(204, 204, 204));
        txtDireccion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(0, 0, 0));
        roundPanel3.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 230, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nombres: ");
        roundPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 16, -1, -1));

        txtNombres.setBackground(new java.awt.Color(204, 204, 204));
        txtNombres.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombres.setForeground(new java.awt.Color(0, 0, 0));
        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });
        roundPanel3.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 230, -1));

        jPanel1.add(roundPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 450, 260));
        jPanel1.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 590));

        roundPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Agregar estudiante");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(jLabel1)
                .addContainerGap(586, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel1.add(roundPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 1020, -1));

        roundPanel5.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel4.setBackground(new java.awt.Color(51, 51, 51));

        btnGuardar.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(204, 204, 204));
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        roundPanel5.add(roundPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, -1, -1));

        tblEstudiante.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblEstudiante);

        roundPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 960, 190));

        txtApellidos.setBackground(new java.awt.Color(204, 204, 204));
        txtApellidos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtApellidos.setForeground(new java.awt.Color(0, 0, 0));
        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });
        roundPanel5.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 230, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Apellidos:");
        roundPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, -1, -1));

        jPanel1.add(roundPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 1020, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        actualizar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void cbxTituloBachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTituloBachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTituloBachActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

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
            new FrmEstudiante().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jlabel;
    private javax.swing.JLabel Jlabelll;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
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
    private javax.swing.JScrollPane jScrollPane1;
    private plantilla.components.Menu_Admin menu_Admin1;
    private plantilla.swing.RoundPanel roundPanel2;
    private plantilla.swing.RoundPanel roundPanel3;
    private plantilla.swing.RoundPanel roundPanel4;
    private plantilla.swing.RoundPanel roundPanel5;
    private javax.swing.JTable tblEstudiante;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEtnia;
    private javax.swing.JTextField txtFechaNac;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
