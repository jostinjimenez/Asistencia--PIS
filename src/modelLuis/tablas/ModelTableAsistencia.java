package modelLuis.tablas;


import javax.swing.table.AbstractTableModel;
import model.Estudiante;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;





public class ModelTableAsistencia extends AbstractTableModel {

    private ListaEnlazada<Matricula> estudiantes;

    @Override
    public int getRowCount() {
        return getLlantas().getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
       Estudiante estudiante = null;
        try {
            estudiante = estudiantes.get(row);
            estudiante.getId();
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
        switch (col) {
            case 0:
                return (auto != null) ? auto.getColor() : "";
            case 1:
                return (auto != null) ? auto.getDescripcion() : "";
            case 2:
                return (auto != null) ? auto.getPrecio() : "";
            case 3:
                return (auto != null) ? auto.getModelo() : "";
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Color";
            case 1:
                return "Descripcion";
            case 2:
                return "Precio";
            case 3:
                return "Modelo";
            default:
                return null;
        }
    }

    /**
     * @return the llantas
     */
    public LinkedList<Auto> getLlantas() {
        return llantas;
    }

    /**
     * @param llantas the llantas to set
     */
    public void setLlantas(LinkedList<Auto> llantas) {
        this.llantas = llantas;
    }

}
