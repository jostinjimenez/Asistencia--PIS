package model;

public class Cursa {

    private Integer id;
    private String paralelo;

    private Integer idMatricula;
    private Integer idAsignatura;
    private Integer idPeriodoAcademico;
    private Integer idDocente;

    public Cursa() {
    }

    public Cursa(Integer id, String paralelo, Integer idMatricula, Integer idAsignatura, Integer idPeriodoAcademico, Integer idDocente) {
        this.id = id;
        this.paralelo = paralelo;
        this.idMatricula = idMatricula;
        this.idAsignatura = idAsignatura;
        this.idPeriodoAcademico = idPeriodoAcademico;
        this.idDocente = idDocente;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Integer getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
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

    public Integer getIdPeriodoAcademico() {
        return idPeriodoAcademico;
    }

    public void setIdPeriodoAcademico(Integer idPeriodoAcademico) {
        this.idPeriodoAcademico = idPeriodoAcademico;
    }

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
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
                    return getIdMatricula() > (cursa.getIdMatricula());
                } else if (field.equalsIgnoreCase("id_docente")) {
                    return getIdDocente() > (cursa.getIdDocente());
                }
            case 0:
                if (field.equalsIgnoreCase("paralelo")) {
                    return getParalelo().compareTo(cursa.getParalelo()) < 0;
                } else if (field.equalsIgnoreCase("id_matricula")) {
                    return getIdMatricula() < (cursa.getIdMatricula());
                } else if (field.equalsIgnoreCase("id_docente")) {
                    return getIdDocente() < (cursa.getIdDocente());
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
                return Integer.compare(Integer.parseInt(text), cursa.getIdMatricula());
            } catch (Exception e) {
            }
            case "id_docente":
                try {
                return Integer.compare(Integer.parseInt(text), cursa.getIdDocente());
            } catch (Exception e) {
            }
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

}
