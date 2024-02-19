package moduloAsignaturas.view.tablas;

import model.*;

import javax.swing.table.AbstractTableModel;

import moduloAsignaturas.controller.MallaController;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import java.util.HashMap;
import java.util.Map;

public class ModeloTablaAsignaturas extends AbstractTableModel {

    private ListaEnlazada<Asignatura> asignaturas;
    private MallaController cc = new MallaController();
    private Map<Integer, Malla> mallas;

    @Override
    public int getRowCount() {
        return getAsignaturas().getSize();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public void setAsignaturas(ListaEnlazada<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
        this.mallas = new HashMap<>();
        for (Asignatura asignatura : asignaturas) {
            try {
                Malla m = cc.busquedaBinaria2(cc.list_All(), asignatura.getMalla_id().toString(), "id");
                this.mallas.put(asignatura.getMalla_id(), m);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        Asignatura asignatura;
        Malla malla;
        try {
            asignatura = asignaturas.get(row);
            malla = this.mallas.get(asignatura.getMalla_id());
            return switch (col) {
                case 0 -> (asignatura != null) ? asignatura.getCodigo_materia() : "";
                case 1 -> (asignatura != null) ? asignatura.getNombre() : "";
                case 2 -> (asignatura != null) ? asignatura.getHoras_Totales() : "";
                case 3 -> (malla != null) ? malla.getDescripcion() : "";
                default -> null;
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Código";
            case 1 -> "Nombre";
            case 2 -> "Horas Totales";
            case 3 -> "Malla";
            default -> null;
        };
    }

    public ListaEnlazada<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public Asignatura getAsignatura(int fila) throws VacioExceptions {
        return asignaturas.get(fila);
    }
}