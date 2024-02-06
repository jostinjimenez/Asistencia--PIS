package modelLuis.view;

import ModuloEstudianteDocente.vista.tablas.ModeloTablaDocente;
import com.formdev.flatlaf.FlatDarkLaf;
import java.util.Objects;
import javax.swing.UIManager;
import model.Asignatura;
import model.Cursa;
import model.Docente;
import model.Horario;

import modelLuis.controller.ControllerAsignatura;
import modelLuis.controller.ControllerCursa;
import modelLuis.controller.ControllerHorario;

import model.Matricula;
import modelLuis.controller.ControllerAsignatura;
import modelLuis.controller.ControllerCursa;
import modelLuis.controller.ControllerHorario;
import modelLuis.controller.ControllerMatricula;
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
            String iden = cursa.getIdAsignatura().toString();
            Asignatura asi = controlerAsignatura.busquedaBinaria2(controlerAsignatura.list_All(), iden, "id", 0);
            if (Objects.equals(cursa.getIdAsignatura(), asi.getId())) {
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
                if (!isHorarioAgregado(horario.getId())) {
                    if (Objects.equals(horario.getIdAsignatura(), asig.getId())) {
                        horarios.add(horario);
                    }
                }
            }
        }
        System.out.println(horarios.getSize());
    }

    private boolean isHorarioAgregado(Integer horarioId) {

        for (Horario horario : horarios) {
            if (Objects.equals(horario.getId(), horarioId)) {
                return true;
            }
        }
        return false;
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
        tblDocente = new javax.swing.JTable();
        menu_Docente1 = new plantilla.components.Menu_Docente();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(21, 21, 21));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
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

        roundPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, -1));

<<<<<<< HEAD
        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 1040, 620));
        bg_panel.add(menu_Docente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
=======
        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 1010, 620));
        bg_panel.add(menu_Docente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 620));
>>>>>>> master

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
            new Frm_DocenteHorario().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JScrollPane jScrollPane1;
    private plantilla.components.Menu_Docente menu_Docente1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tblDocente;
    // End of variables declaration//GEN-END:variables

}
