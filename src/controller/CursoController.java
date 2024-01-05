package controller;

import DAO.DataAccessObject;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;
import model.Curso;

import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class CursoController extends DataAccessObject<Curso> {

    private Curso curso = new Curso();
    private ListaEnlazada<Curso> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public CursoController() {
        super(Curso.class);
        lista = list_All();  // Asegúrate de cargar la lista al inicio
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

    public Boolean guardar() {
        Boolean result = save(curso);

        if (result) {
            System.out.println("Curso guardado correctamente.");
        } else {
            System.out.println("Error al guardar el curso.");
        }

        return result;
    }

    public ListaEnlazada<Curso> getLista() {
        return lista;
    }

    public Boolean actualizar(Integer i) {
        return update(curso, i);
    }

    public void setLista(ListaEnlazada<Curso> lista) {
        this.lista = lista;
    }

    public Integer generarId() {
        // Obtener el máximo ID actual mediante un bucle tradicional
        Integer maxId = 0;
        for (Curso curso : lista) {
            Integer currentId = curso.getId();
            if (currentId != null && currentId > maxId) {
                maxId = currentId;
            }
        }

        // Incrementar el máximo ID en 1 para obtener un nuevo ID único
        return maxId + 1;
    }

    public void quicksort(ListaEnlazada<Curso> lista, String campoOrden, Comparator<Curso> comparador) {
        Curso[] array = lista.toArray();
        quicksort(array, 0, array.length - 1, campoOrden, comparador);
        lista.toList(array);
    }

    // Asegúrate de que estos métodos también estén presentes en tu clase
    private void quicksort(Curso[] array, int low, int high, String campoOrden, Comparator<Curso> comparador) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, campoOrden, comparador);
            quicksort(array, low, pivotIndex - 1, campoOrden, comparador);
            quicksort(array, pivotIndex + 1, high, campoOrden, comparador);
        }
    }

    private int partition(Curso[] array, int low, int high, String campoOrden, Comparator<Curso> comparador) {
        Curso pivote = array[high];
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

    private void swap(Curso[] array, int i, int j) {
        Curso temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void add(Curso nuevoCurso) {
        lista.add(nuevoCurso);
    }

    public boolean updateCurso(Curso curso, int indice) throws VacioExceptions {
        // Asegúrate de que el índice sea válido
        if (indice >= 0 && indice < lista.getSize()) {
            lista.update(indice, curso);
            return true;
        }
        return false;
    }
    
}
