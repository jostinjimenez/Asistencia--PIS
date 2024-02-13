package modelLuis.tablas;

import javax.swing.table.AbstractTableModel;

import ModuloEstudianteDocente.controlador.EstudianteController;
import model.Carrera;
import model.Estudiante;
import model.Matricula;
import model.Persona;
import modulo_carrera.controller.CarreraController;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import static modulo_1.inicio_sesion.controller.util.Utilidades.getPersonaStatic;

public class ModelTableMatriculas extends AbstractTableModel {

    private ListaEnlazada<Matricula> matriculas;
    private CarreraController cc = new CarreraController();

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
            Carrera carrera = cc.busquedaBinaria2(cc.list_All(), matricula.getCarrera_id().toString(), "id");
            Persona persona = getPersonaStatic(matricula.getEstudiante_id());

            return switch (col) {
                case 0 -> (persona != null) ? persona.getNombre() + " " + persona.getApellido() : "";
                case 1 -> (persona != null) ? persona.getDni() : "";
                case 2 -> (matricula != null) ? "Ciclo "  + matricula.getCiclo() : "";
                case 3 -> (carrera != null) ? carrera.getNombre() : "";
                case 4 -> (matricula != null) ? matricula.getFechamatricula() : "";
                //case 5 -> (matricula != null) ? matricula.getEstado_matricula() : "";
                default -> null;
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Nombre Completo";
            case 1 -> "DNI";
            case 2 -> "Ciclo";
            case 3 -> "Carrera";
            case 4 -> "Fecha Matriculacion";
           // case 5 -> "Estado Matricula";
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
