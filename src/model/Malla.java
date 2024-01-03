package model;

public class Malla {
    private Integer id;
    private String duracion;
    private String descripcion;

    public Malla() {
    }

    public Malla(Integer id, String duracion, String descripcion) {
        this.id = id;
        this.duracion = duracion;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

