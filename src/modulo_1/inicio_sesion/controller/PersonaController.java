package modulo_1.inicio_sesion.controller;

import DAO.DataAccessObject;
import model.Cuenta;
import model.Persona;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import java.io.FileOutputStream;

import static modulo_1.inicio_sesion.controller.util.Utilidades.getFieldValue;

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


    // Ordenar por QuickSort
    public ListaEnlazada<Persona> ordenarQS(ListaEnlazada<Persona> lista, Integer type, String field) throws Exception {
        Persona[] personas = lista.toArray();
        quickSort(personas, 0, personas.length - 1, type, field);
        return lista.toList(personas);
    }

    private void quickSort(Persona[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        if (primero < ultimo) {
            int pi = dividir(p, primero, ultimo, type, field);

            quickSort(p, primero, pi - 1, type, field);
            quickSort(p, pi + 1, ultimo, type, field);
        }
    }

    private int dividir(Persona[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        Persona pivot = p[ultimo];
        int i = (primero - 1);

        for (int j = primero; j < ultimo; j++) {
            if (type == 1) {
                if (getFieldValue(p[j], field).compareTo(getFieldValue(pivot, field)) <= 0) {
                    i++;

                    Persona temp = p[i];
                    p[i] = p[j];
                    p[j] = temp;
                }
            } else {
                if (getFieldValue(p[j], field).compareTo(getFieldValue(pivot, field)) >= 0) {
                    i++;

                    Persona temp = p[i];
                    p[i] = p[j];
                    p[j] = temp;
                }
            }
        }

        Persona aux = p[i + 1];
        p[i + 1] = p[ultimo];
        p[ultimo] = aux;

        return i + 1;
    }
}
