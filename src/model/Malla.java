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

    public Boolean compareTo(Malla c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("descripcion")) {
                    return getDescripcion().compareTo(c.getDescripcion()) > 0;
                } else if (field.equalsIgnoreCase("codigo")) {
                    return getCodigo().compareTo(c.getCodigo()) > 0;
                } else if (field.equalsIgnoreCase("nro_asignaturas")) {
                    return getNro_asignaturas() > c.getNro_asignaturas();
                } else if (field.equalsIgnoreCase("total_horas")) {
                    return getTotal_horas() > c.getTotal_horas();
                } else {
                    return false;
                }
            case 0:
                if (field.equalsIgnoreCase("descripcion")) {
                    return getDescripcion().compareTo(c.getDescripcion()) < 0;
                } else if (field.equalsIgnoreCase("codigo")) {
                    return getCodigo().compareTo(c.getCodigo()) < 0;
                } else if (field.equalsIgnoreCase("nro_asignaturas")) {
                    return getNro_asignaturas() < c.getNro_asignaturas();
                } else if (field.equalsIgnoreCase("total_horas")) {
                    return getTotal_horas() < c.getTotal_horas();
                } else {
                    return false;
                }

            default:
                return false;
        }
    }

    public int comparar(Malla malla, String text, String campo) {
        try {
            return switch (campo.toLowerCase()) {
                case "descripcion" -> text.compareTo(malla.getDescripcion().toLowerCase());
                case "codigo" -> text.compareTo(malla.getCodigo().toLowerCase());
                case "nro_asignaturas" -> Integer.compare(Integer.parseInt(text), malla.getNro_asignaturas());
                case "total_horas" -> Integer.compare(Integer.parseInt(text), malla.getTotal_horas());
                case "id" -> Integer.compare(Integer.parseInt(text), malla.getId());
                default -> throw new IllegalArgumentException("Campo de comparación no válido");
            };
        } catch (Exception e) {
            System.out.println("errorr" + e);
        }
        return 0;
    }

}
