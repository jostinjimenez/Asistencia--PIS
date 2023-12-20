package modulo_1.usuario_rol.view.tablas;

import model.Persona;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaPersona extends AbstractTableModel {

    private ListaEnlazada<Persona> personas;

    @Override
    public int getRowCount() {
        return personas.getSize();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    public ListaEnlazada<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ListaEnlazada<Persona> personas) {
        this.personas = personas;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona persona = null;
        try {
            persona = personas.get(rowIndex);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
        return switch (columnIndex) {
            case 0 -> (persona != null) ? persona.getId() : "";
            case 1 -> (persona != null) ? persona.getNombre() : "";
            case 2 -> (persona != null) ? persona.getApellido() : "";
            case 3 -> (persona != null) ? persona.getTelefono() : "";
            case 4 -> (persona != null) ? persona.getDni() : "";
            case 5 -> (persona != null) ? persona.getIdRol() : "";
            default -> null;
        };
    }

    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "ID";
            case 1 -> "Nombre";
            case 2 -> "Apellido";
            case 3 -> "Telefono";
            case 4 -> "DNI";
            case 5 -> "ID Rol";
            default -> null;
        };
    }
}

