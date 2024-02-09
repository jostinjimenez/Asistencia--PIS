package moduloAsignaturas.view;

import moduloAsignaturas.controller.MallaController;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import model.Malla;
import modulo_1.inicio_sesion.view.tablas.ModeloTablaCuenta;
import tda_listas.exceptions.VacioExceptions;
import moduloAsignaturas.view.tablas.ModeloTablaMallas;

import static moduloAsignaturas.view.util_vista.Utiles.cargarCarrera;
import static moduloAsignaturas.view.util_vista.Utiles.getComboCarrera;

public class Frm_MallaAcademica extends javax.swing.JFrame {

    private MallaController mallaController = new MallaController();
    private ModeloTablaMallas modeloTablaMallas = new ModeloTablaMallas();
    private boolean isEditing = false;

    public Frm_MallaAcademica() {
        initComponents();
        limpiar();

        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Mallas Académicas");


    }

    public void cargarTabla() {
        modeloTablaMallas.setMallas(mallaController.list_All());
        tablaMallas.setModel(modeloTablaMallas);
        tablaMallas.updateUI();
    }

    public Boolean validar() {
        return !txtNro_Asignaturas.getText().trim().isEmpty()
                && !txtDescripcion.getText().trim().isEmpty()
                && !txtCodigo.getText().trim().isEmpty()
                && !txtHorasT.getText().trim().isEmpty()
                && cbxCarrera.getSelectedIndex() > 0;
    }

    public void guardar() {
        if (validar()) {
            try {
                if (isEditing) {
                    updateMalla();
                } else {
                    saveMalla();
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

    private void saveMalla() throws Exception {
        mallaController.getMalla().setDescripcion(txtDescripcion.getText());
        mallaController.getMalla().setCodigo(txtCodigo.getText());
        mallaController.getMalla().setNro_asignaturas(Integer.parseInt(txtNro_Asignaturas.getText()));
        mallaController.getMalla().setTotal_horas(Integer.parseInt(txtHorasT.getText()));
        mallaController.getMalla().setCarrera_id(getComboCarrera(cbxCarrera).getId());

        if (mallaController.save() > 0) {
            limpiar();
            cargarTabla();
            JOptionPane.showMessageDialog(null, "Se guardo correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(null, "Se guardó correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }


//    private void eliminar() {
//        int filaSeleccionada = tablaMallas.getSelectedRow();
//
//        if (filaSeleccionada >= 0) {
//            int confirmacion = JOptionPane.showConfirmDialog(
//                    this,
//                    "¿Está seguro de que desea eliminar la malla académica seleccionada?",
//                    "Confirmar eliminación",
//                    JOptionPane.YES_NO_OPTION);
//
//            if (confirmacion == JOptionPane.YES_OPTION) {
//                int mallaAEliminar = obtenerIdDesdeFilaSeleccionada(filaSeleccionada);
//
//                if (mallaController.delete(mallaAEliminar)) {
//                    cargarTabla();
//
//                    JOptionPane.showMessageDialog(
//                            null,
//                            "Se eliminó correctamente",
//                            "OK",
//                            JOptionPane.INFORMATION_MESSAGE);
//                } else {
//                    JOptionPane.showMessageDialog(
//                            null,
//                            "Error al eliminar la malla académica",
//                            "Error",
//                            JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        } else {
//            JOptionPane.showMessageDialog(
//                    null,
//                    "Seleccione una fila para eliminar",
//                    "Error",
//                    JOptionPane.ERROR_MESSAGE);
//        }
//    }

    public void limpiar() {
        txtNro_Asignaturas.setText("");
        txtHorasT.setText("");
        txtDescripcion.setText("");
        txtCodigo.setText("");
        cbxCarrera.setSelectedIndex(0);
        isEditing = false;
        cargarTabla();
        cargarCarrera(cbxCarrera);
    }

    private void updateMalla() {
        if (mallaController.update()) {
            JOptionPane.showMessageDialog(null, "Se actualizó correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiar();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarVista() {
        mallaController.setIndex(tablaMallas.getSelectedRow());
        if (mallaController.getIndex() < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                isEditing = true;
                mallaController.setMalla(modeloTablaMallas.getMallas().get(mallaController.getIndex()));
                txtCodigo.setText(mallaController.getMalla().getCodigo());
                txtDescripcion.setText(mallaController.getMalla().getDescripcion());
                txtHorasT.setText(mallaController.getMalla().getTotal_horas().toString());
                txtNro_Asignaturas.setText(mallaController.getMalla().getNro_asignaturas().toString());
                cbxCarrera.setSelectedIndex(mallaController.getMalla().getCarrera_id());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

//    private void buscar() {
//        // Obtener el criterio ingresado en el campo de búsqueda
//        String criterioBusqueda = txtBuscarCriterio.getText();  // No convertir a minúsculas
//
//        // Obtener el criterio de búsqueda seleccionado en el combobox
//        String criterio = comboBoxCriterio.getSelectedItem().toString();  // No convertir a minúsculas
//
//        // Verificar si se ingresó un criterio válido
//        if (!criterio.isEmpty()) {
//            // Seleccionar el comparador adecuado según el criterio de búsqueda
//            Comparator<Malla> comparador = (criterio.equals("descripcion"))
//                    ? Comparator.comparing(Malla::getDescripcion)
//                    : (criterio.equals("duracion"))
//                    ? Comparator.comparing(Malla::getDuracion)
//                    : Comparator.comparing(Malla::getNombreSilabo);
//
//            // Realizar la búsqueda en el modelo de la tabla
//            int indice = modeloTablaMallas.buscar(criterioBusqueda, comparador, criterio);
//
//            // Verificar si se encontró la asignatura
//            if (indice >= 0) {
//                // Seleccionar la fila encontrada
//                tablaMallas.setRowSelectionInterval(indice, indice);
//                // Cargar los datos seleccionados en los campos de texto
//                cargarDatosSeleccionados();
//            } else {
//                JOptionPane.showMessageDialog(null, "No se encontró ninguna asignatura con ese criterio", "Información", JOptionPane.INFORMATION_MESSAGE);
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Seleccione un criterio (Nombre o Código) para buscar", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        comboBoxCriterio = new javax.swing.JComboBox<>();
        txtBuscarCriterio = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMallas = new javax.swing.JTable();
        ComboBoxOrdenar = new javax.swing.JComboBox<>();
        btnOrdenar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        txtHorasT = new javax.swing.JTextField();
        cbxCarrera = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtNro_Asignaturas = new javax.swing.JTextField();
        menu_Admin1 = new plantilla.components.Menu_Admin();
        header2 = new plantilla.components.Header();

        jLabel3.setText("Ingrese el criterio:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(21, 21, 21));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Horas Totales:");
        roundPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Carrera");
        roundPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Descripción:");
        roundPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtCodigo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 254, 31));

        txtDescripcion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        roundPanel1.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 254, 30));

        btnAgregar.setBackground(new java.awt.Color(242, 242, 242));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/boton-agregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setBorder(null);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 111, 30));

        btnModificar.setBackground(new java.awt.Color(242, 242, 242));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/lista-de-verificacion (1).png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setBorder(null);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 570, 105, 30));

        btnEliminar.setBackground(new java.awt.Color(242, 242, 242));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/error.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(null);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 570, 104, 30));

        comboBoxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "descripcion", "duracion", "silabo" }));
        roundPanel1.add(comboBoxCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 120, -1));
        roundPanel1.add(txtBuscarCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(795, 250, 220, -1));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 200, -1, -1));

        tablaMallas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Descripción", "Duración", "Silabo"
            }
        ));
        tablaMallas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMallasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaMallas);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 920, 193));

        ComboBoxOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente", "Descendente" }));
        roundPanel1.add(ComboBoxOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 320, -1, -1));

        btnOrdenar.setText("Ordenar");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 320, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 28)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Registro de Mallas Academicas");
        roundPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 550, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nro. Asignaturas:");
        roundPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, -1));

        txtHorasT.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtHorasT.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.add(txtHorasT, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 254, 31));

        cbxCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel1.add(cbxCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 120, 200, -1));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Codigo:");
        roundPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        txtNro_Asignaturas.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNro_Asignaturas.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.add(txtNro_Asignaturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 254, 31));

        jPanel3.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 1040, 630));
        jPanel3.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 680));
        jPanel3.add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 1040, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        guardar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        guardar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tablaMallasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMallasMouseClicked

    }//GEN-LAST:event_tablaMallasMouseClicked

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed

    }//GEN-LAST:event_btnOrdenarActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_MallaAcademica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_MallaAcademica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_MallaAcademica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_MallaAcademica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_MallaAcademica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxOrdenar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JComboBox<String> cbxCarrera;
    private javax.swing.JComboBox<String> comboBoxCriterio;
    private plantilla.components.Header header2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private plantilla.components.Menu_Admin menu_Admin1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tablaMallas;
    private javax.swing.JTextField txtBuscarCriterio;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtHorasT;
    private javax.swing.JTextField txtNro_Asignaturas;
    // End of variables declaration//GEN-END:variables
}
