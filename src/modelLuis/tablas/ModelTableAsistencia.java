package modelLuis.tablas;

import javax.swing.table.AbstractTableModel;

import ModuloEstudianteDocente.controlador.EstudianteController;
import model.Asistencia;
import model.Estudiante;
import model.Matricula;

import modelLuis.controller.ControllerAsistencia;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class ModelTableAsistencia extends AbstractTableModel {

    private ListaEnlazada<Matricula> matriculas;
    private EstudianteController c = new EstudianteController();
    private ControllerAsistencia a = new ControllerAsistencia();
    private ListaEnlazada<Asistencia> Asistencias;
    private String norma = "";

    @Override
    public int getRowCount() {
        return getMatriculas().getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Estudiante estudiante = null;
        try {
            estudiante = getEstudiantes().get(row);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
        if (estudiante != null) {
            switch (col) {
                case 0:
                    return estudiante.getApellido();
                case 1:
                    return estudiante.getNombre();
                case 2:
                    return a.getAsistencia().getFalta();
                case 3:
                    return estudiante.getDni();
                default:
                    return null;
            }
        } else {
            return null; // O cualquier valor predeterminado que desees en caso de que estudiante sea null.
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Apellido";
            case 1:
                return "Nombre";
            case 2:
                return "Falta";
            case 3:
                return "DNI";
            default:
                return null;
        }
    }

    public String enviarA() {
        return norma;
    }

    public void setA(String norma) {
        this.norma = norma;
    }

    public String generarA(String norma) {
        return norma;
    }

    /**
     * @return the llantas
     */
    public ListaEnlazada<Matricula> getMatriculas() {
        return matriculas;
    }

    /**
     * @param matriculas the llantas to set
     */
    public void setMatriculas(ListaEnlazada<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public ListaEnlazada<Estudiante> getEstudiantes() throws VacioExceptions {
        ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();

        try {
            for (Matricula matricula : matriculas) {
                String iden = matricula.getEstudiante_id().toString();
                Estudiante estudiante = c.busquedaBinaria2(c.list_All(), iden, "id", 0);
                estudiantes.add(estudiante);
            }
        } catch (Exception e) {
            // Manejar la excepción si es necesario
            e.printStackTrace();
        }

        return estudiantes;
    }

    /**
     * @return the Asistencias
     */
    public ListaEnlazada<Asistencia> getAsistencias() {
        return Asistencias;
    }

    /**
     * @param Asistencias the Asistencias to set
     */
    public void setAsistencias(ListaEnlazada<Asistencia> Asistencias) {
        this.Asistencias = Asistencias;
    }

}
