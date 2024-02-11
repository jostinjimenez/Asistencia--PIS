package modelLuis.tablas;

import javax.swing.table.AbstractTableModel;

import ModuloEstudianteDocente.controlador.EstudianteController;
import model.Estudiante;
import model.Matricula;
import model.Persona;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import static modulo_1.inicio_sesion.controller.util.Utilidades.getPersonaStatic;

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
            Estudiante estudiante = aa.busquedaBinaria2(aa.list_All(), matricula.getEstudiante_id().toString(), "id");
            Persona persona = getPersonaStatic(estudiante.getId());

            return switch (col) {
                case 0 -> (matricula != null) ? matricula.getEstado_matricula() : "";
                case 1 -> (matricula != null) ? matricula.getFechamatricula() : "";
                case 2 -> (matricula != null) ? matricula.getCiclo() : "";
                case 3 -> (persona != null) ? persona.getNombre() + " " + persona.getApellido() : "";
                case 4 -> (persona != null) ? persona.getDni() : "";
                default -> null;
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Estado Matricula";
            case 1 -> "Fecha Matriculacion";
            case 2 -> "Ciclo";
            case 3 -> "Nombre Completo";
            case 4 -> "DNI";
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


    public Matricula getMatricula(int fila) throws VacioExceptions {
        return matriculas.get(fila);
    }

}
