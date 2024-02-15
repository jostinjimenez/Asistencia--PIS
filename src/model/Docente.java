package model;

import static modulo_1.inicio_sesion.controller.util.Utilidades.getPersonaStatic;

public class Docente extends Persona {
    private Integer id;
    private Integer experiencia;
    private String codigo_empleado;
    private String grado_academico;

    public Docente() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
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
        Persona persona = getPersonaStatic(getId());
        assert persona != null;
        return persona.getNombre() + " " + persona.getApellido() + " - " + persona.getDni();
    }
}
