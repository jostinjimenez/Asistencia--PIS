package modulo_1.inicio_sesion.controller;

import DAO.DataAccessObject;

import java.io.FileOutputStream;

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
        ListaEnlazada<Cuenta> cuentasActivas = new ListaEnlazada<>();
        for (Cuenta cuenta : cuentas) {
            if (cuenta.isEstado()) {
                cuentasActivas.add(cuenta);
            }
        }
        return cuentasActivas;
    }

    public Cuenta getCuentaID(Integer id) {
        for (int i = 0; i < cuentas.getSize(); i++) {
            Cuenta cuenta = null;
            try {
                cuenta = cuentas.get(i);
            } catch (VacioExceptions e) {
                throw new RuntimeException(e);
            }
            if (cuenta.getId().equals(id)) {
                return cuenta;
            }
        }
        return null;
    }

    public void setCuentas(ListaEnlazada<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public Cuenta getCuenta() {
        return cuenta != null ? cuenta : new Cuenta();
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
        JOptionPane.showMessageDialog(null, "Usuario incorrecto");
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

    public Boolean delete(Integer idCuenta) {
        for (int i = 0; i < cuentas.getSize(); i++) {
            Cuenta cuenta = null;
            try {
                cuenta = cuentas.get(i);
            } catch (VacioExceptions e) {
                throw new RuntimeException(e);
            }
            if (cuenta.getId().equals(idCuenta)) {
                try {
                    cuenta.setEstado(false);
                    this.xStream.toXML(cuentas, new FileOutputStream(URL));
                    return true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        }
        return false;
    }

//    public Rol getRol(Cuenta cuenta) {
//
//    }
}














