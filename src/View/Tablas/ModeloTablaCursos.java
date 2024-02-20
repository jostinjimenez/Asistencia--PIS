package View.Tablas;

import Controller.Academico.ControllerMatricula;
import Controller.Administrativo.CarreraController;
import model.*;
import Controller.Academico.AsignaturaController;
import Controller.tda_listas.ListaEnlazada;
import Controller.tda_listas.exceptions.VacioExceptions;

import javax.swing.table.AbstractTableModel;

import java.util.HashMap;
import java.util.Map;

import static Controller.Util.Utilidades.getPersonaStatic;

public class ModeloTablaCursos extends AbstractTableModel {

    private ListaEnlazada<Cursa> cursos;
    private Map<Integer, Matricula> matriculas;
    private Map<Integer, Asignatura> asignaturas;
    private Map<Integer, Carrera> carreras;
    AsignaturaController ac = new AsignaturaController();
    ControllerMatricula cm = new ControllerMatricula();
    CarreraController cc = new CarreraController();

    public ListaEnlazada<Cursa> getCursos() {
        return cursos;
    }

    public void setCursos(ListaEnlazada<Cursa> cursos) {
        this.cursos = cursos;
        this.matriculas = new HashMap<>();
        this.asignaturas = new HashMap<>();
        this.carreras = new HashMap<>();
        for (Cursa cursa : cursos) {
            try {
                Matricula m = cm.busquedaBinaria2(cm.list_All(), String.valueOf(cursa.getMatricula_id()), "id");
                this.matriculas.put(cursa.getMatricula_id(), m);
                Asignatura a = ac.buscarAsignaturasPorCursa(cursa.getId());
                this.asignaturas.put(cursa.getId(), a);
                Carrera c = cc.busquedaBinaria2(cc.list_All(), String.valueOf(m.getCarrera_id()), "id");
                this.carreras.put(m.getCarrera_id(), c);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int getRowCount() {
        if (cursos.isEmpty()) {
            return 1;
        } else {
            return cursos.getSize();
        }
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (cursos.isEmpty()) {
            return switch (col) {
                case 0 -> "No hay cursos para el docente";
                default -> "";
            };
        } else {
            try {
                Cursa cursa = cursos.get(row);
                Asignatura asignatura = this.asignaturas.get(cursa.getId());
                Matricula matricula = this.matriculas.get(cursa.getMatricula_id());
                Carrera carrera = this.carreras.get(matricula.getCarrera_id());
                return switch (col) {
                    case 0 -> (carrera != null) ? carrera.getNombre() : "";
                    case 1 -> (asignatura != null) ? asignatura.getNombre() : "";
                    case 2 -> (matricula != null) ? matricula.getCiclo() : "";
                    case 3 -> cursa.getParalelo();
                    default -> null;
                };
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Carrera";
            case 1 -> "Asignatura";
            case 2 -> "Ciclo";
            case 3 -> "Paralelo";
            default -> null;
        };
    }

    public Cursa getCursa(int fila) throws VacioExceptions {
        return cursos.get(fila);
    }

}