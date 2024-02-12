package plantilla.components;

import ModuloMatricula.Views.Frm_Cursas;
import com.raven.swing.ButtonMenu;

import java.awt.*;
import java.util.Objects;
import javax.swing.*;

import model.Cuenta;
import model.Persona;
import modelLuis.view.Frm_HorarioAdmi;
import modulo_1.inicio_sesion.controller.CuentaController;
import modulo_1.inicio_sesion.view.forms.Frm_Inicio_Sesion;
import ModuloEstudianteDocente.vista.*;
import ModuloMatricula.Views.Frm_Maatricula;
import modulo_1.inicio_sesion.view.forms.Frm_Usuarios;
import moduloAsignaturas.view.*;

import modulo_1.inicio_sesion.view.forms.mainFrm.Frm_Main_Admin;
import modulo_1.inicio_sesion.view.forms.mainFrm.Perfil_Modal;
import modulo_1.inicio_sesion.view.util.Utiles;
import modulo_1.periodo_academico.controller.PeriodoAcController;
import modulo_1.periodo_academico.view.forms.Frm_PeriodosAcademicos;
import modulo_carrera.view.forms.Frm_Carrera;
import net.miginfocom.swing.MigLayout;
import plantilla.swing.scrollbar.ScrollBarCustom;

public class Menu_Admin extends javax.swing.JPanel {

    CuentaController cc = Utiles.getCc();
    Cuenta cuentaUsu = Utiles.getCuentaUsu();
    PeriodoAcController pac = new PeriodoAcController();

    public Menu_Admin() {

        initComponents();
        setOpaque(false);
        ScrollBarCustom sb = new ScrollBarCustom();
        sb.setForeground(new Color(130, 130, 130, 100));
        jScrollPane1.setVerticalScrollBar(sb);
        panelMenu.setLayout(new MigLayout("wrap, fillx, inset 3", "[fill]", "[]0[]"));
        initMenu();
    }


    public void initMenu() {
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/menu.png"))), "Menu Principal", 0);
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/usuario.png"))), "Usuarios", 1);
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/estudiante.png"))), "Estudiantes", 2);
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/docente.png"))), "Docentes", 3);
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/matricula.png"))), "Matriculas", 4);
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/asignatura.png"))), "Asignaturas", 5);
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/carrera.png"))), "Carreras", 6);
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/periodo.png"))), "Periodos Academicos", 7);
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/horario.png"))), "Horarios", 8);
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/malla.png"))), "Mallas Academicas", 9);
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/cursa.png"))), "Cursas", 10);

        addEmpty();
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/salir.png"))), "Cerrar Sesión", 12);
    }

    private void addEmpty() {
        panelMenu.add(new JLabel(), "push");
    }

    private void setSelected(ButtonMenu menu) {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof ButtonMenu) {
                ButtonMenu b = (ButtonMenu) com;
                b.setSelected(false);
            }
        }
        menu.setSelected(true);
    }

    private void addMenu(Icon icon, String text, int index) {
        ButtonMenu menu = new ButtonMenu();
        menu.setIcon(icon);
        menu.setText("  " + text);
        menu.setFont(new Font("Roboto", Font.PLAIN, 12));
        menu.setForeground(new Color(0,0,0));
        switch (text) {
            case "Cerrar Sesión" -> menu.addActionListener(e -> {
                int dialogResult = JOptionPane.showConfirmDialog(null, "¿Desea cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    cerrarSesion();
                }
            });
            case "Menu Principal" -> menu.addActionListener(e -> {
                Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Admin.this);
                currentWindow.dispose();

                Frm_Main_Admin frm = new Frm_Main_Admin(cc);
                frm.setVisible(true);
            });
            case "Estudiantes" -> menu.addActionListener(e -> {
                Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Admin.this);
                currentWindow.dispose();

                FrmEstudiante frm = new FrmEstudiante(cc);
                frm.setVisible(true);
            });
            case "Docentes" -> menu.addActionListener(e -> {
                Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Admin.this);
                currentWindow.dispose();

                FrmDocente frm = new FrmDocente(cc);
                frm.setVisible(true);
            });
            case "Usuarios" -> menu.addActionListener(e -> {
                Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Admin.this);
                currentWindow.dispose();

                Frm_Usuarios frm = new Frm_Usuarios();
                frm.setVisible(true);
            });
            case "Periodos Academicos" -> menu.addActionListener(e -> {
                Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Admin.this);
                currentWindow.dispose();

                Frm_PeriodosAcademicos frm = new Frm_PeriodosAcademicos();
                frm.setVisible(true);
            });
            case "Horarios" -> menu.addActionListener(e -> {
                Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Admin.this);
                currentWindow.dispose();

                Frm_HorarioAdmi frm = new Frm_HorarioAdmi();
                frm.setVisible(true);
            });
            case "Matriculas" -> menu.addActionListener(e -> {
                Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Admin.this);
                currentWindow.dispose();

                Frm_Maatricula frm = new Frm_Maatricula();
                frm.setVisible(true);
            });
            case "Asignaturas" -> menu.addActionListener(e -> {
                Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Admin.this);
                currentWindow.dispose();

                FrmAgregarAsignatura frm = new FrmAgregarAsignatura();
                frm.setVisible(true);
            });
            case "Carreras" -> menu.addActionListener(e -> {
                Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Admin.this);
                currentWindow.dispose();

                Frm_Carrera frm = new Frm_Carrera();
                frm.setVisible(true);
            });
            case "Mallas Academicas" -> menu.addActionListener(e -> {
                Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Admin.this);
                currentWindow.dispose();

                Frm_MallaAcademica frm = new Frm_MallaAcademica();
                frm.setVisible(true);
            });
            case "Cursas" -> menu.addActionListener(e -> {
                Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Admin.this);
                currentWindow.dispose();

                Frm_Cursas frm = new Frm_Cursas();
                frm.setVisible(true);
            });
        }

        panelMenu.add(menu);
    }

    private void cerrarSesion() {
        // Cierra la ventana actual
        Window currentWindow = SwingUtilities.getWindowAncestor(this);
        currentWindow.dispose();

        // Limpia los datos de la cuenta
        Utiles.setCc(null);
        Utiles.setCuentaUsu(null);

        // Abre la ventana de inicio de sesión
        Frm_Inicio_Sesion inicioSesion = new Frm_Inicio_Sesion();
        inicioSesion.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new plantilla.swing.RoundPanel();
        roundPanel2 = new plantilla.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelMenu = new javax.swing.JPanel();

        setBackground(new java.awt.Color(225, 233, 243));

        roundPanel1.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
        );

        roundPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelMenu);

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelMenu;
    private plantilla.swing.RoundPanel roundPanel1;
    private plantilla.swing.RoundPanel roundPanel2;
    // End of variables declaration//GEN-END:variables
}
