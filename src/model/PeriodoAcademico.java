package model;

import tda_listas.ListaEnlazada;

public class PeriodoAcademico {
    // Atributos
    private Integer id;
    private Integer anio;
    private String fechaInicio;
    private String fechaFin;
    private Boolean estado;

    private ListaEnlazada<Integer> id_matriculas;


    // Constructor
    public PeriodoAcademico() {
    }

    public PeriodoAcademico(Integer id, Integer anio, String fechaInicio, String fechaFin, Boolean estado, ListaEnlazada<Integer> id_matriculas) {
        this.id = id;
        this.anio = anio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.id_matriculas = id_matriculas;
    }

    public ListaEnlazada<Integer> getId_matriculas() {
        if (id_matriculas == null) id_matriculas = new ListaEnlazada<>();
        return id_matriculas;
    }

    public void setId_matriculas(ListaEnlazada<Integer> id_matriculas) {
        this.id_matriculas = id_matriculas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "id " + id  + "fechaInicio " + fechaInicio + ", fechaFin " + fechaFin + anio;
    }

    public Boolean compareTo(PeriodoAcademico pa, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return this.getId() > (pa.getId());
                } else if (field.equalsIgnoreCase("anio")) {
                    return this.getAnio() > (pa.getAnio());
                } else if (field.equalsIgnoreCase("fechaInicio")) {
                    return this.getFechaInicio().toLowerCase().compareTo(pa.getFechaInicio().toLowerCase()) > 0;
                } else if (field.equalsIgnoreCase("fechaFin")) {
                    return this.getFechaFin().toLowerCase().compareTo(pa.getFechaFin().toLowerCase()) > 0;
                }

            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return this.getId() < (pa.getId());
                } else if (field.equalsIgnoreCase("anio")) {
                    return this.getAnio() < (pa.getAnio());
                } else if (field.equalsIgnoreCase("fechaInicio")) {
                    return this.getFechaInicio().toLowerCase().compareTo(pa.getFechaInicio().toLowerCase()) < 0;
                } else if (field.equalsIgnoreCase("fechaFin")) {
                    return this.getFechaFin().toLowerCase().compareTo(pa.getFechaFin().toLowerCase()) < 0;
                }

            default:
                return false;
        }
    }
}

