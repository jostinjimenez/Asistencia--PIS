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

    public Asignatura(Integer id, String nombre, Integer codigo, Integer horasTotales, ListaEnlazada<Integer> id_cursas) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.horasTotales = horasTotales;
        this.id_cursas = id_cursas;
    }

    public Asignatura(Integer nuevoId, String nombre, Integer codigo, Integer horasTotales) {
        this.id = nuevoId;
        this.nombre = nombre;
        this.codigo = codigo;
        this.horasTotales = horasTotales;
    }

    // Constructor
    public Asignatura() {
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

    public Boolean comparar(Asignatura as, String field, Integer type) {

        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return getId() > as.getId();
                }
            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId() < as.getId();
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
