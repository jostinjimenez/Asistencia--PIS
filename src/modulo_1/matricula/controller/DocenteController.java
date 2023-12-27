package modulo_1.matricula.controller;

import DAO.DataAccessObject;
import model.Docente;
import tda_listas.ListaEnlazada;

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
        this.docente.setId(generarID());
        return save(docente);
    }

    public Boolean update(Integer index) {
        return update(docente, index);
    }
}

