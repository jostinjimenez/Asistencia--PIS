package ModuloMatricula.Controller;

import DAO.DataAccessObject;
import model.Matricula;
import model.catalogo.EstadoMatricula;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

/**
 *
 * @author Usuario
 */
public class ControllerMatricula extends DataAccessObject<Matricula> {

    private Matricula matricula = new Matricula();
    private ListaEnlazada<Matricula> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public ControllerMatricula() {
        super(Matricula.class);
    }

    public Matricula getMatricula() {
        if (matricula == null) {
            matricula = new Matricula();
        }
        return matricula;
    }

    public void setAsistencia(Matricula matricula) {
        this.matricula = matricula;
    }

    public Boolean saved() {
        return save(matricula);
    }

    public ListaEnlazada<Matricula> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;

    }

    public Boolean update1(Integer i) {
        return update(matricula, i);
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Matricula> lista) {
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

    public ListaEnlazada<Matricula> quicksort(ListaEnlazada<Matricula> lista, Integer type, String field) throws VacioExceptions {

        Matricula[] m = lista.toArray();
        quicksort(m, 0, m.length - 1, type, field);
        lista.toList(m);
        return lista;
    }

    private void quicksort(Matricula[] m, int low, int high, Integer type, String field) {
        if (low < high) {
            int pivotIndex = partition(m, low, high, type, field);
            quicksort(m, low, pivotIndex - 1, type, field);
            quicksort(m, pivotIndex + 1, high, type, field);
        }
    }

    private int partition(Matricula[] array, int low, int high, Integer type, String field) {
        Matricula pivote = array[high];
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

    private void swap(Matricula[] array, int i, int j) {
        Matricula temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public ListaEnlazada<Matricula> busquedaBinaria(ListaEnlazada<Matricula> lista, String text, String campo, String tipo, Integer type) throws VacioExceptions {
        ListaEnlazada<Matricula> listaOrdenada = ordenarLista(lista, campo, tipo);

        ListaEnlazada<Matricula> marc = new ListaEnlazada<>();
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

    private int busquedaBinaria1(ListaEnlazada<Matricula> lista, String text, String campo) throws VacioExceptions {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Matricula mid = lista.get(indice);
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

    private boolean getForm(Matricula matricula, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "ciclo":
                return Integer.toString(matricula.getCiclo()).equalsIgnoreCase(text);
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

    private ListaEnlazada<Matricula> ordenarLista(ListaEnlazada<Matricula> lista, String campo, String tipo) throws VacioExceptions {
        ListaEnlazada<Matricula> listaOrdenada = new ListaEnlazada<>();
        listaOrdenada = quicksort(lista, 0, campo);
        return listaOrdenada;
    }

    public static void main(String[] args) throws VacioExceptions {
        ListaEnlazada<Integer> ids = new ListaEnlazada();
        ids.add(3);
        Matricula matricula = new Matricula(3, "2023-11-01", 1, "Computacion", EstadoMatricula.MATRICULADO, 3, 1, ids);
        ControllerMatricula c = new ControllerMatricula();
        c.save(matricula);
        //  System.out.println(c.busquedaBinaria(c.list_All(), "", "ciclo", "quicksort", 0));

    }

}
