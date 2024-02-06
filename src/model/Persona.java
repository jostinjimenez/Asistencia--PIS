package model;

import java.util.Date;

public class Persona {

    private Integer id;
    private String nombre;
    private String apellido;
    private String correo_personal;
    private Date fecha_nacimiento;
    private String telefono;
    private String dni;
    private Boolean activo;
    private String foto;

    private Integer rol_id;

    public Persona() {
    }

    // Getters y Setters

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo_personal() {
        return correo_personal;
    }

    public void setCorreo_personal(String correo_personal) {
        this.correo_personal = correo_personal;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getRol_id() {
        return rol_id;
    }

    public void setRol_id(Integer rol_id) {
        this.rol_id = rol_id;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }

    public Boolean compareTo(Persona c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("nombre")) {
                    return this.getNombre().toLowerCase().compareTo(c.getNombre().toLowerCase()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId() > (c.getId());
                } else if (field.equalsIgnoreCase("apellido")) {
                    return this.getApellido().toLowerCase().compareTo(c.getApellido().toLowerCase()) > 0;
                } else if (field.equalsIgnoreCase("correo_personal")) {
                    return this.getCorreo_personal().compareTo(c.getCorreo_personal().toLowerCase()) > 0;
                } else if (field.equalsIgnoreCase("telefono")) {
                    return this.getTelefono().compareTo(c.getTelefono()) > 0;
                } else if (field.equalsIgnoreCase("dni")) {
                    return this.getDni().compareTo(c.getDni()) > 0;
                } else if (field.equalsIgnoreCase("activo")) {
                    return this.getActivo() == c.getActivo();
                } else if (field.equalsIgnoreCase("idRol")) {
                    return this.getRol_id() > (c.getRol_id());
                }
            case 0:
                if (field.equalsIgnoreCase("nombre")) {
                    return this.getNombre().toLowerCase().compareTo(c.getNombre().toLowerCase()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId() < (c.getId());
                } else if (field.equalsIgnoreCase("apellido")) {
                    return this.getApellido().toLowerCase().compareTo(c.getApellido().toLowerCase()) < 0;
                } else if (field.equalsIgnoreCase("correo_personal")) {
                    return this.getCorreo_personal().compareTo(c.getCorreo_personal().toLowerCase()) < 0;
                } else if (field.equalsIgnoreCase("telefono")) {
                    return this.getTelefono().compareTo(c.getTelefono()) < 0;
                } else if (field.equalsIgnoreCase("dni")) {
                    return this.getDni().compareTo(c.getDni()) < 0;
                } else if (field.equalsIgnoreCase("activo")) {
                    return this.getActivo() != c.getActivo();
                } else if (field.equalsIgnoreCase("idRol")) {
                    return this.getRol_id() < (c.getRol_id());
                }
            default:
                return false;
        }
    }
}
