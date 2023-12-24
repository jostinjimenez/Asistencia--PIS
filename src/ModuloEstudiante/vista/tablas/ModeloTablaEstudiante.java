/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloEstudiante.vista.tablas;

import tda_listas.ListaEnlazada;
import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;
import model.Estudiante;

/**
 *
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
        return 9;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Estudiante est = null;
        
        try {
            est = estudiante.get(row);
        } catch (Exception e) {
        }
        
        switch (col) {
            case 0:
                return (est != null) ? est.getId(): " ";
            case 1:
                return (est != null) ? est.getNombre() : " ";
            case 2:
                return (est != null) ? est.getFecha_nacimiento() : " ";
            case 3:
                return (est != null) ? est.getCorreo_personal() : " ";
            case 4:
                return (est != null) ? est.getDni() : " ";
            case 5:
                return (est != null) ? est.getTelefono() : " ";
            case 6:
                return (est != null) ? est.getEtnia() : " ";
            case 7:
                return (est != null) ? est.getTitulo_bachiller() : " ";
            case 8:
                return (est != null) ? est.getDireccion() : " ";
            

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
                return "Etnia";
            case 7: 
                return "Titulo de bachiller";
            case 8: 
                return "Direccion";
            default:
                return null;
        }
    }

   

}