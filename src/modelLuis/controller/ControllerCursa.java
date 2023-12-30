/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelLuis.controller;

import DAO.DataAccessObject;
import model.Cursa;
import tda_listas.ListaEnlazada;

/**
 *
 * @author Usuario
 */
public class ControllerCursa extends DataAccessObject<Cursa> {

    private Cursa cursa = new Cursa();
    private ListaEnlazada<Cursa> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public ControllerCursa() {
        super(Cursa.class);
    }

    public Cursa getAsistencia() {
        if (cursa == null) {
            cursa = new Cursa();
        }
        return cursa;
    }

    public void setAsistencia(Cursa cursa) {
        this.cursa= cursa;
    }

    public Boolean saved() {
        return save(cursa);
    }

    public ListaEnlazada<Cursa> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;

    }

    public Boolean update1(Integer i) {
        return update(cursa, i);
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Cursa> lista) {
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

    public static void main(String[] args) {
        Cursa cursa = new Cursa(1, "B", 1, 1, 1, 1);
        ControllerCursa c = new ControllerCursa();
        System.out.println( c.save(cursa));

    }

}
