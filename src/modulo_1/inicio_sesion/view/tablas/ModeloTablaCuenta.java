package modulo_1.inicio_sesion.view.tablas;

import model.Persona;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import javax.swing.table.AbstractTableModel;

import model.Cuenta;
import Controller.Login.CuentaController;

import java.util.HashMap;
import java.util.Map;

public class ModeloTablaCuenta extends AbstractTableModel {

    private ListaEnlazada<Cuenta> cuentas;
    private Map<Integer, Persona> personas;

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
        CuentaController cc = new CuentaController();
        this.personas = new HashMap<>();
        for (Cuenta cuenta : cuentas) {
            try {
                Persona p = cc.getPersona(cuenta.getPersona_id());
                this.personas.put(cuenta.getPersona_id(), p);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cuenta cuenta = null;
        try {
            cuenta = cuentas.get(rowIndex);
            Persona p = this.personas.get(cuenta.getPersona_id());
            return switch (columnIndex) {
                case 0 -> cuenta.getCorreo_institucional();
                case 1 -> p.getNombre() + " " + p.getApellido();
                case 2 -> (p.getActivo()) ? "Activo" : "Inactivo";
                case 3 -> p.getDni();
                case 4 -> {
                    yield switch (p.getRol_id()) {
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
            case 0 -> "Correo Electronico";
            case 1 -> "Usuario";
            case 2 -> "Estado";
            case 3 -> "DNI";
            case 4 -> "Rol";

            default -> null;
        };
    }
}

