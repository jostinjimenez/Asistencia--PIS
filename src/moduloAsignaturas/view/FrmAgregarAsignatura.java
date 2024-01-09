package moduloAsignaturas.view;

import moduloAsignaturas.controller.AsignaturaController;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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

        // Centrar la ventana
        setLocationRelativeTo(null);

        // Deshabilitar la modificación del tamaño
        setResizable(false);

        // Establecer el nombre de la ventana
        setTitle("Agregar Asignaturas");

        // Inicializar el controlador y el modelo de la tabla
        ac = new AsignaturaController();
        mta = new ModeloTablaAsignaturas();
        mta.setAsignaturaController(ac);

        // Cargar contenido en la tabla al iniciar
        cargarTabla();
    }

    public void cargarTabla() {
        System.out.println("Cargando la tabla...");
        mta.getAsignaturaController().setLista(mta.getAsignaturaController().list_All());
        tablaAsignaturas.setModel(mta);
        mta.fireTableDataChanged();
        tablaAsignaturas.updateUI();
        tablaAsignaturas.repaint();  // Agrega esta línea
        System.out.println("Tabla cargada exitosamente.");
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

                // Agregamos mensajes de depuración
                System.out.println("Datos de la Asignatura antes de guardar:");
                System.out.println("ID: " + ac.getAsignatura().getId());
                System.out.println("Nombre: " + ac.getAsignatura().getNombre());
                System.out.println("Código: " + ac.getAsignatura().getCodigo());
                System.out.println("Horas Totales: " + ac.getAsignatura().getHorasTotales());

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
                    Asignatura asignaturaAEliminar = ac.getLista().get(filaSeleccionada);

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
        jPanel2 = new javax.swing.JPanel();
        btnRegresar = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreAsignatura = new javax.swing.JTextField();
        txtCodigoAsigntura = new javax.swing.JTextField();
        txtHorasTotales = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAsignaturas = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        ComboBoxOrdenar = new javax.swing.JComboBox<>();
        btnOrdenar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        comboBoxCriterio = new javax.swing.JComboBox<>();
        menu_Admin1 = new plantilla.components.Menu_Admin();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/volver.png"))); // NOI18N
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar-sesion.png"))); // NOI18N
        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.setOpaque(true);
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCerrarSesion)
                    .addComponent(btnRegresar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1016, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel5.setText("Agregar Asignaturas");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 83, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(457, 133, 510, 10));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setText("Nombre de la Asignatura:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 162, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Codigo de la Asignatura:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 206, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("Horas totales:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 250, -1, -1));
        jPanel1.add(txtNombreAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 161, 692, -1));
        jPanel1.add(txtCodigoAsigntura, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 205, 692, -1));
        jPanel1.add(txtHorasTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 249, 692, -1));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar-el-archivo.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 293, -1, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/error.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(822, 293, -1, -1));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 405, 480, 141));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(657, 293, -1, -1));
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(519, 361, 175, -1));

        jLabel1.setText("Ingrese el criterio:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 366, -1, -1));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(712, 352, -1, -1));

        ComboBoxOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente", "Descendente" }));
        jPanel1.add(ComboBoxOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 450, 512, -1));

        btnOrdenar.setText("Ordenar");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });
        jPanel1.add(btnOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(511, 494, -1, -1));

        jLabel6.setText("Ordenar por Criterio");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 416, 512, -1));

        comboBoxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Codigo" }));
        jPanel1.add(comboBoxCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 361, 120, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(483, 0, 797, -1));
        getContentPane().add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 546));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(267, 45, -1, -1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        JOptionPane.showMessageDialog(this, "Sesión cerrada", "Información", JOptionPane.INFORMATION_MESSAGE);
        dispose(); // Cierra la ventana actual
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
//        Frm_Ejemplo frmEjemplo = new Frm_Ejemplo(); 
//        frmEjemplo.setVisible(true);
//        this.dispose(); // Cierra la ventana actual
    }//GEN-LAST:event_btnRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAgregarAsignatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarAsignatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarAsignatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarAsignatura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAgregarAsignatura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxOrdenar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> comboBoxCriterio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private plantilla.components.Menu_Admin menu_Admin1;
    private javax.swing.JTable tablaAsignaturas;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigoAsigntura;
    private javax.swing.JTextField txtHorasTotales;
    private javax.swing.JTextField txtNombreAsignatura;
    // End of variables declaration//GEN-END:variables

}
