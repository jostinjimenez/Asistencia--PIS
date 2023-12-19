package modulo_1.usuario_rol.controller;

import DAO.DataAccessObject;
import model.Cuenta;
import model.Persona;
import tda_listas.ListaEnlazada;

public class PersonaController extends DataAccessObject<Persona> {
    // Atributos
    private ListaEnlazada<Persona> personas;
    private Persona persona = new Persona();
    private Integer index = -1;

    // Constructor
    public PersonaController() {
        super(Persona.class);
        this.personas = new ListaEnlazada<>();
    }

    // Getters y Setters
    public ListaEnlazada<Persona> getPersonas() {
        if (personas.isEmpty()) {
            personas = this.list_All();
        }
        return personas;
    }

    public void setPersonas(ListaEnlazada<Persona> personas) {
        this.personas = personas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    // Metodos
    public Boolean save() {
        this.persona.setId(generarID());
        return save(persona);
    }

    public Boolean update(Integer index) {
        return update(persona, index);
    }
}
