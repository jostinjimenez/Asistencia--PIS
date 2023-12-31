/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelLuis.controller;

import DAO.DataAccessObject;
import model.Asistencia;
import tda_listas.ListaEnlazada;

/**
 *
 * @author Usuario
 */
public class ControllerAsistencia extends DataAccessObject<Asistencia> {

    private Asistencia asistencia = new Asistencia();
    private ListaEnlazada<Asistencia> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public ControllerAsistencia() {
        super(Asistencia.class);
    }

    public Asistencia getAsistencia() {
        if (asistencia == null) {
            asistencia = new Asistencia();
        }
        return asistencia;
    }

    public void setAsistencia(Asistencia asitencia) {
        this.asistencia = asitencia;
    }

    public Boolean saved() {
        return save(asistencia);
    }

    public ListaEnlazada<Asistencia> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;

    }

    public Boolean update1(Integer i) {
        return update(asistencia, i);
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Asistencia> lista) {
        this.lista = lista;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    public int generatedId() {
        return generarID();
    }

}
