package model;

public class Carrera {
    private Integer id;
    private String nombre;
    private String area_estudio;
    private String modalidad;
    private String titulo_otorgado;

    public Carrera() {
    }

    // Getters and Setters

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

    public String getArea_estudio() {
        return area_estudio;
    }

    public void setArea_estudio(String area_estudio) {
        this.area_estudio = area_estudio;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getTitulo_otorgado() {
        return titulo_otorgado;
    }

    public void setTitulo_otorgado(String titulo_otorgado) {
        this.titulo_otorgado = titulo_otorgado;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
