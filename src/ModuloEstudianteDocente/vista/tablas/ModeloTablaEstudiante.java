/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloEstudianteDocente.vista.tablas;

import ModuloEstudianteDocente.controlador.EstudianteController;
import tda_listas.ListaEnlazada;

import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;

import model.Estudiante;

import java.text.SimpleDateFormat;

/**
 * @author santiago
 */
public class ModeloTablaEstudiante extends AbstractTableModel {

    private ListaEnlazada<Estudiante> estudiante = new ListaEnlazada<>();


    public ListaEnlazada<Estudiante> getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(ListaEnlazada<Estudiante> estudiante) {
        this.estudiante = estudiante;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }


    @Override
    public int getRowCount() {
        if (estudiante != null) {
            return estudiante.getSize();
        } else {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Estudiante est = null;
        EstudianteController ce = new EstudianteController();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");

        //TODO: Mostrar todos los datos del estudiante
        try {
            est = estudiante.get(row);
            ce.setEstudiante(est);
            return switch (col) {
                case 0-> (est != null) ? est.getNacionalidad() : " ";
                case 1 -> (est != null) ? est.getProvincia() : " ";
                case 2 -> (est != null) ? est.getCanton() : " ";
                case 3 -> (est != null) ? est.getEtnia() : " ";
//                case 4 -> (est != null) ? est.getTelefono() : " ";
//                case 5 -> (est != null) ? est.getEtnia() : " ";
                default -> null;
            };
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        return switch (col) {
            case 0 -> "Nacionalidad";
            case 1 -> "Provincia";
            case 2 -> "Canton";
            case 3 -> "Etnia";
//            case 4 -> "Telefono";
//            case 5 -> "Etnia";
            default -> null;
        };
    }


}