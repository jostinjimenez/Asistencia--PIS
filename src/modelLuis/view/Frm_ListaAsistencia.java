package modelLuis.view;

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
import modelLuis.controller.ControllerMatricula;
import modelLuis.tablas.ModelTableAsistenciaDocente;
import modelLuis.view.util.Util_VistaLinked1_Asistencia;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class Frm_ListaAsistencia extends javax.swing.JFrame {

    private ListaEnlazada<Horario> horarios = new ListaEnlazada<>();
    private ControllerAsignatura controlerAsignatura = new ControllerAsignatura();
    private ControllerCursa controlCursa = new ControllerCursa();
    private ListaEnlazada<Cursa> cursas = new ListaEnlazada<>();
    private ListaEnlazada<Asignatura> listaAsis = new ListaEnlazada<>();
    private ControllerHorario controlHorario = new ControllerHorario();
    private ModelTableAsistenciaDocente a = new ModelTableAsistenciaDocente();
    private Integer id = 1;

    public Frm_ListaAsistencia() {
        initComponents();

        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void cargarCombos() throws VacioExceptions {
        ControllerCursa cc = new ControllerCursa();
        ControllerAsignatura ca = new ControllerAsignatura();
        ControllerMatricula cm = new ControllerMatricula();
        ListaEnlazada<Asignatura> listaAsig = new ListaEnlazada<>();
        ListaEnlazada<Cursa> listaCursa = new ListaEnlazada<>();
        ListaEnlazada<Matricula> listMatr = new ListaEnlazada<>();
        listaCursa = cc.busquedaBinaria(cc.list_All(), id.toString(), "id_docente", "quicksort", 0);
        for (Cursa cursa : listaCursa) {
            Matricula matricula = cm.busquedaBinaria2(cm.list_All(), cursa.getIdMatricula().toString(), "id");
            Asignatura asig = ca.busquedaBinaria2(ca.list_All(), cursa.getIdAsignatura().toString(), "id", 0);
            listaAsig.add(asig);
            listMatr.add(matricula);
        }
        Util_VistaLinked1_Asistencia.setListaMatr(listMatr);
        Util_VistaLinked1_Asistencia.setListaAsig(listaAsig);
        Util_VistaLinked1_Asistencia.setListaCursa(listaCursa);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg_panel.setBackground(new java.awt.Color(21, 21, 21));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 170, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 180, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roundPanel1.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 160, -1));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 1040, 620));

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
            new Frm_ListaAsistencia().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg_panel;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private plantilla.swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables

}
