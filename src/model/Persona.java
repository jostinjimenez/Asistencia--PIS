package model;

public class Persona {

    private Integer id;
    private String nombre;
    private String apellido;
    private String correo_personal;
    private String fecha_nacimiento;
    private String telefono;
    private String dni;
    private boolean activo;

    private Integer idRol;
    private Integer idCuenta;

    public Persona(Integer id, String nombre, String apellido, String correoPersonal, String fechaNacimiento, String telefono, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo_personal = correoPersonal;
        this.fecha_nacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.dni = dni;
    }

    public Persona() {
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
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

    public String getCorreo_personal() {
        return correo_personal;
    }

    public void setCorreo_personal(String correo_personal) {
        this.correo_personal = correo_personal;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
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

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido;
    }

    public Boolean compareTo(Persona c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("nombre")) {
                    return this.getNombre().compareTo(c.getNombre()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId() > (c.getId());
                } else if (field.equalsIgnoreCase("apellido")) {
                    return this.getApellido().compareTo(c.getApellido()) > 0;
                } else if (field.equalsIgnoreCase("correo_personal")) {
                    return this.getCorreo_personal().compareTo(c.getCorreo_personal()) > 0;
                } else if (field.equalsIgnoreCase("fecha_nacimiento")) {
                    return this.getFecha_nacimiento().compareTo(c.getFecha_nacimiento()) > 0;
                } else if (field.equalsIgnoreCase("telefono")) {
                    return this.getTelefono().compareTo(c.getTelefono()) > 0;
                } else if (field.equalsIgnoreCase("dni")) {
                    return this.getDni().compareTo(c.getDni()) > 0;
                } else if (field.equalsIgnoreCase("activo")) {
                    return this.isActivo() == c.isActivo();
                } else if (field.equalsIgnoreCase("idRol")) {
                    return this.getIdRol() > (c.getIdRol());
                } else if (field.equalsIgnoreCase("idCuenta")) {
                    return this.getIdCuenta() > (c.getIdCuenta());
                }
            case 0:
                if (field.equalsIgnoreCase("nombre")) {
                    return this.getNombre().compareTo(c.getNombre()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId() < (c.getId());
                } else if (field.equalsIgnoreCase("apellido")) {
                    return this.getApellido().compareTo(c.getApellido()) < 0;
                } else if (field.equalsIgnoreCase("correo_personal")) {
                    return this.getCorreo_personal().compareTo(c.getCorreo_personal()) < 0;
                } else if (field.equalsIgnoreCase("fecha_nacimiento")) {
                    return this.getFecha_nacimiento().compareTo(c.getFecha_nacimiento()) < 0;
                } else if (field.equalsIgnoreCase("telefono")) {
                    return this.getTelefono().compareTo(c.getTelefono()) < 0;
                } else if (field.equalsIgnoreCase("dni")) {
                    return this.getDni().compareTo(c.getDni()) < 0;
                } else if (field.equalsIgnoreCase("activo")) {
                    return this.isActivo() != c.isActivo();
                } else if (field.equalsIgnoreCase("idRol")) {
                    return this.getIdRol() < (c.getIdRol());
                } else if (field.equalsIgnoreCase("idCuenta")) {
                    return this.getIdCuenta() < (c.getIdCuenta());
                }
            default:
                return false;
        }
    }
}
