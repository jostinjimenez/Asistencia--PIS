package model;

import java.util.Objects;

public class Asignatura {

    // Atributos
    private Integer id;
    private String nombre;
    private Integer horas_Totales;
    private String codigo_materia;
    private Boolean silabo;

    private Integer malla_id;

    // Constructor
    public Asignatura() {
    }

    public Boolean getSilabo() {
        return silabo;
    }

    public void setSilabo(Boolean silabo) {
        this.silabo = silabo;
    }

    public Integer getMalla_id() {
        return malla_id;
    }

    public void setMalla_id(Integer malla_id) {
        this.malla_id = malla_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo_materia() {
        return codigo_materia;
    }

    public void setCodigo_materia(String codigo_materia) {
        this.codigo_materia = codigo_materia;
    }

    public Integer getHoras_Totales() {
        return horas_Totales;
    }

    public void setHoras_Totales(Integer horas_Totales) {
        this.horas_Totales = horas_Totales;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Asignatura that = (Asignatura) obj;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public Boolean comparar(Asignatura as, String field, Integer type) {

        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return getId() > as.getId();
                }
            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId() < as.getId();
                }
            default:
                return false;
        }
    }

    public int comparar(Asignatura asignatura, String text, String campo) {
        return switch (campo.toLowerCase()) {
            case "id" -> Integer.compare(Integer.parseInt(text), asignatura.getId());
            case "nombre" -> asignatura.getNombre().compareToIgnoreCase(text);
            case "codigo_materia" -> asignatura.getCodigo_materia().compareToIgnoreCase(text);
            default -> throw new IllegalArgumentException("Campo de comparación no válido");
        };
    }
}
