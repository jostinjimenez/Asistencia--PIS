package model.catalogo;

public enum TipoFalta {
    JUSTIFICADA("Justificada"),
    INJUSTIFICADA("Injustificada"),
    ASISTIO("Asistio");
    private String nombre;

    TipoFalta(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
