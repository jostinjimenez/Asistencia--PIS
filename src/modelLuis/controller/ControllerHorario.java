/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelLuis.controller;

import DAO.DataAccessObject;
import model.Horario;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

/**
 *
 * @author Usuario
 */
public class ControllerHorario extends DataAccessObject<Horario> {
 private Horario horario = new Horario();
    private ListaEnlazada<Horario> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public ControllerHorario() {
        super(Horario.class);
    }

    public Horario getAsistencia() {
        if (horario == null) {
            horario = new Horario();
        }
        return horario;
    }

    public void setAsistencia(Horario horario) {
        this.horario = horario;
    }

    public Boolean saved() {
        return save(horario);
    }

    public ListaEnlazada<Horario> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;

    }

    public Boolean update1(Integer i) {
        return update(horario, i);
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Horario> lista) {
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

    public int generatedId() {
        return generarID();
    }

    public ListaEnlazada<Horario> quicksort(ListaEnlazada<Horario> lista, Integer type, String field) throws VacioExceptions {

        Horario[] m = lista.toArray();
        quicksort(m, 0, m.length - 1, type, field);
        lista.toList(m);
        return lista;
    }

    private void quicksort(Horario[] m, int low, int high, Integer type, String field) {
        if (low < high) {
            int pivotIndex = partition(m, low, high, type, field);
            quicksort(m, low, pivotIndex - 1, type, field);
            quicksort(m, pivotIndex + 1, high, type, field);
        }
    }

    private int partition(Horario[] array, int low, int high, Integer type, String field) {
        Horario pivote = array[high];
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

    private void swap(Horario[] array, int i, int j) {
        Horario temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public Horario busquedaBinaria2(ListaEnlazada<Horario> lista, String text, String campo) throws VacioExceptions {
        ListaEnlazada<Horario> listaOrdenada = ordenarLista(lista, campo);
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);
        if (index != -1) {
            return listaOrdenada.get(index);
        } else {
            System.out.println("Elemento no encontrado");
            return null;
        }
    }

    public ListaEnlazada<Horario> busquedaBinaria(ListaEnlazada<Horario> lista, String text, String campo, String tipo) throws VacioExceptions {
        ListaEnlazada<Horario> listaOrdenada = ordenarLista(lista, campo);
        ListaEnlazada<Horario> marc = new ListaEnlazada<>();
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

    private int busquedaBinaria1(ListaEnlazada<Horario> lista, String text, String campo) throws VacioExceptions {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Horario mid = lista.get(indice);
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

    private boolean getForm(Horario horario, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "id":
                return Integer.toString(horario.getId()).equalsIgnoreCase(text);
            case "id_asignatura":
                return Integer.toString(horario.getIdAsignatura()).equalsIgnoreCase(text);
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

    private ListaEnlazada<Horario> ordenarLista(ListaEnlazada<Horario> lista, String campo) throws VacioExceptions {
        ListaEnlazada<Horario> listaOrdenada = quicksort(lista, 0, campo);
        return listaOrdenada;
    }
}
