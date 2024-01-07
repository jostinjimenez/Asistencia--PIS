package controller;

import DAO.DataAccessObject;
import java.util.Comparator;
import model.Asignatura;

import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

public class ControllerAsignatura extends DataAccessObject<Asignatura> {

    private Asignatura asignatura = new Asignatura();
    private ListaEnlazada<Asignatura> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public ControllerAsignatura() {
        super(Asignatura.class);
    }

    public Asignatura getAsistencia() {
        if (asignatura == null) {
            asignatura = new Asignatura();
        }
        return asignatura;
    }

    public void setAsistencia(Asignatura asitencia) {
        this.asignatura = asitencia;
    }

    public Boolean saved() {
        Boolean result = save(asignatura);

        if (result) {
            System.out.println("Asignatura guardada correctamente.");
        } else {
            System.out.println("Error al guardar la asignatura.");
        }

        return result;
    }

    public ListaEnlazada<Asignatura> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;

    }

    public Boolean update(Integer i) {
        return update(asignatura, i);
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Asignatura> lista) {
        this.lista = lista;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer generatedId() {
        // Obtener el máximo ID actual mediante un bucle tradicional
        Integer maxId = 0;
        for (Asignatura asignatura : getLista()) {
            Integer currentId = asignatura.getId();
            if (currentId != null && currentId > maxId) {
                maxId = currentId;
            }
        }

        // Incrementar el máximo ID en 1 para obtener un nuevo ID único
        return maxId + 1;
    }

    public static void main(String[] args) {
        ListaEnlazada<Integer> ids = new ListaEnlazada();
        ids.add(2);
        Asignatura asig = new Asignatura(2, "Estrutura Datos", 37733, 279, ids);
        ControllerAsignatura c = new ControllerAsignatura();
        System.out.println(c.save(asig));
    }

    public ListaEnlazada<Asignatura> quicksort(ListaEnlazada<Asignatura> lista, Integer type, String field) throws VacioExceptions {

        Asignatura[] m = lista.toArray();
        quicksort(m, 0, m.length - 1, type, field);
        lista.toList(m);
        return lista;
    }

    private void quicksort(Asignatura[] m, int low, int high, Integer type, String field) {
        if (low < high) {
            int pivotIndex = partition(m, low, high, type, field);
            quicksort(m, low, pivotIndex - 1, type, field);
            quicksort(m, pivotIndex + 1, high, type, field);
        }
    }

    private int partition(Asignatura[] array, int low, int high, Integer type, String field) {
        Asignatura pivote = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j].comparar(pivote, field, type)) {
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

    private int busquedaBinaria1(ListaEnlazada<Asignatura> lista, String text, String campo) throws VacioExceptions {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Asignatura mid = lista.get(indice);
            int resultado = mid.comparar(mid, text, campo);
            if (resultado == 0) {
                int izquierda = indice - 1;
                while (izquierda >= 0 && getForm(lista.get(izquierda), text, campo)) {
                    indice = izquierda;
                    izquierda--;
                }
                return indice;
            } else if (resultado < 0) {
                sup = indice - 1;
            } else {
                infe = indice + 1;
            }
        }

        return -1;
    }

    private boolean getForm(Asignatura matricula, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "id":
                return Integer.toString(matricula.getId()).equalsIgnoreCase(text);
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

    private ListaEnlazada<Asignatura> ordenarLista(ListaEnlazada<Asignatura> lista, String campo) throws VacioExceptions {
        ListaEnlazada<Asignatura> listaOrdenada = quicksort(lista, 0, campo);
        return listaOrdenada;
    }

    public Asignatura busquedaBinaria2(ListaEnlazada<Asignatura> lista, String text, String campo, Integer type) throws VacioExceptions {
        ListaEnlazada<Asignatura> listaOrdenada = ordenarLista(lista, campo);
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);

        if (index != -1) {
            return listaOrdenada.get(index);
        } else {
            System.out.println("Elemento no encontrado");
            return null;
        }
    }

    public Boolean updateAsignatura(Asignatura asignatura, Integer index) {
        DataAccessObject<Asignatura> dao = new DataAccessObject<>(Asignatura.class);
        Boolean result = dao.update(asignatura, index);

        if (result) {
            System.out.println("Asignatura actualizada correctamente en la posición: " + index);
        } else {
            System.out.println("Error al actualizar la asignatura en la posición: " + index);
        }

        return result;
    }
}
