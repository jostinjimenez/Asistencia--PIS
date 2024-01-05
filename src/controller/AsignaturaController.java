package controller;

import DAO.DataAccessObject;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;
import model.Asignatura;

import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class AsignaturaController extends DataAccessObject<Asignatura> {

    private Asignatura asignatura = new Asignatura();
    private ListaEnlazada<Asignatura> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public AsignaturaController() {
        super(Asignatura.class);
        lista = list_All();  // Asegúrate de cargar la lista al inicio
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

    public Boolean guardar() {
        Boolean result = save(asignatura);

        if (result) {
            System.out.println("Asignatura guardada correctamente.");
        } else {
            System.out.println("Error al guardar la asignatura.");
        }

        return result;
    }

    public ListaEnlazada<Asignatura> getLista() {
        return lista;
    }

    public Boolean actualizar(Integer i) {
        return update(asignatura, i);
    }

    public void setLista(ListaEnlazada<Asignatura> lista) {
        this.lista = lista;
    }

    public Integer generarId() {
        // Obtener el máximo ID actual mediante un bucle tradicional
        Integer maxId = 0;
        for (Asignatura asignatura : lista) {
            Integer currentId = asignatura.getId();
            if (currentId != null && currentId > maxId) {
                maxId = currentId;
            }
        }

        // Incrementar el máximo ID en 1 para obtener un nuevo ID único
        return maxId + 1;
    }

    public void quicksort(ListaEnlazada<Asignatura> lista, String campoOrden, Comparator<Asignatura> comparador) {
        Asignatura[] array = lista.toArray();
        quicksort(array, 0, array.length - 1, campoOrden, comparador);
        lista.toList(array);
    }

    // Asegúrate de que estos métodos también estén presentes en tu clase
    private void quicksort(Asignatura[] array, int low, int high, String campoOrden, Comparator<Asignatura> comparador) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, campoOrden, comparador);
            quicksort(array, low, pivotIndex - 1, campoOrden, comparador);
            quicksort(array, pivotIndex + 1, high, campoOrden, comparador);
        }
    }

    private int partition(Asignatura[] array, int low, int high, String campoOrden, Comparator<Asignatura> comparador) {
        Asignatura pivote = array[high];
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

    private void swap(Asignatura[] array, int i, int j) {
        Asignatura temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void add(Asignatura nuevaAsignatura) {
        lista.add(nuevaAsignatura);
    }

    public boolean updateAsignatura(Asignatura asignatura, int indice) throws VacioExceptions {
        // Asegúrate de que el índice sea válido
        if (indice >= 0 && indice < lista.getSize()) {
            lista.update(indice, asignatura);
            return true;
        }
        return false;
    }

}
