package view.tablas;

import java.util.Comparator;
import javax.swing.table.AbstractTableModel;
import model.Curso;
import controller.CursoController;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class ModeloTablaCursos extends AbstractTableModel {

    private CursoController controller;

    public ModeloTablaCursos() {
        controller = new CursoController();
    }

    @Override
    public int getRowCount() {
        return controller.getLista().getSize();
    }

    @Override
    public int getColumnCount() {
        return 4; // Asegúrate de ajustar el número de columnas según tu necesidad
    }

    @Override
    public Object getValueAt(int row, int col) {
        Curso curso = null;
        try {
            curso = controller.getLista().get(row);
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
                return "Nro. Estudiante";
            case 3:
                return "Nro. Aula";
            default:
                return null;
        }
    }

    public Curso buscar(String criterioBusqueda, Comparator<Curso> comparador, String criterio) {
        ListaEnlazada<Curso> lista = getLista();

        for (int i = 0; i < lista.getSize(); i++) {
            try {
                Curso actual = lista.get(i);

                if ("codigo curso".equalsIgnoreCase(criterio) && comparador.compare(actual, new Curso(-1, -1, criterioBusqueda, null, -1)) == 0) {
                    return actual;
                } else if ("nro. estudiante".equalsIgnoreCase(criterio) && comparador.compare(actual, new Curso(-1, Integer.parseInt(criterioBusqueda), "", null, -1)) == 0) {
                    return actual;
                } else if ("nro. aula".equalsIgnoreCase(criterio) && comparador.compare(actual, new Curso(-1, -1, "", criterioBusqueda, null, -1)) == 0) {
                    return actual;
                }
            } catch (VacioExceptions e) {
                throw new RuntimeException(e);
            }
        }

        return null; // Devuelve null si no se encuentra el curso
    }

    public boolean esCampoValido(String campo) {
        return campo.equalsIgnoreCase("codigo curso") || campo.equalsIgnoreCase("nro. estudiante") || campo.equalsIgnoreCase("nro. aula");
    }

    public void quicksort(String campoOrden, String tipoOrden) throws VacioExceptions {
        Comparator<Curso> comparador = (campoOrden.equalsIgnoreCase("codigo curso"))
                ? Comparator.comparing(Curso::getCodCurso)
                : (campoOrden.equalsIgnoreCase("nro. estudiante"))
                ? Comparator.comparing(Curso::getNroEstudiante)
                : Comparator.comparing(Curso::getNroAula);

        if (tipoOrden.equalsIgnoreCase("ascendente")) {
            controller.quicksort(controller.getLista(), campoOrden, comparador);
        } else if (tipoOrden.equalsIgnoreCase("descendente")) {
            comparador = comparador.reversed();
            controller.quicksort(controller.getLista(), campoOrden, comparador);
        }

        fireTableDataChanged();
    }

    public void setLista(ListaEnlazada<Curso> lista) {
        controller.setLista(lista);
    }

    public Curso getCursoAt(int index) {
        try {
            return controller.getLista().get(index);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
    }

    public ListaEnlazada<Curso> getLista() {
        return controller.getLista();
    }

    public void ordenar(String campoOrden, String tipoOrden) {
        // Obtén la lista actual de cursos
        ListaEnlazada<Curso> lista = getLista();

        // Asegúrate de que la lista no sea nula
        if (lista != null && !lista.isEmpty()) {
            // Crea un comparador según el campo y tipo de orden
            Comparator<Curso> comparador = (campoOrden.equalsIgnoreCase("codigo curso"))
                    ? Comparator.comparing(Curso::getCodCurso)
                    : (campoOrden.equalsIgnoreCase("nro. estudiante"))
                    ? Comparator.comparing(Curso::getNroEstudiante)
                    : Comparator.comparing(Curso::getNroAula);

            // Obtén el array de cursos
            Curso[] arrayCursos = lista.toArray();

            // Aplica el algoritmo de ordenación
            for (int i = 0; i < arrayCursos.length - 1; i++) {
                for (int j = 0; j < arrayCursos.length - 1 - i; j++) {
                    if (comparador.compare(arrayCursos[j], arrayCursos[j + 1]) > 0) {
                        // Intercambia los elementos si están en el orden incorrecto
                        Curso temp = arrayCursos[j];
                        arrayCursos[j] = arrayCursos[j + 1];
                        arrayCursos[j + 1] = temp;
                    }
                }
            }

            // Actualiza la lista con el array ordenado
            lista.toList(arrayCursos);

            // Notifica a la tabla que los datos han cambiado
            fireTableDataChanged();
        }
    }
}
