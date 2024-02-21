package View.Login.mainFrm;

import Controller.Academico.ControllerCursa;
import Controller.tda_listas.ListaEnlazada;
import View.Tablas.ModelTableMatriculas;
import View.Tablas.ModeloTablaCursas;
import View.Tablas.ModeloTablaCursos;
import View.Util.HeaderRenderer;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import Controller.Login.CuentaController;
import model.Cuenta;
import model.Cursa;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

public class Frm_Main_Docente extends javax.swing.JFrame {

    public Frm_Main_Docente() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    Cuenta cuentaUsu;
    ControllerCursa ccu = new ControllerCursa();
    ModeloTablaCursos mtc = new ModeloTablaCursos();

    public Frm_Main_Docente(Cuenta cuenta) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cuentaUsu = cuenta;
        cargarTabla();
    }

    public void cargarTabla() {
        ListaEnlazada<Cursa> listaFiltrada = ccu.buscarCursaPorDocente(cuentaUsu.getPersona_id());
        mtc.setCursos(listaFiltrada);
        tblTabla.setModel(mtc);

        TableRowSorter<ModeloTablaCursos> trs = new TableRowSorter<>(mtc);
        tblTabla.setRowSorter(trs);
        tblTabla.getTableHeader().setReorderingAllowed(false);
        tblTabla.getTableHeader().setResizingAllowed(false);
        tblTabla.getTableHeader().setDefaultRenderer(new HeaderRenderer());
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblTabla.getColumnCount(); i++) {
            tblTabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    // Metodos
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        panelPrincipal = new plantilla.swing.RoundPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        headerUser1 = new plantilla.components.HeaderUser();
        menu_Docente2 = new plantilla.components.Menu_Docente();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        bg_panel.setBackground(new java.awt.Color(225, 233, 243));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 23)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 153, 255));
        jLabel7.setText("Cursos");
        panelPrincipal.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        tblTabla.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTabla.setRowHeight(30);
        jScrollPane1.setViewportView(tblTabla);

        panelPrincipal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 830, 390));

        bg_panel.add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 1030, 620));

        headerUser1.setBackground(new java.awt.Color(255, 255, 255));
        bg_panel.add(headerUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 1030, 50));
        bg_panel.add(menu_Docente2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 680));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {

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

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private plantilla.components.HeaderUser headerUser1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private plantilla.components.Menu_Docente menu_Docente2;
    private plantilla.swing.RoundPanel panelPrincipal;
    private javax.swing.JTable tblTabla;
    // End of variables declaration//GEN-END:variables

}
