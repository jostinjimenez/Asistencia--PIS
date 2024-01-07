package model.catalogo;

public enum EstadoMatricula {
    MATRICULADO("Matriculado"),
    NO_MATRICULADO("No Matriculado"),
    EN_ESPERA("En Espera");

    private String nombre;

    EstadoMatricula(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

