package model;

import model.catalogo.EstadoMatricula;
import tda_listas.ListaEnlazada;

public class Matricula {
    private Integer id;
    private String fechaMatricula;
    private Integer ciclo;
    private String carrera;

    private EstadoMatricula estado;
    private Integer idEstudiante;
    private Integer idPeriodoAcademico;
    private ListaEnlazada<Cursa> cursas;

    public Matricula() {
    }

    public Matricula(Integer id, String fechaMatricula, Integer ciclo, String carrera) {
        this.id = id;
        this.fechaMatricula = fechaMatricula;
        this.ciclo = ciclo;
        this.carrera = carrera;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdPeriodoAcademico() {
        return idPeriodoAcademico;
    }

    public void setIdPeriodoAcademico(Integer idPeriodoAcademico) {
        this.idPeriodoAcademico = idPeriodoAcademico;
    }

    public ListaEnlazada<Cursa> getCursas() {
        return cursas;
    }

    public void setCursas(ListaEnlazada<Cursa> cursas) {
        this.cursas = cursas;
    }

    public EstadoMatricula getEstado() {
        return estado;
    }

    public void setEstado(EstadoMatricula estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(String fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "id=" + id +
                '}';
    }
}

