package modulo_carrera.view.forms;

import javax.swing.*;

import modulo_1.inicio_sesion.view.tablas.ModeloTablaCuenta;
import modulo_carrera.controller.CarreraController;

public class Modal_Carrera extends javax.swing.JDialog {

    public Modal_Carrera(java.awt.Frame parent, boolean modal, ModeloTablaCuenta mtp, JTable jTable1) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        pc.setIndex(-1);
        carrerC.setIndex(-1);

        cargarVista(mtp, jTable1);
        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> this.dispose());
    }

    public Modal_Carrera(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> this.dispose());
    }

    //Variables
    private CarreraController carrerC = new CarreraController();
    private boolean isEditing = false;

    //Metodos
    public void cargarVista(ModeloTablaCuenta mtp, JTable jTable1) {
        pc.setIndex(jTable1.getSelectedRow());
        if (pc.getIndex() < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                isEditing = true;
                carrerC.setCuenta(mtp.getCuentas().get(carrerC.getIndex()));
                txtNombre.setText(carrerC.getPersona(carrerC.getCuenta().getId()).getNombre());
                txtArea.setText(carrerC.getPersona(carrerC.getCuenta().getId()).getApellido());
                txtModalidad.setText(carrerC.getPersona(carrerC.getCuenta().getId()).getDni());
                txtTitulo.setText(carrerC.getPersona(carrerC.getCuenta().getId()).getCorreo_personal());
                txtModalidad.setEnabled(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    public Boolean validar() {
        return !txtNombre.getText().trim().isEmpty()
                && !txtArea.getText().trim().isEmpty()
                && !txtModalidad.getText().trim().isEmpty()
                && !txtTitulo.getText().trim().isEmpty();
    }

    public void guardar() {
        if (validar()) {
            try {
                if (isEditing) {
                    updateCarrera();
                } else {
                    saveCarrera();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveCarrera() throws Exception {
        carrerC.getCarrera().setArea_estudio(txtArea.getText());
        carrerC.getCarrera().setModalidad(txtModalidad.getText());
        carrerC.getCarrera().setNombre(txtNombre.getText());
        carrerC.getCarrera().setTitulo_otorgado(txtTitulo.getText());

        if (carrerC.save() > 0) {
            JOptionPane.showMessageDialog(null, "Se guardó correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCarrera() {
        if (carrerC.update()) {
            JOptionPane.showMessageDialog(null, "Se actualizó correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generarCorreoInst() {
        String nombre = txtNombre.getText().contains(" ") ? txtNombre.getText().substring(0, txtNombre.getText().indexOf(" ")) : txtNombre.getText();
        String apellido = txtArea.getText().contains(" ") ? txtArea.getText().substring(0, txtArea.getText().indexOf(" ")) : txtArea.getText();
        return nombre.toLowerCase() + "." + apellido.toLowerCase() + "@unl.edu.ec";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtModalidad = new javax.swing.JTextField();
        txtArea = new javax.swing.JTextField();
        txtTitulo = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCancelar.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 110, 30));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Nueva Carrera");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, -1, -1));

        txtNombre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 240, 20));

        txtModalidad.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtModalidad.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(txtModalidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 240, 20));

        txtArea.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtArea.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(txtArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 240, 20));

        txtTitulo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 240, 20));

        btnGuardar.setBackground(new java.awt.Color(102, 102, 255));
        btnGuardar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 270, 110, 30));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Nombres: ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Area de estudio:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Titulo:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Modalidad");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {

    }

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Modal_Carrera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Modal_Carrera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Modal_Carrera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Modal_Carrera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                Modal_Carrera dialog = new Modal_Carrera(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtModalidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
