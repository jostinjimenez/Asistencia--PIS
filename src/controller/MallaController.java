package controller;

import DAO.DataAccessObject;
import tda_listas.ListaEnlazada;
import tda_listas.Nodo;
import tda_listas.exceptions.VacioExceptions;
import java.util.Comparator;
import java.util.Iterator;
import model.Malla;

public class MallaController extends DataAccessObject<Malla> {

    private Malla malla = new Malla();
    private ListaEnlazada<Malla> lista;
    private static Integer lastUsedId = 0;

    public MallaController() {
        super(Malla.class);
        lastUsedId = generarID();
    }

    public Malla getMalla() {
        if (malla == null) {
            malla = new Malla();
        }
        return malla;
    }

    public void setMalla(Malla malla) {
        this.malla = malla;
    }

    public boolean validar() {
        // Verificar que la Malla no es null
        if (malla == null) {
            return false;
        }

        // Verificar que los campos de la Malla no son null o vacíos
        return malla.isValid();
    }

    public Boolean saved() {
        if (validar()) {
            try {
                // Obtener los datos del formulario
                String duracion = malla.getDuracion();
                String descripcion = malla.getDescripcion();
                String nombreSilabo = malla.getNombreSilabo();
                byte[] silabo = malla.getSilabo();

                // Incrementar el lastUsedId antes de asignarlo al nuevo ID
                Integer id = ++lastUsedId;

                // Crear una nueva instancia de Malla con los valores correctos
                Malla mallaGuardar = new Malla();
                mallaGuardar.setId(id);
                mallaGuardar.setDuracion(duracion);
                mallaGuardar.setDescripcion(descripcion);
                mallaGuardar.setNombreSilabo(nombreSilabo);
                mallaGuardar.setSilabo(silabo);

                // Guardar la Malla
                boolean isSaved = save(mallaGuardar);
                if (isSaved) {
                    lista = getLista(); // Actualizar la lista después de guardar
                }

                return isSaved;
            } catch (Exception e) {
                // Manejar la excepción aquí
                System.out.println("Error al guardar la Malla: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Error: los datos de la Malla no son válidos");
            return false;
        }
    }

    public String generatedCode() throws VacioExceptions {
        return String.format("%04d", getLastUsedIdFromDatabase());
    }

    public ListaEnlazada<Malla> getLista() {
        lista = list_All(); // Actualizar la lista desde la base de datos
        return lista;
    }

    public Boolean update(Integer i) {
        return update(malla, i);
    }

    public void setLista(ListaEnlazada<Malla> lista) {
        this.lista = lista;
    }

    private Integer getLastUsedIdFromDatabase() throws VacioExceptions {
        // Obtener el último ID generado por el DataAccessObject
        return generarID();
    }

    public int busquedaLineal(Malla elemento, Comparator<Malla> comparador) {
        Nodo<Malla> current = lista.getHead();
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

    public int buscar(String criterioBusqueda, Comparator<Malla> comparador, String criterio) {
        Nodo<Malla> current = (Nodo<Malla>) lista.getHead();

        int index = 0;

        while (current != null) {
            Malla malla = current.getData();

            if ("duracion".equalsIgnoreCase(criterio) && malla.getDuracion().equals(criterioBusqueda)) {
                return index;
            } else if ("descripcion".equalsIgnoreCase(criterio) && malla.getDescripcion().equals(criterioBusqueda)) {
                return index;
            } else if ("nombreSilabo".equalsIgnoreCase(criterio) && malla.getNombreSilabo().equals(criterioBusqueda)) {
                return index;
            }

            current = current.getNext();
            index++;
        }

        System.out.println("No se encontró ninguna malla con el criterio proporcionado.");
        return -1;
    }

    public Integer getIndex() {
        Iterator<Malla> iterator = lista.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            if (iterator.next() == malla) {
                return index;
            }
            index++;
        }

        return -1;
    }

    public void quicksort(ListaEnlazada<Malla> lista, Comparator<Malla> comparador, boolean ascendente) throws VacioExceptions {
        quicksortRecursivo(lista, 0, lista.getSize() - 1, comparador, ascendente);
    }

    private void quicksortRecursivo(ListaEnlazada<Malla> lista, int low, int high, Comparator<Malla> comparador, boolean ascendente) throws VacioExceptions {
        if (low < high) {
            int pivotIndex = particion(lista, low, high, comparador, ascendente);

            // Recursivamente ordenar los elementos antes y después del pivote
            quicksortRecursivo(lista, low, pivotIndex - 1, comparador, ascendente);
            quicksortRecursivo(lista, pivotIndex + 1, high, comparador, ascendente);
        }
    }

    private int particion(ListaEnlazada<Malla> lista, int low, int high, Comparator<Malla> comparador, boolean ascendente) throws VacioExceptions {
        Malla pivot = lista.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compareMallas(lista.get(j), pivot, comparador, ascendente) <= 0) {
                i++;
                swap(lista, i, j);
            }
        }

        swap(lista, i + 1, high);
        return i + 1;
    }

    private void swap(ListaEnlazada<Malla> lista, int i, int j) throws VacioExceptions {
        Malla temp = lista.get(i);
        lista.update(i, lista.get(j));
        lista.update(j, temp);
    }

    private int compareMallas(Malla m1, Malla m2, Comparator<Malla> comparador, boolean ascendente) {
        int resultado = comparador.compare(m1, m2);
        return ascendente ? resultado : -resultado;
    }
}
