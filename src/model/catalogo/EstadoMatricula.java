package model.catalogo;

public enum EstadoMatricula {
    MATRICULADO("Matriculado"),
    APROBADO("Aprobado"),
    RETIRADO("Retirado");

    private final String nombre;

    EstadoMatricula(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

