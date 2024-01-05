package view.tablas;

import java.util.Comparator;
import javax.swing.table.AbstractTableModel;
import controller.MallaController;
import model.Malla;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class ModeloTablaMallas extends AbstractTableModel {

    private MallaController controller;

    public ModeloTablaMallas() {
        controller = new MallaController();
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
        Malla malla = null;
        try {
            malla = controller.getLista().get(row);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }

        switch (col) {
            case 0:
                return (malla != null) ? malla.getId() : "";
            case 1:
                return (malla != null) ? malla.getDuracion() : "";
            case 2:
                return (malla != null) ? malla.getDescripcion() : "";
            case 3:
                return (malla != null) ? malla.getNombreSilabo() : "";
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
                return "Duración";
            case 2:
                return "Descripción";
            case 3:
                return "Nombre del Silabo";
            default:
                return null;
        }
    }

    public Malla buscar(String criterioBusqueda, Comparator<Malla> comparador, String criterio) {
        ListaEnlazada<Malla> lista = getLista();

        for (int i = 0; i < lista.getSize(); i++) {
            try {
                Malla actual = lista.get(i);

                if ("duracion".equalsIgnoreCase(criterio) && comparador.compare(actual, new Malla(-1, criterioBusqueda, "", "", null)) == 0) {
                    return actual;
                } else if ("descripcion".equalsIgnoreCase(criterio) && comparador.compare(actual, new Malla(-1, "", criterioBusqueda, "", null)) == 0) {
                    return actual;
                } else if ("nombre del silabo".equalsIgnoreCase(criterio) && comparador.compare(actual, new Malla(-1, "", "", criterioBusqueda, null)) == 0) {
                    return actual;
                }
            } catch (VacioExceptions e) {
                throw new RuntimeException(e);
            }
        }

        return null; // Devuelve null si no se encuentra la malla
    }

    public boolean esCampoValido(String campo) {
        return campo.equalsIgnoreCase("duracion") || campo.equalsIgnoreCase("descripcion") || campo.equalsIgnoreCase("nombre del silabo");
    }

    public void quicksort(String campoOrden, String tipoOrden) throws VacioExceptions {
        Comparator<Malla> comparador = (campoOrden.equalsIgnoreCase("duracion"))
                ? Comparator.comparing(Malla::getDuracion)
                : (campoOrden.equalsIgnoreCase("descripcion"))
                ? Comparator.comparing(Malla::getDescripcion)
                : Comparator.comparing(Malla::getNombreSilabo);

        if (tipoOrden.equalsIgnoreCase("ascendente")) {
            controller.quicksort(controller.getLista(), campoOrden, comparador);
        } else if (tipoOrden.equalsIgnoreCase("descendente")) {
            comparador = comparador.reversed();
            controller.quicksort(controller.getLista(), campoOrden, comparador);
        }

        fireTableDataChanged();
    }

    public void setLista(ListaEnlazada<Malla> lista) {
        controller.setLista(lista);
    }

    public Malla getMallaAt(int index) {
        try {
            return controller.getLista().get(index);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
    }

    public ListaEnlazada<Malla> getLista() {
        return controller.getLista();
    }

    public void ordenar(String campoOrden, String tipoOrden) {
        // Obtén la lista actual de mallas
        ListaEnlazada<Malla> lista = getLista();

        // Asegúrate de que la lista no sea nula
        if (lista != null && !lista.isEmpty()) {
            // Crea un comparador según el campo y tipo de orden
            Comparator<Malla> comparador = (campoOrden.equalsIgnoreCase("duracion"))
                    ? Comparator.comparing(Malla::getDuracion)
                    : (campoOrden.equalsIgnoreCase("descripcion"))
                    ? Comparator.comparing(Malla::getDescripcion)
                    : Comparator.comparing(Malla::getNombreSilabo);

            // Obtén el array de mallas
            Malla[] arrayMallas = lista.toArray();

            // Aplica el algoritmo de ordenación
            for (int i = 0; i < arrayMallas.length - 1; i++) {
                for (int j = 0; j < arrayMallas.length - 1 - i; j++) {
                    if (comparador.compare(arrayMallas[j], arrayMallas[j + 1]) > 0) {
                        // Intercambia los elementos si están en el orden incorrecto
                        Malla temp = arrayMallas[j];
                        arrayMallas[j] = arrayMallas[j + 1];
                        arrayMallas[j + 1] = temp;
                    }
                }
            }

            // Actualiza la lista con el array ordenado
            lista.toList(arrayMallas);

            // Notifica a la tabla que los datos han cambiado
            fireTableDataChanged();
        }
    }
}
