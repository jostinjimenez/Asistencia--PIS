package plantilla.components;

import com.raven.swing.ButtonMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.Persona;
import modulo_1.inicio_sesion.controller.CuentaController;
import modulo_1.inicio_sesion.view.forms.Frm_Inicio_Sesion;
import ModuloEstudianteDocente.vista.*;
import modulo_1.inicio_sesion.view.forms.Frm_Usuarios;

import modulo_1.inicio_sesion.view.forms.mainFrm.Frm_Main_Admin;
import modulo_1.inicio_sesion.view.util.Utiles;
import net.miginfocom.swing.MigLayout;
import plantilla.swing.scrollbar.ScrollBarCustom;

public class Menu_Admin extends javax.swing.JPanel {

    CuentaController cc = Utiles.getCc();

    public Menu_Admin() {

        initComponents();
        setOpaque(false);
        ScrollBarCustom sb = new ScrollBarCustom();
        sb.setForeground(new Color(130, 130, 130, 100));
        jScrollPane1.setVerticalScrollBar(sb);
        panelMenu.setLayout(new MigLayout("wrap, fillx, inset 3", "[fill]", "[]0[]"));
        initMenu();

        Persona persona = cc.getPersona(cc.getCuenta().getIdPersona());
        if (persona != null) {
            txtUsername.setText(persona.toString());
        } else {
            txtUsername.setText("Username");
        }

    }

    public void initMenu() {
        addMenu(new ImageIcon(getClass().getResource("/plantilla/img/menu/menu.png")), "Menu Principal", 0);
        addMenu(new ImageIcon(getClass().getResource("/plantilla/img/menu/user.png")), "Usuarios", 1);
        addMenu(new ImageIcon(getClass().getResource("/plantilla/img/menu/roles.png")), "Roles", 2);
        addMenu(new ImageIcon(getClass().getResource("/plantilla/img/menu/estudiante.png")), "Estudiantes", 3);
        addMenu(new ImageIcon(getClass().getResource("/plantilla/img/menu/docente.png")), "Docentes", 4);
        addMenu(new ImageIcon(getClass().getResource("/plantilla/img/menu/matricula.png")), "Matriculas", 5);
        addMenu(new ImageIcon(getClass().getResource("/plantilla/img/menu/asignatura.png")), "Asignaturas", 6);
        addMenu(new ImageIcon(getClass().getResource("/plantilla/img/menu/periodo.png")), "Periodos Academicos", 7);
        addEmpty();
        addMenu(new ImageIcon(getClass().getResource("/plantilla/img/menu/salir.png")), "Cerrar Sesión", 8);
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

                FrmEstudiante frm = new FrmEstudiante();
                frm.setVisible(true);
            });
            case "Docentes" -> menu.addActionListener(e -> {
                Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Admin.this);
                currentWindow.dispose();

                FrmDocente frm = new FrmDocente();
                frm.setVisible(true);
            });
            case "Usuarios" -> menu.addActionListener(e -> {
                Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Admin.this);
                currentWindow.dispose();

                Frm_Usuarios frm = new Frm_Usuarios();
                frm.setVisible(true);
            });
        }

        panelMenu.add(menu);
    }

    private void cerrarSesion() {
        // Cierra la ventana actual
        Window currentWindow = SwingUtilities.getWindowAncestor(this);
        currentWindow.dispose();

        // Abre la ventana de inicio de sesión
        Frm_Inicio_Sesion inicioSesion = new Frm_Inicio_Sesion();
        inicioSesion.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new plantilla.swing.RoundPanel();
        imageAvatar1 = new com.raven.swing.ImageAvatar();
        jLabel2 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JLabel();
        roundPanel2 = new plantilla.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelMenu = new javax.swing.JPanel();

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));

        imageAvatar1.setForeground(new java.awt.Color(231, 231, 231));
        imageAvatar1.setBorderSize(2);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plantilla/img/profile.jpg"))); // NOI18N

        jLabel2.setForeground(new java.awt.Color(203, 203, 203));
        jLabel2.setText("Admin");

        txtUsername.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtUsername.setText("jLabel1");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(roundPanel1Layout.createSequentialGroup().addGap(10, 10, 10).addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(roundPanel1Layout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabel2)).addGroup(roundPanel1Layout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        roundPanel1Layout.setVerticalGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(roundPanel1Layout.createSequentialGroup().addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(roundPanel1Layout.createSequentialGroup().addGap(20, 20, 20).addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel2)).addGroup(roundPanel1Layout.createSequentialGroup().addGap(10, 10, 10).addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(10, 10, 10)));

        roundPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panelMenu.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 208, Short.MAX_VALUE));
        panelMenuLayout.setVerticalGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 578, Short.MAX_VALUE));

        jScrollPane1.setViewportView(panelMenu);

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(roundPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1).addContainerGap()));
        roundPanel2Layout.setVerticalGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(roundPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1).addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(10, 10, 10).addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelMenu;
    private plantilla.swing.RoundPanel roundPanel1;
    private plantilla.swing.RoundPanel roundPanel2;
    private javax.swing.JLabel txtUsername;
    // End of variables declaration//GEN-END:variables
}
