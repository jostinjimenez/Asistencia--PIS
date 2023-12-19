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
}

