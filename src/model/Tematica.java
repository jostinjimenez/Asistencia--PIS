package model;

import java.util.Date;

public class Tematica {

    private Integer id;
    private String nombre;
    private Date fecha;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
        @Override
    public String toString() {
        return "Tematica{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha='" + getFecha() + '\'' +
                '}';
    }

}
