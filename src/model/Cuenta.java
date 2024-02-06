package model;

public class Cuenta {

    // Atributos
    private Integer id;
    private String correo_institucional;
    private String clave;

    private Integer persona_id;

    //Constructor
    public Cuenta() {
    }

    public Cuenta(Integer id, String correo_institucional, String clave, Boolean estado, Integer persona_id) {
        this.id = id;
        this.correo_institucional = correo_institucional;
        this.clave = clave;
        this.persona_id = persona_id;
    }

    //Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo_institucional() {
        return correo_institucional;
    }

    public void setCorreo_institucional(String correo_institucional) {
        this.correo_institucional = correo_institucional;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getPersona_id() {
        return persona_id;
    }

    public void setPersona_id(Integer persona_id) {
        this.persona_id = persona_id;
    }

    @Override
    public String toString() {
        return "Cuenta{"
                  + "correo='" + correo_institucional + '\''
                  + '}';
    }

    public Boolean compareTo(Cuenta c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("correo")) {
                    return this.getCorreo_institucional().compareTo(c.getCorreo_institucional()) > 0;
                }
                else if (field.equalsIgnoreCase("id")) {
                    return this.getId() > (c.getId());

                }
                else if (field.equalsIgnoreCase("clave")) {
                    return this.getClave().compareTo(c.getClave()) > 0;
                }
                else if (field.equalsIgnoreCase("idPersona")) {
                    return this.getPersona_id() > (c.getPersona_id());
                }
            case 0:
                if (field.equalsIgnoreCase("correo")) {
                    return this.getCorreo_institucional().compareTo(c.getCorreo_institucional()) < 0;
                }
                else if (field.equalsIgnoreCase("id")) {
                    return this.getId() < (c.getId());
                }
                else if (field.equalsIgnoreCase("clave")) {
                    return this.getClave().compareTo(c.getClave()) < 0;
                }
                else if (field.equalsIgnoreCase("idPersona")) {
                    return this.getPersona_id() < (c.getPersona_id());
                }
            default:
                return false;
        }
    }
}
