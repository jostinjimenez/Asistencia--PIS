package modelLuis.tablas;

import javax.swing.table.AbstractTableModel;

import ModuloEstudianteDocente.controlador.EstudianteController;
import model.Estudiante;
import model.Matricula;
import modelLuis.controller.ControllerEstudiante;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class ModelTableMatriculas extends AbstractTableModel {

    private ListaEnlazada<Matricula> matriculas;
    private EstudianteController aa = new EstudianteController();

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
            matricula.getId();
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
        switch (col) {
            case 0:
                return (matricula != null) ? matricula.getId() : "";
            case 1:
                return (matricula != null) ? matricula.getCarrera() : "";
            case 2:
                return (matricula != null) ? matricula.getFechaMatricula() : "";
            case 3:
                return (matricula != null) ? matricula.getCiclo() : "";
            case 4:
                try {
                Estudiante estudiante = aa.busquedaBinaria2(aa.list_All(), matricula.getEstudiante_id().toString(), "id", 0);
                return (estudiante != null) ? estudiante.getNombre() + " " + estudiante.getApellido() : "";
            } catch (Exception e) {
            }
            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Carrera";
            case 2:
                return "Fecha Matriculacion";
            case 3:
                return "Ciclo";
            case 4:
                return "Nombre";
            default:
                return null;
        }
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

}
