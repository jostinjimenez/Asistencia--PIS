package model;

import tda_listas.ListaEnlazada;

public class Estudiante extends Persona {
    private String direccion;
    private String etnia;
    private Boolean titulo_bachiller;

    private ListaEnlazada<Matricula> matriculas;

    public Estudiante(Integer id, String nombre, String apellido, String correoPersonal, String fechaNacimiento, String telefono, String dni, String direccion, String etnia, Boolean titulo_bachiller) {
        super(id, nombre, apellido, correoPersonal, fechaNacimiento, telefono, dni);
        this.direccion = direccion;
        this.etnia = etnia;
        this.titulo_bachiller = titulo_bachiller;
    }

    public ListaEnlazada<Matricula> getMatriculas() {
        if (matriculas == null)
            matriculas = new ListaEnlazada<>();
        return matriculas;
    }

    public void setMatriculas(ListaEnlazada<Matricula> matriculas) {
        this.matriculas = matriculas;
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

    public Boolean getTitulo_bachiller() {
        return titulo_bachiller;
    }

    public void setTitulo_bachiller(Boolean titulo_bachiller) {
        this.titulo_bachiller = titulo_bachiller;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
