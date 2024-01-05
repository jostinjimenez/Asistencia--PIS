package view.tablas;

import java.util.Comparator;
import javax.swing.table.AbstractTableModel;
import model.Asignatura;
import controller.AsignaturaController;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class ModeloTablaAsignaturas extends AbstractTableModel {

    private AsignaturaController controller;

    public ModeloTablaAsignaturas() {
        controller = new AsignaturaController();
    }

    @Override
    public int getRowCount() {
        return controller.getLista().getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Asignatura asignatura = null;
        try {
            asignatura = controller.getLista().get(row);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }

        switch (col) {
            case 0:
                return (asignatura != null) ? asignatura.getId() : "";
            case 1:
                return (asignatura != null) ? asignatura.getNombre() : "";
            case 2:
                return (asignatura != null) ? asignatura.getCodigo() : "";
            case 3:
                return (asignatura != null) ? asignatura.getHorasTotales() : "";
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
                return "Nombre";
            case 2:
                return "Código";
            case 3:
                return "Horas Totales";
            default:
                return null;
        }
    }

    public Asignatura buscar(String criterioBusqueda, Comparator<Asignatura> comparador, String criterio) {
        ListaEnlazada<Asignatura> lista = getLista();

        for (int i = 0; i < lista.getSize(); i++) {
            try {
                Asignatura actual = lista.get(i);

                // Aquí aplicamos la comparación con el criterio deseado
                if ("nombre".equalsIgnoreCase(criterio) && comparador.compare(actual, new Asignatura(-1, criterioBusqueda, -1, -1)) == 0) {
                    return actual;
                } else if ("codigo".equalsIgnoreCase(criterio) && comparador.compare(actual, new Asignatura(-1, "", Integer.parseInt(criterioBusqueda), -1)) == 0) {
                    return actual;
                }
            } catch (VacioExceptions e) {
                throw new RuntimeException(e);
            }
        }

        return null; // Devuelve null si no se encuentra la asignatura
    }

    public boolean esCampoValido(String campo) {
        return campo.equalsIgnoreCase("nombre") || campo.equalsIgnoreCase("codigo");
    }

    public void quicksort(String campoOrden, String tipoOrden) throws VacioExceptions {
        Comparator<Asignatura> comparador = (campoOrden.equalsIgnoreCase("nombre"))
                ? Comparator.comparing(Asignatura::getNombre)
                : Comparator.comparing(asignatura -> asignatura.getCodigo().toString());

        if (tipoOrden.equalsIgnoreCase("ascendente")) {
            controller.quicksort(controller.getLista(), campoOrden, comparador);
        } else if (tipoOrden.equalsIgnoreCase("descendente")) {
            comparador = comparador.reversed();
            controller.quicksort(controller.getLista(), campoOrden, comparador);
        }

        fireTableDataChanged();
    }

    public void setLista(ListaEnlazada<Asignatura> lista) {
        controller.setLista(lista);
    }

    public Asignatura getAsignaturaAt(int index) {
        try {
            return controller.getLista().get(index);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
    }

    public ListaEnlazada<Asignatura> getLista() {
        return controller.getLista();
    }

    public void ordenar(String campoOrden, String tipoOrden) {
        // Obtén la lista actual de asignaturas
        ListaEnlazada<Asignatura> lista = getLista();

        // Asegúrate de que la lista no sea nula
        if (lista != null && !lista.isEmpty()) {
            // Crea un comparador según el campo y tipo de orden
            Comparator<Asignatura> comparador = (campoOrden.equalsIgnoreCase("nombre"))
                    ? Comparator.comparing(Asignatura::getNombre)
                    : Comparator.comparing(asignatura -> asignatura.getCodigo().toString());

            // Obtén el array de asignaturas
            Asignatura[] arrayAsignaturas = lista.toArray();

            // Aplica el algoritmo de ordenación
            for (int i = 0; i < arrayAsignaturas.length - 1; i++) {
                for (int j = 0; j < arrayAsignaturas.length - 1 - i; j++) {
                    if (comparador.compare(arrayAsignaturas[j], arrayAsignaturas[j + 1]) > 0) {
                        // Intercambia los elementos si están en el orden incorrecto
                        Asignatura temp = arrayAsignaturas[j];
                        arrayAsignaturas[j] = arrayAsignaturas[j + 1];
                        arrayAsignaturas[j + 1] = temp;
                    }
                }
            }

            // Actualiza la lista con el array ordenado
            lista.toList(arrayAsignaturas);

            // Notifica a la tabla que los datos han cambiado
            fireTableDataChanged();
        }
    }
}
