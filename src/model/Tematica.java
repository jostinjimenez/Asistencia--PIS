package model;

import java.time.LocalDate;

public class Tematica {

    private Integer id;
    private String nombre;
    private String fecha;

    public Tematica() {
    }

    public Integer getId() {
        return id;
    }

    public Tematica(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String generarFecha() {
        LocalDate fechaHoy = LocalDate.now();
        fecha = fechaHoy.toString();
        return fecha;
    }

    @Override
    public String toString() {
        return "Tematica{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
