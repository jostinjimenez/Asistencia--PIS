package modulo_1.matricula.view.forms;

import model.PeriodoAcademico;
import modulo_1.inicio_sesion.view.forms.*;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import model.Rol;
import modulo_1.inicio_sesion.controller.PersonaController;
import modulo_1.inicio_sesion.view.tablas.ModeloTablaPersona;
import modulo_1.inicio_sesion.view.util.Utiles;
import modulo_1.matricula.controller.PeriodoAcController;
import modulo_1.matricula.view.tablas.ModeloTablaPeriodoAc;
import tda_listas.ListaEnlazada;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.event.ItemEvent;
import java.util.Objects;

import static modulo_1.inicio_sesion.view.util.Utiles.cargaRol;

public class Frm_PeriodosAcademicos extends javax.swing.JFrame {

    public Frm_PeriodosAcademicos() {
        initComponents();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cargarTabla();

        jTable1.getSelectionModel().addListSelectionListener(e -> {
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
        });

        btnEliminar.addActionListener(e -> eliminarRegistro());

        txtCriterio.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                actualizarTabla();
            }

            public void removeUpdate(DocumentEvent e) {
                actualizarTabla();
            }

            public void insertUpdate(DocumentEvent e) {
                actualizarTabla();
            }
        });
    }

    ModeloTablaPeriodoAc mtpa = new ModeloTablaPeriodoAc();
    PeriodoAcController pc = new PeriodoAcController();

    // Metodos
    public void actualizarTabla() {
        String criterio = txtCriterio.getText();
        if (criterio.isEmpty()) {
            cargarTabla();
            return;
        }

        try {
            ListaEnlazada<PeriodoAcademico> resultados = new ListaEnlazada<>();
            String criterioSeleccionado = Objects.requireNonNull(cbxCriterio.getSelectedItem()).toString().toLowerCase();
            switch (criterioSeleccionado) {
                case "id":
                    if (!criterio.matches("\\d+")) {
                        cargarTabla();
                        return;
                    }
                    Integer id = Integer.valueOf(criterio);
                    PeriodoAcademico paId = pc.buscarId(pc.getPeriodoAcademicos(), id);
                    if (paId != null) {
                        resultados.add(paId);
                    }
                    break;
                case "anio":
                    if (!criterio.matches("\\d+")) {
                        cargarTabla();
                        return;
                    }
                    Integer anio = Integer.valueOf(criterio);
                    resultados = pc.buscarAnio(pc.getPeriodoAcademicos(), anio);
                    System.out.println(resultados.print());
                    break;
                case "fechaInicio":
                    resultados = pc.buscarFechaInicio(pc.getPeriodoAcademicos(), criterio);
                    System.out.println(resultados.print());
                    break;
                case "fechaFin":
                    resultados = pc.buscarFechaFin(pc.getPeriodoAcademicos(), criterio);
                    System.out.println(resultados.print());
                    break;
                default:
                    cargarTabla();
                    return;
            }

            if (resultados.isEmpty()) {
                PeriodoAcademico noResult = new PeriodoAcademico();
                noResult.setId(-1);
                noResult.setFechaFin("No se encontraron resultados para la búsqueda");
                resultados.add(noResult);
            }
            mtpa.setPeriodoAcademicos(resultados);
            mtpa.fireTableDataChanged();
            jTable1.setModel(mtpa);
            jTable1.updateUI();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarRegistro() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                int idPersona = (int) jTable1.getValueAt(selectedRow, 0);
                if (pc.delete(idPersona)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarTabla() {
        mtpa.setPeriodoAcademicos(pc.list_All());
        mtpa.setPeriodoAcademicos(pc.getPeriodoAcademicos());
        mtpa.fireTableDataChanged();
        jTable1.setModel(mtpa);
        jTable1.updateUI();

        TableRowSorter<ModeloTablaPeriodoAc> trs = new TableRowSorter<>(mtpa);
        jTable1.setRowSorter(trs);
        jTable1.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCriterio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        cbxCriterio = new javax.swing.JComboBox<>();
        cbxAscDesc = new javax.swing.JComboBox<>();
        menu_Admin1 = new plantilla.components.Menu_Admin();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(21, 21, 21));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Periodo Academico");
        roundPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Buscar");
        roundPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 70, 60, 20));

        txtCriterio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCriterio.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        roundPanel1.add(txtCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, 240, 20));

        jTable1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 1000, 360));

        btnNuevo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnNuevo.setText("Agregar periodo Academico");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        roundPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 220, 30));

        btnEditar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 570, 110, 30));

        btnEliminar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnEliminar.setText("Dar de Baja");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        roundPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 570, 110, 30));

        cbxCriterio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Id", "Anio", "Fecha Inicio", "Fecha Fin"}));
        cbxCriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriterioItemStateChanged(evt);
            }
        });
        roundPanel1.add(cbxCriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 150, 160, -1));

        cbxAscDesc.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbxAscDesc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        roundPanel1.add(cbxAscDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(856, 150, 160, -1));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 1040, 620));
        bg_panel.add(menu_Admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 620));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        NuevoPeriodo nu = new NuevoPeriodo(this, true, mtpa, jTable1);
        nu.setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cbxCriterioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCriterioItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //buscar();
        }
    }//GEN-LAST:event_cbxCriterioItemStateChanged

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        NuevoPeriodo nu = new NuevoPeriodo(this, true);
        nu.setVisible(true);
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatNordIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Frm_PeriodosAcademicos().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cbxAscDesc;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private plantilla.components.Menu_Admin menu_Admin1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTextField txtCriterio;
    // End of variables declaration//GEN-END:variables

}
