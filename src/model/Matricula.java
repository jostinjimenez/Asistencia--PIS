package model;

import java.util.Date;

import static Controller.Util.Utilidades.getPersonaStatic;


public class Matricula {

    private Integer id;
    private String estado_matricula;
    private Date fechamatricula;
    private Integer ciclo;

    private Integer estudiante_id;
    private Integer periodoacademico_id;
    private Integer carrera_id;

    public Matricula() {
    }

    public Integer getCarrera_id() {
        return carrera_id;
    }

    public void setCarrera_id(Integer carrera_id) {
        this.carrera_id = carrera_id;
    }

    public Integer getEstudiante_id() {
        return estudiante_id;
    }

    public void setEstudiante_id(Integer estudiante_id) {
        this.estudiante_id = estudiante_id;
    }

    public Integer getPeriodoacademico_id() {
        return periodoacademico_id;
    }

    public void setPeriodoacademico_id(Integer periodoacademico_id) {
        this.periodoacademico_id = periodoacademico_id;
    }

    public String getEstado_matricula() {
        return estado_matricula;
    }

    public void setEstado_matricula(String estado_matricula) {
        this.estado_matricula = estado_matricula;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechamatricula() {
        return fechamatricula;
    }

    public void setFechamatricula(Date fechamatricula) {
        this.fechamatricula = fechamatricula;
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    @Override
    public String toString() {
        Persona p = getPersonaStatic(getEstudiante_id());
        assert p != null;
        String nombre = p.getNombre().contains(" ") ? p.getNombre().substring(0, p.getNombre().indexOf(" ")) : p.getNombre();
        String apellido = p.getApellido().contains(" ") ? p.getApellido().substring(0, p.getApellido().indexOf(" ")) : p.getApellido();
        return nombre+ " " + apellido + " -> " + "Ciclo: " +  getCiclo();
    }


    public Boolean comparar(Matricula c, String field, Integer type) {

        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("ciclo")) {
                    return getCiclo() > (c.getCiclo());
                } else if (field.equalsIgnoreCase("id_estudiante")) {
                    return getEstudiante_id() > (c.getEstudiante_id());
                } else if (field.equalsIgnoreCase("id")) {
                    return getEstudiante_id() > (c.getEstudiante_id());
                }

            case 0:
                if (field.equalsIgnoreCase("ciclo")) {
                    return getCiclo() < (c.getCiclo());
                } else if (field.equalsIgnoreCase("id_estudiante")) {
                    return getEstudiante_id() < (c.getEstudiante_id());
                } else if (field.equalsIgnoreCase("id")) {
                    return getId() < (c.getId());
                }
            default:
                return false;
        }
    }

    public int comparar(Matricula matricula, String text, String campo) {
        switch (campo.toLowerCase()) {

            case "ciclo":
                try {
                return Integer.compare(Integer.parseInt(text), matricula.getCiclo());
            } catch (Exception e) {
            }
            case "id_estudiante":
               try {
                return Integer.compare(Integer.parseInt(text), matricula.getEstudiante_id());
            } catch (Exception e) {
            }
            case "id":
               try {
                return Integer.compare(Integer.parseInt(text), matricula.getId());
            } catch (Exception e) {
            }
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

}
