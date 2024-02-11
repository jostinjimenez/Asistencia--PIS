/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloEstudianteDocente.vista.tablas;

import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;

import ModuloEstudianteDocente.controlador.DocenteController;
import model.Docente;
import model.Estudiante;
import model.Persona;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import java.text.SimpleDateFormat;

import static modulo_1.inicio_sesion.controller.util.Utilidades.getPersonaStatic;

/**
 * @author LENOVO
 */
public class ModeloTablaDocente extends AbstractTableModel {
    private ListaEnlazada<Docente> docentes = new ListaEnlazada<>();


    public ListaEnlazada<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(ListaEnlazada<Docente> docentes) {
        this.docentes = docentes;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }


    @Override
    public int getRowCount() {
        if (docentes != null) {
            return docentes.getSize();
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
        Docente doce = null;
        Persona per = null;
        try {
            doce = docentes.get(row);
            per = getPersonaStatic(doce.getId());
            return switch (col) {
                case 0 -> doce.getCodigo_empleado();
                case 1 -> (per != null) ? per.getNombre() + " " + per.getApellido(): " ";
                case 2 -> (per != null) ? per.getDni() : " ";
                case 3 -> (per != null) ? per.getCorreo_personal() : " ";
                case 4 -> doce.getExperiencia();
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
            case 0 -> "Codigo Empleado";
            case 1 -> "Nombre";
            case 2 -> "DNI";
            case 3 -> "Correo";
            case 4 -> "Experiencia";
            default -> null;
        };
    }

    public Docente getDocente(int fila) throws VacioExceptions {
        return docentes.get(fila);
    }
}
