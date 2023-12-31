package plantilla.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Header extends javax.swing.JPanel {

    public Header() {
        initComponents();
        setOpaque(false);
        setBackground(new Color(51, 51, 51));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonBadges3 = new plantilla.swing.ButtonBadges();

        setBackground(new java.awt.Color(51, 51, 51));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonBadges3.setBackground(new java.awt.Color(51, 51, 51));
        buttonBadges3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plantilla/img/icons8-más-1-semana-32.png"))); // NOI18N
        buttonBadges3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        add(buttonBadges3, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, -1, 40));
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
    private plantilla.swing.ButtonBadges buttonBadges3;
    // End of variables declaration//GEN-END:variables
}
