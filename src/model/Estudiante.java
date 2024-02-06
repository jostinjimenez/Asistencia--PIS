package model;

import java.util.Date;
import tda_listas.ListaEnlazada;

public class Estudiante extends Persona {

    private String etnia;
    private Boolean titulo_bachiller;
    private String nacionalidad;
    private String canton;
    private String provincia;
    private String calle_direccion;


    public Estudiante() {
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCalle_direccion() {
        return calle_direccion;
    }

    public void setCalle_direccion(String calle_direccion) {
        this.calle_direccion = calle_direccion;
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
