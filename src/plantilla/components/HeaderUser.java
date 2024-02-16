package plantilla.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;
import javax.swing.ImageIcon;
import model.Cuenta;
import model.Persona;
import modulo_1.inicio_sesion.controller.CuentaController;
import modulo_1.inicio_sesion.view.forms.mainFrm.Perfil_Modal;
import modulo_1.inicio_sesion.view.util.Utiles;

import static modulo_1.inicio_sesion.view.util.Utiles.cargarPeriodo;

public class HeaderUser extends javax.swing.JPanel {

    CuentaController cc = Utiles.getCc();
    Cuenta cuentaUsu = Utiles.getCuentaUsu();

    public HeaderUser() {
        initComponents();
        setOpaque(false);
        setBackground(new Color(51, 51, 51));
        
        //labelFoto.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/icons8-más-1-semana-32.png"))));
        cargarFoto();

    }

    public void cargarFoto(){
        try {
            Persona persona = cc.getPersona(cuentaUsu.getPersona_id());
            if (persona != null) {
                txtUsername.setText(persona.toString());
                if (persona.getFoto() != null) {
                    fotoUsuario.setIcon(new ImageIcon("multimedia/" + persona.getFoto()));
                } else {
                    fotoUsuario.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/user.png"))));
                }
            } else {
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUsername = new javax.swing.JLabel();
        fotoUsuario = new plantilla.swing.ImageAvatar();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        txtUsername.setBackground(new java.awt.Color(255, 255, 255));
        txtUsername.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(0, 0, 0));
        txtUsername.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtUsername.setText("Username");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(727, Short.MAX_VALUE)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(fotoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addComponent(fotoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        Area area = new Area(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        area.add(new Area(new Rectangle2D.Double(0, 20, getWidth(), getHeight())));
        g2.fill(area);
        g2.dispose();
        super.paint(grphcs);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private plantilla.swing.ImageAvatar fotoUsuario;
    private javax.swing.JLabel txtUsername;
    // End of variables declaration//GEN-END:variables
}
