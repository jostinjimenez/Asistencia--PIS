package modulo_1.periodo_academico.view.tablas;

import model.PeriodoAcademico;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaPeriodoAc extends AbstractTableModel {

    private ListaEnlazada<PeriodoAcademico> periodoAcademicos;

    @Override
    public int getRowCount() {
        return periodoAcademicos.getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
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
            case 0 -> (pa != null) ? pa.getId() : "";
            case 1 -> (pa != null) ? pa.getFechaInicio() : "";
            case 2 -> (pa != null) ? pa.getFechaFin() : "";
            case 3 -> (pa != null) ? pa.getEstado() : "";
            default -> null;
        };
    }

    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "ID";
            case 1 -> "Fecha Inicio";
            case 2 -> "Fecha Fin";
            case 3 -> "Estado";
            default -> null;
        };
    }
}

