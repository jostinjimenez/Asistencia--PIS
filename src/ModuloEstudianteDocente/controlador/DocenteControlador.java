/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloEstudianteDocente.controlador;

import DAO.DataAccessObject;
import model.Docente;
import modulo_1.inicio_sesion.controller.PersonaController;

import java.io.IOException;

/**
 * @author LENOVO
 */
public class DocenteControlador extends DataAccessObject<Docente> {

    private Docente docente = new Docente();

    public DocenteControlador() {
        super(Docente.class);
    }

    public Docente getDocente() {
        if (docente == null) {
            docente = new Docente();
        }
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

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
        pc.save();

        return result;
    }

    public void update(Integer pos) throws IOException {
        this.update(docente, pos);
    }


    public static void main(String[] args) {
        DocenteControlador dc = new DocenteControlador();
        dc.getDocente().setNombre("Juan");
        dc.getDocente().setApellido("Perez");
        dc.save();
    }
}
