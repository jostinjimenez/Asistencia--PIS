package moduloAsignaturas.view.tablas;

import moduloAsignaturas.controller.AsignaturaController;
import java.util.Comparator;
import javax.swing.table.AbstractTableModel;
import model.Asignatura;
import tda_listas.exceptions.VacioExceptions;

public class ModeloTablaAsignaturas extends AbstractTableModel {

    private AsignaturaController asignaturaController;

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return (asignaturaController != null && asignaturaController.getAsignaturas() != null) ? asignaturaController.getAsignaturas().getSize() : 0;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Asignatura asignatura;
        try {
            asignatura = asignaturaController.getAsignaturas().get(row);
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

    public AsignaturaController getAsignaturaController() {
        return asignaturaController;
    }

    public void setAsignaturaController(AsignaturaController asignaturaController) {
        this.asignaturaController = asignaturaController;
    }

    public int buscar(String criterioBusqueda, Comparator<Asignatura> comparador, String criterio) {
        int resultado = asignaturaController.buscar(criterioBusqueda, comparador, criterio);
        return resultado;
    }

    public boolean esCampoValido(String campo) {
        // Método para verificar si el campo de búsqueda es válido (nombre o código)
        boolean esValido = campo.equalsIgnoreCase("nombre") || campo.equalsIgnoreCase("codigo");
        return esValido;
    }

    public void ordenar(String campo, String tipoOrden) {
        System.out.println("Antes de ordenar: " + asignaturaController.getAsignaturas().print());

        Comparator<Asignatura> comparador = (campo.equals("nombre"))
                ? Comparator.comparing(Asignatura::getNombre)
                : Comparator.comparing(Asignatura::getCodigo);

        if (tipoOrden.equals("descendente")) {
            comparador = comparador.reversed();
        }

        System.out.println("Después de ordenar: " + asignaturaController.getAsignaturas().print());

        fireTableDataChanged();
    }

    private Comparator<Asignatura> obtenerComparador(String campoOrden) {
        switch (campoOrden) {
            case "nombre":
                return Comparator.comparing(Asignatura::getNombre);
            case "codigo":
                return Comparator.comparing(Asignatura::getCodigo);
            // Puedes agregar más casos según tus necesidades
            default:
                return null;
        }
    }
}
