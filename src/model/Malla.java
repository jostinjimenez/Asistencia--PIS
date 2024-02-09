package model;

import java.util.Objects;

public class Malla {

    private Integer id;
    private String descripcion;
    private String codigo;
    private Integer nro_asignaturas;
    private Integer total_horas;

    private Integer carrera_id;

    public Malla() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getNro_asignaturas() {
        return nro_asignaturas;
    }

    public void setNro_asignaturas(Integer nro_asignaturas) {
        this.nro_asignaturas = nro_asignaturas;
    }

    public Integer getTotal_horas() {
        return total_horas;
    }

    public void setTotal_horas(Integer total_horas) {
        this.total_horas = total_horas;
    }

    public Integer getCarrera_id() {
        return carrera_id;
    }

    public void setCarrera_id(Integer carrera_id) {
        this.carrera_id = carrera_id;
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
