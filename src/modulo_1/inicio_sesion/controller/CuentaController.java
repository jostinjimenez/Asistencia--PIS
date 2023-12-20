package modulo_1.inicio_sesion.controller;

import DAO.DataAccessObject;
import model.Cuenta;
import model.Persona;
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

//    public boolean iniciarSesion(String correoInstitucional, String clave) {
//        System.out.println("Correo: " + correoInstitucional);
//        System.out.println("Clave: " + clave);
//
//        for (Cuenta cuenta : this.getCuentas()) {
//            if (cuenta.getCorreo().equalsIgnoreCase(correoInstitucional)) {
//                if (cuenta.getClave().equalsIgnoreCase(clave)) {
//                    return true;
//                }else {
//                    JOptionPane.showMessageDialog(null, "Clave incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
//                    return false;
//                }
//            }else {
//                JOptionPane.showMessageDialog(null, "Correo incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
//                return false;
//            }
//        }
//        return false;
//    }


    // Busqueda binaria


}

