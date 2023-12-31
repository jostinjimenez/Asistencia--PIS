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
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class Util_VistaLinked1_Asistencia {

    private static ListaEnlazada<Asignatura> listaAsig = new ListaEnlazada<>();
    private static ListaEnlazada<Cursa> listaCursa = new ListaEnlazada<>();
    private static ListaEnlazada<Matricula> listaMatr = new ListaEnlazada<>();

    public static void cargaParalelos(JComboBox cbxmarca) throws VacioExceptions {
        cbxmarca.removeAllItems();
        try {
            for (int i = 0; i < listaCursa.getSize(); i++) {
                cbxmarca.addItem(listaCursa.get(i));
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static Cursa getComboCursa(JComboBox cbx) {
        return (Cursa) cbx.getSelectedItem();
    }

    public static void cargaCiclos(JComboBox cbxmarca) throws VacioExceptions {
        cbxmarca.removeAllItems();
        try {
            for (int i = 0; i < listaMatr.getSize(); i++) {
                cbxmarca.addItem(listaMatr.get(i));
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

    public static void cargaAsig(JComboBox cbxmarca) throws VacioExceptions {
        cbxmarca.removeAllItems();
        try {
            for (int i = 0; i < listaAsig.getSize(); i++) {
                cbxmarca.addItem(listaAsig.get(i));
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static Asignatura getComboAsig(JComboBox cbx) {
        return (Asignatura) cbx.getSelectedItem();
    }

    /**
     * @return the listaAsig
     */
    public static ListaEnlazada<Asignatura> getListaAsig() {
        return listaAsig;
    }

    /**
     * @param aListaAsig the listaAsig to set
     */
    public static void setListaAsig(ListaEnlazada<Asignatura> aListaAsig) {
        listaAsig = aListaAsig;
    }

    /**
     * @return the listaCursa
     */
    public static ListaEnlazada<Cursa> getListaCursa() {
        return listaCursa;
    }

    /**
     * @param aListaCursa the listaCursa to set
     */
    public static void setListaCursa(ListaEnlazada<Cursa> aListaCursa) {
        listaCursa = aListaCursa;
    }

    /**
     * @return the listaMatr
     */
    public static ListaEnlazada<Matricula> getListaMatr() {
        return listaMatr;
    }

    /**
     * @param aListaMatr the listaMatr to set
     */
    public static void setListaMatr(ListaEnlazada<Matricula> aListaMatr) {
        listaMatr = aListaMatr;
    }
    
    

}
