/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloEstudianteDocente.vista.tablas;

import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;

import ModuloEstudianteDocente.controlador.DocenteController;
import model.Docente;
import tda_listas.ListaEnlazada;

import java.text.SimpleDateFormat;

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
        return 9;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Docente doc = null;
        DocenteController ce = new DocenteController();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");

        try {
            doc = docentes.get(row);
            ce.setDocente(doc);
            String fechaFormateada = sdf.format(ce.getDocente().getFecha_nacimiento());

            return switch (col) {
//                case 0 -> (doc != null) ? doc.getId(): " ";
//                case 1 -> (doc != null) ? doc.getNombre() : " ";
//                case 2 -> (doc != null) ? fechaFormateada: " ";
//                case 3 -> (doc != null) ? doc.getCorreo_personal() : " ";
//                case 4 -> (doc != null) ? doc.getDni() : " ";
//                case 5 -> (doc != null) ? doc.getTelefono() : " ";
                case 0 -> (doc != null) ? doc.getCodigo_empleado() : " ";
                case 1 -> (doc != null) ? doc.getExperiencia() : " ";
                case 2 -> (doc != null) ? doc.getGrado_academico() : " ";
                default -> null;
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getColumnName(int col) {
        return switch (col) {
//            case 0 -> "ID";
//            case 1 -> "Nombre";
//            case 2 -> "Fecha Nacimiento";
//            case 3 -> "Correo";
//            case 4 -> "DNI";
//            case 5 -> "Telefono";
            case 0 -> "Codigo";
            case 1 -> "Años Experiencia";
            case 2 -> "Grado Academico";
            default -> null;
        };
    }
}
