package modulo_1.inicio_sesion.view.tablas;

import model.Persona;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import javax.swing.table.AbstractTableModel;

import model.Cuenta;
import modulo_1.inicio_sesion.controller.CuentaController;

public class ModeloTablaCuenta extends AbstractTableModel {

    private ListaEnlazada<Cuenta> cuentas;

    @Override
    public int getRowCount() {
        return cuentas.getSize();
    }

    @Override
    public int getColumnCount() {
        return 5;
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
        CuentaController cc = new CuentaController();
        try {
            cuenta = cuentas.get(rowIndex);
            Persona p = cc.getPersona(cuenta.getIdPersona());
            return switch (columnIndex) {
                case 0 -> cuenta.getId();
                case 1 -> cuenta.getCorreo();
                case 2 -> p.getNombre() + " " + p.getApellido();
                case 3 -> (p.isActivo()) ? "Activo" : "Inactivo";
                case 4 -> {
                    yield switch (p.getIdRol()) {
                        case 1 -> "Administrador";
                        case 2 -> "Estudiante";
                        case 3 -> "Docente";
                        default -> "";
                    };
                }
                default -> null;
            };
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
    }

    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Nro";
            case 1 -> "Correo Electronico";
            case 2 -> "Usuario";
            case 3 -> "Estado";
            case 4 -> "Rol";
            default -> null;
        };
    }
}

