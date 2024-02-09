/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.ControllerCarrera.Tabla;


import tda_listas.ListaEnlazada;
import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;
import model.Carrera;

/**
 * @author santiago
 */
public class ModeloTablaCarreras extends AbstractTableModel {

    private Carrera carrera = new Carrera();

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Carrera carr = getCarrera();
        try {
            return switch (col) {
                case 0 ->
                    (carr != null) ? carr.getNombre() : " ";
                case 1 ->
                    (carr != null) ? carr.getArea_estudio() : " ";
                case 2 ->
                    (carr != null) ? carr.getCodigo() : " ";
                case 3 ->
                    (carr != null) ? carr.getModalidad() : " ";
                case 4 ->
                    (carr != null) ? carr.getTitulo_otorgado() : " ";
                default ->
                    null;
            };
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        return switch (col) {
            case 0 ->
                "Nombre";
            case 1 ->
                "Area Estudio";
            case 2 ->
                "Codigo";
            case 3 ->
                "Modalida";
            case 4 ->
                "Titulo Otorgado";
            default ->
                null;
        };
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

}
