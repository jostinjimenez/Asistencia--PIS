package view.tablas;

import javax.swing.table.AbstractTableModel;
import model.Asignatura;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import java.util.Comparator;

public class ModeloTablaAsignaturas extends AbstractTableModel {

    private ListaEnlazada<Asignatura> asignaturas;

    @Override
    public int getRowCount() {
        return asignaturas.getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Asignatura asignatura = null;
        try {
            asignatura = asignaturas.get(row);
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

    public ListaEnlazada<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ListaEnlazada<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public void sortAscendente(Comparator<Asignatura> comparator) throws VacioExceptions {
        asignaturas.quicksort(comparator);
    }

    public void sortDescendente(Comparator<Asignatura> comparator) throws VacioExceptions {
        asignaturas.quicksortDescendente(comparator);
    }

    public int buscar(String criterioBusqueda, Comparator<Asignatura> comparador, String criterio) {
        // Modifica el método buscar para que maneje la búsqueda por nombre o código
        int resultado = asignaturas.buscar(criterioBusqueda, comparador, criterio);
        return resultado;
    }

    public boolean esCampoValido(String campo) {
        // Método para verificar si el campo de búsqueda es válido (nombre o código)
        return campo.equalsIgnoreCase("nombre") || campo.equalsIgnoreCase("codigo");
    }

    public void ordenar(String campoOrden, String tipoOrden) throws VacioExceptions {
        // Modifica el método ordenar para que acepte el campo de orden y el tipo de orden
        Comparator<Asignatura> comparador = (campoOrden.equalsIgnoreCase("nombre"))
                ? Comparator.comparing(Asignatura::getNombre)
                : Comparator.comparing(Asignatura::getCodigo);

        if (tipoOrden.equalsIgnoreCase("ascendente")) {
            asignaturas.quicksort(comparador);
        } else if (tipoOrden.equalsIgnoreCase("descendente")) {
            asignaturas.quicksortDescendente(comparador);
        }

        fireTableDataChanged(); // Notifica a la tabla que los datos han cambiado
    }
}
