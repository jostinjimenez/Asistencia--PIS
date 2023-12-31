package model.catalogo;

public enum TipoFalta {
    JUSTIFICADA("Justificada"),
    INJUSTIFICADA("Injustificada"),
    ASISTIO("Asistio"),
    NULL ("Null");

    private TipoFalta() {
    }
    private String nombre;

     TipoFalta(String 
    nombre

    
        ) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
