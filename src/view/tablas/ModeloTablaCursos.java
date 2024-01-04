package view.tablas;

import javax.swing.table.AbstractTableModel;
import model.Curso;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class ModeloTablaCursos extends AbstractTableModel {

    private ListaEnlazada<Curso> cursos;

    @Override
    public int getRowCount() {
        return cursos.getSize();
    }

    @Override
    public int getColumnCount() {
        return 4; // Ahora incluimos el campo "ID"
    }

    @Override
    public Object getValueAt(int row, int col) {
        Curso curso = null;
        try {
            curso = cursos.get(row);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }

        switch (col) {
            case 0:
                return (curso != null) ? curso.getId() : "";
            case 1:
                return (curso != null) ? curso.getNroEstudiante() : "";
            case 2:
                return (curso != null) ? curso.getCodCurso() : "";
            case 3:
                return (curso != null) ? curso.getNroAula() : "";
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID"; 
            case 1:
                return "Nro. Estudiante";
            case 2:
                return "Código de Curso";
            case 3:
                return "Nro. de Aula";
            default:
                return null;
        }
    }

    /**
     * @return the cursos
     */
    public ListaEnlazada<Curso> getCursos() {
        return cursos;
    }

    /**
     * @param cursos the cursos to set
     */
    public void setCursos(ListaEnlazada<Curso> cursos) {
        this.cursos = cursos;
    }
}