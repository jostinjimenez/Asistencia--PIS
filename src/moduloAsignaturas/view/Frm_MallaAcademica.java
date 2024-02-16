package moduloAsignaturas.view;

import ModuloMatricula.tablas.ModeloTablaCursas;
import moduloAsignaturas.controller.MallaController;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import model.Malla;
import modulo_1.inicio_sesion.view.tablas.ModeloTablaCuenta;
import modulo_1.inicio_sesion.view.util.HeaderRenderer;
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
        setupListeners();
        limpiar();

        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Mallas Académicas");


    }

    public void cargarTabla() {
        modeloTablaMallas.setMallas(mallaController.list_All());
        tablaMallas.setModel(modeloTablaMallas);
        tablaMallas.updateUI();

        TableRowSorter<ModeloTablaMallas> trs = new TableRowSorter<>(modeloTablaMallas);
        tablaMallas.setRowSorter(trs);
        tablaMallas.getTableHeader().setReorderingAllowed(false);
        tablaMallas.getTableHeader().setResizingAllowed(false);
        tablaMallas.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        tablaMallas.setRowHeight(30); // Ajusta este valor según tus necesidades

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablaMallas.getColumnCount(); i++) {
            tablaMallas.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    public Boolean validar() {
        return !txtNro_Asignaturas.getText().trim().isEmpty()
                && !txtDescripcion.getText().trim().isEmpty()
                && !txtCodigo.getText().trim().isEmpty()
                && !txtHorasT.getText().trim().isEmpty()
                && cbxCarrera.getSelectedIndex() >= 0;
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

    private void setupListeners() {
        txtCodigo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || txtCodigo.getText().length() >= 30) {
                    e.consume();
                }
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

        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        comboBoxCriterio = new javax.swing.JComboBox<>();
        txtBuscarCriterio = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMallas = new javax.swing.JTable();
        ComboBoxOrdenar = new javax.swing.JComboBox<>();
        btnOrdenar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHorasT = new javax.swing.JTextField();
        cbxCarrera = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtNro_Asignaturas = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        menu_Admin1 = new plantilla.components.Menu_Admin();
        header1 = new plantilla.components.Header();

        jLabel3.setText("Ingrese el criterio:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(225, 233, 243));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Horas Totales:");
        roundPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Carrera");
        roundPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Descripción:");
        roundPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        txtCodigo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(0, 0, 0));
        roundPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 254, 31));

        txtDescripcion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(0, 0, 0));
        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        roundPanel1.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 254, 30));

        comboBoxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "descripcion", "duracion", "silabo" }));
        roundPanel1.add(comboBoxCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 120, -1));
        roundPanel1.add(txtBuscarCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 320, 220, -1));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, -1, 30));

        tablaMallas.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
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

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 102, 102));
        jLabel5.setText("Registro de Mallas Academicas");
        roundPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Nro. Asignaturas:");
        roundPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, -1, -1));

        txtHorasT.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtHorasT.setForeground(new java.awt.Color(0, 0, 0));
        roundPanel1.add(txtHorasT, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, 254, 31));

        cbxCarrera.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel1.add(cbxCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, 200, -1));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Codigo:");
        roundPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        txtNro_Asignaturas.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNro_Asignaturas.setForeground(new java.awt.Color(0, 0, 0));
        roundPanel1.add(txtNro_Asignaturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 120, 254, 31));

        btnGuardar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 0, 0));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 560, -1, -1));

        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 560, -1, -1));

        jPanel3.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 1040, 620));
        jPanel3.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 680));

        header1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.add(header1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMallasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMallasMouseClicked

    }//GEN-LAST:event_tablaMallasMouseClicked

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed

    }//GEN-LAST:event_btnOrdenarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        guardar();
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
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JComboBox<String> cbxCarrera;
    private javax.swing.JComboBox<String> comboBoxCriterio;
    private plantilla.components.Header header1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
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
