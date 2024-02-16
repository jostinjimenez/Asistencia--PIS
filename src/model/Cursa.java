package model;

public class Cursa {

    private Integer id;
    private String paralelo;

    private Integer matricula_id;
    private Integer asignatura_id;
    private Integer docente_id;

    public Cursa() {
    }

    public Integer getMatricula_id() {
        return matricula_id;
    }

    public void setMatricula_id(Integer matricula_id) {
        this.matricula_id = matricula_id;
    }

    public Integer getAsignatura_id() {
        return asignatura_id;
    }

    public void setAsignatura_id(Integer asignatura_id) {
        this.asignatura_id = asignatura_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public Integer getDocente_id() {
        return docente_id;
    }

    public void setDocente_id(Integer docente_id) {
        this.docente_id = docente_id;
    }

    @Override
    public String toString() {
        return paralelo;
    }

    public Boolean comparar(Cursa cursa, String field, Integer type) {

        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("paralelo")) {
                    return getParalelo().compareTo(cursa.getParalelo()) > 0;
                } else if (field.equalsIgnoreCase("id_matricula")) {
                    return getMatricula_id() > (cursa.getMatricula_id());
                } else if (field.equalsIgnoreCase("id_docente")) {
                    return getDocente_id() > (cursa.getDocente_id());
                }
            case 0:
                if (field.equalsIgnoreCase("paralelo")) {
                    return getParalelo().compareTo(cursa.getParalelo()) < 0;
                } else if (field.equalsIgnoreCase("id_matricula")) {
                    return getMatricula_id() < (cursa.getMatricula_id());
                } else if (field.equalsIgnoreCase("id_docente")) {
                    return getDocente_id() < (cursa.getDocente_id());
                }
            default:
                return false;
        }
    }

    public int comparar(Cursa cursa, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "paralelo":
                return text.compareTo(cursa.getParalelo().toLowerCase());

            case "id_matricula":
               try {
                return Integer.compare(Integer.parseInt(text), cursa.getMatricula_id());
            } catch (Exception e) {
            }
            case "id_docente":
                try {
                return Integer.compare(Integer.parseInt(text), cursa.getDocente_id());
            } catch (Exception e) {
            }
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

}
