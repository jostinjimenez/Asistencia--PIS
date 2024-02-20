package modulo_1.inicio_sesion.view.util;

import model.*;
import Controller.Academico.AsignaturaController;
import Controller.Login.CuentaController;
import Controller.Administrativo.RolController;
import Controller.Administrativo.PeriodoAcController;
import tda_listas.exceptions.VacioExceptions;

import javax.swing.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import Controller.Administrativo.CarreraController;

public class Utiles {

    public static void cargarAsignaturas(JComboBox cbxPeriodo) {
        AsignaturaController rc = new AsignaturaController();
        cbxPeriodo.removeAllItems();

        try {
            if (rc.getAsignaturas().getSize() > 0) {
                for (int i = 0; i < rc.getAsignaturas().getSize(); i++) {
                    cbxPeriodo.addItem(rc.getAsignaturas().get(i));
                }
            } else {
                cbxPeriodo.addItem("No hay asignaturas");
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static Asignatura getComboAsignatura(JComboBox cbx) {
        return (Asignatura) cbx.getSelectedItem();
    }

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

    public static void cargarPeriodo(JComboBox cbxPeriodo) {
        PeriodoAcController rc = new PeriodoAcController();
        cbxPeriodo.removeAllItems();

        try {
            if (rc.getPeriodoAcademicos().getSize() > 0) {
                for (int i = 0; i < rc.getPeriodoAcademicos().getSize(); i++) {
                    cbxPeriodo.addItem(rc.getPeriodoAcademicos().get(i));
                }
            } else {
                cbxPeriodo.addItem("No hay periodos academicos");
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static PeriodoAcademico getComboPeriodo(JComboBox cbx) {
        return (PeriodoAcademico) cbx.getSelectedItem();
    }

    public static void cargarCarrera(JComboBox cbxPeriodo) {
        CarreraController rc = new CarreraController();
        cbxPeriodo.removeAllItems();

        try {
            if (rc.getCarreras().getSize() > 0) {
                for (int i = 0; i < rc.getCarreras().getSize(); i++) {
                    cbxPeriodo.addItem(rc.getCarreras().get(i));
                }
            } else {
                cbxPeriodo.addItem("No hay carreras");
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static Carrera getComboCarrera(JComboBox cbx) {
        return (Carrera) cbx.getSelectedItem();
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
        if (cuentaUsu == null) {
            cuentaUsu = new Cuenta();
        }
        return cuentaUsu;
    }

    public static void setCuentaUsu(Cuenta cuentaUsu) {
        Utiles.cuentaUsu = cuentaUsu;
    }

    public static CuentaController getCc() {
        if (cc == null) {
            cc = new CuentaController();
        }
        return cc;
    }

    public static void setCc(CuentaController cc) {
        Utiles.cc = cc;
    }

    PeriodoAcController pac = new PeriodoAcController();

    public PeriodoAcController getPac() {
        return pac;
    }

    public void setPac(PeriodoAcController pac) {
        this.pac = pac;
    }

    public static void copiarArchivo(File origen, File destino) throws Exception {
        Files.copy(origen.toPath(),
                (destino).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
    }

    public static String extension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }
}
