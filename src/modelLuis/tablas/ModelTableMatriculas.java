package modelLuis.tablas;

import javax.swing.table.AbstractTableModel;

import ModuloEstudianteDocente.controlador.EstudianteController;
import model.Estudiante;
import model.Matricula;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class ModelTableMatriculas extends AbstractTableModel {

    private ListaEnlazada<Matricula> matriculas;
    private EstudianteController aa = new EstudianteController();

    @Override
    public int getRowCount() {
        return getMatriculas().getSize();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Matricula matricula = null;
        try {
            matricula = matriculas.get(row);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
        switch (col) {
            case 0:
                return (matricula != null) ? matricula.getEstado_matricula().getNombre() : "";
            case 1:
                return (matricula != null) ? matricula.getFechaMatricula() : "";
            case 2:
                return (matricula != null) ? matricula.getCiclo() : "";
            case 3:
                try {
                Estudiante estudiante = aa.busquedaBinaria2(aa.list_All(), matricula.getEstudiante_id().toString(), "id", 0);
                return (estudiante != null) ? estudiante.getNacionalidad() : "";
                // TODO: Mostrar el nombre del estudiante mediante un join con la tabla estudiante y persona
            } catch (Exception ignored) {
            }
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        return switch (column) {
            case 1 -> "Estado Matricula";
            case 2 -> "Fecha Matriculacion";
            case 3 -> "Ciclo";
            case 4 -> "Nombre";
            default -> null;
        };
    }

    /**
     * @return the matriculas
     */
    public ListaEnlazada<Matricula> getMatriculas() {
        return matriculas;
    }

    /**
     * @param matriculas the matriculas to set
     */
    public void setMatriculas(ListaEnlazada<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

}
