package model;

import java.util.Objects;

public class Malla {

    private Integer id;
    private String duracion;
    private String descripcion;
    private String nombreSilabo; // Nombre del archivo PDF
    private byte[] silabo; // Este atributo representa el archivo PDF

    public Malla() {
    }

    public Malla(Integer id, String duracion, String descripcion) {
        this.id = id;
        this.duracion = duracion;
        this.descripcion = descripcion;
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

    @Override
    public String toString() {
        return descripcion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Asignatura that = (Asignatura) obj;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isValid() {
        // Verificar que los campos necesarios no sean null o vacíos
        return duracion != null && !duracion.isEmpty()
                && descripcion != null && !descripcion.isEmpty()
                && nombreSilabo != null && !nombreSilabo.isEmpty();
    }
}
