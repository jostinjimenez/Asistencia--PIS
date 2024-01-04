package controller;

import tda_listas.ListaEnlazada;
import DAO.DataAccessObject;
import model.Curso;

import java.util.Objects;
import tda_listas.exceptions.VacioExceptions;

public class CursoController extends DataAccessObject<Curso> {

    private Curso curso = new Curso();
    private ListaEnlazada<Curso> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public CursoController() {
        super(Curso.class);
    }

    public Curso getCurso() {
        if (curso == null) {
            curso = new Curso();
        }
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public boolean validar() {
        // Verificar que el Curso no es null
        if (curso == null) {
            return false;
        }

        // Verificar que los campos de Curso no son null
        if (curso.getCodCurso() == null || curso.getNroEstudiante() == null || curso.getNroAula() == null) {
            return false;
        }

        // Verificar que los campos de Curso no están vacíos
        if (curso.getCodCurso().isEmpty() || curso.getNroEstudiante() <= 0 || curso.getNroAula().isEmpty()) {
            return false;
        }

        // Si todas las verificaciones pasan, los datos de Curso son válidos
        return true;
    }

    public Boolean saved() {
        if (validar()) {
            try {
                // Obtener el ID actual antes de guardar
                Integer originalId = curso.getId();

                // Generar un ID único para el Curso
                Integer id = generarID();

                // Asignar el ID al Curso
                curso.setId(id);

                // Guardar el Curso
                boolean isSaved = save(curso);
                if (isSaved) {
                    lista = getLista(); // Actualizar la lista después de guardar
                }

                // Restaurar el ID original
                curso.setId(originalId);

                return isSaved;
            } catch (Exception e) {
                // Manejar la excepción aquí
                System.out.println("Error al guardar el Curso: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Error: los datos del Curso no son válidos");
            return false;
        }
    }

    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        Integer length = list_All().getSize() + 1;
        Integer pos = Integer.toString(length).length();
        for (int i = 0; i < (10 - pos); i++) {
            code.append("0");
        }
        code.append(length.toString());
        return code.toString();
    }

    public ListaEnlazada<Curso> getLista() {
        lista = list_All(); // Actualizar la lista desde la base de datos
        return lista;
    }

    public Boolean update(Integer i) {
        return update(curso, i);
    }

    public void setLista(ListaEnlazada<Curso> lista) {
        this.lista = lista;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    private Integer getLastUsedIdFromDatabase() throws VacioExceptions {
        // Obtener la lista actual de objetos desde el archivo JSON
        ListaEnlazada<Curso> list = list_All();

        // Verificar si la lista está vacía
        if (list.getSize() == 0) {
            return 0; // No hay objetos en la lista, devuelve 0 como valor predeterminado
        }

        // Obtener el último objeto de la lista
        Curso lastObject = list.get(list.getSize() - 1);

        try {
            // Obtener el ID del último objeto y devolverlo
            return (lastObject != null) ? lastObject.getId() : 0;
        } catch (Exception e) {
            System.out.println("Error al obtener el último ID desde la base de datos: " + e.getMessage());
            return 0; // Devolver 0 en caso de error
        }
    }
}
