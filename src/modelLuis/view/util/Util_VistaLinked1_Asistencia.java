package modelLuis.view.util;

import javax.swing.JComboBox;
import model.Cursa;
import model.Matricula;
import modelLuis.controller.ControllerCursa;
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

}
