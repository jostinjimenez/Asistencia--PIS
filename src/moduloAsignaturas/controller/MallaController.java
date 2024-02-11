package moduloAsignaturas.controller;

import DataBase.DataAccessObject;
import tda_listas.ListaEnlazada;
import tda_listas.Nodo;
import tda_listas.exceptions.VacioExceptions;
import java.util.Comparator;
import java.util.Iterator;
import model.Malla;

public class MallaController extends DataAccessObject<Malla> {

    private Malla malla = new Malla();
    private ListaEnlazada<Malla> mallas;
    private Integer index = -1;

    public MallaController() {
        super(Malla.class);
        this.mallas = new ListaEnlazada<>();

    }

    public ListaEnlazada<Malla> getMallas() {
        if (mallas.isEmpty()) {
            mallas = list_All();
        }
        return mallas;

    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setMallas(ListaEnlazada<Malla> mallas) {
        this.mallas = mallas;
    }

    public void setMalla(Malla malla) {
        this.malla = malla;
    }

    public Malla getMalla() {
        if (malla == null) {
            malla = new Malla();
        }
        return malla;
    }

    public Integer save() throws Exception {
        return super.save(this.malla);
    }

    public Boolean update() {
        try {
            update(this.malla);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int busquedaLineal(Malla elemento, Comparator<Malla> comparador) {
        Nodo<Malla> current = mallas.getHead();
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

    public boolean delete(int mallaAEliminar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
