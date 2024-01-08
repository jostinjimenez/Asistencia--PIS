package modelLuis.tablas;

<<<<<<< HEAD
=======
import java.util.logging.Level;
import java.util.logging.Logger;
>>>>>>> master
import javax.swing.table.AbstractTableModel;
import model.Asignatura;
import model.Horario;
import modelLuis.controller.ControllerAsignatura;
import modelLuis.controller.ControllerHorario;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class ModelTableAsistenciaEstudiante extends AbstractTableModel {

    private ListaEnlazada<Horario> horarios = new ListaEnlazada<>();
    private ListaEnlazada<Asignatura> listaAsis = new ListaEnlazada<>();
    private ControllerAsignatura controlerAsignatura = new ControllerAsignatura();
    private ControllerHorario conHor = new ControllerHorario();

    @Override
    public int getRowCount() {
        return horarios.getSize();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Asignatura asig = null;
        Horario horario = new Horario();

        try {
            horario = conHor.quicksort(horarios, 0, "id").get(row);
        } catch (VacioExceptions ex) {
            System.out.println("Error al obtener horario: " + ex.getMessage());
            return null;
        }

        if (horario != null) {

            switch (col) {
                case 0:
                    return horario.getDia();
                case 1:
                    return obtenerNombreAsignatura(horario);
                case 2:
                    return horario.getHoraInicio() + "-" + horario.getHoraFin();
                default:
                    break;
            }
        }

        return null;
    }
    // Método auxiliar para obtener el nombre de la asignatura

    private String obtenerNombreAsignatura(Horario horario) {
        try {
            Asignatura asig = controlerAsignatura.busquedaBinaria2(getListaAsis(), horario.getIdAsignatura().toString(), "id", 0);
            return asig.getNombre();
        } catch (VacioExceptions ex) {
            System.out.println("Error al obtener asignatura: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Dia";
            case 1:
                return "Nombre Asignatura";
            case 2:
                return "Hora Clase";
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

    /**
     * @return the listaAsis
     */
    public ListaEnlazada<Asignatura> getListaAsis() {
        return listaAsis;
    }

    /**
     * @param listaAsis the listaAsis to set
     */
    public void setListaAsis(ListaEnlazada<Asignatura> listaAsis) {
        this.listaAsis = listaAsis;
    }

}
