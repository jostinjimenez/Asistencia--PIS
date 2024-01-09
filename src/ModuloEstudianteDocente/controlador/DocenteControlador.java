/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloEstudianteDocente.controlador;

import DAO.DataAccessObject;
import model.Docente;
import java.io.IOException;

/**
 *
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

    public Boolean save(Integer id) {
        docente.setId(id);
        return save(docente);
    }

    public void update(Integer pos) throws IOException {
        this.update(docente, pos);
    }

    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        Integer length = list_All().getSize() + 1;
        Integer pos = length.toString().length();
        for (int i = 0; i < (10 - pos); i++) {
            code.append("0");
        }
        code.append(length.toString());
        return code.toString();
    }
}
