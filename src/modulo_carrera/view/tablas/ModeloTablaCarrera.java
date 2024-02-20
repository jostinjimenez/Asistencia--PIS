/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modulo_carrera.view.tablas;

import model.Carrera;
import Controller.Administrativo.CarreraController;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;

/**
 * @author santiago
 */
public class ModeloTablaCarrera extends AbstractTableModel {

    private ListaEnlazada<Carrera> lista = new ListaEnlazada<>();

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    @Override
    public int getRowCount() {
        if (lista != null) {
            return lista.getSize();
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
        Carrera carr = null;
        CarreraController cc = new CarreraController();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");

        //TODO: Mostrar todos los datos del estudiante
        try {
            carr = lista.get(row);
            cc.setCarrera(carr);
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

    /**
     * @return the lista
     */
    public ListaEnlazada<Carrera> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Carrera> lista) {
        this.lista = lista;
    }

    public Carrera getCarrera(int fila) throws VacioExceptions {
        return lista.get(fila);
    }

}
