/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ModuloEstudiante.vista;

import ModuloEstudiante.controlador.ControladorEstudiante;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import ModuloEstudiante.vista.tablas.ModeloTablaEstudiante;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.Estudiante;



/**
 *
 * @author LENOVO
 */
public class FrmEstudiante extends javax.swing.JFrame {
    
    private ControladorEstudiante estudianteControlador = new ControladorEstudiante();
    private ModeloTablaEstudiante modeloEstudiante = new ModeloTablaEstudiante();
    private Integer fila = -1;
    

    /**
     * Creates new form FrmEstudiante
     */
    public FrmEstudiante() {
        initComponents();
        setupListeners();
        cargarTabla();
    }
    
    private void cargarTabla(){
        modeloEstudiante.setEstudiante(estudianteControlador.list_All());
        tblEstudiante.setModel(modeloEstudiante);
        tblEstudiante.updateUI();
    }
    
    private void limpiar(){
        txtNombres.setText(" ");
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
    
    private Boolean validar(){
        return !txtNombres.getText().trim().isEmpty()
                && !txtFechaNac.getText().trim().isEmpty()
                && !txtCorreo.getText().trim().isEmpty()
                && !txtCedula.getText().trim().isEmpty()
                && !txtTelefono.getText().trim().isEmpty()
                && !txtEtnia.getText().trim().isEmpty()
                && !txtDireccion.getText().trim().isEmpty();
    }
    
    public void guardar(){
        if (validar()) {
            try{
                Estudiante est = new Estudiante();
                est.setNombre(txtNombres.getText());
                est.setFecha_nacimiento(txtFechaNac.getText());
                est.setCorreo_personal(txtCorreo.getText());
                est.setDni(txtCedula.getText());
                est.setTelefono(txtTelefono.getText());
                est.setEtnia(txtEtnia.getText());  
                est.setTitulo_bachiller(true);
                //est.setTitulo_bachiller(cbxTituloBach.getSelectedItem().toString());
                est.setDireccion(txtDireccion.getText());
                
                if (fila != -1) {
                    estudianteControlador.update(est,fila);
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Estudiante actualizado correctamente", 
                            "Mensaje",
                            JOptionPane.INFORMATION_MESSAGE);
                }else{
                    est.setId(estudianteControlador.generarID());
                    estudianteControlador.save(est);
                    limpiar();
                    
                    JOptionPane.showMessageDialog(null, "Estudiante registrado correctamente", 
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
        int fila = tblEstudiante.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione una fila",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                this.fila = fila;
                
                estudianteControlador.setEstudiante(modeloEstudiante.getEstudiante().get(fila));
                txtNombres.setText(estudianteControlador.getEstudiante().getNombre());
                txtFechaNac.setText(estudianteControlador.getEstudiante().getFecha_nacimiento());
                txtCorreo.setText(estudianteControlador.getEstudiante().getCorreo_personal());
                txtCedula.setText(estudianteControlador.getEstudiante().getDni());
                txtTelefono.setText(estudianteControlador.getEstudiante().getTelefono());
                txtEtnia.setText(estudianteControlador.getEstudiante().getTelefono());
                cbxTituloBach.setSelectedItem(true);
                txtDireccion.setText(estudianteControlador.getEstudiante().getDireccion());
                
            }catch(Exception e){
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
        
        txtFechaNac.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == '/') || txtFechaNac.getText().length() >= 10) {
                    e.consume(); 
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                validateDateFormat();
            }
        });
    }
    
    private void validateDateFormat() {
        String inputDate = txtFechaNac.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            // Intentar parsear la fecha; si tiene éxito, se considera una fecha válida
            Date parsedDate = dateFormat.parse(inputDate);
        } catch (ParseException e) {
            // Si hay una excepción, la fecha no es válida
            System.out.println("Fecha no valida");
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

        buttonBadges1 = new com.raven.swing.ButtonBadges();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEstudiante = new javax.swing.JTable();
        panelMenu = new javax.swing.JPanel();
        roundPanel2 = new plantilla.swing.RoundPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        roundPanel3 = new plantilla.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Jlabelll = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
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
        roundPanel4 = new plantilla.swing.RoundPanel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        roundPanel5 = new plantilla.swing.RoundPanel();
        imageAvatar1 = new com.raven.swing.ImageAvatar();
        jLabel8 = new javax.swing.JLabel();

        buttonBadges1.setText("buttonBadges1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

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
        jScrollPane2.setViewportView(tblEstudiante);

        jPanel4.add(jScrollPane2);

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 650, 230));

        panelMenu.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        jPanel1.add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 500));

        roundPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\LENOVO\\Downloads\\volver.png")); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Agregar estudiante");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(176, 176, 176)
                .addComponent(jLabel1)
                .addContainerGap(216, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel1.add(roundPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 650, 80));

        roundPanel3.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombres: ");
        roundPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 16, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha de nacimiento:");
        roundPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Correo:");
        roundPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        Jlabelll.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Jlabelll.setForeground(new java.awt.Color(255, 255, 255));
        Jlabelll.setText("Direccion:");
        roundPanel3.add(Jlabelll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Titulo de bachiller: ");
        roundPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        txtNombres.setBackground(new java.awt.Color(204, 204, 204));
        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });
        roundPanel3.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 230, -1));

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
        roundPanel3.add(txtFechaNac, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 230, -1));

        txtCorreo.setBackground(new java.awt.Color(204, 204, 204));
        roundPanel3.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 231, -1));

        txtCedula.setBackground(new java.awt.Color(204, 204, 204));
        roundPanel3.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 231, -1));

        txtTelefono.setBackground(new java.awt.Color(204, 204, 204));
        roundPanel3.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 231, -1));

        txtEtnia.setBackground(new java.awt.Color(204, 204, 204));
        roundPanel3.add(txtEtnia, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 231, -1));

        cbxTituloBach.setBackground(new java.awt.Color(204, 204, 204));
        cbxTituloBach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SI", "NO" }));
        roundPanel3.add(cbxTituloBach, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 230, -1));

        txtDireccion.setBackground(new java.awt.Color(204, 204, 204));
        roundPanel3.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 230, -1));

        jPanel1.add(roundPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 460, 260));

        roundPanel4.setBackground(new java.awt.Color(51, 51, 51));

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

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

        jPanel1.add(roundPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 100, 180, 260));

        roundPanel5.setBackground(new java.awt.Color(51, 51, 51));

        imageAvatar1.setForeground(new java.awt.Color(231, 231, 231));
        imageAvatar1.setBorderSize(2);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plantilla/img/profile.jpg"))); // NOI18N

        jLabel8.setForeground(new java.awt.Color(203, 203, 203));
        jLabel8.setText("Estudiante");

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );

        jPanel1.add(roundPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        actualizar();
    }//GEN-LAST:event_btnModificarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEstudiante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jlabel;
    private javax.swing.JLabel Jlabelll;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private com.raven.swing.ButtonBadges buttonBadges1;
    private javax.swing.JComboBox<String> cbxTituloBach;
    private com.raven.swing.ImageAvatar imageAvatar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelMenu;
    private plantilla.swing.RoundPanel roundPanel2;
    private plantilla.swing.RoundPanel roundPanel3;
    private plantilla.swing.RoundPanel roundPanel4;
    private plantilla.swing.RoundPanel roundPanel5;
    private javax.swing.JTable tblEstudiante;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEtnia;
    private javax.swing.JTextField txtFechaNac;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
