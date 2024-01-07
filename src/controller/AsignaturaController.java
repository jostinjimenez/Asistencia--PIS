package controller;

import DAO.DataAccessObject;
import tda_listas.ListaEnlazada;
import tda_listas.Nodo;
import tda_listas.exceptions.VacioExceptions;
import java.util.Comparator;
import java.util.Iterator;
import model.Asignatura;

public class AsignaturaController extends DataAccessObject<Asignatura> {

    private Asignatura asignatura = new Asignatura();
    private ListaEnlazada<Asignatura> lista;
    private static Integer lastUsedId = 0;

    public AsignaturaController() {
        super(Asignatura.class);
        lastUsedId = generarID();
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

        // Verificar que los campos de la Asignatura no son null o vacíos
        return asignatura.isValid();
    }

    public Boolean saved() {
        if (validar()) {
            try {
                // Obtener los datos del formulario
                String nombre = asignatura.getNombre();
                Integer codigo = asignatura.getCodigo();
                Integer horasTotales = asignatura.getHorasTotales();

                // Incrementar el lastUsedId antes de asignarlo al nuevo ID
                Integer id = ++lastUsedId;

                // Crear una nueva instancia de Asignatura con los valores correctos
                Asignatura asignaturaGuardar = new Asignatura();
                asignaturaGuardar.setId(id);
                asignaturaGuardar.setNombre(nombre);
                asignaturaGuardar.setCodigo(codigo);
                asignaturaGuardar.setHorasTotales(horasTotales);

                // Guardar la Asignatura
                boolean isSaved = save(asignaturaGuardar);
                if (isSaved) {
                    lista = getLista(); // Actualizar la lista después de guardar
                }

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
        return String.format("%04d", getLastUsedIdFromDatabase());
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

    private Integer getLastUsedIdFromDatabase() throws VacioExceptions {
        // Obtener el último ID generado por el DataAccessObject
        return generarID();
    }

    public int busquedaLineal(Asignatura elemento, Comparator<Asignatura> comparador) {
        Nodo<Asignatura> current = lista.getHead();
        int index = 0;

        while (current != null) {
            if (comparador.compare(current.getData(), elemento) == 0) {
                return index;
            }

            current = current.getNext();
            index++;
        }

        return -1;
    }

    public int buscar(String criterioBusqueda, Comparator<Asignatura> comparador, String criterio) {
        Nodo<Asignatura> current = (Nodo<Asignatura>) lista.getHead();

        int index = 0;

        while (current != null) {
            Asignatura asignatura = current.getData();

            if ("nombre".equalsIgnoreCase(criterio) && asignatura.getNombre().equals(criterioBusqueda)) {
                return index;
            } else if ("codigo".equalsIgnoreCase(criterio) && asignatura.getCodigo().equals(criterioBusqueda)) {
                return index;
            }

            current = current.getNext();
            index++;
        }

        System.out.println("No se encontró ninguna asignatura con el criterio proporcionado.");
        return -1;
    }

    public Integer getIndex() {
        Iterator<Asignatura> iterator = lista.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            if (iterator.next() == asignatura) {
                return index;
            }
            index++;
        }

        return -1;
    }

    public void quicksort(ListaEnlazada<Asignatura> lista, Comparator<Asignatura> comparador, boolean ascendente) throws VacioExceptions {
        quicksortRecursivo(lista, 0, lista.getSize() - 1, comparador, ascendente);
    }

    private void quicksortRecursivo(ListaEnlazada<Asignatura> lista, int low, int high, Comparator<Asignatura> comparador, boolean ascendente) throws VacioExceptions {
        if (low < high) {
            int pivotIndex = particion(lista, low, high, comparador, ascendente);

            // Recursivamente ordenar los elementos antes y después del pivote
            quicksortRecursivo(lista, low, pivotIndex - 1, comparador, ascendente);
            quicksortRecursivo(lista, pivotIndex + 1, high, comparador, ascendente);
        }
    }

    private int particion(ListaEnlazada<Asignatura> lista, int low, int high, Comparator<Asignatura> comparador, boolean ascendente) throws VacioExceptions {
        Asignatura pivot = lista.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compareAsignaturas(lista.get(j), pivot, comparador, ascendente) <= 0) {
                i++;
                swap(lista, i, j);
            }
        }

        swap(lista, i + 1, high);
        return i + 1;
    }

    private void swap(ListaEnlazada<Asignatura> lista, int i, int j) throws VacioExceptions {
        Asignatura temp = lista.get(i);
        lista.update(i, lista.get(j));
        lista.update(j, temp);
    }

    private int compareAsignaturas(Asignatura a1, Asignatura a2, Comparator<Asignatura> comparador, boolean ascendente) {
        int resultado = comparador.compare(a1, a2);
        return ascendente ? resultado : -resultado;
    }
    
}
