package model;

import java.util.Date;

public class Docente extends Persona {
    private Integer anios_experiencia;
    private String codigo_empleado;
    private String grado_academico;

    public Docente() {
    }

    public Docente(Integer anios_experiencia, String codigo_empleado, String grado_academico) {
        this.anios_experiencia = anios_experiencia;
        this.codigo_empleado = codigo_empleado;
        this.grado_academico = grado_academico;
    }

    public Integer getAnios_experiencia() {
        return anios_experiencia;
    }

    public void setAnios_experiencia(Integer anios_experiencia) {
        this.anios_experiencia = anios_experiencia;
    }

    public String getCodigo_empleado() {
        return codigo_empleado;
    }

    public void setCodigo_empleado(String codigo_empleado) {
        this.codigo_empleado = codigo_empleado;
    }

    public String getGrado_academico() {
        return grado_academico;
    }

    public void setGrado_academico(String grado_academico) {
        this.grado_academico = grado_academico;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
