package modelLuis.view;

import ModuloMatricula.Controller.ControllerMatricula;
import com.formdev.flatlaf.FlatDarkLaf;
import java.util.Objects;
import javax.swing.UIManager;
import model.Asignatura;
import model.Cursa;
import model.Horario;
import model.Matricula;
import modelLuis.controller.ControllerAsignatura;
import modelLuis.controller.ControllerCursa;
import modelLuis.controller.ControllerHorario;
import modelLuis.tablas.ModelTableAsistenciaEstudiante;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class Frm_HorarioEstudiante extends javax.swing.JFrame {

    ModelTableAsistenciaEstudiante a = new ModelTableAsistenciaEstudiante();

    private ListaEnlazada<Horario> horarios = new ListaEnlazada<>();
    private ListaEnlazada<Integer> ids;
    private ControllerAsignatura controlerAsignatura = new ControllerAsignatura();
    private ControllerMatricula controlMatricula = new ControllerMatricula();
    private ControllerCursa controlCursa = new ControllerCursa();
    private ListaEnlazada<Cursa> cursas = new ListaEnlazada<>();
    private ListaEnlazada<Asignatura> listaAsis = new ListaEnlazada<>();
    private ControllerHorario controlHorario = new ControllerHorario();
    private Integer id_Matricula;
    private Integer id = 1;

    public Frm_HorarioEstudiante() {
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
        tblHorario.setModel(a);
        tblHorario.updateUI();
    }

    public void getMatricula() throws VacioExceptions {
        String idd = id.toString();
        Matricula matricula = controlMatricula.busquedaBinaria2(controlMatricula.list_All(), idd, "id_estudiante");
        id_Matricula = matricula.getId();
    }

    public void getAsignaturas() throws VacioExceptions {
        getMatricula();
        String ident = id_Matricula.toString();
        cursas = controlCursa.busquedaBinaria(controlCursa.list_All(), ident, "id_matricula", "quicksort", 0);
        for (Cursa cursa : cursas) {
            String iden = cursa.getAsignatura_id().toString();
            Asignatura asi = controlerAsignatura.busquedaBinaria2(controlerAsignatura.list_All(), iden, "id", 0);
            System.out.println(asi.getId());
            if (Objects.equals(cursa.getAsignatura_id(), asi.getId())) {
                listaAsis.add(asi);
            }
        }

    }

    public void getHorario() throws VacioExceptions {
        getAsignaturas();
        for (Asignatura asig : listaAsis) {
            String id_asi = asig.getId().toString();
            ListaEnlazada<Horario> listaHorarios = controlHorario.busquedaBinaria(controlHorario.list_All(), id_asi, "id_asignatura", "quicksort");
            for (Horario horario : listaHorarios) {
                if (Objects.equals(horario.getIdAsignatura(), asig.getId())) {
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
        tblHorario = new javax.swing.JTable();
        menu_Estudiante1 = new plantilla.components.Menu_Estudiante();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(21, 21, 21));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblHorario.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblHorario);

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 640, 340));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 1010, 620));
        bg_panel.add(menu_Estudiante1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 8, 240, 620));

        getContentPane().add(bg_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 640));

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
            new Frm_HorarioEstudiante().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JScrollPane jScrollPane1;
    private plantilla.components.Menu_Estudiante menu_Estudiante1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tblHorario;
    // End of variables declaration//GEN-END:variables

}
