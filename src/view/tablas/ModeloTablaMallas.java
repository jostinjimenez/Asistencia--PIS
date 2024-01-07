package view.tablas;

import controller.MallaController;
import java.util.Comparator;
import javax.swing.table.AbstractTableModel;
import model.Malla;
import tda_listas.exceptions.VacioExceptions;

public class ModeloTablaMallas extends AbstractTableModel {

    private MallaController mallaController;

    @Override
    public int getColumnCount() {
        return 4; // Se ha eliminado la columna correspondiente al atributo 'silabo'
    }

    @Override
    public int getRowCount() {
        return (mallaController != null && mallaController.getLista() != null) ? mallaController.getLista().getSize() : 0;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Malla malla;
        try {
            malla = mallaController.getLista().get(row);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }

        switch (col) {
            case 0:
                return (malla != null) ? malla.getId() : "";
            case 1:
                return (malla != null) ? malla.getDescripcion() : "";
            case 2:
                return (malla != null) ? malla.getDuracion() : "";
            case 3:
                return (malla != null) ? malla.getNombreSilabo() : ""; // Atributo 'nombreSilabo' en lugar de 'silabo'
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
                return "Descripción";
            case 2:
                return "Duración";
            case 3:
                return "Nombre del Silabo";
            default:
                return null;
        }
    }

    public MallaController getMallaController() {
        return mallaController;
    }

    public void setMallaController(MallaController mallaController) {
        this.mallaController = mallaController;
    }

    public int buscar(String criterioBusqueda, Comparator<Malla> comparador, String criterio) {
        int resultado = mallaController.buscar(criterioBusqueda, comparador, criterio);
        return resultado;
    }

    public boolean esCampoValido(String campo) {
        // Método para verificar si el campo de búsqueda es válido (duracion o nombreSilabo)
        boolean esValido = campo.equalsIgnoreCase("duracion") || campo.equalsIgnoreCase("nombreSilabo");
        return esValido;
    }

    public void ordenar(String campo, String tipoOrden) {
        System.out.println("Antes de ordenar: " + mallaController.getLista().print());

        Comparator<Malla> comparador = obtenerComparador(campo);

        if (tipoOrden.equals("descendente")) {
            comparador = comparador.reversed();
        }

        System.out.println("Después de ordenar: " + mallaController.getLista().print());

        fireTableDataChanged();
    }

    private Comparator<Malla> obtenerComparador(String campoOrden) {
        switch (campoOrden) {
            case "duracion":
                return Comparator.comparing(Malla::getDuracion);
            case "nombreSilabo":
                return Comparator.comparing(Malla::getNombreSilabo);
            // Puedes agregar más casos según tus necesidades
            default:
                return null;
        }
    }
}
