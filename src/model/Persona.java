package model;

public class Persona {

    private Integer id;
    private String nombre;
    private String correo_personal;
    private String fecha_nacimiento;
    private String telefono;
    private String dni;

    private Integer id_cuenta;
    private Integer id_rol;

    public Persona() {
    }

    public Persona(Integer id, String nombre, String correo_personal, String fecha_nacimiento, String telefono, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.correo_personal = correo_personal;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono = telefono;
        this.dni = dni;
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

}
