package model;

public class Carrera {

    private Integer id;
    private String nombre;
    private String area_estudio;
    private String modalidad;
    private String titulo_otorgado;
    private String codigo;

    public Carrera() {
    }

    // Getters and Setters
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

    public String getArea_estudio() {
        return area_estudio;
    }

    public void setArea_estudio(String area_estudio) {
        this.area_estudio = area_estudio;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getTitulo_otorgado() {
        return titulo_otorgado;
    }

    public void setTitulo_otorgado(String titulo_otorgado) {
        this.titulo_otorgado = titulo_otorgado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return nombre;
    }

<<<<<<< HEAD
    public Boolean comparar(Carrera c, String field, Integer type) {

=======
    public Boolean compareTo(Carrera c, String field, Integer type) {
>>>>>>> master
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("nombre")) {
                    return getNombre().compareTo(c.getNombre()) > 0;
                } else if (field.equalsIgnoreCase("area_estudio")) {
                    return getArea_estudio().compareTo(c.getArea_estudio()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id > (c.getId());
<<<<<<< HEAD
                }

=======
                } else if (field.equalsIgnoreCase("codigo")) {
                    return getCodigo().compareTo(c.getCodigo()) > 0;
                } else if (field.equalsIgnoreCase("modalidad")) {
                    return getModalidad().compareTo(c.getModalidad()) > 0;
                } else if (field.equalsIgnoreCase("titulo_otorgado")) {
                    return getTitulo_otorgado().compareTo(c.getTitulo_otorgado()) > 0;
                } else {
                    return false;
                }
>>>>>>> master
            case 0:
                if (field.equalsIgnoreCase("nombre")) {
                    return getNombre().compareTo(c.getNombre()) < 0;
                } else if (field.equalsIgnoreCase("area_estudio")) {
                    return getArea_estudio().compareTo(c.getArea_estudio()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return id < (c.getId());
<<<<<<< HEAD
=======
                } else if (field.equalsIgnoreCase("codigo")) {
                    return getCodigo().compareTo(c.getCodigo()) < 0;
                } else if (field.equalsIgnoreCase("modalidad")) {
                    return getModalidad().compareTo(c.getModalidad()) < 0;
                } else if (field.equalsIgnoreCase("titulo_otorgado")) {
                    return getTitulo_otorgado().compareTo(c.getTitulo_otorgado()) < 0;
                } else {
                    return false;
>>>>>>> master
                }

            default:
                return false;
        }
    }

    public int comparar(Carrera carrera, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "nombre":
                return text.compareTo(carrera.getNombre().toLowerCase());
            case "area_estudio":
                return text.compareTo(carrera.getArea_estudio().toLowerCase());
            case "id":
                try {
                return Integer.compare(Integer.parseInt(text), carrera.getId());
            } catch (Exception e) {
                System.out.println("errorr" + e);
            }
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

}
