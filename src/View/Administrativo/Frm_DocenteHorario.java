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

    private ListaEnlazada<Horario> horariosLunes = new ListaEnlazada<>();
    private ListaEnlazada<Horario> horariosMartes = new ListaEnlazada<>();
    private ListaEnlazada<Horario> horariosMiercoles = new ListaEnlazada<>();
    private ListaEnlazada<Horario> horariosJueves = new ListaEnlazada<>();
    private ListaEnlazada<Horario> horariosViernes = new ListaEnlazada<>();
    private ListaEnlazada<Horario> horariosSabados = new ListaEnlazada<>();
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
//        a.setHorarios(listaHor);
//        a.setListaAsis(listaAsis);
//        tblSabado.setModel(a);
//        tblSabado.updateUI();
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
                if (Objects.equals(horario.getAsignatura_id(), asig.getId())) {
                    if (horario.getDia().equals("LUNES")) {
                        horariosLunes.add(horario);
                        a.setHorarios(horariosLunes);
                        a.setListaAsis(listaAsis);
                        a.setDia("Lunes");
                        tblLunes1.setModel(a);
                        tblLunes1.updateUI();
                    } else if (horario.getDia().equals("MARTES")) {
                        horariosMartes.add(horario);
                        a.setHorarios(horariosMartes);
                        a.setListaAsis(listaAsis);
                        a.setDia("Martes");
                        tblMartes.setModel(a);
                        tblMartes.updateUI();
                    } else if (horario.getDia().equals("MIERCOLES")) {
                        horariosMiercoles.add(horario);
                        a.setHorarios(horariosMiercoles);
                        a.setListaAsis(listaAsis);
                        a.setDia("Miercoles");
                        tblMiercoles.setModel(a);
                        tblMiercoles.updateUI();
                    } else if (horario.getDia().equals("JUEVES")) {
                        horariosJueves.add(horario);
                        a.setHorarios(horariosJueves);
                        a.setListaAsis(listaAsis);
                        a.setDia("Jueves");
                        tblJueves.setModel(a);
                        tblJueves.updateUI();
                    } else if (horario.getDia().equals("VIERNES")) {
                        horariosViernes.add(horario);
                        a.setHorarios(horariosViernes);
                        a.setListaAsis(listaAsis);
                        a.setDia("Viernes");
                        tblViernes.setModel(a);
                        tblViernes.updateUI();
                    } else if (horario.getDia().equals("SABADO")) {
                        horariosSabados.add(horario);
                        a.setHorarios(horariosSabados);
                        a.setListaAsis(listaAsis);
                        a.setDia("Sabado");
                        tblSabado.setModel(a);
                        tblSabado.updateUI();
                    }
                }
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
        jScrollPane8 = new javax.swing.JScrollPane();
        tblLunes1 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblMartes = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblMiercoles = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblJueves = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblViernes = new javax.swing.JTable();
        menu_Docente1 = new plantilla.components.Menu_Docente();
        headerUser1 = new plantilla.components.HeaderUser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg_panel.setBackground(new java.awt.Color(225, 233, 243));
        bg_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        roundPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 120, 260));

        tblLunes1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(tblLunes1);

        roundPanel1.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 120, 260));

        tblMartes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(tblMartes);

        roundPanel1.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 120, 260));

        tblMiercoles.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane10.setViewportView(tblMiercoles);

        roundPanel1.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 120, 260));

        tblJueves.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane11.setViewportView(tblJueves);

        roundPanel1.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 120, 260));

        tblViernes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane12.setViewportView(tblViernes);

        roundPanel1.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 120, 260));

        bg_panel.add(roundPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 1030, 610));
        bg_panel.add(menu_Docente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 680));

        headerUser1.setBackground(new java.awt.Color(246, 246, 246));
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
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private plantilla.components.Menu_Docente menu_Docente1;
    private plantilla.swing.RoundPanel roundPanel1;
    private javax.swing.JTable tblJueves;
    private javax.swing.JTable tblLunes1;
    private javax.swing.JTable tblMartes;
    private javax.swing.JTable tblMiercoles;
    private javax.swing.JTable tblSabado;
    private javax.swing.JTable tblViernes;
    // End of variables declaration//GEN-END:variables

}
