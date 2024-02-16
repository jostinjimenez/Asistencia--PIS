/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelLuis.controller;

import DataBase.DataAccessObject;
import model.Cursa;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

/**
 *
 * @author Usuario
 */
public class ControllerCursa extends DataAccessObject<Cursa> {

    private Cursa cursa = new Cursa();
    private ListaEnlazada<Cursa> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public ControllerCursa() {
        super(Cursa.class);
    }

    public Cursa getCursa() {
        if (cursa == null) {
            cursa = new Cursa();
        }
        return cursa;
    }

    public void setCursa(Cursa cursa) {
        this.cursa = cursa;
    }

    public Integer save() throws Exception {
        return super.save(this.cursa, "SQC_CURSA");
    }

    public Boolean update() {
        try {
            update(this.cursa);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ListaEnlazada<Cursa> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;

    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Cursa> lista) {
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

    public ListaEnlazada<Cursa> quicksort(ListaEnlazada<Cursa> lista, Integer type, String field) throws VacioExceptions {

        Cursa[] m = lista.toArray();
        quicksort(m, 0, m.length - 1, type, field);
        lista.toList(m);
        return lista;
    }

    private void quicksort(Cursa[] m, int low, int high, Integer type, String field) {
        if (low < high) {
            int pivotIndex = partition(m, low, high, type, field);
            quicksort(m, low, pivotIndex - 1, type, field);
            quicksort(m, pivotIndex + 1, high, type, field);
        }
    }

    private int partition(Cursa[] array, int low, int high, Integer type, String field) {
        Cursa pivote = array[high];
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

    private void swap(Cursa[] array, int i, int j) {
        Cursa temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public Cursa busquedaBinaria2(ListaEnlazada<Cursa> lista, String text, String campo, Integer type) throws VacioExceptions {
        ListaEnlazada<Cursa> listaOrdenada = ordenarLista(lista, campo);
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);

        if (index != -1) {
            return listaOrdenada.get(index);
        } else {
            System.out.println("Elemento no encontrado");
            return null;
        }
    }

    public ListaEnlazada<Cursa> busquedaBinaria(ListaEnlazada<Cursa> lista, String text, String campo, String tipo, Integer type) throws VacioExceptions {
        ListaEnlazada<Cursa> listaOrdenada = ordenarLista(lista, campo);

        ListaEnlazada<Cursa> marc = new ListaEnlazada<>();
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

    private int busquedaBinaria1(ListaEnlazada<Cursa> lista, String text, String campo) throws VacioExceptions {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Cursa mid = lista.get(indice);
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

    private boolean getForm(Cursa cursa, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "paralelo":
                return cursa.getParalelo().equalsIgnoreCase(text);
            case "id_matricula":
                return Integer.toString(cursa.getMatricula_id()).equalsIgnoreCase(text);
            case "id_docente":
                return Integer.toString(cursa.getDocente_id()).equalsIgnoreCase(text);
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }

    }

    private ListaEnlazada<Cursa> ordenarLista(ListaEnlazada<Cursa> lista, String campo) throws VacioExceptions {
        ListaEnlazada<Cursa> listaOrdenada = quicksort(lista, 0, campo);
        return listaOrdenada;
    }


}
