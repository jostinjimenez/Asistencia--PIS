package view.tablas;

import javax.swing.table.AbstractTableModel;
import model.Malla;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class ModeloTablaMallas extends AbstractTableModel {

    private ListaEnlazada<Malla> mallas;

    @Override
    public int getRowCount() {
        return mallas.getSize();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Malla malla = null;
        try {
            malla = mallas.get(row);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }

        switch (col) {
            case 0:
                return (malla != null) ? malla.getDescripcion() : "";
            case 1:
                return (malla != null) ? malla.getDuracion() : "";
            case 2:
                return (malla != null) ? malla.getNombreSilabo() : "";
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Descripción";
            case 1:
                return "Duración";
            case 2:
                return "Silabo";
            default:
                return null;
        }
    }

    /**
     * @return the mallas
     */
    public ListaEnlazada<Malla> getMallas() {
        return mallas;
    }

    /**
     * @param mallas the mallas to set
     */
    public void setMallas(ListaEnlazada<Malla> mallas) {
        this.mallas = mallas;
    }
}