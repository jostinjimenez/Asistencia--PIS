package model;

import java.util.Objects;
import tda_listas.ListaEnlazada;

public class Asignatura {

    // Atributos
    Integer id;
    private String nombre;
    private Integer horasTotales;
    private Integer codigo;

    private ListaEnlazada<Integer> id_cursas;

    public Asignatura(Integer id, String nombre, Integer horasTotales, Integer codigo, ListaEnlazada<Integer> id_cursas) {
        this.id = id;
        this.nombre = nombre;
        this.horasTotales = horasTotales;
        this.codigo = codigo;
        this.id_cursas = id_cursas;
    }

    // Constructor
    public Asignatura() {
    }

    public Asignatura(Integer nuevoId, String nombre, Integer codigo, Integer horasTotales) {
        this.id = nuevoId;
        this.nombre = nombre;
        this.codigo = codigo;
        this.horasTotales = horasTotales;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Asignatura that = (Asignatura) obj;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    public boolean isValid() {
        // Verificar que los campos necesarios no sean null o vacíos
        return nombre != null && !nombre.isEmpty()
                && codigo != null && codigo > 0
                && horasTotales != null && horasTotales > 0;
    }
}