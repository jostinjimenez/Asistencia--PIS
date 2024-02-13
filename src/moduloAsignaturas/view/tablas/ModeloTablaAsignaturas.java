package moduloAsignaturas.view.tablas;

import model.*;

import javax.swing.table.AbstractTableModel;

import moduloAsignaturas.controller.MallaController;
import modulo_carrera.controller.CarreraController;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import static modulo_1.inicio_sesion.controller.util.Utilidades.getPersonaStatic;

public class ModeloTablaAsignaturas extends AbstractTableModel {

    private ListaEnlazada<Asignatura> asignaturas;
    private MallaController cc = new MallaController();

    @Override
    public int getRowCount() {
        return getAsignaturas().getSize();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Asignatura asignatura;
        Malla malla;
        try {
            asignatura = asignaturas.get(row);
            malla = cc.busquedaBinaria2(cc.list_All(), asignatura.getMalla_id().toString(), "id");
            return switch (col) {
                case 0 -> (asignatura != null) ? asignatura.getId() : "";
                case 1 -> (asignatura != null) ? asignatura.getNombre() : "";
                case 2 -> (asignatura != null) ? asignatura.getCodigo_materia() : "";
                case 3 -> (asignatura != null) ? asignatura.getHoras_Totales() : "";
                case 4 -> (malla != null) ? malla.getDescripcion() : "";
                default -> null;
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "ID";
            case 1 -> "Nombre";
            case 2 -> "Código";
            case 3 -> "Horas Totales";
            case 4 -> "Malla";
            default -> null;
        };
    }

    /**
     * @return the matriculas
     */
    public ListaEnlazada<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    /**
     * @param asignaturas the matriculas to set
     */
    public void setAsignaturas(ListaEnlazada<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }


    public Asignatura getAsignatura(int fila) throws VacioExceptions {
        return asignaturas.get(fila);
    }
}

