package ModuloMatricula.tablas;

import Controller.Academico.ControllerMatricula;
import model.*;
import Controller.Academico.AsignaturaController;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import javax.swing.table.AbstractTableModel;

import java.util.HashMap;
import java.util.Map;

import static Controller.Util.Utilidades.getPersonaStatic;

public class ModeloTablaCursas extends AbstractTableModel {

    private ListaEnlazada<Cursa> cursas;
    private Map<Integer, Persona> personas;
    private Map<Integer, Matricula> matriculas;
    private Map<Integer, Asignatura> asignaturas;
    AsignaturaController ac = new AsignaturaController();
    ControllerMatricula cm = new ControllerMatricula();


    public ListaEnlazada<Cursa> getCursas() {
        return cursas;
    }

    public void setCursas(ListaEnlazada<Cursa> cursas) {
        this.cursas = cursas;
        this.personas = new HashMap<>();
        this.matriculas = new HashMap<>();
        this.asignaturas = new HashMap<>();
        for (Cursa cursa : cursas) {
            try {
                Persona p = getPersonaStatic(cursa.getDocente_id());
                this.personas.put(cursa.getDocente_id(), p);
                Matricula m = cm.busquedaBinaria2(cm.list_All(), String.valueOf(cursa.getMatricula_id()), "id");
                this.matriculas.put(cursa.getMatricula_id(), m);
                Asignatura a = ac.buscarAsignaturasPorCursa(cursa.getId());
                this.asignaturas.put(cursa.getId(), a);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
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
        try {
            Cursa cursa = cursas.get(row);
            Asignatura asignatura = this.asignaturas.get(cursa.getId());
            Persona persona = this.personas.get(cursa.getDocente_id());
            Matricula matricula = this.matriculas.get(cursa.getMatricula_id());
            return switch (col) {
                case 0 -> (matricula != null) ? matricula.toString() : "";
                case 1 -> (persona != null) ? persona.getNombre() + " " + persona.getApellido() : "";
                case 2 -> (asignatura != null) ? asignatura.getNombre() : "";
                case 3 -> cursa.getParalelo();
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
            case 2 -> "Asignatura";
            case 3 -> "Paralelo";
            default -> null;
        };
    }

    public Cursa getCursa(int fila) throws VacioExceptions {
        return cursas.get(fila);
    }

}