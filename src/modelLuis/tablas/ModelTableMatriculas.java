package modelLuis.tablas;

import javax.swing.table.AbstractTableModel;

import model.Carrera;
import model.Matricula;
import model.Persona;
import Controller.Administrativo.CarreraController;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import java.util.HashMap;
import java.util.Map;

import static Controller.Util.Utilidades.getPersonaStatic;

public class ModelTableMatriculas extends AbstractTableModel {

    private ListaEnlazada<Matricula> matriculas;
    private CarreraController cc = new CarreraController();
    private Map<Integer, Persona> personas;
    private Map<Integer, Carrera> carreras;

    @Override
    public int getRowCount() {
        return getMatriculas().getSize();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public void setMatriculas(ListaEnlazada<Matricula> matriculas) {
        this.matriculas = matriculas;
        this.personas = new HashMap<>();
        this.carreras = new HashMap<>();
        for (Matricula matricula : matriculas) {
            try {
                Persona p = getPersonaStatic(matricula.getEstudiante_id());
                this.personas.put(matricula.getEstudiante_id(), p);
                Carrera c = cc.busquedaBinaria2(cc.list_All(), matricula.getCarrera_id().toString(), "id");
                this.carreras.put(matricula.getCarrera_id(), c);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        Matricula matricula = null;
        try {
            matricula = matriculas.get(row);
            Carrera carrera = this.carreras.get(matricula.getCarrera_id());
            Persona persona = this.personas.get(matricula.getEstudiante_id());

            return switch (col) {
                case 0 -> (persona != null) ? persona.getNombre() + " " + persona.getApellido() : "";
                case 1 -> (persona != null) ? persona.getDni() : "";
                case 2 -> (matricula != null) ? "Ciclo " + matricula.getCiclo() : "";
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

    public Matricula getMatricula(int fila) throws VacioExceptions {
        return matriculas.get(fila);
    }

}