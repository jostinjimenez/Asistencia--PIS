package model;

import tda_listas.ListaEnlazada;

import java.util.Objects;

public class Curso {

    private Integer id;
    private Integer nroEstudiante;
    private String codCurso;
    private String nroAula;

    private ListaEnlazada<Integer> id_asignaturas;
    private Integer idMalla;

    public Curso() {
    }

    public Curso(Integer id, Integer nroEstudiante, String codCurso, ListaEnlazada<Integer> id_asignaturas, Integer idMalla) {
        this.id = id;
        this.nroEstudiante = nroEstudiante;
        this.codCurso = codCurso;
        this.id_asignaturas = id_asignaturas;
        this.idMalla = idMalla;
    }

    public Curso(Integer id, Integer nroEstudiante, String codCurso, String nroAula, ListaEnlazada<Integer> id_asignaturas, Integer idMalla) {
        this.id = id;
        this.nroEstudiante = nroEstudiante;
        this.codCurso = codCurso;
        this.nroAula = nroAula;
        this.id_asignaturas = id_asignaturas;
        this.idMalla = idMalla;
    }

    public ListaEnlazada<Integer> getId_asignaturas() {
        if (id_asignaturas == null) {
            id_asignaturas = new ListaEnlazada<>();
        }
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

    /**
     * @return the nroAula
     */
    public String getNroAula() {
        return nroAula;
    }

    /**
     * @param nroAula the nroAula to set
     */
    public void setNroAula(String nroAula) {
        this.nroAula = nroAula;
    }

}
