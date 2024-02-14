package ModuloEstudianteDocente.vista.tablas;

import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;

import model.Docente;
import model.Persona;
import modulo_1.inicio_sesion.controller.PersonaController;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;


import static modulo_1.inicio_sesion.controller.util.Utilidades.getPersonaStatic;

public class ModeloTablaDocente extends AbstractTableModel {
    private ListaEnlazada<Docente> docentes = new ListaEnlazada<>();


    public ListaEnlazada<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(ListaEnlazada<Docente> docentes) {
        this.docentes = docentes;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
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
        Docente doce = null;
        PersonaController pc = new PersonaController();
        try {
            doce = docentes.get(row);
            Persona per = pc.find(doce.getId());
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
            case 0 -> "Codigo Empleado";
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
