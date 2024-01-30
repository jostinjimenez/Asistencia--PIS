package plantilla;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class AccionesCellRenderer extends JPanel implements TableCellRenderer {

    private JButton editarButton = new JButton();
    private JButton eliminarButton = new JButton();

    public AccionesCellRenderer() {
        setLayout(new java.awt.GridLayout(1, 0)); // Coloca los botones uno al lado del otro

        editarButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/editar.png"))));
        editarButton.setBorderPainted(false); // Elimina el borde del botón
        editarButton.setContentAreaFilled(false); // Elimina el fondo del botón
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Editar");
            }
        });

        eliminarButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/plantilla/img/eliminar.png"))));
        eliminarButton.setBorderPainted(false);
        eliminarButton.setContentAreaFilled(false);
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Eliminar");
            }
        });

        add(editarButton);
        add(eliminarButton);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        eliminarButton.setVisible(value == null); // Muestra el botón de eliminar solo si el valor es nulo
        return this;
    }
}
