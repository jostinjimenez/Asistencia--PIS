package model;

public class Estudiante extends Persona{
    private String direccion;
    private String etnia;
    private Double titulo_bachiller;

    public Estudiante() {
    }

    public Estudiante(String direccion, String etnia, Double titulo_bachiller, Integer id, String nombre, String correo_personal, String fecha_nacimiento, String telefono, String dni) {
        super(id, nombre, correo_personal, fecha_nacimiento, telefono, dni);
        this.direccion = direccion;
        this.etnia = etnia;
        this.titulo_bachiller = titulo_bachiller;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEtnia() {
        return etnia;
    }

    public void setEtnia(String etnia) {
        this.etnia = etnia;
    }

    public Double getTitulo_bachiller() {
        return titulo_bachiller;
    }

    public void setTitulo_bachiller(Double titulo_bachiller) {
        this.titulo_bachiller = titulo_bachiller;
    }
}
