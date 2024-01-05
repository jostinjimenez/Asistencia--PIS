package modulo_1.matricula.view.tablas;

import model.PeriodoAcademico;
import model.Persona;
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
        return 5;
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
            case 1 -> (pa != null) ? pa.getAnio() : "";
            case 2 -> (pa != null) ? pa.getFechaInicio() : "";
            case 3 -> (pa != null) ? pa.getFechaFin() : "";
            case 4 -> (pa != null) ? pa.getEstado() : "";
            default -> null;
        };
    }

    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "ID";
            case 1 -> "Año";
            case 2 -> "Fecha Inicio";
            case 3 -> "Fecha Fin";
            case 4 -> "Estado";
            default -> null;
        };
    }
}

