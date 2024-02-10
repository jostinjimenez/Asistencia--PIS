package modulo_carrera.controller;


import DataBase.DataAccessObject;

import model.*;
import tda_listas.ListaEnlazada;

public class CarreraController extends DataAccessObject<Carrera> {

    // Atributos
    private ListaEnlazada<Carrera> carreras;
    private Carrera carrera = new Carrera();
    private Integer index = -1;

    // Constructor
    public CarreraController() {
        super(Cuenta.class);
        this.carreras = new ListaEnlazada<>();
    }

    // Getters y Setters
    public ListaEnlazada<Carrera> getCarreras() {
        if (carreras.isEmpty()){
            carreras = this.list_All();
        }
        return carreras;
    }

    public void setCarreras(ListaEnlazada<Carrera> carreras) {
        this.carreras = carreras;
    }

    public Carrera getCarrera() {
        return carrera != null ? carrera : new Carrera();
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    // Metodos
    public Integer save() throws Exception {
        return super.save(this.carrera);
    }

    public Boolean update(Integer index) {
        try {
            update(this.carrera);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}














