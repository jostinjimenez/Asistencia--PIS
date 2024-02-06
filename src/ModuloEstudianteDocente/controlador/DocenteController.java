package ModuloEstudianteDocente.controlador;

import DataBase.DataAccessObject;
import model.Docente;
import modulo_1.inicio_sesion.controller.PersonaController;
import tda_listas.ListaEnlazada;

import java.io.IOException;
import java.lang.reflect.Field;

import static modulo_1.inicio_sesion.controller.util.Utilidades.getField;

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
    public Boolean save() throws Exception {
        return super.saveB(this.docente);
    }

    public Boolean update() throws IOException {
        try {
            update(this.docente);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

    public static void main(String[] args) {
        DocenteController dc = new DocenteController();
        Docente d = new Docente();

        d.setId(1);
        d.setNombre("Juan");
        d.setApellido("Perez");
        d.setDni("1150696977");
        d.setCorreo_personal("sdasdasd");
        d.setTelefono("123456789");
        d.setActivo(true);
        d.setFoto("user.png");
        d.setGrado_academico("Ingeniero");
        d.setActivo(true);
        d.setFoto("user.png");
        dc.setDocente(d);

    }

}
