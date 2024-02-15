package modelLuis.tablas;

import javax.swing.table.AbstractTableModel;
import model.Asignatura;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class ModelTableMateria extends AbstractTableModel {

    private ListaEnlazada<Asignatura> asignaturas;

    @Override
    public int getRowCount() {
        return getAsignaturas().getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Asignatura asignatura = null;
        try {
            asignatura = getAsignaturas().get(row);
            asignatura.getId();
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
        switch (col) {
            case 0:
                return (asignatura != null) ? asignatura.getId() : "";
            case 1:
                return (asignatura != null) ? asignatura.getHoras_Totales() : "";
            case 2:
                return (asignatura != null) ? asignatura.getCodigo_materia() : "";
            case 3:
                return (asignatura != null) ? asignatura.getNombre() : "";
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Horas T";
            case 2:
                return "Codigo";
            case 3:
                return "Nombre";
            default:
                return null;
        }
    }

    /**
     * @return the asignaturas
     */
    public ListaEnlazada<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    /**
     * @param asignaturas the asignaturas to set
     */
    public void setAsignaturas(ListaEnlazada<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Asignatura getAsignatura(int fila) throws VacioExceptions {
        return asignaturas.get(fila);

    }

}
