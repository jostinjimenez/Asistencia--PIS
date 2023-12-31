/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelLuis.controller;

import DAO.DataAccessObject;
import model.Estudiante;
import tda_listas.ListaEnlazada;

/**
 *
 * @author Usuario
 */
public class ControllerEstudiante extends DataAccessObject<Estudiante> {

    private Estudiante estudiante = new Estudiante();
    private ListaEnlazada<Estudiante> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public ControllerEstudiante() {
        super(Estudiante.class);
    }

    public Estudiante getEstudiante() {
        if (estudiante == null) {
            estudiante = new Estudiante();
        }
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Boolean saved() {
        return save(estudiante);
    }

    public ListaEnlazada<Estudiante> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;

    }

    public Boolean update1(Integer i) {
        return update(estudiante, i);
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Estudiante> lista) {
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

    public static void main(String[] args) {
        ListaEnlazada<Integer> ids = new ListaEnlazada();
        ids.add(1);
        ids.add(2);
        Estudiante estudiante = new Estudiante(1, "Juan", "Nose", "Juanzzz", "27272", "262662222", "265252", "Zamora", "ahhaha", true, ids);
        ControllerEstudiante c = new ControllerEstudiante();
        c.save(estudiante);
        
    }

}
