package modulo_1.inicio_sesion.controller;

import DAO.DataAccessObject;
import model.Cuenta;
import model.Persona;
import model.Rol;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import javax.swing.*;

public class CuentaController extends DataAccessObject<Cuenta> {
    // Atributos
    private ListaEnlazada<Cuenta> cuentas;
    private Cuenta cuenta = new Cuenta();
    private Integer index = -1;

    // Constructor
    public CuentaController() {
        super(Cuenta.class);
        this.cuentas = new ListaEnlazada<>();
    }

    // Getters y Setters
    public ListaEnlazada<Cuenta> getCuentas() {
        if (cuentas.isEmpty()) {
            cuentas = this.list_All();
        }
        return cuentas;
    }

    public void setCuentas(ListaEnlazada<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    // Metodos
    public Boolean save() {
        this.cuenta.setId(generarID());
        return save(cuenta);
    }

    public Boolean update(Integer index) {
        return update(cuenta, index);
    }

    public Cuenta validarCuenta(String usuario, String clave) {
        Cuenta cuenta = null;

        for (Cuenta c : this.getCuentas()) {
            if (c.getCorreo().equalsIgnoreCase(usuario)) {
                if (c.getClave().equalsIgnoreCase(clave)) {
                    cuenta = c;
                    return cuenta;
                } else {
                    JOptionPane.showMessageDialog(null, "Clave incorrecta");
                    return null;
                }
            }
        }

        if (cuenta == null) {
            JOptionPane.showMessageDialog(null, "Usuario incorrecto");
        }

        return cuenta;
    }

    public Integer identificarRolPersona(Persona p) {
        Integer idRol = null;
        for (Cuenta cuenta : getCuentas()) {
            if (cuenta.getIdPersona().equals(p.getId())) {
                idRol = getPersona(cuenta.getIdPersona()).getIdRol().intValue();
                break;
            }
        }
        return idRol;
    }

    public Persona getPersona(Integer idPersona) {
        Persona persona = null;
        for (Cuenta cuenta : getCuentas()) {
            if (cuenta.getIdPersona().equals(idPersona)) {
                persona = new PersonaController().getPersonaID(idPersona);
                break;
            }
        }
        return persona;
    }

}

