/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloEstudianteDocente.vista;

import ModuloEstudianteDocente.controlador.DocenteControlador;
import ModuloEstudianteDocente.vista.tablas.ModeloTablaDocente;
import javax.swing.JOptionPane;
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
        cargarTabla();
    }
    
    private void cargarTabla(){
        modeloDocente.setDocente(docenteControlador.list_All());
        tblDocente.setModel(modeloDocente);
        tblDocente.updateUI();
    }
    
    private void limpiar(){
        
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
    
    private Boolean validar(){
        return !txtNombresDoc.getText().trim().isEmpty()
                && !txtFechaNacim.getText().trim().isEmpty()
                && !txtCorreoPersonal.getText().trim().isEmpty()
                && !txtDNI.getText().trim().isEmpty()
                && !txtTelefn.getText().trim().isEmpty()
                && !txtCodigoEmp.getText().trim().isEmpty()
                && !txtAniosExpe.getText().trim().isEmpty()
                && !txtGradoAcademico.getText().trim().isEmpty();
        
    }
    
    public void guardar(){
        if (validar()) {
            try{
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
                    docenteControlador.update(docte,fila);
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Docente actualizado correctamente", 
                            "Mensaje",
                            JOptionPane.INFORMATION_MESSAGE);
                }else{
                    docte.setId(docenteControlador.generarID());
                    docenteControlador.save(docte);
                    limpiar();
                    
                    JOptionPane.showMessageDialog(null, "Docente registrado correctamente", 
                            "Mensaje",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), 
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                
            }
        }else{
            JOptionPane.showMessageDialog(null,
                    "Por favor llene todos los campos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizar(){
        int fila = tblDocente.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione una fila",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            try{
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
                
            }catch(Exception e){
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
        roundPanel3 = new plantilla.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocente = new javax.swing.JTable();
        roundPanel4 = new plantilla.swing.RoundPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
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
        roundPanel6 = new plantilla.swing.RoundPanel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        roundPanel7 = new plantilla.swing.RoundPanel();
        imageAvatar1 = new com.raven.swing.ImageAvatar();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                .addContainerGap(405, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel1.add(roundPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 790, 80));

        roundPanel3.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel3.setLayout(new javax.swing.BoxLayout(roundPanel3, javax.swing.BoxLayout.LINE_AXIS));

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

        roundPanel3.add(jScrollPane1);

        jPanel1.add(roundPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, 790, 180));

        roundPanel4.setBackground(new java.awt.Color(51, 51, 51));

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel4Layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
        );

        jPanel1.add(roundPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 230, 450));

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

        jPanel1.add(roundPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 600, 260));

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

        jPanel1.add(roundPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 100, 180, 260));

        roundPanel7.setBackground(new java.awt.Color(51, 51, 51));

        imageAvatar1.setForeground(new java.awt.Color(231, 231, 231));
        imageAvatar1.setBorderSize(2);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plantilla/img/profile.jpg"))); // NOI18N

        jLabel7.setForeground(new java.awt.Color(203, 203, 203));
        jLabel7.setText("Docente");

        javax.swing.GroupLayout roundPanel7Layout = new javax.swing.GroupLayout(roundPanel7);
        roundPanel7.setLayout(roundPanel7Layout);
        roundPanel7Layout.setHorizontalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        roundPanel7Layout.setVerticalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel7Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel7Layout.createSequentialGroup()
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(15, 15, 15))))
        );

        jPanel1.add(roundPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 230, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1054, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                .addContainerGap())
        );

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
            java.util.logging.Logger.getLogger(FrmDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDocente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jlabel111111;
    private javax.swing.JLabel Jlablsss;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private com.raven.swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabeljhgfgh;
    private plantilla.swing.RoundPanel roundPanel1;
    private plantilla.swing.RoundPanel roundPanel2;
    private plantilla.swing.RoundPanel roundPanel3;
    private plantilla.swing.RoundPanel roundPanel4;
    private plantilla.swing.RoundPanel roundPanel5;
    private plantilla.swing.RoundPanel roundPanel6;
    private plantilla.swing.RoundPanel roundPanel7;
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
