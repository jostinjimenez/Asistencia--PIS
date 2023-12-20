package modulo_1.usuario_rol.view.util;

import model.Rol;
import modulo_1.usuario_rol.controller.RolController;
import tda_listas.exceptions.VacioExceptions;

import javax.swing.*;

public class Utiles {

    public static void cargaRol(JComboBox cbxRol) {
        RolController rc = new RolController();
        cbxRol.removeAllItems();
        try {
            for (int i = 0; i < rc.getRoles().getSize(); i++) {
                cbxRol.addItem(rc.getRoles().get(i).getNombre());
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static Rol getComboMarca(JComboBox cbx) {
        return (Rol) cbx.getSelectedItem();
    }

    public static boolean validadorDeCedula(String cedula) {
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
}

