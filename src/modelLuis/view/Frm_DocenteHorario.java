package modelLuis.view;

import com.formdev.flatlaf.FlatDarkLaf;
import java.util.Objects;
import javax.swing.UIManager;
import model.Asignatura;
import model.Cursa;
import model.Horario;
import modelLuis.controller.ControllerAsignatura;
import modelLuis.controller.ControllerCursa;
import modelLuis.controller.ControllerHorario;
import modelLuis.tablas.ModelTableAsistenciaDocente;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class Frm_DocenteHorario extends javax.swing.JFrame {

    private ListaEnlazada<Horario> horarios = new ListaEnlazada<>();
    private ControllerAsignatura controlerAsignatura = new ControllerAsignatura();
    private ControllerCursa controlCursa = new ControllerCursa();
    private ListaEnlazada<Cursa> cursas = new ListaEnlazada<>();
    private ListaEnlazada<Asignatura> listaAsis = new ListaEnlazada<>();
    private ControllerHorario controlHorario = new ControllerHorario();
    private ModelTableAsistenciaDocente a = new ModelTableAsistenciaDocente();
    private Integer id = 1;

    public Frm_DocenteHorario() {
        initComponents();
        cargarTabla();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void cargarTabla() {
        try {
            getHorario();
        } catch (Exception e) {
        }
        a.setHorarios(horarios);
        a.setListaAsis(listaAsis);
        tblDocente.setModel(a);
        tblDocente.updateUI();
    }

    public void getCursa() throws VacioExceptions {
        String idd = id.toString();
        cursas = controlCursa.busquedaBinaria(controlCursa.list_All(), idd, "id_docente", "quicksort", 0);
        for (Cursa cursa : cursas) {
            String iden = cursa.getAsignatura_id().toString();
            Asignatura asi = controlerAsignatura.busquedaBinaria2(controlerAsignatura.list_All(), iden, "id", 0);
            if (Objects.equals(cursa.getAsignatura_id(), asi.getId())) {
                listaAsis.add(asi);
            }
        }
    }

    public void getHorario() throws VacioExceptions {
        getCursa();
        for (Asignatura asig : listaAsis) {
            String id_asi = asig.getId().toString();
            ListaEnlazada<Horario> listaHorarios = controlHorario.busquedaBinaria(controlHorario.list_All(), id_asi, "id_asignatura", "quicksort");
            for (Horario horario : listaHorarios) {
                if (Objects.equals(horario.getAsignatura_id(), asig.getId())) {
                    horarios.add(horario);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocente = new javax.swing.JTable();
        menu_Docente1 = new plantilla.components.Menu_Docente();
        headerUser1 = new plantilla.components.HeaderUser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg_panel.setBackground(new java.awt.Color(225, 233, 243));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDocente.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblDocente);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1000, 490));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 1030, 610));
        bg_panel.add(menu_Docente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 680));

        headerUser1.setBackground(new java.awt.Color(246, 246, 246));
        bg_panel.add(headerUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 1030, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Frm_DocenteHorario().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private plantilla.components.HeaderUser headerUser1;
    private javax.swing.JScrollPane jScrollPane1;
    private plantilla.components.Menu_Docente menu_Docente1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tblDocente;
    // End of variables declaration//GEN-END:variables

}
