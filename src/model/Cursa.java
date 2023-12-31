package model;

import tda_listas.ListaEnlazada;

public class Cursa {

    private Integer id;
    private String paralelo;

    private Integer idMatricula;
    private Integer idAsignatura;
    private Integer idPeriodoAcademico;
    private Integer idDocente;

    public Cursa() {
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Integer getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public Integer getIdPeriodoAcademico() {
        return idPeriodoAcademico;
    }

    public void setIdPeriodoAcademico(Integer idPeriodoAcademico) {
        this.idPeriodoAcademico = idPeriodoAcademico;
    }

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public Cursa(Integer id, String paralelo, Integer idMatricula, Integer idAsignatura, Integer idPeriodoAcademico, Integer idDocente) {
        this.id = id;
        this.paralelo = paralelo;
        this.idMatricula = idMatricula;
        this.idAsignatura = idAsignatura;
        this.idPeriodoAcademico = idPeriodoAcademico;
        this.idDocente = idDocente;
    }

    @Override
    public String toString() {
        return paralelo;
    }

}
