package model;

import java.time.LocalDate;
import model.catalogo.EstadoMatricula;
import tda_listas.ListaEnlazada;

public class Matricula {

    private Integer id;
    private String fechaMatricula;
    private Integer ciclo;
    private String carrera;

    private EstadoMatricula estado;
    private Integer estudiante_id;
    private Integer periodoacademico_id;
    private ListaEnlazada<Integer> id_cursas;

    public Matricula() {
    }

    public Matricula(Integer id, String fechaMatricula, Integer ciclo, String carrera, EstadoMatricula estado, Integer estudiante_id, Integer periodoacademico_id, ListaEnlazada<Integer> id_cursas) {
        this.id = id;
        this.fechaMatricula = fechaMatricula;
        this.ciclo = ciclo;
        this.carrera = carrera;
        this.estado = estado;
        this.estudiante_id = estudiante_id;
        this.periodoacademico_id = periodoacademico_id;
        this.id_cursas = id_cursas;
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

    public ListaEnlazada<Integer> getId_cursas() {
        return id_cursas;
    }

    public void setId_cursas(ListaEnlazada<Integer> id_cursas) {
        this.id_cursas = id_cursas;
    }

    public EstadoMatricula getEstado() {
        return estado;
    }

    public void setEstado(EstadoMatricula estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(String fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return ciclo + "-" + carrera;
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
    public String generarFecha() {
        LocalDate fechaHoy = LocalDate.now();
       String fecha = fechaHoy.toString();
        return fecha;
    }

}
