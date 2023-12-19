package model;

import model.catalogo.TipoFalta;

import java.util.Timer;

public class Horario {
    private Integer id;
    private String fecha;
    private Timer horaInicio;
    private Timer horaFin;
    private TipoFalta tipo;

    private Integer idAsignatura;

    public Horario() {
    }

    public Horario(Integer id, String fecha, Timer horaInicio, Timer horaFin, TipoFalta tipo, Integer idAsignatura) {
        this.id = id;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.tipo = tipo;
        this.idAsignatura = idAsignatura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Timer getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Timer horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Timer getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Timer horaFin) {
        this.horaFin = horaFin;
    }

    public TipoFalta getTipo() {
        return tipo;
    }

    public void setTipo(TipoFalta tipo) {
        this.tipo = tipo;
    }

    public Integer getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }
}

