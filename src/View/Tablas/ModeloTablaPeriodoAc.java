package View.Tablas;

import model.PeriodoAcademico;
import Controller.tda_listas.ListaEnlazada;
import Controller.tda_listas.exceptions.VacioExceptions;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaPeriodoAc extends AbstractTableModel {

    private ListaEnlazada<PeriodoAcademico> periodoAcademicos;

    @Override
    public int getRowCount() {
        return periodoAcademicos.getSize();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    public ListaEnlazada<PeriodoAcademico> getPeriodoAcademicos() {
        return periodoAcademicos;
    }

    public void setPeriodoAcademicos(ListaEnlazada<PeriodoAcademico> periodoAcademicos) {
        this.periodoAcademicos = periodoAcademicos;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PeriodoAcademico pa = null;
        try {
            pa = periodoAcademicos.get(rowIndex);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
        return switch (columnIndex) {
            case 0-> (pa != null) ? pa.getFecha_Inicio() : "";
            case 1 -> (pa != null) ? pa.getFecha_fin() : "";
            case 2 -> (pa != null) ? pa.getEstado() : "";
            default -> null;
        };
    }

    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Fecha Inicio";
            case 1 -> "Fecha Fin";
            case 2 -> "Estado";
            default -> null;
        };
    }
}

