package moduloAsignaturas.view;

import moduloAsignaturas.controller.AsignaturaController;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import moduloAsignaturas.view.tablas.ModeloTablaAsignaturas;
import model.Asignatura;
import tda_listas.exceptions.VacioExceptions;

public class FrmAgregarAsignatura extends javax.swing.JFrame {

    private AsignaturaController ac = new AsignaturaController();
    private ModeloTablaAsignaturas mta = new ModeloTablaAsignaturas();

    /**
     * Creates new form Frm_AgregarAsistencia
     */
    public FrmAgregarAsignatura() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Agregar Asignaturas");

        ac = new AsignaturaController();
        mta = new ModeloTablaAsignaturas();
        mta.setAsignaturaController(ac);

        cargarTabla();
    }

    public void cargarTabla() {
        mta.getAsignaturaController().setAsignaturas(mta.getAsignaturaController().list_All());
        tablaAsignaturas.setModel(mta);
        mta.fireTableDataChanged();
        tablaAsignaturas.updateUI();
        tablaAsignaturas.repaint();  // Agrega esta línea
    }

    public Boolean validar() {
        return !txtNombreAsignatura.getText().trim().isEmpty()
                && !txtCodigoAsigntura.getText().trim().isEmpty()
                && !txtHorasTotales.getText().trim().isEmpty();
    }

    public void guardar() {
        if (validar()) {
            try {
                // Obtener los datos del formulario
                String nombre = txtNombreAsignatura.getText();
                String codigoStr = txtCodigoAsigntura.getText();
                String horasTotalesStr = txtHorasTotales.getText();

                // Convertir a los tipos de datos apropiados
                Integer horasTotales = Integer.parseInt(horasTotalesStr);
                Integer codigo = Integer.parseInt(codigoStr);

                // Generar nuevo id
                Integer nuevoId = Integer.parseInt(ac.generatedCode());

                // Configurar los datos en el controlador
                ac.getAsignatura().setId(nuevoId);
                ac.getAsignatura().setNombre(nombre);  
                ac.getAsignatura().setCodigo(codigo);  
                ac.getAsignatura().setHorasTotales(horasTotales);


                if (ac.saved()) {
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

    private void eliminar() {
        // Obtener el índice de la fila seleccionada
        int filaSeleccionada = tablaAsignaturas.getSelectedRow();

        // Verificar si se ha seleccionado alguna fila
        if (filaSeleccionada >= 0) {
            // Confirmar si realmente desea eliminar la fila
            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de que desea eliminar la asignatura seleccionada?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                try {
                    // Obtener el objeto Asignatura correspondiente a la fila seleccionada
                    Asignatura asignaturaAEliminar = ac.getAsignaturas().get(filaSeleccionada);

                    // Eliminar el objeto Asignatura utilizando el método delete del controlador
                    if (ac.delete(asignaturaAEliminar)) {
                        // Actualizar la tabla
                        cargarTabla();

                        JOptionPane.showMessageDialog(
                                null,
                                "Se eliminó correctamente",
                                "OK",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Error al eliminar la asignatura",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (VacioExceptions e) {
                    // Manejar la excepción aquí
                    JOptionPane.showMessageDialog(
                            null,
                            "Error al eliminar la asignatura: " + e.getMessage(),
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
        txtNombreAsignatura.setText("");
        txtCodigoAsigntura.setText("");
        txtHorasTotales.setText("");
    }

    public void modificar() {
        if (validar()) {
            // Obtener el índice de la fila seleccionada
            int filaSeleccionada = tablaAsignaturas.getSelectedRow();

            // Verificar si se ha seleccionado alguna fila
            if (filaSeleccionada >= 0) {
                // Obtener los datos del formulario
                String nombre = txtNombreAsignatura.getText();
                String codigoStr = txtCodigoAsigntura.getText();
                String horasTotalesStr = txtHorasTotales.getText();

                // Convertir a los tipos de datos apropiados
                Integer horasTotales = Integer.parseInt(horasTotalesStr);
                Integer codigo = Integer.parseInt(codigoStr);
                
                // Configurar los datos en el controlador
                ac.getAsignatura().setNombre(nombre);
                ac.getAsignatura().setCodigo(codigo);
                ac.getAsignatura().setHorasTotales(horasTotales);

                // Actualizar la información en la lista y en la tabla
                ac.update(filaSeleccionada);
                cargarTabla();

                JOptionPane.showMessageDialog(null, "Se modificó correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
        }
    }

    private void cargarDatosSeleccionados() {
        // Obtener el índice de la fila seleccionada
        int filaSeleccionada = tablaAsignaturas.getSelectedRow();

        // Verificar si se ha seleccionado alguna fila
        if (filaSeleccionada >= 0) {
            // Obtener los datos de la fila seleccionada desde el modelo de la tabla
            String id = tablaAsignaturas.getValueAt(filaSeleccionada, 0).toString();
            String nombre = tablaAsignaturas.getValueAt(filaSeleccionada, 1).toString();
            String codigo = tablaAsignaturas.getValueAt(filaSeleccionada, 2).toString();
            String horasTotales = tablaAsignaturas.getValueAt(filaSeleccionada, 3).toString();

            // Mostrar los datos en los campos de texto
            txtNombreAsignatura.setText(nombre);
            txtCodigoAsigntura.setText(codigo);
            txtHorasTotales.setText(horasTotales);
        }
    }

    private void buscar() throws VacioExceptions {
        // Obtener el criterio ingresado en el campo de búsqueda
        String criterioBusqueda = txtBuscar.getText();  // No convertir a minúsculas

        // Obtener el criterio de búsqueda seleccionado en el combobox
        String criterio = comboBoxCriterio.getSelectedItem().toString();  // No convertir a minúsculas

        // Verificar si se ingresó un criterio válido
        if (!criterio.isEmpty()) {
            // Seleccionar el comparador adecuado según el criterio de búsqueda
            Comparator<Asignatura> comparador = (criterio.equals("nombre"))
                    ? Comparator.comparing(Asignatura::getNombre) // Comparar directamente sin convertir a minúsculas
                    : Comparator.comparing(Asignatura::getCodigo);  // Comparar directamente sin convertir a minúsculas

            // Realizar la búsqueda en el modelo de la tabla
            int indice = mta.buscar(criterioBusqueda, comparador, criterio);

            // Verificar si se encontró la asignatura
            if (indice >= 0) {
                // Seleccionar la fila encontrada
                tablaAsignaturas.setRowSelectionInterval(indice, indice);
                // Cargar los datos seleccionados en los campos de texto
                cargarDatosSeleccionados();
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna asignatura con ese criterio", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un criterio (Nombre o Código) para buscar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ordenar() throws VacioExceptions {
        // Obtener el campo de orden del ComboBox
        String campoOrden = comboBoxCriterio.getSelectedItem().toString().toLowerCase();
        // Obtener el tipo de orden del ComboBox
        String tipoOrden = ComboBoxOrdenar.getSelectedItem().toString().toLowerCase();

        if (!campoOrden.isEmpty() && mta.esCampoValido(campoOrden)) {
            // Llama al método ordenar con el campo de orden y tipo de orden
            mta.ordenar(campoOrden, tipoOrden);
        } else {
            JOptionPane.showMessageDialog(null, "Campo de orden inválido", "Error", JOptionPane.ERROR_MESSAGE);
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
        menu_Admin1 = new plantilla.components.Menu_Admin();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAsignaturas = new javax.swing.JTable();
        btnOrdenar = new javax.swing.JButton();
        ComboBoxOrdenar = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        comboBoxCriterio = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigoAsigntura = new javax.swing.JTextField();
        txtHorasTotales = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtNombreAsignatura = new javax.swing.JTextField();
        header2 = new plantilla.components.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(21, 21, 21));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 680));

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setForeground(new java.awt.Color(0, 0, 0));

        tablaAsignaturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Codigo", "Horas Totales"
            }
        ));
        tablaAsignaturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAsignaturasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAsignaturas);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 980, 220));

        btnOrdenar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnOrdenar.setForeground(new java.awt.Color(0, 0, 0));
        btnOrdenar.setText("Ordenar");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 300, -1, -1));

        ComboBoxOrdenar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        ComboBoxOrdenar.setForeground(new java.awt.Color(0, 0, 0));
        ComboBoxOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente", "Descendente" }));
        roundPanel1.add(ComboBoxOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 300, 130, -1));

        txtBuscar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 240, 175, -1));

        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 230, -1, -1));

        comboBoxCriterio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        comboBoxCriterio.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Codigo" }));
        roundPanel1.add(comboBoxCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 120, -1));

        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar-el-archivo.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/error.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 570, -1, -1));

        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 570, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Codigo de la Asignatura:");
        roundPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Horas totales:");
        roundPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        txtCodigoAsigntura.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCodigoAsigntura.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.add(txtCodigoAsigntura, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 100, 250, -1));

        txtHorasTotales.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtHorasTotales.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.add(txtHorasTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 250, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Agregar Asignaturas");
        roundPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 510, 10));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre de la Asignatura:");
        roundPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        txtNombreAsignatura.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombreAsignatura.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.add(txtNombreAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 250, -1));

        jPanel1.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 1040, 630));
        jPanel1.add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 1040, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tablaAsignaturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAsignaturasMouseClicked
        cargarDatosSeleccionados();
    }//GEN-LAST:event_tablaAsignaturasMouseClicked

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
        try {
            ordenar();
        } catch (VacioExceptions ex) {
            Logger.getLogger(FrmAgregarAsignatura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOrdenarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            buscar();
        } catch (VacioExceptions ex) {
            Logger.getLogger(FrmAgregarAsignatura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FrmAgregarAsignatura().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxOrdenar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JComboBox<String> comboBoxCriterio;
    private plantilla.components.Header header2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private plantilla.components.Menu_Admin menu_Admin1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tablaAsignaturas;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigoAsigntura;
    private javax.swing.JTextField txtHorasTotales;
    private javax.swing.JTextField txtNombreAsignatura;
    // End of variables declaration//GEN-END:variables

}
