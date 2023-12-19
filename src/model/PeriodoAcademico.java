package model;

import tda_listas.ListaEnlazada;

public class PeriodoAcademico {
    // Atributos
    private Integer id;
    private Integer anio;
    private String fechaInicio;
    private String fechaFin;
    private Boolean estado;

    private ListaEnlazada<Matricula> matriculas;


    // Constructor
    public PeriodoAcademico() {
    }

    public ListaEnlazada<Matricula> getMatriculas() {
        if (matriculas == null)
            matriculas = new ListaEnlazada<>();
        return matriculas;
    }

    public void setMatriculas(ListaEnlazada<Matricula> matriculas) {
        this.matriculas = matriculas;
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
        return "PeriodoAcademico{" +
                "fechaInicio='" + fechaInicio + '\'' +
                ", fechaFin='" + fechaFin + '\'' +
                '}';
    }
}
