package plantilla.components;

import com.raven.swing.ButtonMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;
import plantilla.swing.scrollbar.ScrollBarCustom;

public class Menu_Admin extends javax.swing.JPanel {

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
//        if (text.equals("Cerrar Sesión")) {
//            menu.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    cerrarSesion();
//                }
//            });
//        }
        panelMenu.add(menu);
    }

//    private void cerrarSesion() {
//        // Cierra la ventana actual
//        Window currentWindow = SwingUtilities.getWindowAncestor(this);
//        currentWindow.dispose();
//
//        // Abre la ventana de inicio de sesión
//        Frm_Inicio_Sesion inicioSesion = new Frm_Inicio_Sesion();
//        inicioSesion.setVisible(true);
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 222, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
