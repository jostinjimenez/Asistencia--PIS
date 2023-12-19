package model;

import tda_listas.ListaEnlazada;

public class Curso {
    private Integer id;
    private Integer nroEstudiante;
    private String codCurso;

    private ListaEnlazada<Asignatura> asignaturas;
    private Integer idMalla;

    public Curso() {
    }

    public ListaEnlazada<Asignatura> getAsignaturas() {
        if (asignaturas == null)
            asignaturas = new ListaEnlazada<>();
        return asignaturas;
    }

    public void setAsignaturas(ListaEnlazada<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Integer getIdMalla() {
        return idMalla;
    }

    public void setIdMalla(Integer idMalla) {
        this.idMalla = idMalla;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNroEstudiante() {
        return nroEstudiante;
    }

    public void setNroEstudiante(Integer nroEstudiante) {
        this.nroEstudiante = nroEstudiante;
    }

    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }
}

