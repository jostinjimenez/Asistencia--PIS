package model;

import java.util.Date;

public class PeriodoAcademico {
    // Atributos
    private Integer id;
    private Date fecha_Inicio;
    private Date fecha_fin;
    private Boolean estado;


    // Constructor
    public PeriodoAcademico() {
    }

    public PeriodoAcademico(Integer id, Date fecha_Inicio, Date fecha_fin, Boolean estado) {
        this.id = id;
        this.fecha_Inicio = fecha_Inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha_Inicio() {
        return fecha_Inicio;
    }

    public void setFecha_Inicio(Date fecha_Inicio) {
        this.fecha_Inicio = fecha_Inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return fecha_Inicio + " - " + fecha_fin;
    }

    public Boolean compareTo(PeriodoAcademico pa, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return this.getId() > (pa.getId());
                } else if (field.equalsIgnoreCase("fechaInicio")) {
                    return this.getFecha_Inicio().compareTo(pa.getFecha_Inicio()) > 0;
                } else if (field.equalsIgnoreCase("fechaFin")) {
                    return this.getFecha_fin().compareTo(pa.getFecha_fin()) > 0;
                }

            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return this.getId() < (pa.getId());
                } else if (field.equalsIgnoreCase("fechaInicio")) {
                    return this.getFecha_Inicio().compareTo(pa.getFecha_Inicio()) < 0;
                } else if (field.equalsIgnoreCase("fechaFin")) {
                    return this.getFecha_fin().compareTo(pa.getFecha_fin()) < 0;
                }
            default:
                return false;
        }
    }
}

