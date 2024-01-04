package controller;

import DAO.DataAccessObject;
import java.util.Comparator;
import model.Asignatura;
import tda_listas.ListaEnlazada;

import java.util.Objects;
import tda_listas.exceptions.VacioExceptions;

public class AsignaturaController extends DataAccessObject<Asignatura> {

    private Asignatura asignatura = new Asignatura();
    private ListaEnlazada<Asignatura> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public AsignaturaController() {
        super(Asignatura.class);
    }

    public Asignatura getAsignatura() {
        if (asignatura == null) {
            asignatura = new Asignatura();
        }
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public boolean validar() {
        // Verificar que la Asignatura no es null
        if (asignatura == null) {
            return false;
        }

        // Verificar que los campos de la Asignatura no son null
        if (asignatura.getNombre() == null || asignatura.getCodigo() == null || asignatura.getHorasTotales() == null) {
            return false;
        }

        // Verificar que los campos de la Asignatura no están vacíos
        if (asignatura.getNombre().isEmpty() || asignatura.getCodigo().isEmpty() || asignatura.getHorasTotales() <= 0) {
            return false;
        }

        // Si todas las verificaciones pasan, los datos de la Asignatura son válidos
        return true;
    }

    public Boolean saved() {
        if (validar()) {
            try {
                // Obtener el ID actual antes de guardar
                Integer originalId = asignatura.getId();

                // Generar un id único para la Asignatura
                Integer id = getLastUsedIdFromDatabase();

                // Asignar el id a la Asignatura
                asignatura.setId(id);

                // Guardar la Asignatura
                boolean isSaved = save(asignatura);
                if (isSaved) {
                    lista = getLista(); // Actualizar la lista después de guardar
                }

                // Restaurar el ID original
                asignatura.setId(originalId);

                return isSaved;
            } catch (Exception e) {
                // Manejar la excepción aquí
                System.out.println("Error al guardar la Asignatura: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Error: los datos de la Asignatura no son válidos");
            return false;
        }
    }

    public String generatedCode() throws VacioExceptions {
        return String.format("%04d", getLastUsedIdFromDatabase() + 1);
    }

    public ListaEnlazada<Asignatura> getLista() {
        lista = list_All(); // Actualizar la lista desde la base de datos
        return lista;
    }

    public Boolean update(Integer i) {
        return update(asignatura, i);
    }

    public void setLista(ListaEnlazada<Asignatura> lista) {
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
        ListaEnlazada<Asignatura> list = list_All();

        // Verificar si la lista está vacía
        if (list.getSize() == 0) {
            return 0; // No hay objetos en la lista, devuelve 0 como valor predeterminado
        }

        // Obtener el último objeto de la lista
        Asignatura lastObject = list.get(list.getSize() - 1);

        try {
            // Obtener el ID del último objeto y devolverlo
            return (lastObject != null) ? lastObject.getId() : 0;
        } catch (Exception e) {
            System.out.println("Error al obtener el último ID desde la base de datos: " + e.getMessage());
            return 0; // Devolver 0 en caso de error
        }
    }

    public int buscar(String criterioBusqueda, Comparator<Asignatura> comparador) throws VacioExceptions {
        return getLista().busquedaLineal(new Asignatura(criterioBusqueda), comparador);
    }

    public void ordenar(Comparator<Asignatura> comparador) throws VacioExceptions {
        getLista().quicksort(comparador);
    }
}
