package model;

import java.util.Date;
import tda_listas.ListaEnlazada;

public class Estudiante extends Persona {

    private String direccion;
    private String etnia;
    private Boolean titulo_bachiller;


    public Estudiante() {
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
