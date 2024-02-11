/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloEstudianteDocente.vista.tablas;

import ModuloEstudianteDocente.controlador.EstudianteController;
import model.Carrera;
import model.Persona;
import modulo_1.inicio_sesion.controller.PersonaController;
import tda_listas.ListaEnlazada;

import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;

import model.Estudiante;
import tda_listas.exceptions.VacioExceptions;

import java.text.SimpleDateFormat;

import static modulo_1.inicio_sesion.controller.util.Utilidades.getPersonaStatic;

/**
 * @author santiago
 */
public class ModeloTablaEstudiante extends AbstractTableModel {

    private ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();

    public ListaEnlazada<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ListaEnlazada<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }


    @Override
    public int getRowCount() {
        if (estudiantes != null) {
            return estudiantes.getSize();
        } else {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Estudiante est = null;
        Persona per = null;
        try {
            est = estudiantes.get(row);
            per = getPersonaStatic(est.getId());
            return switch (col) {
                case 0 -> (per != null) ? per.getNombre() + " " + per.getApellido(): " ";
                case 1 -> (per != null) ? per.getDni() : " ";
                case 2 -> est.getCanton();
                case 3 -> est.getProvincia();
                case 4 -> est.getNacionalidad();
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
            case 0 -> "Nombre";
            case 1 -> "DNI";
            case 2 -> "Canton";
            case 3 -> "Provincia";
            case 4 -> "Nacionalidad";
            default -> null;
        };
    }

    public Estudiante getEstudiante(int fila) throws VacioExceptions {
        return estudiantes.get(fila);
    }
}