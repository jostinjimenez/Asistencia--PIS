package model;

public class Docente extends Persona {
    private Integer experiencia;
    private String codigo_empleado;
    private String grado_academico;

    public Docente() {
    }

    public Docente(Integer experiencia, String codigo_empleado, String grado_academico) {
        this.experiencia = experiencia;
        this.codigo_empleado = codigo_empleado;
        this.grado_academico = grado_academico;
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
        return super.toString();
    }
}
