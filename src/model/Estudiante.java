package model;

public class Estudiante extends Persona {
    private String direccion;
    private String etnia;
    private Double titulo_bachiller;

    public Estudiante(Integer id, String nombre, String apellido, String correoPersonal, String fechaNacimiento, String telefono, String dni, String direccion, String etnia, Double titulo_bachiller) {
        super(id, nombre, apellido, correoPersonal, fechaNacimiento, telefono, dni);
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

    @Override
    public String toString() {
        return super.toString();
    }
}
