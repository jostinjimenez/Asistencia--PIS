package modelLuis.tablas;

import javax.swing.table.AbstractTableModel;
import model.Asignatura;
import model.Horario;
import modelLuis.controller.ControllerAsignatura;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class ModelTableHorario extends AbstractTableModel {

    private ListaEnlazada<Horario> horarios;
    private ControllerAsignatura a = new ControllerAsignatura();

    @Override
    public int getRowCount() {
        return getHorarios().getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Horario horario = null;
        try {
            horario = horarios.get(row);
            horario.getId();
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
        switch (col) {
            case 0:
                return (horario != null) ? horario.getDia() : "";
            case 1:
                return (horario != null) ? horario.getHoraFin() : "";
            case 2:
                return (horario != null) ? horario.getHoraInicio() : "";
            case 3:
                String id = horario.getIdAsignatura().toString();
                Asignatura as;
                try {
                    as = a.busquedaBinaria2(a.list_All(), id, "id", 0);
                    return (as != null) ? as.getNombre() : "";
                } catch (VacioExceptions ex) {

                }
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Dia";
            case 1:
                return "Hora Inicio";
            case 2:
                return "Hora Fin";
            case 3:
                return "Materia";
            default:
                return null;
        }
    }

    /**
     * @return the horarios
     */
    public ListaEnlazada<Horario> getHorarios() {
        return horarios;
    }

    /**
     * @param horarios the horarios to set
     */
    public void setHorarios(ListaEnlazada<Horario> horarios) {
        this.horarios = horarios;
    }

}
