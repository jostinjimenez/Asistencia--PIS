package model;

public class Horario {

    private Integer id;
    private String dia;
    private String horaInicio;
    private String horaFin;

    private Integer asignatura_id;

    public Horario() {
    }

    public Horario(Integer id, String dia, String horaInicio, String horaFin, Integer idAsignatura) {
        this.id = id;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.asignatura_id = idAsignatura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getAsignatura_id() {
        return asignatura_id;
    }

    public void setAsignatura_id(Integer idAsignatura) {
        this.asignatura_id = idAsignatura;
    }

    /**
     * @return the dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return dia + ": " + horaInicio + "-" + horaFin;
    }

    public Boolean comparar(Horario c, String field, Integer type) {

        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return getId() > (c.getId());
                } else if (field.equalsIgnoreCase("id_asignatura")) {
                    return getAsignatura_id() > (c.getAsignatura_id());
                }

            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId() < (c.getId());
                } else if (field.equalsIgnoreCase("id_asignatura")) {
                    return getAsignatura_id() < (c.getAsignatura_id());
                }
            default:
                return false;
        }
    }

    public int comparar(Horario horario, String text, String campo) {
        switch (campo.toLowerCase()) {

            case "id":
                try {
                return Integer.compare(Integer.parseInt(text), horario.getId());
            } catch (Exception e) {
            }
            case "id_asignatura":
               try {
                return Integer.compare(Integer.parseInt(text), horario.getAsignatura_id());
            } catch (Exception e) {
            }

            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }
}
