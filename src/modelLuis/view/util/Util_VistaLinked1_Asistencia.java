package modelLuis.view.util;

import javax.swing.JComboBox;
import model.Asignatura;
import model.Cursa;
import model.Horario;
import model.Matricula;
import modelLuis.controller.ControllerAsignatura;
import modelLuis.controller.ControllerCursa;
import modelLuis.controller.ControllerHorario;
import modelLuis.controller.ControllerMatricula;
import tda_listas.exceptions.VacioExceptions;

public class Util_VistaLinked1_Asistencia {

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

}
