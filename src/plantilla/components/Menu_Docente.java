package plantilla.components;

import plantilla.swing.ButtonMenu;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Window;
import java.util.Objects;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.Cuenta;
import View.Academico.Frm_AsistenciaJ;
import View.Administrativo.Frm_DocenteHorario;
import Controller.Login.CuentaController;
import View.Login.Frm_Inicio_Sesion;
import View.Login.mainFrm.Frm_Main_Docente;
import View.Util.UtilVista;
import net.miginfocom.swing.MigLayout;
import plantilla.swing.scrollbar.ScrollBarCustom;

import static View.Util.UtilVista.getCuentaUsu;

public class Menu_Docente extends javax.swing.JPanel {

    CuentaController cc = UtilVista.getCc();
    Cuenta cuentaUsu = new Cuenta();

    public Menu_Docente() {

        initComponents();
        setOpaque(false);
        ScrollBarCustom sb = new ScrollBarCustom();
        sb.setForeground(new Color(130, 130, 130, 100));
        jScrollPane1.setVerticalScrollBar(sb);
        panelMenu.setLayout(new MigLayout("wrap, fillx, inset 3", "[fill]", "[]0[]"));
        initMenu();

        try {
            cuentaUsu = getCuentaUsu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initMenu() {
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/menu.png"))), "Menu Principal", 0);
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/asistencia.png"))), "Registro de Asistencia", 1);
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/horario.png"))), "Horario Academico", 2);
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/informes.png"))), "Reportes de Asistencia", 3);
        addEmpty();
        addMenu(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/menu/salir.png"))), "Cerrar Sesión", 8);
    }

    private void addEmpty() {
        panelMenu.add(new JLabel(), "push");
    }

    private void addMenu(Icon icon, String text, int index) {
        ButtonMenu menu = new ButtonMenu();
        menu.setIcon(icon);
        menu.setText("  " + text);
        menu.setFont(new Font("Roboto", Font.PLAIN, 12));
        menu.setForeground(new Color(0, 0, 0));

        switch (text) {
            case "Cerrar Sesión" ->
                menu.addActionListener(e -> {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "¿Desea cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        cerrarSesion();
                    }
                });
            case "Menu Principal" ->
                menu.addActionListener(e -> {
                    Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Docente.this);
                    currentWindow.dispose();

                    Frm_Main_Docente frm = new Frm_Main_Docente();
                    frm.setVisible(true);
                });
            case "Registro de Asistencia" ->
                menu.addActionListener(e -> {
                    Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Docente.this);
                    currentWindow.dispose();

                    Frm_AsistenciaJ frm = new Frm_AsistenciaJ(cuentaUsu);
                    //Frm_AsistenciaJ frm = new Frm_AsistenciaJ();
                    frm.setVisible(true);
                });
            case "Horario Academico" ->
                menu.addActionListener(e -> {
                    Window currentWindow = SwingUtilities.getWindowAncestor(Menu_Docente.this);
                    currentWindow.dispose();

                    Frm_DocenteHorario frm = new Frm_DocenteHorario(); // Enviar el ID del docente
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

    private void setSelected(ButtonMenu menu) {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof ButtonMenu) {
                ButtonMenu b = (ButtonMenu) com;
                b.setSelected(false);
            }
        }
        menu.setSelected(true);
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

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1)
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
