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
import model.Estudiante;
import model.Matricula;
import modelLuis.controller.ControllerAsignatura;
import modelLuis.controller.ControllerEstudiante;
import tda_listas.exceptions.VacioExceptions;

public class Util_VistaLinked_Matricula{

    public static void cargaEstudiantes(JComboBox cbxmarca) throws VacioExceptions {
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

   

}