/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloEstudianteDocente.vista.tablas;

import ModuloEstudianteDocente.controlador.EstudianteController;
import model.Persona;
import modulo_1.inicio_sesion.controller.PersonaController;
import tda_listas.ListaEnlazada;

import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;

import model.Estudiante;

import java.text.SimpleDateFormat;

/**
 * @author santiago
 */
public class ModeloTablaEstudiante extends AbstractTableModel {

    private ListaEnlazada<Estudiante> estudiantes = new ListaEnlazada<>();
    private ListaEnlazada<Persona> personas = new ListaEnlazada<>();

    public ListaEnlazada<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ListaEnlazada<Persona> personas) {
        this.personas = personas;
    }

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
        return 6;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Estudiante est = null;
        Persona per = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");

        try {
            est = estudiantes.get(row);
            per = personas.get(est.getId());
            return switch (col) {
                case 0 -> per.getNombre() + " " + per.getApellido();
                case 1 -> per.getDni();
                case 2 -> per.getTelefono();
                case 3 -> est.getNacionalidad();
                case 4 -> est.getProvincia();
                case 5 -> est.getCanton();
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
            case 0 -> "Estudiante";
            case 1 -> "DNI";
            case 2 -> "Telefono";
            case 3 -> "Nacionalidad";
            case 4 -> "Provincia";
            case 5 -> "Canton";
            default -> null;
        };
    }
}