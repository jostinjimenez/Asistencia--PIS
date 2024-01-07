/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloEstudianteDocente.vista.tablas;

import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;
import model.Docente;
import tda_listas.ListaEnlazada;

/**
 *
 * @author LENOVO
 */
public class ModeloTablaDocente extends AbstractTableModel{
    private ListaEnlazada<Docente> docente = new ListaEnlazada<>();
    
    

    public ListaEnlazada<Docente> getDocente() {
        return docente;
    }

    public void setDocente(ListaEnlazada<Docente> docente) {
        this.docente = docente;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }
    
    

    @Override
    public int getRowCount() {
        if (docente != null) {
            return docente.getSize();
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
        
        try {
            doc = docente.get(row);
        } catch (Exception e) {
        }
        
        switch (col) {
            case 0:
                return (doc != null) ? doc.getId(): " ";
            case 1:
                return (doc != null) ? doc.getNombre() : " ";
            case 2:
                return (doc != null) ? doc.getFecha_nacimiento() : " ";
            case 3:
                return (doc != null) ? doc.getCorreo_personal() : " ";
            case 4:
                return (doc != null) ? doc.getDni() : " ";
            case 5:
                return (doc != null) ? doc.getTelefono() : " ";
            case 6:
                return (doc != null) ? doc.getCodigo_empleado() : " ";
            case 7:
                return (doc != null) ? doc.getAnios_experiencia() : " ";
            case 8:
                return (doc != null) ? doc.getGrado_academico() : " ";
            

            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "ID";
            case 1:
                return "Nombre";
            case 2:
                return "Fecha Nacimiento";
            case 3:
                return "Correo";
            case 4: 
                return "DNI";
            case 5: 
                return "Telefono";
            case 6: 
                return "Codigo";
            case 7: 
                return "Años Experiencia";
            case 8: 
                return "Grado Academico";
            default:
                return null;
        }
    }
}
