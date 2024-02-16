package model;

public class Asistencia {
    private Integer id;
    private String observacion;
    private String estado_asistencia;
    private Integer tematica_id;
    private Integer horario_id;
    


    private Integer cursa_id;

    public Asistencia() {
    }

    public Asistencia(Integer id, String observacion, Integer idTematica, Integer idHorario, Integer idCursa) {
        this.id = id;
        this.observacion = observacion;
        this.tematica_id = idTematica;
        this.horario_id = idHorario;
        this.cursa_id = idCursa;
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

    public String getEstado_asistencia() {
        return estado_asistencia;
    }

    public void setEstado_asistencia(String estado_asistencia) {
        this.estado_asistencia = estado_asistencia;
    }

    public Integer getTematica_id() {
        return tematica_id;
    }

    public void setTematica_id(Integer tematica_id) {
        this.tematica_id = tematica_id;
    }

    public Integer getHorario_id() {
        return horario_id;
    }

    public void setHorario_id(Integer horario_id) {
        this.horario_id = horario_id;
    }

    public Integer getCursa_id() {
        return cursa_id;
    }

    public void setCursa_id(Integer cursa_id) {
        this.cursa_id = cursa_id;
    }
}

