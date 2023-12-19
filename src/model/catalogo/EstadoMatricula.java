package model.catalogo;

public enum EstadoMatricula {
    MATRICULADO("Matriculado"),
    RETIRADO("Retirado"),
    APROBADO("Aprobado"),
    REPROBADO("Reprobado");

    private String nombre;

    EstadoMatricula(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

