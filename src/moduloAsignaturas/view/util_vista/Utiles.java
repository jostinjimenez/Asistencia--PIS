package moduloAsignaturas.view.util_vista;

import Modelo.ControllerCarrera.ControllerCarrera;
import model.Carrera;
import model.Cuenta;
import model.PeriodoAcademico;
import model.Rol;
import modulo_1.inicio_sesion.controller.CuentaController;
import modulo_1.inicio_sesion.controller.RolController;
import modulo_1.periodo_academico.controller.PeriodoAcController;
import tda_listas.exceptions.VacioExceptions;

import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Utiles {

    public static void cargarCarrera(JComboBox cbxCarrera) {
        ControllerCarrera rc = new ControllerCarrera();
        cbxCarrera.removeAllItems();

        try {
            if (rc.getLista().getSize() > 0) {
                for (int i = 0; i < rc.getLista().getSize(); i++) {
                    cbxCarrera.addItem(rc.getLista().get(i));
                }
            } else {
                cbxCarrera.addItem("No hay carreras");
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static Carrera getComboCarrera(JComboBox cbx) {
        return (Carrera) cbx.getSelectedItem();
    }

}

