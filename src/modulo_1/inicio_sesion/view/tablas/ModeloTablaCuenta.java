package modulo_1.inicio_sesion.view.tablas;

import ModuloEstudianteDocente.controlador.ControladorEstudiante;
import model.Persona;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import javax.swing.table.AbstractTableModel;
import model.Cuenta;
import model.Estudiante;
import modulo_1.inicio_sesion.controller.CuentaController;

public class ModeloTablaCuenta extends AbstractTableModel {

    private ListaEnlazada<Cuenta> cuentas;

    @Override
    public int getRowCount() {
        return cuentas.getSize();
    }

    @Override
    public int getColumnCount() {
        return 4 ;
    }

    public ListaEnlazada<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ListaEnlazada<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cuenta cuenta = null;
        try {
            cuenta = cuentas.get(rowIndex);            
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
        return switch (columnIndex) {
            case 0 -> (cuenta != null) ? cuenta.getId() : "";
            case 1 -> (cuenta != null) ? cuenta.getCorreo(): "";
            case 2 -> (cuenta != null) ? cuenta.getClave(): "";
            case 3 -> (cuenta != null) ? cuenta.isEstado(): "";
            //case 4 -> (cuenta != null) ? estudiante.getIdRol(): "";
            default -> null;
        };
    }

    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "ID";
            case 1 -> "Usuario";
            case 2 -> "Clave";
            case 3 -> "Estado";
            //case 4 -> "Rol";
            default -> null;
        };
    }
}

