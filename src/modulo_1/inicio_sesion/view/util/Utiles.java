package modulo_1.inicio_sesion.view.util;

import model.Rol;
import modulo_1.inicio_sesion.controller.CuentaController;
import modulo_1.inicio_sesion.controller.RolController;
import tda_listas.exceptions.VacioExceptions;

import javax.swing.*;
import model.Cuenta;

public class Utiles {

    public static void cargaRol(JComboBox cbxRol) {
        RolController rc = new RolController();
        cbxRol.removeAllItems();

        cbxRol.addItem(new Rol(0, "Todos", "Todos los roles"));

        try {
            for (int i = 0; i < rc.getRoles().getSize(); i++) {
                cbxRol.addItem(rc.getRoles().get(i));
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static void cargaRoll(JComboBox cbxRol) {
        RolController rc = new RolController();
        cbxRol.removeAllItems();
        try {
            for (int i = 0; i < rc.getRoles().getSize(); i++) {
                cbxRol.addItem(rc.getRoles().get(i));
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static Rol getComboRol(JComboBox cbx) {
        return (Rol) cbx.getSelectedItem();
    }

    public static boolean validarDni(String cedula) {
        if (cedula.length() != 10) {
            return false;
        }

        for (int i = 0; i < cedula.length(); i++) {
            if (!Character.isDigit(cedula.charAt(i))) {
                return false;
            }
        }

        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            if (i % 2 == 0) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }
            suma += digito;
        }

        int modulo = suma % 10;
        int digitoVerificador = (modulo == 0) ? 0 : 10 - modulo;

        return digitoVerificador == Character.getNumericValue(cedula.charAt(9));
    }

    private static CuentaController cc;
    private static Cuenta cuentaUsu;

    public static Cuenta getCuentaUsu() {
        if (cuentaUsu == null)
            cuentaUsu = new Cuenta();
        return cuentaUsu;
    }

    public static void setCuentaUsu(Cuenta cuentaUsu) {
        Utiles.cuentaUsu = cuentaUsu;
    }

    public static CuentaController getCc() {
        if (cc == null)
            cc = new CuentaController();
        return cc;
    }

    public static void setCc(CuentaController cc) {
        Utiles.cc = cc;
    }
    
    public void GenerarCorreoInstitucional(String nombre, String apellido) {
        String correo = nombre.toLowerCase() + "." + apellido.toLowerCase() + "@unl.edu.ec";
    }
   
}

