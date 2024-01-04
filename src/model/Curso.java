package model;

import tda_listas.ListaEnlazada;

import java.util.Objects;

public class Curso {
    private Integer id;
    private Integer nroEstudiante;
    private String codCurso;
    private String nroAula;

    private ListaEnlazada<Asignatura> asignaturas;
    private Integer idMalla;

    public Curso() {
    }

    public Curso(Integer id, Integer nroEstudiante, String codCurso, String nroAula, ListaEnlazada<Asignatura> asignaturas, Integer idMalla) {
        this.id = id;
        this.nroEstudiante = nroEstudiante;
        this.codCurso = codCurso;
        this.nroAula = nroAula;
        this.asignaturas = asignaturas;
        this.idMalla = idMalla;
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

    public String getNroAula() {
        return nroAula;
    }

    public void setNroAula(String nroAula) {
        this.nroAula = nroAula;
    }

    @Override
    public String toString() {
        return codCurso;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Curso curso = (Curso) obj;
        return Objects.equals(id, curso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}