package moduloAsignaturas.view.tablas;

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
        return 4;
    }

    public ListaEnlazada<Malla> getMallas() {
        return mallas;
    }

    public void setMallas(ListaEnlazada<Malla> mallas) {
        this.mallas = mallas;
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
                return (malla != null) ? malla.getCodigo() : "";
            case 1:
                return (malla != null) ? malla.getDescripcion() : "";
            case 2:
                return (malla != null) ? malla.getNro_asignaturas() : "";
            case 3:
                return (malla != null) ? malla.getTotal_horas() : ""; // Atributo 'nombreSilabo' en lugar de 'silabo'
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Código";
            case 1:
                return "Descripción";
            case 2:
                return "Nro. Asignaturas";
            case 3:
                return "Horas totales";
            default:
                return null;
        }
    }
}
