package model;

import java.util.Objects;
import tda_listas.ListaEnlazada;

public class Asignatura {

    // Atributos
    private Integer id;
    private String nombre;
    private String codigo;
    private Integer horasTotales;

    private ListaEnlazada<Cursa> cursas;

    // Constructor
    public Asignatura() {
    }
    
    public Asignatura(String nombre) {
        this.nombre = nombre;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
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
}