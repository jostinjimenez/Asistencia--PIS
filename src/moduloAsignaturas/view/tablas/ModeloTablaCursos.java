package moduloAsignaturas.view.tablas;

import moduloAsignaturas.controller.CursoController;
import java.util.Comparator;
import javax.swing.table.AbstractTableModel;
import model.Curso;
import tda_listas.exceptions.VacioExceptions;

public class ModeloTablaCursos extends AbstractTableModel {

    private CursoController cursoController;

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return (cursoController != null && cursoController.getLista() != null) ? cursoController.getLista().getSize() : 0;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Curso curso;
        try {
            curso = cursoController.getLista().get(row);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }

        switch (col) {
            case 0:
                return (curso != null) ? curso.getId() : "";
            case 1:
                return (curso != null) ? curso.getCodCurso() : "";
            case 2:
                return (curso != null) ? curso.getNroEstudiante() : "";
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
                return "Código Curso";
            case 2:
                return "Nro Estudiante";
            case 3:
                return "Nro Aula";
            default:
                return null;
        }
    }

    public CursoController getCursoController() {
        return cursoController;
    }

    public void setCursoController(CursoController cursoController) {
        this.cursoController = cursoController;
    }

    public int buscar(String criterioBusqueda, Comparator<Curso> comparador, String criterio) {
        int resultado = cursoController.buscar(criterioBusqueda, comparador, criterio);
        return resultado;
    }

    public boolean esCampoValido(String campo) {
        // Método para verificar si el campo de búsqueda es válido (codCurso, nroAula)
        boolean esValido = campo.equalsIgnoreCase("codCurso") || campo.equalsIgnoreCase("nroAula");
        return esValido;
    }

    public void ordenar(String campo, String tipoOrden) {
        System.out.println("Antes de ordenar: " + cursoController.getLista().print());

        Comparator<Curso> comparador = (campo.equals("codCurso"))
                ? Comparator.comparing(Curso::getCodCurso)
                : Comparator.comparing(Curso::getNroAula);

        if (tipoOrden.equals("descendente")) {
            comparador = comparador.reversed();
        }

        System.out.println("Después de ordenar: " + cursoController.getLista().print());

        fireTableDataChanged();
    }

    private Comparator<Curso> obtenerComparador(String campoOrden) {
        switch (campoOrden) {
            case "codCurso":
                return Comparator.comparing(Curso::getCodCurso);
            case "nroAula":
                return Comparator.comparing(Curso::getNroAula);
            // Puedes agregar más casos según tus necesidades
            default:
                return null;
        }
    }
}
