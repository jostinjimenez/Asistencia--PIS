package moduloAsignaturas.view;

import moduloAsignaturas.controller.MallaController;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Malla;
import tda_listas.exceptions.VacioExceptions;
import moduloAsignaturas.view.tablas.ModeloTablaMallas;

public class Frm_MallaAcademica extends javax.swing.JFrame {

    private MallaController mallaController = new MallaController();
    private ModeloTablaMallas modeloTablaMallas = new ModeloTablaMallas();

    public Frm_MallaAcademica() {
        initComponents();

        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Mallas Académicas");

        mallaController = new MallaController();
        modeloTablaMallas = new ModeloTablaMallas();
        modeloTablaMallas.setMallaController(mallaController);

        cargarTabla();
    }

    public void cargarTabla() {
        modeloTablaMallas.getMallaController().setLista(modeloTablaMallas.getMallaController().list_All());
        tablaMallas.setModel(modeloTablaMallas);
        tablaMallas.updateUI();
    }

    public Boolean validar() {
        return !txtDuracion.getText().trim().isEmpty()
                && !txtDescripcion.getText().trim().isEmpty()
                && !txtNombreSilabo.getText().trim().isEmpty();
    }

    public void agregar() {
        if (validar()) {
            try {
                // Obtener los datos del formulario
                String descripcion = txtDescripcion.getText();
                String duracion = txtDuracion.getText();
                String silabo = txtNombreSilabo.getText();

                // Generar nuevo id
                Integer nuevoId = Integer.parseInt(mallaController.generatedCode());

                // Configurar los datos en el controlador
                mallaController.getMalla().setId(nuevoId);
                mallaController.getMalla().setDescripcion(descripcion);
                mallaController.getMalla().setDuracion(duracion);
                mallaController.getMalla().setNombreSilabo(silabo);

                if (mallaController.saved()) {
                    cargarTabla();
                    JOptionPane.showMessageDialog(null, "Se guardo correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                // Manejar la excepción aquí
                System.out.println("Error al guardar la Asignatura desde el formulario: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
        }
    }

    private int obtenerIdDesdeFilaSeleccionada(int filaSeleccionada) {
        int columnaId = 0;
        return (int) tablaMallas.getValueAt(filaSeleccionada, columnaId);
    }

    private void eliminar() {
        int filaSeleccionada = tablaMallas.getSelectedRow();

        if (filaSeleccionada >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de que desea eliminar la malla académica seleccionada?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                int mallaAEliminar = obtenerIdDesdeFilaSeleccionada(filaSeleccionada);

                if (mallaController.delete(mallaAEliminar)) {
                    cargarTabla();

                    JOptionPane.showMessageDialog(
                            null,
                            "Se eliminó correctamente",
                            "OK",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Error al eliminar la malla académica",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Seleccione una fila para eliminar",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiar() {
        txtDuracion.setText("");
        txtDescripcion.setText("");
        txtNombreSilabo.setText("");
    }

    public void modificar() throws VacioExceptions {
        if (validar()) {
            String duracion = txtDuracion.getText();
            String descripcion = txtDescripcion.getText();
            String nombreSilabo = txtNombreSilabo.getText();

            mallaController.getMalla().setDuracion(duracion);
            mallaController.getMalla().setDescripcion(descripcion);
            mallaController.getMalla().setNombreSilabo(nombreSilabo);

            int filaSeleccionada = tablaMallas.getSelectedRow();

            if (filaSeleccionada >= 0 && filaSeleccionada < mallaController.getLista().getSize()) {
                Malla nuevaMalla = cargarDatosSeleccionados();
                mallaController.update(nuevaMalla, filaSeleccionada);

                cargarTabla();
                JOptionPane.showMessageDialog(null, "Se modificó correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
        }
    }

    private Malla cargarDatosSeleccionados() {
        int filaSeleccionada = tablaMallas.getSelectedRow();
        int id = obtenerIdDesdeFilaSeleccionada(filaSeleccionada);
        String duracion = txtDuracion.getText();
        String descripcion = txtDescripcion.getText();
        String nombreSilabo = txtNombreSilabo.getText();

        return new Malla(id, duracion, descripcion, nombreSilabo, null);
    }

    private void buscar() {
        // Obtener el criterio ingresado en el campo de búsqueda
        String criterioBusqueda = txtBuscarCriterio.getText();  // No convertir a minúsculas

        // Obtener el criterio de búsqueda seleccionado en el combobox
        String criterio = comboBoxCriterio.getSelectedItem().toString();  // No convertir a minúsculas

        // Verificar si se ingresó un criterio válido
        if (!criterio.isEmpty()) {
            // Seleccionar el comparador adecuado según el criterio de búsqueda
            Comparator<Malla> comparador = (criterio.equals("descripcion"))
                    ? Comparator.comparing(Malla::getDescripcion)
                    : (criterio.equals("duracion"))
                    ? Comparator.comparing(Malla::getDuracion)
                    : Comparator.comparing(Malla::getNombreSilabo);

            // Realizar la búsqueda en el modelo de la tabla
            int indice = modeloTablaMallas.buscar(criterioBusqueda, comparador, criterio);

            // Verificar si se encontró la asignatura
            if (indice >= 0) {
                // Seleccionar la fila encontrada
                tablaMallas.setRowSelectionInterval(indice, indice);
                // Cargar los datos seleccionados en los campos de texto
                cargarDatosSeleccionados();
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna asignatura con ese criterio", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un criterio (Nombre o Código) para buscar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ordenar() {
        String campoOrden = comboBoxCriterio.getSelectedItem().toString().toLowerCase();
        String tipoOrden = ComboBoxOrdenar.getSelectedItem().toString().toLowerCase();

        if (!campoOrden.isEmpty() && modeloTablaMallas.esCampoValido(campoOrden)) {
            modeloTablaMallas.ordenar(campoOrden, tipoOrden);
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Campo de orden inválido",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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

        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreSilabo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtDuracion = new javax.swing.JTextField();
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
        jLabel1.setText("Silabo:");
        roundPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Duración:");
        roundPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Descripción:");
        roundPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        txtNombreSilabo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombreSilabo.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.add(txtNombreSilabo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 254, 31));

        txtDescripcion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        roundPanel1.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 254, 30));

        txtDuracion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtDuracion.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.add(txtDuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 254, 30));

        btnAgregar.setBackground(new java.awt.Color(242, 242, 242));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/boton-agregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setBorder(null);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 111, 30));

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

        jPanel3.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 1040, 630));
        jPanel3.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 680));
        jPanel3.add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 1040, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            modificar();
        } catch (VacioExceptions ex) {
            Logger.getLogger(Frm_MallaAcademica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tablaMallasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMallasMouseClicked
        cargarDatosSeleccionados();
    }//GEN-LAST:event_tablaMallasMouseClicked

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
        ordenar();
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
    private javax.swing.JComboBox<String> comboBoxCriterio;
    private plantilla.components.Header header2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private plantilla.components.Menu_Admin menu_Admin1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tablaMallas;
    private javax.swing.JTextField txtBuscarCriterio;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtNombreSilabo;
    // End of variables declaration//GEN-END:variables
}
