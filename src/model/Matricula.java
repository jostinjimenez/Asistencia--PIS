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
    private ListaEnlazada<Integer> id_cursas;

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

    public ListaEnlazada<Integer> getId_cursas() {
        return id_cursas;
    }

    public void setId_cursas(ListaEnlazada<Integer> id_cursas) {
        this.id_cursas = id_cursas;
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

