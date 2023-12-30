/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelLuis.controller;

import DAO.DataAccessObject;
import model.Matricula;
import tda_listas.ListaEnlazada;

/**
 *
 * @author Usuario
 */
public class ControllerMatricula extends DataAccessObject<Matricula> {
    
    private Matricula matricula = new Matricula();
    private ListaEnlazada<Matricula> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public ControllerMatricula() {
        super(Matricula.class);
    }

    public Matricula getMatricula() {
        if (matricula == null) {
            matricula = new Matricula();
        }
        return matricula;
    }

    public void setAsistencia(Matricula matricula) {
        this.matricula= matricula;
    }

    public Boolean saved() {
        return save(matricula);
    }

    public ListaEnlazada<Matricula> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;

    }

    public Boolean update1(Integer i) {
        return update(matricula, i);
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Matricula> lista) {
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
         Matricula matricula = new Matricula(1, "30/12/2023", 1, "Computacion");
        ControllerMatricula c = new ControllerMatricula();
        c.save(matricula);
    
    }

}
