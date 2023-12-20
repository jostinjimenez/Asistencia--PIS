package modulo_1.usuario_rol.controller;

import DAO.DataAccessObject;
import model.Cuenta;
import model.Persona;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import java.io.FileOutputStream;

public class PersonaController extends DataAccessObject<Persona> {
    // Atributos
    private ListaEnlazada<Persona> personas;
    private Persona persona = new Persona();
    private Integer index = -1;

    // Constructor
    public PersonaController() {
        super(Persona.class);
        this.personas = this.list_All();
    }

    // Getters y Setters
    public ListaEnlazada<Persona> getPersonas() {
        ListaEnlazada<Persona> personasActivas = new ListaEnlazada<>();
        for (Persona persona : personas) {
            if (persona.isActivo()) {
                personasActivas.add(persona);
            }
        }
        return personasActivas;
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

    public Boolean delete(Integer idPersona) {
        for (int i = 0; i < personas.getSize(); i++) {
            Persona persona = null;
            try {
                persona = personas.get(i);
            } catch (VacioExceptions e) {
                throw new RuntimeException(e);
            }
            if (persona.getId().equals(idPersona)) {
                try {
                    persona.setActivo(false);
                    this.xStream.toXML(personas, new FileOutputStream(URL));
                    return true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        }
        return false;
    }
}
