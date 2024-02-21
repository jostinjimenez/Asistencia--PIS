package View.Administrativo;

import com.formdev.flatlaf.FlatDarkLaf;
import java.util.Objects;
import javax.swing.UIManager;
import model.Asignatura;
import model.Cuenta;
import model.Cursa;
import model.Horario;
import Controller.Academico.ControllerCursa;
import Controller.Administrativo.ControllerHorario;
import View.Tablas.ModelTableAsistenciaDocente;
import Controller.Academico.AsignaturaController;
import Controller.tda_listas.ListaEnlazada;
import Controller.tda_listas.exceptions.VacioExceptions;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frm_DocenteHorario extends javax.swing.JFrame {

    private ListaEnlazada<Horario> listaHor = new ListaEnlazada<>();

    private AsignaturaController controlerAsignatura = new AsignaturaController();
    private ControllerCursa controlCursa = new ControllerCursa();
    private ListaEnlazada<Cursa> cursas = new ListaEnlazada<>();
    private ListaEnlazada<Asignatura> listaAsis = new ListaEnlazada<>();
    private ControllerHorario controlHorario = new ControllerHorario();
    private ModelTableAsistenciaDocente a = new ModelTableAsistenciaDocente();
    Cuenta cuentaUsu;

    JPanel panel2 = new JPanel();

    public Frm_DocenteHorario() {
        initComponents();
        //cargarTabla();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public Frm_DocenteHorario(Cuenta cuenta) {
        initComponents();
        cuentaUsu = cuenta;
        cargarTabla();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void cargarTabla() {
        try {
            getHorario();
        } catch (Exception e) {
        }
        a.setHorarios(listaHor);
        a.setListaAsis(listaAsis);
        tblSabado.setModel(a);
        tblSabado.updateUI();
    }

    public void getCursa() throws VacioExceptions {
        String idd = cuentaUsu.getPersona_id().toString();
        cursas = controlCursa.busquedaBinaria(controlCursa.list_All(), idd, "id_docente", "quicksort", 0);
        for (Cursa cursa : cursas) {
            String iden = cursa.getAsignatura_id().toString();
            Asignatura asi = controlerAsignatura.busquedaBinaria2(controlerAsignatura.list_All(), iden, "id");
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
                  listaHor.add(horario);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_panel = new javax.swing.JPanel();
        roundPanel1 = new plantilla.swing.RoundPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSabado = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        menu_Docente1 = new plantilla.components.Menu_Docente();
        headerUser1 = new plantilla.components.HeaderUser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg_panel.setBackground(new java.awt.Color(225, 233, 243));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblSabado.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(tblSabado);

        roundPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 800, 260));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 153, 255));
        jLabel1.setText("Docente Horario");
        roundPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 1030, 610));

        menu_Docente1.setBackground(new java.awt.Color(255, 255, 255));
        bg_panel.add(menu_Docente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 680));

        headerUser1.setBackground(new java.awt.Color(255, 255, 255));
        bg_panel.add(headerUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 1030, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            UIManager.setLookAndFeel(new FlatLightLaf());
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane7;
    private plantilla.components.Menu_Docente menu_Docente1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tblSabado;
    // End of variables declaration//GEN-END:variables

}
