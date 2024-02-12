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

        try {
            Persona persona = cc.getPersona(cuentaUsu.getPersona_id());
            if (persona != null) {
                txtUsername.setText(persona.toString());
                fotoUsuario.setIcon(new ImageIcon("/multimedia/" + persona.getFoto()));

            } else {
                txtUsername.setText("Username");
                fotoUsuario.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/user.png"))));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fotoUsuario = new com.raven.swing.ImageAvatar();
        txtUsername = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        fotoUsuario.setForeground(new java.awt.Color(231, 231, 231));
        fotoUsuario.setBorderSize(2);
        fotoUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fotoUsuarioMouseClicked(evt);
            }
        });

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
                .addComponent(fotoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fotoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fotoUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fotoUsuarioMouseClicked
        Perfil_Modal pm = new Perfil_Modal(null, true, cc);
        pm.setVisible(true);
    }//GEN-LAST:event_fotoUsuarioMouseClicked

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
    private com.raven.swing.ImageAvatar fotoUsuario;
    private javax.swing.JLabel txtUsername;
    // End of variables declaration//GEN-END:variables
}
