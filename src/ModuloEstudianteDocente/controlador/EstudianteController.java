package ModuloEstudianteDocente.controlador;

import java.io.IOException;

import DataBase.DataAccessObject;

import java.lang.reflect.Field;

import model.Estudiante;
import modulo_1.inicio_sesion.controller.PersonaController;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import static modulo_1.inicio_sesion.controller.util.Utilidades.getField;

public class EstudianteController extends DataAccessObject<Estudiante> {
    // Atributos
    private ListaEnlazada<Estudiante> estudiantes;
    private Estudiante estudiante = new Estudiante();
    private Integer index = -1;

    // Constructor
    public EstudianteController() {
        super(Estudiante.class);
        this.estudiantes = new ListaEnlazada<>();
    }

    // Getters y Setters
    public ListaEnlazada<Estudiante> getEstudiantes() {
        if (estudiantes.isEmpty()) {
            estudiantes = this.list_All();
        }
        return estudiantes;
    }

    public void setEstudiantes(ListaEnlazada<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    // Metodos
    public Integer save() throws Exception {
        Integer idGenerado = super.save(estudiante);
        if (idGenerado == null) {
            return null;
        }
        PersonaController pc = new PersonaController();
        pc.getPersona().setNombre(estudiante.getNombre());
        pc.getPersona().setApellido(estudiante.getApellido());
        pc.getPersona().setDni(estudiante.getDni());
        pc.getPersona().setCorreo_personal(estudiante.getCorreo_personal());
        pc.getPersona().setFecha_nacimiento(estudiante.getFecha_nacimiento());
        pc.getPersona().setTelefono(estudiante.getTelefono());
        pc.getPersona().setRol_id(3);
        pc.getPersona().setId(idGenerado);
        pc.getPersona().setActivo(true);
        //pc.getPersona().setFoto("user.png");
        pc.save();
        return idGenerado;
    }

    public Boolean update() throws IOException {
        try {
            update(this.estudiante);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ListaEnlazada<Estudiante> ordenarQS(ListaEnlazada<Estudiante> lista, Integer type, String field) throws Exception {
        Estudiante[] personas = lista.toArray();
        Field faux = getField(Estudiante.class, field);
        if (faux != null) {
            quickSort(personas, 0, personas.length - 1, type, field);
        } else {
            throw new Exception("El atributo no existe");
        }
        return lista.toList(personas);
    }

    private void quickSort(Estudiante[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        if (primero < ultimo) {
            int pi = partition(p, primero, ultimo, type, field);

            quickSort(p, primero, pi - 1, type, field);
            quickSort(p, pi + 1, ultimo, type, field);
        }
    }

    private int partition(Estudiante[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        Estudiante pivot = p[ultimo];
        int i = (primero - 1);

        for (int j = primero; j < ultimo; j++) {
            if (p[j].compareTo(pivot, field, type)) {
                i++;
                Estudiante temp = p[i];
                p[i] = p[j];
                p[j] = temp;
            }
        }

        Estudiante aux = p[i + 1];
        p[i + 1] = p[ultimo];
        p[ultimo] = aux;

        return i + 1;
    }

}
