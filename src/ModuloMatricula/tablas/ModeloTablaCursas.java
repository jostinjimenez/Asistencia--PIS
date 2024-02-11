package ModuloMatricula.tablas;

import ModuloEstudianteDocente.controlador.EstudianteController;
import model.Cursa;
import model.Estudiante;
import model.Matricula;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaCursas extends AbstractTableModel {

    private ListaEnlazada<Cursa> cursas;

    public ListaEnlazada<Cursa> getCursas() {
        return cursas;
    }

    public void setCursas(ListaEnlazada<Cursa> cursas) {
        this.cursas = cursas;
    }

    @Override
    public int getRowCount() {
        return getCursas().getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Cursa cursa = null;
        try {
            cursa = cursas.get(row);

            return switch (col) {
                case 0 -> (cursa != null) ? cursa.getMatricula_id() : "";
                case 1 -> (cursa != null) ? cursa.getDocente_id() : "";
                case 2 -> (cursa != null) ? cursa.getParalelo() : "";
                case 3 -> (cursa != null) ? cursa.getAsignatura_id() : "";
                default -> null;
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Matricula";
            case 1 -> "Docente";
            case 2 -> "Paralelo";
            case 3 -> "Asignatura";
            default -> null;
        };
    }

    public Cursa getCursa(int fila) throws VacioExceptions {
        return cursas.get(fila);
    }

}
