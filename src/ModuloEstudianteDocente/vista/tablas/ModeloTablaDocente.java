package ModuloEstudianteDocente.vista.tablas;

import javax.swing.table.AbstractTableModel;

import model.Docente;
import model.Persona;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;


import java.util.HashMap;
import java.util.Map;

import static Controller.Util.Utilidades.getPersonaStatic;

public class ModeloTablaDocente extends AbstractTableModel {
    private ListaEnlazada<Docente> docentes = new ListaEnlazada<>();
    private Map<Integer, Persona> personas;



    public ListaEnlazada<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(ListaEnlazada<Docente> docentes) {
        this.docentes = docentes;
        this.personas = new HashMap<>();
        for (Docente docente : docentes) {
            try {
                Persona p = getPersonaStatic(docente.getId());
                this.personas.put(docente.getId(), p);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public int getRowCount() {
        return docentes.getSize();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int row, int col) {
        try {
            Docente doce = docentes.get(row);
            Persona per = this.personas.get(doce.getId());
            return switch (col) {
                case 0 -> doce.getCodigo_empleado();
                case 1 -> (per != null) ? per.getNombre(): " ";
                case 2 -> (per != null) ? per.getApellido(): " ";
                case 3 -> (per != null) ? per.getDni() : " ";
                case 4 -> (per != null) ? per.getCorreo_personal() : " ";
                case 5 -> doce.getExperiencia();
                default -> null;
            };
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        return switch (col) {
            case 0 -> "Codigo";
            case 1 -> "Nombre";
            case 2 -> "Apellido";
            case 3 -> "DNI";
            case 4 -> "Correo";
            case 5 -> "Experiencia";
            default -> null;
        };
    }

    public Docente getDocente(int fila) throws VacioExceptions {
        return docentes.get(fila);
    }
}
