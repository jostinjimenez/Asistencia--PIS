package tda_listas;

import tda_listas.exceptions.VacioExceptions;

import java.util.Iterator;
import java.util.Comparator;

import static java.lang.reflect.Array.newInstance;
import model.Asignatura;

public class ListaEnlazada<E> implements Iterable<E> {

    private Nodo<E> head;
    private Nodo<E> last;
    private Integer size;

    public ListaEnlazada() {
        head = null;
        last = null;
        size = 0;
    }

    public Integer getSize() {
        return size;
    }

    public Nodo<E> getHead() {
        return head;
    }

    public Boolean isEmpty() {
        return head == null || size == 0;
    }

    protected void addFist(E data) {
        if (isEmpty()) {
            Nodo<E> aux = new Nodo<>(data, null);
            head = aux;
            last = aux;
        } else {
            Nodo<E> headOld = head;
            head = new Nodo<>(data, headOld);
        }
        size++;
    }

    public void addLast(E data) {
        if (isEmpty()) {
            addFist(data);
        } else {
            Nodo<E> aux = new Nodo<>(data, null);
            last.setNext(aux);
            last = aux;
            size++;
        }
    }

    public void add(E data) {
        addLast(data);
    }

    public void add(E data, Integer post) throws VacioExceptions {
        if (post == 0) {
            addFist(data);
        } else if (post == size - 1) {
            addLast(data);
        } else {
            Nodo<E> search_preview = getNode(post - 1);
            Nodo<E> search = getNode(post);
            Nodo<E> aux = new Nodo<>(data, search);
            search_preview.setNext(aux);
            size++;
        }
    }

    public E getFirst() throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Error, Lista vacia");
        } else {
            return head.getData();
        }
    }

    public E getLast() throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Error, Lista vacia");
        } else {
            return last.getData();
        }
    }

    public E get(Integer index) throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Error, Lista vacia");
        } else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Error, Esta fuera del limite de la lista");
        } else if (index == 0) {
            return getFirst();
        } else if (index == (size - 1)) {
            return getLast();
        } else {
            Nodo<E> search = getNode(index);
            return search.getData();
        }
    }

    private Nodo<E> getNode(Integer post) throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Error, Lista vacia");
        } else if (post < 0 || post >= size) {
            throw new IndexOutOfBoundsException("Error, Esta fuera del limite de la lista");
        } else if (post == 0) {
            return head;
        } else if (post == (size - 1)) {
            return last;
        } else {
            Nodo<E> search = head;
            int cont = 0;
            while (cont < post) {
                cont++;
                search = search.getNext();
            }
            return search;
        }
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            sb.append(":Lista vacia");
        } else {
            Nodo<E> aux = head;
            while (aux != null) {
                sb.append(aux.getData().toString()).append("\n");
                aux = aux.getNext();
            }
        }
        return sb.toString();
    }

    public void update(Integer pos, E data) throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Lista Vacia");
        } else if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException("Error, esta fuera de los limites");
        } else if (pos == 0) {
            head.setData(data);
        } else if (pos == (size - 1)) {
            last.setData(data);
        } else {
            Nodo<E> search = head;
            Integer cont = 0;
            while (cont < pos) {
                cont++;
                search = search.getNext();
            }
            search.setData(data);
        }
    }

    public E deleteFirst() throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Lista Vacia");
        } else {
            E element = head.getData();
            Nodo<E> aux = head.getNext();
            head = aux;
            if (size.intValue() == 1) {
                last = null;
            }
            size--;

            return element;
        }
    }

    public E deleteLast() throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Lista Vacia");
        } else {
            E element = last.getData();
            Nodo<E> aux = getNode(size - 2);
            if (aux == null) {
                last = null;
                if (size == 2) {
                    last = head;
                } else {
                    head = null;
                }
            } else {
                last = null;
                last = aux;
                last.setNext(null);
            }
            size--;
            return element;
        }
    }

    public E delete(Integer post) throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Lista Esta Vacia ");
        } else if (post < 0 || post >= size) {
            throw new IndexOutOfBoundsException("Error esta fuera de los limites de la lista");
        } else if (post == 0) {
            return deleteFirst();
        } else if (post == (size - 1)) {
            return deleteLast();
        } else {
            Nodo<E> preview = getNode(post - 1);
            Nodo<E> actually = preview.getNext();
            E element = actually.getData();
            Nodo<E> next = actually.getNext();
            preview.setNext(next);
            size--;
            return element;
        }
    }

    public void clear() {
        head = null;
        last = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Nodo<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E data = current.getData();
                current = current.getNext();
                return data;
            }
        };
    }

    public void reverse() {
        Nodo<E> prev = null;
        Nodo<E> next = null;
        Nodo<E> actual = head;
        while (actual != null) {
            next = actual.getNext();
            actual.setNext(prev);
            prev = actual;
            actual = next;
        }
        head = prev;
    }

    public E[] toArray() {
        Class clazz = null;
        E[] matriz = null;
        if (this.size > 0) {
            clazz = head.getData().getClass();
            matriz = (E[]) newInstance(clazz, size);

            Nodo<E> aux = head;
            for (int i = 0; i < size; i++) {
                matriz[i] = aux.getData();
                aux = aux.getNext();
            }
        }
        return matriz;
    }

    public ListaEnlazada<E> toList(E[] m) {
        clear();
        for (E e : m) {
            this.add(e);
        }
        return this;
    }

    public int indexOf(E data) {
        Nodo<E> current = head;
        int index = 0;

        while (current != null) {
            if (current.getData().equals(data)) {
                return index;
            }

            current = current.getNext();
            index++;
        }

        return -1;
    }

    public void quicksort(Comparator<E> comparator) throws VacioExceptions {
        quicksort(0, size - 1, comparator);
    }

    private void quicksort(int low, int high, Comparator<E> comparator) throws VacioExceptions {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);

            quicksort(low, pivotIndex - 1, comparator);
            quicksort(pivotIndex + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<E> comparator) throws VacioExceptions {
        E pivot = get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(get(j), pivot) < 0) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) throws VacioExceptions {
        Nodo<E> nodoI = getNode(i);
        Nodo<E> nodoJ = getNode(j);

        E temp = nodoI.getData();
        nodoI.setData(nodoJ.getData());
        nodoJ.setData(temp);
    }

    public int busquedaLineal(E elemento, Comparator<E> comparador) {
        Nodo<E> current = head;
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
        Nodo<Asignatura> current = (Nodo<Asignatura>) this.getHead();

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

    public void sortAscendente(Comparator<E> comparator) throws VacioExceptions {
        quicksort(0, size - 1, comparator);
    }

    public void sortDescendente(Comparator<E> comparator) throws VacioExceptions {
        quicksortDescendente(0, size - 1, comparator);
    }

    private void quicksortDescendente(int low, int high, Comparator<E> comparator) throws VacioExceptions {
        if (low < high) {
            int pivotIndex = partitionDescendente(low, high, comparator);

            quicksortDescendente(low, pivotIndex - 1, comparator);
            quicksortDescendente(pivotIndex + 1, high, comparator);
        }
    }

    private int partitionDescendente(int low, int high, Comparator<E> comparator) throws VacioExceptions {
        E pivot = get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(get(j), pivot) > 0) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    public void quicksortDescendente(Comparator<E> comparator) throws VacioExceptions {
        quicksortDescendente(0, size - 1, comparator);
    }

}
