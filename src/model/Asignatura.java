package model;

import tda_listas.ListaEnlazada;

public class Asignatura {

    // Atributos
    private Integer id;
    private String nombre;
    private Integer codigo;
    private Integer horasTotales;

    private ListaEnlazada<Integer> id_cursas;

    public Asignatura(Integer id, String nombre, Integer codigo, Integer horasTotales, ListaEnlazada<Integer> id_cursas) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.horasTotales = horasTotales;
        this.id_cursas = id_cursas;
    }

    // Constructor
    public Asignatura() {
    }

    public Asignatura(Integer id, String nombre, Integer codigo, Integer horasTotales, ListaEnlazada<Integer> id_cursas) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.horasTotales = horasTotales;
        this.id_cursas = id_cursas;
    }

    public ListaEnlazada<Integer> getId_cursas() {
        return id_cursas;
    }

    public void setId_cursas(ListaEnlazada<Integer> id_cursas) {
        this.id_cursas = id_cursas;
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

    public Boolean comparar(Asignatura as, String field, Integer type) {

        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return getId() > as.getId();
                }
            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId() > as.getId();
                }
            default:
                return false;
        }
    }

    public int comparar(Asignatura estudiante, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "id":
                return Integer.compare(Integer.parseInt(text), estudiante.getId());
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }
}
