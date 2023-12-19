package model;

import tda_listas.ListaEnlazada;

public class Asignatura {
    // Atributos
    private Integer id;
    private String nombre;
    private Integer codigo;
    private Integer horasTotales;

    private ListaEnlazada<Cursa> cursas;


    // Constructor
    public Asignatura() {
    }

    public ListaEnlazada<Cursa> getCursas() {
        return cursas;
    }

    public void setCursas(ListaEnlazada<Cursa> cursas) {
        this.cursas = cursas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(Integer horasTotales) {
        this.horasTotales = horasTotales;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

