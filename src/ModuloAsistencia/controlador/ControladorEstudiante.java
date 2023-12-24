/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloAsistencia.controlador;

import java.io.IOException;
import DAO.DataAccessObject;
import model.Estudiante;

/**
 *
 * @author LENOVO
 */
public class ControladorEstudiante extends DataAccessObject<Estudiante>{
    private Estudiante estudiante = new Estudiante();
    
    
    
    public ControladorEstudiante() {
        super(Estudiante.class);
    }

    public Estudiante getEstudiante() {
         if (estudiante == null) {
            estudiante = new Estudiante();
        }
        return estudiante;
    }

    public void setSensor(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    public Boolean save() {
        estudiante.setId(generarID());
        return save(estudiante);
    }

    public void update(Integer pos) throws IOException{
        this.update(estudiante, pos);
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
