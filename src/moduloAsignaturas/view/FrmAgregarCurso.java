package moduloAsignaturas.view;

import moduloAsignaturas.controller.CursoController;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Curso;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;
import moduloAsignaturas.view.tablas.ModeloTablaCursos;

public class FrmAgregarCurso extends javax.swing.JFrame {

    private CursoController cc = new CursoController();
    private ModeloTablaCursos mtc = new ModeloTablaCursos();

    public FrmAgregarCurso() {
        initComponents();

        // Centrar la ventana
        setLocationRelativeTo(null);

        // Deshabilitar la modificación del tamaño
        setResizable(false);

        // Establecer el nombre de la ventana
        setTitle("Agregar Cursos");

        // Inicializar el controlador y el modelo de la tabla
        cc = new CursoController();
        mtc = new ModeloTablaCursos();
        mtc.setCursoController(cc);

        // Cargar contenido en la tabla al iniciar
        cargarTabla();
    }

    public void cargarTabla() {
        mtc.getCursoController().setLista(mtc.getCursoController().list_All());
        mtc.fireTableDataChanged();
        tablaCursos.setModel(mtc);
        tablaCursos.updateUI();
    }

    public Boolean validar() {
        return !txtCodigo.getText().trim().isEmpty()
                && !txtNroEstudiantes.getText().trim().isEmpty()
                && !txtNroAula.getText().trim().isEmpty();
    }

    public void agregar() {
        if (validar()) {
            try {
                // Obtener los datos del formulario
                String codigo = txtCodigo.getText();
                String nroEstudiantesStr = txtNroEstudiantes.getText();
                String nroAula = txtNroAula.getText();

                // Validar que nroEstudiantesStr sea numérico
                if (esNumero(nroEstudiantesStr)) {
                    // Convertir a los tipos de datos apropiados
                    Integer nroEstudiantes = Integer.parseInt(nroEstudiantesStr);

                    // Configurar los datos en el controlador
                    cc.getCurso().setCodCurso(codigo);
                    cc.getCurso().setNroEstudiante(nroEstudiantes);
                    cc.getCurso().setNroAula(nroAula);

                    if (cc.saved()) {
                        cargarTabla();
                        JOptionPane.showMessageDialog(null, "Se guardó correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor numérico en Nro. Estudiantes", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
        }
    }

    private boolean esNumero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int obtenerIdDesdeFilaSeleccionada(int filaSeleccionada) {
        // Obtener el valor de la columna que contiene el ID desde el modelo de tabla
        int columnaId = 0; // Asume que la columna 0 contiene el ID, ajusta según tu estructura
        return (int) tablaCursos.getValueAt(filaSeleccionada, columnaId);
    }

    private void eliminar() {
        // Obtener el índice de la fila seleccionada
        int filaSeleccionada = tablaCursos.getSelectedRow();

        // Verificar si se ha seleccionado alguna fila
        if (filaSeleccionada >= 0) {
            // Confirmar si realmente desea eliminar la fila
            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Está seguro de que desea eliminar el curso seleccionado?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                // Obtener el objeto Curso correspondiente a la fila seleccionada
                int cursoAEliminar = obtenerIdDesdeFilaSeleccionada(filaSeleccionada);
                // Eliminar el objeto Curso utilizando el método delete del controlador
                if (cc.delete(cursoAEliminar)) {
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
                            "Error al eliminar el curso",
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
        txtNroAula.setText("");
        txtNroEstudiantes.setText("");
        txtNroAula.setText("");
    }

    public void modificar() throws VacioExceptions {
        if (validar()) {
            // Obtener los datos del formulario
            String codigo = txtCodigo.getText();
            String nroEstudiantesStr = txtNroEstudiantes.getText();
            String nroAula = txtNroAula.getText();

            // Convertir a los tipos de datos apropiados
            Integer nroEstudiantes = Integer.parseInt(nroEstudiantesStr);

            // Configurar los datos en el controlador
            cc.getCurso().setCodCurso(codigo);
            cc.getCurso().setNroEstudiante(nroEstudiantes);
            cc.getCurso().setNroAula(nroAula);

            // Obtener el índice de la fila seleccionada
            int filaSeleccionada = tablaCursos.getSelectedRow();

            // Actualizar la información en la lista y en la tabla
            if (filaSeleccionada >= 0) {
                cc.update(filaSeleccionada);
                cargarTabla(); 
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
        }
    }

    private void cargarDatosSeleccionados() {
        // Obtener el índice de la fila seleccionada
        int filaSeleccionada = tablaCursos.getSelectedRow();

        // Verificar si se ha seleccionado alguna fila
        if (filaSeleccionada >= 0) {
            // Obtener los datos de la fila seleccionada desde el modelo de la tabla
            String id = tablaCursos.getValueAt(filaSeleccionada, 0).toString();
            String codigo = tablaCursos.getValueAt(filaSeleccionada, 1).toString();
            String nroEstudiantes = tablaCursos.getValueAt(filaSeleccionada, 2).toString();
            String nroAula = tablaCursos.getValueAt(filaSeleccionada, 3).toString();

            // Mostrar los datos en los campos de texto
            txtCodigo.setText(codigo);
            txtNroEstudiantes.setText(nroEstudiantes);
            txtNroAula.setText(nroAula);
        }
    }

    public int buscarIndice(Curso curso) {
        ListaEnlazada<Curso> lista = cc.getLista();

        for (int i = 0; i < lista.getSize(); i++) {
            try {
                Curso actual = lista.get(i);
                if (actual.equals(curso)) {
                    return i;
                }
            } catch (VacioExceptions e) {
                throw new RuntimeException(e);
            }
        }

        return -1; // Devuelve -1 si no se encuentra el curso
    }

    private void buscar() throws VacioExceptions {
        // Obtener el criterio ingresado en el campo de búsqueda
        String criterioBusqueda = txtBuscarCriterio.getText();  // No convertir a minúsculas

        // Obtener el criterio de búsqueda seleccionado en el combobox
        String criterio = comboBoxCriterio.getSelectedItem().toString();  // No convertir a minúsculas

        // Verificar si se ingresó un criterio válido
        if (!criterio.isEmpty()) {
            // Seleccionar el comparador adecuado según el criterio de búsqueda
            Comparator<Curso> comparador = (criterio.equals("Codigo"))
                    ? Comparator.comparing(Curso::getCodCurso) // Comparar directamente sin convertir a minúsculas
                    : Comparator.comparing(Curso::getNroAula);  // Comparar directamente sin convertir a minúsculas

            // Realizar la búsqueda en el modelo de la tabla
            int indice = mtc.buscar(criterioBusqueda, comparador, criterio);

            // Verificar si se encontró la asignatura
            if (indice >= 0) {
                // Seleccionar la fila encontrada
                tablaCursos.setRowSelectionInterval(indice, indice);
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

        if (!campoOrden.isEmpty() && mtc.esCampoValido(campoOrden)) {
            mtc.ordenar(campoOrden, tipoOrden);
            cargarTabla();  // Actualizar la tabla después de ordenar
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

        jPanel2 = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCursos = new javax.swing.JTable();
        comboBoxCriterio = new javax.swing.JComboBox<>();
        txtBuscarCriterio = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        ComboBoxOrdenar = new javax.swing.JComboBox<>();
        btnOrdenar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtNroEstudiantes = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtNroAula = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        menu_Admin1 = new plantilla.components.Menu_Admin();
        header2 = new plantilla.components.Header();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(21, 21, 21));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Codigo", "Nro. Estudiantes", "Nro. Aula"
            }
        ));
        tablaCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCursosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCursos);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 970, 200));

        comboBoxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Codigo", "Nro. Aula" }));
        roundPanel1.add(comboBoxCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 120, -1));
        roundPanel1.add(txtBuscarCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 240, 175, -1));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 230, -1, -1));

        ComboBoxOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente", "Descendente" }));
        roundPanel1.add(ComboBoxOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 320, -1, -1));

        btnOrdenar.setText("Ordenar");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 320, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 28)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Registro de Cursos");
        roundPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

        txtNroEstudiantes.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNroEstudiantes.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.add(txtNroEstudiantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 250, -1));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nro. Aula:");
        roundPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nro. Estudiantes:");
        roundPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Codigo del Curso:");
        roundPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        btnAgregar.setBackground(new java.awt.Color(242, 242, 242));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/boton-agregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setBorder(null);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 111, 30));

        btnModificar.setBackground(new java.awt.Color(242, 242, 242));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/lista-de-verificacion (1).png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setBorder(null);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 580, 105, 30));

        btnEliminar.setBackground(new java.awt.Color(242, 242, 242));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/error.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(null);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 580, 104, -1));

        txtNroAula.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNroAula.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.add(txtNroAula, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 110, 250, -1));

        txtCodigo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 250, -1));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        roundPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 381, -1));

        jPanel2.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 1040, 630));
        jPanel2.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 680));
        jPanel2.add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 1040, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCursosMouseClicked
        cargarDatosSeleccionados();
    }//GEN-LAST:event_tablaCursosMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            buscar();
        } catch (VacioExceptions ex) {
            Logger.getLogger(FrmAgregarCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
        ordenar();
    }//GEN-LAST:event_btnOrdenarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            modificar();
        } catch (VacioExceptions ex) {
            Logger.getLogger(FrmAgregarCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
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
            java.util.logging.Logger.getLogger(FrmAgregarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAgregarCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAgregarCurso().setVisible(true);
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private plantilla.components.Menu_Admin menu_Admin1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tablaCursos;
    private javax.swing.JTextField txtBuscarCriterio;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNroAula;
    private javax.swing.JTextField txtNroEstudiantes;
    // End of variables declaration//GEN-END:variables
}
