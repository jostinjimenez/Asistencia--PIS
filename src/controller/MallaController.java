package controller;

import DAO.DataAccessObject;
import java.util.Comparator;
import javax.swing.table.AbstractTableModel;
import model.Malla;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class MallaController extends DataAccessObject<Malla> {

    private Malla malla = new Malla();
    private ListaEnlazada<Malla> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public MallaController() {
        super(Malla.class);
        lista = list_All();  // Asegúrate de cargar la lista al inicio
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

    public Boolean guardar() {
        Boolean result = save(malla);

        if (result) {
            System.out.println("Malla guardada correctamente.");
        } else {
            System.out.println("Error al guardar la malla.");
        }

        return result;
    }

    public ListaEnlazada<Malla> getLista() {
        return lista;
    }

    public Boolean actualizar(Integer i) {
        return update(malla, i);
    }

    public void setLista(ListaEnlazada<Malla> lista) {
        this.lista = lista;
    }

    public Integer generarId() {
        // Obtener el máximo ID actual mediante un bucle tradicional
        Integer maxId = 0;
        for (Malla malla : lista) {
            Integer currentId = malla.getId();
            if (currentId != null && currentId > maxId) {
                maxId = currentId;
            }
        }

        // Incrementar el máximo ID en 1 para obtener un nuevo ID único
        return maxId + 1;
    }

    public void quicksort(ListaEnlazada<Malla> lista, String campoOrden, Comparator<Malla> comparador) {
        Malla[] array = lista.toArray();
        quicksort(array, 0, array.length - 1, campoOrden, comparador);
        lista.toList(array);
    }

    // Asegúrate de que estos métodos también estén presentes en tu clase
    private void quicksort(Malla[] array, int low, int high, String campoOrden, Comparator<Malla> comparador) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, campoOrden, comparador);
            quicksort(array, low, pivotIndex - 1, campoOrden, comparador);
            quicksort(array, pivotIndex + 1, high, campoOrden, comparador);
        }
    }

    private int partition(Malla[] array, int low, int high, String campoOrden, Comparator<Malla> comparador) {
        Malla pivote = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparador.compare(array[j], pivote) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(Malla[] array, int i, int j) {
        Malla temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void add(Malla nuevaMalla) {
        lista.add(nuevaMalla);
    }

    public boolean updateMalla(Malla malla, int indice) throws VacioExceptions {
        // Asegúrate de que el índice sea válido
        if (indice >= 0 && indice < lista.getSize()) {
            lista.update(indice, malla);
            return true;
        }
        return false;
    }

    public Malla buscar(String criterioBusqueda, Comparator<Malla> comparador, String criterio) {
        ListaEnlazada<Malla> lista = getLista();

        for (int i = 0; i < lista.getSize(); i++) {
            try {
                Malla actual = lista.get(i);

                switch (criterio) {
                    case "descripcion":
                        if (actual.getDescripcion().equalsIgnoreCase(criterioBusqueda) && comparador.compare(actual, new Malla()) == 0) {
                            return actual;
                        }
                        break;
                    case "duracion":
                        if (actual.getDuracion().equalsIgnoreCase(criterioBusqueda) && comparador.compare(actual, new Malla()) == 0) {
                            return actual;
                        }
                        break;
                    case "silabo":
                        if (actual.getNombreSilabo().equalsIgnoreCase(criterioBusqueda) && comparador.compare(actual, new Malla()) == 0) {
                            return actual;
                        }
                        break;
                    // Agrega más casos según tus necesidades

                    default:
                        break;
                }
            } catch (VacioExceptions e) {
                throw new RuntimeException(e);
            }
        }

        return null; // Devuelve null si no se encuentra la malla
    }
}
