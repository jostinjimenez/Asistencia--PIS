package model;

import tda_listas.ListaEnlazada;

public class Estudiante extends Persona {

    private String direccion;
    private String etnia;
    private Boolean titulo_bachiller;

    private ListaEnlazada<Integer> id_matriculas;

    public Estudiante() {
    }

    public Estudiante(Integer id, String nombre, String apellido, String correoPersonal, String fechaNacimiento, String telefono, String dni, String direccion, String etnia, Boolean titulo_bachiller, ListaEnlazada<Integer> id_matriculas) {
        super(id, nombre, apellido, correoPersonal, fechaNacimiento, telefono, dni);
        this.direccion = direccion;
        this.etnia = etnia;
        this.titulo_bachiller = titulo_bachiller;
        this.id_matriculas = id_matriculas;
    }

    public ListaEnlazada<Integer> getId_matriculas() {
        if (id_matriculas == null) {
            id_matriculas = new ListaEnlazada<>();
        }
        return id_matriculas;
    }

    public void setId_matriculas(ListaEnlazada<Integer> id_matriculas) {
        this.id_matriculas = id_matriculas;
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

    public Boolean comparar(Estudiante estudiante, String field, Integer type) {

        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return getId() > estudiante.getId();
                }
            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId() < estudiante.getId();
                }
            default:
                return false;
        }
    }

    public int comparar(Estudiante estudiante, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "id":
               return Integer.compare(Integer.parseInt(text),estudiante.getId());
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }
}
