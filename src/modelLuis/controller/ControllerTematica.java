/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelLuis.controller;

import DAO.DataAccessObject;
import model.Tematica;
import tda_listas.ListaEnlazada;

/**
 *
 * @author Usuario
 */
public class ControllerTematica extends DataAccessObject<Tematica> {

    private Tematica tematica = new Tematica();
    private ListaEnlazada<Tematica> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public ControllerTematica() {
        super(Tematica.class);
    }

    public Tematica getTematica() {
        if (tematica == null) {
            tematica = new Tematica();
        }
        return tematica;
    }

    public void setAsistencia(Tematica tematica) {
        this.tematica = tematica;
    }

    public Boolean saved() {
        return save(tematica);
    }

    public ListaEnlazada<Tematica> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;

    }

    public Boolean update1(Integer i) {
        return update(tematica, i);
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Tematica> lista) {
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
