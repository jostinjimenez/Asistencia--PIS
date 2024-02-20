package View.Tablas;

import javax.swing.table.AbstractTableModel;
import model.Asignatura;

public class ModelTableMaterias extends AbstractTableModel {

    private Asignatura asig = new Asignatura();

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Asignatura asignatura = getAsig();
        
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
     * @return the asig
     */
    public Asignatura getAsig() {
        return asig;
    }

    /**
     * @param asig the asig to set
     */
    public void setAsig(Asignatura asig) {
        this.asig = asig;
    }



}
