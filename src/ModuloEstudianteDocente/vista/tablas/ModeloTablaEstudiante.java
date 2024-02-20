package ModuloEstudianteDocente.vista.tablas;

import model.Persona;
import tda_listas.ListaEnlazada;

import javax.swing.table.AbstractTableModel;

import model.Estudiante;
import tda_listas.exceptions.VacioExceptions;

import java.util.HashMap;
import java.util.Map;

import static Controller.Util.Utilidades.getPersonaStatic;

public class ModeloTablaEstudiante extends AbstractTableModel {

    private ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();
    private Map<Integer, Persona> personas;

    public ListaEnlazada<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ListaEnlazada<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
        this.personas = new HashMap<>();
        for (Estudiante estudiante : estudiantes) {
            try {
                Persona p = getPersonaStatic(estudiante.getId());
                this.personas.put(estudiante.getId(), p);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int getRowCount() {
        if (estudiantes != null) {
            return estudiantes.getSize();
        } else {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int row, int col) {
        try {
            Estudiante est = estudiantes.get(row);
            Persona per = this.personas.get(est.getId());
            return switch (col) {
                case 0 -> (per != null) ? per.getNombre() + " " + per.getApellido() : " ";
                case 1 -> (per != null) ? per.getDni() : " ";
                case 2 -> est.getCanton();
                case 3 -> est.getProvincia();
                case 4 -> est.getNacionalidad();
                default -> null;
            };
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        return switch (col) {
            case 0 -> "Nombre";
            case 1 -> "DNI";
            case 2 -> "Canton";
            case 3 -> "Provincia";
            case 4 -> "Nacionalidad";
            default -> null;
        };
    }

    public Estudiante getEstudiante(int fila) throws VacioExceptions {
        return estudiantes.get(fila);
    }

    public Persona getPersona(int fila) throws VacioExceptions {
        return personas.get(estudiantes.get(fila).getId());
    }


}