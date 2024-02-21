package View.Tablas;

import Controller.Academico.AsignaturaController;
import Controller.Academico.ControllerCursa;
import Controller.Academico.ControllerMatricula;
import Controller.Academico.ControllerTematica;
import Controller.Administrativo.CarreraController;
import Controller.Administrativo.ControllerHorario;
import Controller.Administrativo.PeriodoAcController;
import Controller.tda_listas.ListaEnlazada;
import Controller.tda_listas.exceptions.VacioExceptions;
import model.*;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Map;

public class ModeloTablaDetalleAsisEstudiante extends AbstractTableModel {

    private ListaEnlazada<Asistencia> asistencias;

    private Map<Integer, Tematica> tematicas;
    private Map<Integer, Horario> horarios;
    ControllerTematica ct = new ControllerTematica();
    ControllerHorario ch = new ControllerHorario();

    public ListaEnlazada<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(ListaEnlazada<Asistencia> asistencias) {
        this.asistencias = asistencias;
        this.tematicas = new HashMap<>();
        this.horarios = new HashMap<>();
        for (Asistencia asistencia : asistencias) {
            try {
                Tematica t = ct.find(asistencia.getTematica_id());
                this.tematicas.put(asistencia.getTematica_id(), t);
                Horario h = ch.find(asistencia.getHorario_id());
                this.horarios.put(asistencia.getHorario_id(), h);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int getRowCount() {
        if (asistencias.isEmpty()) {
            return 1;
        } else {
            return asistencias.getSize();
        }
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (asistencias.isEmpty()) {
            return switch (col) {
                case 0 -> "No hay datos";
                default -> "";
            };
        } else {
            try {
                Asistencia asistencia = asistencias.get(row);
                Tematica tematica = this.tematicas.get(asistencia.getTematica_id());
                Horario horario = this.horarios.get(asistencia.getHorario_id());

                return switch (col) {
                    case 0 -> (tematica != null) ? tematica.getNombre() : "";
                    case 1 -> (horario != null) ? horario.getDia() : "";
                    case 2 -> (horario != null) ? horario.getHoraInicio() + " - " + horario.getHoraFin() : "";
                    case 3 -> (asistencia != null) ? asistencia.getEstado_asistencia() : "";
                    case 4 -> (asistencia != null) ? asistencia.getObservacion() : "";
                    default -> null;
                };
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Tema";
            case 1 -> "Dia";
            case 2 -> "Horario";
            case 3 -> "Estado";
            case 4 -> "Observacion";
            default -> null;
        };
    }

    public Asistencia getAsistencia(int fila) throws VacioExceptions {
        return asistencias.get(fila);
    }

}