package model;

public class Malla {
    private Integer id;
    private String duracion;
    private String descripcion;
    private String nombreSilabo; // Nombre del archivo PDF
    private byte[] silabo; // Este atributo representa el archivo PDF

    public Malla() {
    }

    public Integer getId() {
        return id;
    }

    public Malla(Integer id, String duracion, String descripcion, String nombreSilabo, byte[] silabo) {
        this.id = id;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.nombreSilabo = nombreSilabo;
        this.silabo = silabo;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getSilabo() {
        return silabo;
    }

    public void setSilabo(byte[] silabo) {
        this.silabo = silabo;
    }

    public String getNombreSilabo() {
        return nombreSilabo;
    }

    public void setNombreSilabo(String nombreSilabo) {
        this.nombreSilabo = nombreSilabo;
    }
    
}