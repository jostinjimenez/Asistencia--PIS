/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloEstudianteDocente.controlador;

import DAO.DataAccessObject;
import model.Docente;
import model.Estudiante;
import modulo_1.inicio_sesion.controller.PersonaController;
import tda_listas.ListaEnlazada;

import java.io.IOException;
import java.lang.reflect.Field;

import static modulo_1.inicio_sesion.controller.util.Utilidades.getField;

/**
 * @author LENOVO
 */
public class DocenteController extends DataAccessObject<Docente> {

    // Atributos
    private ListaEnlazada<Docente> docentes;
    private Docente docente = new Docente();
    private Integer index = -1;

    // Constructor
    public DocenteController() {
        super(Docente.class);
        this.docentes = new ListaEnlazada<>();
    }

    // Getters y Setters
    public ListaEnlazada<Docente> getDocentes() {
        if (docentes.isEmpty()) {
            docentes = this.list_All();
        }
        return docentes;
    }

    public void setDocentes(ListaEnlazada<Docente> docentes) {
        this.docentes = docentes;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    // Metodos
    public Boolean save() {
        Integer id = generarID();

        docente.setId(id);
        Boolean result = save(docente);

        PersonaController pc = new PersonaController();
        pc.getPersona().setNombre(docente.getNombre());
        pc.getPersona().setApellido(docente.getApellido());
        pc.getPersona().setDni(docente.getDni());
        pc.getPersona().setCorreo_personal(docente.getCorreo_personal());
        pc.getPersona().setFecha_nacimiento(docente.getFecha_nacimiento());
        pc.getPersona().setTelefono(docente.getTelefono());
        pc.getPersona().setIdRol(3);
        pc.getPersona().setId(id);
        pc.getPersona().setActivo(true);
        pc.getPersona().setFoto("user.png");
        pc.save();

        return result;
    }

    public Boolean update() throws IOException {
        return update(docente, buscarIndex(docente.getId() + 1));
    }

    public Integer buscarIndex(Integer id) {
        int ind = 0;
        if (list_All().isEmpty()) {
            Docente[] personas1 = list_All().toArray();
            for (Docente persona : personas1) {
                if (id.intValue() == persona.getId().intValue()) {
                    ind = 1;
                    break;
                }
            }
        }
        return ind;
    }

    public ListaEnlazada<Docente> ordenarQS(ListaEnlazada<Docente> lista, Integer type, String field) throws Exception {
        Docente[] personas = lista.toArray();
        Field faux = getField(Docente.class, field);
        if (faux != null) {
            quickSort(personas, 0, personas.length - 1, type, field);
        } else {
            throw new Exception("El atributo no existe");
        }
        return lista.toList(personas);
    }

    private void quickSort(Docente[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        if (primero < ultimo) {
            int pi = partition(p, primero, ultimo, type, field);

            quickSort(p, primero, pi - 1, type, field);
            quickSort(p, pi + 1, ultimo, type, field);
        }
    }

    private int partition(Docente[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        Docente pivot = p[ultimo];
        int i = (primero - 1);

        for (int j = primero; j < ultimo; j++) {
            if (p[j].compareTo(pivot, field, type)) {
                i++;
                Docente temp = p[i];
                p[i] = p[j];
                p[j] = temp;
            }
        }

        Docente aux = p[i + 1];
        p[i + 1] = p[ultimo];
        p[ultimo] = aux;

        return i + 1;
    }

}
