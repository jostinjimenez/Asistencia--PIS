package model;

import tda_listas.ListaEnlazada;

public class Curso {
    private Integer id;
    private Integer nroEstudiante;
    private String codCurso;

    private ListaEnlazada<Integer> id_asignaturas;
    private Integer idMalla;

    public Curso() {
    }

    public ListaEnlazada<Integer> getId_asignaturas() {
        if (id_asignaturas == null)
            id_asignaturas = new ListaEnlazada<>();
        return id_asignaturas;
    }

    public void setId_asignaturas(ListaEnlazada<Integer> id_asignaturas) {
        this.id_asignaturas = id_asignaturas;
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

