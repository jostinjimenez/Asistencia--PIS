/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloMatricula.Views.UtilVista;

/**
 *
 * @author juanc
 */
import javax.swing.JComboBox;
import model.Asignatura;
import model.Cursa;
import model.Docente;
import model.Estudiante;
import model.Horario;
import model.Matricula;
import model.PeriodoAcademico;
import modelLuis.controller.ControllerAsignatura;
import modelLuis.controller.ControllerCursa;
import modelLuis.controller.ControllerEstudiante;
import modelLuis.controller.ControllerHorario;
import modelLuis.controller.ControllerMatricula;
import modelLuis.controller.ControllerPeriodoAcademico;
import modelLuis.controller.DocenteController;
import tda_listas.exceptions.VacioExceptions;

public class Util_VistaLinked_Matricula {

    public static void cargaParalelos(JComboBox cbxmarca) throws VacioExceptions {
        ControllerCursa ac = new ControllerCursa();
        cbxmarca.removeAllItems();
        try {
            for (int i = 0; i < ac.getLista().getSize(); i++) {
                cbxmarca.addItem(ac.getLista().get(i));
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static Cursa getComboCursa(JComboBox cbx) {
        return (Cursa) cbx.getSelectedItem();
    }

    public static void cargaCiclos(JComboBox cbxmarca) throws VacioExceptions {
        ControllerMatricula ac = new ControllerMatricula();
        cbxmarca.removeAllItems();
        try {
            for (int i = 0; i < ac.getLista().getSize(); i++) {
                cbxmarca.addItem(ac.getLista().get(i));
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static Matricula getComboMatricula(JComboBox cbx) {
        return (Matricula) cbx.getSelectedItem();
    }

    public static void cargaMaterias(JComboBox cbxmarca) throws VacioExceptions {
        ControllerAsignatura ac = new ControllerAsignatura();
        cbxmarca.removeAllItems();
        try {
            for (int i = 0; i < ac.getLista().getSize(); i++) {
                cbxmarca.addItem(ac.getLista().get(i));
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static Asignatura getComboAsignatura(JComboBox cbx) {
        return (Asignatura) cbx.getSelectedItem();
    }

    public static void cargaHorario(JComboBox cbxmarca) throws VacioExceptions {
        ControllerHorario ac = new ControllerHorario();
        cbxmarca.removeAllItems();
        try {
            for (int i = 0; i < ac.getLista().getSize(); i++) {
                cbxmarca.addItem(ac.getLista().get(i));
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static Horario getComboHorario(JComboBox cbx) {
        return (Horario) cbx.getSelectedItem();
    }

    public static void cargaEstudiante(JComboBox cbxmarca) throws VacioExceptions {
        ControllerEstudiante ac = new ControllerEstudiante();
        cbxmarca.removeAllItems();
        try {
            for (int i = 0; i < ac.getLista().getSize(); i++) {
                cbxmarca.addItem(ac.getLista().get(i));
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static Estudiante getComboEstudiante(JComboBox cbx) {
        return (Estudiante) cbx.getSelectedItem();
    }

    public static void cargaPeriodoAcademico(JComboBox cbxmarca) throws VacioExceptions {
        ControllerPeriodoAcademico ac = new ControllerPeriodoAcademico();
        cbxmarca.removeAllItems();
        try {
            for (int i = 0; i < ac.getPeriodoAcademicos().getSize(); i++) {
                cbxmarca.addItem(ac.getPeriodoAcademicos().get(i));
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static PeriodoAcademico getComboPeriodo(JComboBox cbx) {
        return (PeriodoAcademico) cbx.getSelectedItem();
    }

    public static void cargaDocente(JComboBox cbxmarca) throws VacioExceptions {
        DocenteController ac = new DocenteController();
        cbxmarca.removeAllItems();
        try {
            for (int i = 0; i < ac.getDocentes().getSize(); i++) {
                cbxmarca.addItem(ac.getDocentes().get(i));
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static Docente getComboDocente(JComboBox cbx) {
        return (Docente) cbx.getSelectedItem();
    }

}