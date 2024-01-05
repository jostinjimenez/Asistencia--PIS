package model;

import tda_listas.ListaEnlazada;

public class Asignatura {

    // Atributos
    private Integer id;
    private String nombre;
    private Integer horasTotales;
    private Integer codigo;

    private ListaEnlazada<Integer> id_cursas;

    public Asignatura(Integer id, String nombre, Integer horasTotales, Integer codigo, ListaEnlazada<Integer> id_cursas) {
        this.id = id;
        this.nombre = nombre;
        this.horasTotales = horasTotales;
        this.codigo = codigo;
        this.id_cursas = id_cursas;
    }

    // Constructor
    public Asignatura() {
    }

    public Asignatura(Integer nuevoId, String nombre, Integer codigo, Integer horasTotales) {
        this.id = nuevoId;
        this.nombre = nombre;
        this.codigo = codigo;
        this.horasTotales = horasTotales;
    }

    public ListaEnlazada<Integer> getId_cursas() {
        return id_cursas;
    }

    public void setId_cursas(ListaEnlazada<Integer> id_cursas) {
        this.id_cursas = id_cursas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(Integer horasTotales) {
        this.horasTotales = horasTotales;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public boolean comparar(String texto, String campo, Integer type) {
        switch (campo.toLowerCase()) {
            case "nombre":
                return compararCampo(nombre, texto, type);
            case "codigo":
                return compararCampo(codigo.toString(), texto, type);
            // Agrega otros campos según sea necesario
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

    private boolean compararCampo(String campo, String texto, Integer type) {
        // Lógica de comparación para campos String
        // Puedes ajustar esto según tus necesidades específicas
        switch (type) {
            case 0: // Igual
                return campo.equalsIgnoreCase(texto);
            case 1: // Contiene
                return campo.toLowerCase().contains(texto.toLowerCase());
            // Agrega otros tipos de comparación según sea necesario
            default:
                throw new IllegalArgumentException("Tipo de comparación no válido");
        }
    }
}
