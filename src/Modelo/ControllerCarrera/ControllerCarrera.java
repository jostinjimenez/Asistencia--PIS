package Modelo.ControllerCarrera;

import DAO.DataAccessObject;
import model.Carrera;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

/**
 *
 * @author Usuario
 */
public class ControllerCarrera extends DataAccessObject<Carrera> {

    private Carrera carrera = new Carrera();
    private ListaEnlazada<Carrera> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public ControllerCarrera() {
        super(Carrera.class);
    }

    public Carrera getCarrera() {
        if (carrera == null) {
            carrera = new Carrera();
        }
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public ListaEnlazada<Carrera> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;
    }

    public void setLista(ListaEnlazada<Carrera> lista) {
        this.lista = lista;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean saved() {
        return save(getCarrera());
    }


    public Boolean update1(Integer i) {
        return update(getCarrera(), i);
    }

    public ListaEnlazada<Carrera> quicksort(ListaEnlazada<Carrera> lista, Integer type, String field) throws VacioExceptions {

        Carrera[] m = lista.toArray();
        quicksort(m, 0, m.length - 1, type, field);
        lista.toList(m);
        return lista;
    }

    private void quicksort(Carrera[] m, int low, int high, Integer type, String field) {
        if (low < high) {
            int pivotIndex = partition(m, low, high, type, field);
            quicksort(m, low, pivotIndex - 1, type, field);
            quicksort(m, pivotIndex + 1, high, type, field);
        }
    }

    private int partition(Carrera[] array, int low, int high, Integer type, String field) {
        Carrera pivote = array[high];
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

    private void swap(Carrera[] array, int i, int j) {
        Carrera temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public ListaEnlazada<Carrera> busquedaBinaria(ListaEnlazada<Carrera> lista, String text, String campo, String tipo, Integer type) throws VacioExceptions {
        ListaEnlazada<Carrera> listaOrdenada = ordenarLista(lista, campo);

        ListaEnlazada<Carrera> marc = new ListaEnlazada<>();
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);
        if (index != -1) {
            while (index < listaOrdenada.getSize() && getForm(listaOrdenada.get(index), text, campo)) {
                marc.add(listaOrdenada.get(index));
                index++;
            }

        } else {
            System.out.println("Elemento no encontrado");
        }

        return marc;
    }

    private int busquedaBinaria1(ListaEnlazada<Carrera> lista, String text, String campo) throws VacioExceptions {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Carrera mid = lista.get(indice);
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
    
        public Carrera busquedaBinaria2(ListaEnlazada<Carrera> lista, String text, String campo, Integer type) throws VacioExceptions {
        ListaEnlazada<Carrera> listaOrdenada = ordenarLista(lista, campo);
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);

        if (index != -1) {
            return listaOrdenada.get(index);
        } else {
            System.out.println("Elemento no encontrado");
            return null;
        }
    }

    private boolean getForm(Carrera carrera, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "nombre":
                return carrera.getNombre().equalsIgnoreCase(text);
            case "area_estudio":
                return carrera.getArea_estudio().equalsIgnoreCase(text);
            case "id":
                return Integer.toString(carrera.getId()).equalsIgnoreCase(text);
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

    private ListaEnlazada<Carrera> ordenarLista(ListaEnlazada<Carrera> lista, String campo) throws VacioExceptions {
        ListaEnlazada<Carrera> listaOrdenada = new ListaEnlazada<>();
        listaOrdenada = quicksort(lista, 0, campo);
        return listaOrdenada;
    }

}
