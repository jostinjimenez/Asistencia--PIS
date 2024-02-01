package model;

public class Cuenta {

    // Atributos
    private Integer id;
    private String correo;
    private String clave;

    private Integer idPersona;

    //Constructor
    public Cuenta() {
    }

    public Cuenta(Integer id, String correo, String clave, Boolean estado, Integer idPersona) {
        this.id = id;
        this.correo = correo;
        this.clave = clave;
        this.idPersona = idPersona;
    }

    //Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public String toString() {
        return "Cuenta{"
                  + "correo='" + correo + '\''
                  + '}';
    }

    public Boolean compareTo(Cuenta c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("correo")) {
                    return this.getCorreo().compareTo(c.getCorreo()) > 0;
                }
                else if (field.equalsIgnoreCase("id")) {
                    return this.getId() > (c.getId());

                }
                else if (field.equalsIgnoreCase("clave")) {
                    return this.getClave().compareTo(c.getClave()) > 0;
                }
                else if (field.equalsIgnoreCase("idPersona")) {
                    return this.getIdPersona() > (c.getIdPersona());
                }
            case 0:
                if (field.equalsIgnoreCase("correo")) {
                    return this.getCorreo().compareTo(c.getCorreo()) < 0;
                }
                else if (field.equalsIgnoreCase("id")) {
                    return this.getId() < (c.getId());
                }
                else if (field.equalsIgnoreCase("clave")) {
                    return this.getClave().compareTo(c.getClave()) < 0;
                }
                else if (field.equalsIgnoreCase("idPersona")) {
                    return this.getIdPersona() < (c.getIdPersona());
                }
            default:
                return false;
        }
    }
}
