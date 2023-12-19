package model;

import tda_listas.ListaEnlazada;

public class Rol {
    // Atributos
    private Integer id;
    private String nombre;
    private String descripcion;

    private ListaEnlazada<Persona> personas;

    //Constructor
    public Rol() {
    }

    public Rol(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public ListaEnlazada<Persona> getPersonas() {
        if (personas == null)
            personas = new ListaEnlazada<>();
        return personas;
    }

    public void setPersonas(ListaEnlazada<Persona> personas) {
        this.personas = personas;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Metodos
    @Override
    public String toString() {
        return nombre;
    }


}

