/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelLuis.controller;

import DAO.DataAccessObject;
import model.Horario;
import tda_listas.ListaEnlazada;

/**
 *
 * @author Usuario
 */
public class ControllerHorario extends DataAccessObject<Horario> {

    private Horario horario = new Horario();
    private ListaEnlazada<Horario> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public ControllerHorario() {
        super(Horario.class);
    }

    public Horario getAsistencia() {
        if (horario == null) {
            horario = new Horario();
        }
        return horario;
    }

    public void setAsistencia(Horario horario) {
        this.horario = horario;
    }

    public Boolean saved() {
        return save(horario);
    }

    public ListaEnlazada<Horario> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;

    }

    public Boolean update1(Integer i) {
        return update(horario, i);
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Horario> lista) {
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
