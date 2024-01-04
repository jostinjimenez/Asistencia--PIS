package model;

import model.catalogo.TipoFalta;

public class Asistencia {
    private Integer id;
    private String observacion;
    private TipoFalta falta;
    private Integer idTematica;
    private Integer idHorario;
    


    private Integer idCursa;

    public Asistencia() {
    }

    public Asistencia(Integer id, String observacion, Integer idTematica, Integer idHorario, Integer idCursa) {
        this.id = id;
        this.observacion = observacion;
        this.idTematica = idTematica;
        this.idHorario = idHorario;
        this.idCursa = idCursa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getIdTematica() {
        return idTematica;
    }

    public void setIdTematica(Integer idTematica) {
        this.idTematica = idTematica;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Integer getIdCursa() {
        return idCursa;
    }

    public void setIdCursa(Integer idCursa) {
        this.idCursa = idCursa;
    }

    /**
     * @return the falta
     */
    public TipoFalta getFalta() {
        return falta;
    }

    /**
     * @param falta the falta to set
     */
    public void setFalta(TipoFalta falta) {
        this.falta = falta;
    }
    
}

