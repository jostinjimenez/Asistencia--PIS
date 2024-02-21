package View.Tablas;

import javax.swing.table.AbstractTableModel;
import model.Asignatura;
import model.Horario;
import Controller.Administrativo.ControllerHorario;
import Controller.Academico.AsignaturaController;
import Controller.tda_listas.ListaEnlazada;
import Controller.tda_listas.exceptions.VacioExceptions;

public class ModelTableAsistenciaDocente extends AbstractTableModel {

    private ListaEnlazada<Horario> horarios = new ListaEnlazada<>();
    private ListaEnlazada<Asignatura> listaAsis = new ListaEnlazada<>();
    private AsignaturaController controlerAsignatura = new AsignaturaController();
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
            Asignatura asig = controlerAsignatura.busquedaBinaria2(getListaAsis(), horario.getAsignatura_id().toString(), "id");
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

    public ListaEnlazada<Horario> getHorarios() {
        return horarios;
    }


    public void setHorarios(ListaEnlazada<Horario> horarios) {
        this.horarios = horarios;
    }

    public ListaEnlazada<Asignatura> getListaAsis() {
        return listaAsis;
    }

    public void setListaAsis(ListaEnlazada<Asignatura> listaAsis) {
        this.listaAsis = listaAsis;
    }


}
