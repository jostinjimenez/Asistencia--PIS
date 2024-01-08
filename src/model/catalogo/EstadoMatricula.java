package model.catalogo;

public enum EstadoMatricula {
    MATRICULADO("Matriculado"),
    APROBADO("Aprobado"),
    REPROBADO("Reprobado"),
    RETIRADO("Retirado");

    private String nombre;

    EstadoMatricula(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

