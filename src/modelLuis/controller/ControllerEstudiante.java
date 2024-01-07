/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelLuis.controller;

import DAO.DataAccessObject;

import model.Estudiante;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

/**
 *
 * @author Usuario
 */
public class ControllerEstudiante extends DataAccessObject<Estudiante> {

    private Estudiante estudiante = new Estudiante();
    private ListaEnlazada<Estudiante> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public ControllerEstudiante() {
        super(Estudiante.class);
    }

    public Estudiante getEstudiante() {
        if (estudiante == null) {
            estudiante = new Estudiante();
        }
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Boolean saved() {
        return save(estudiante);
    }

    public ListaEnlazada<Estudiante> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;

    }

    public Boolean update1(Integer i) {
        return update(estudiante, i);
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Estudiante> lista) {
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

    public ListaEnlazada<Estudiante> quicksort(ListaEnlazada<Estudiante> lista, Integer type, String field) throws VacioExceptions {

        Estudiante[] m = lista.toArray();
        quicksort(m, 0, m.length - 1, type, field);
        lista.toList(m);
        return lista;
    }

    private void quicksort(Estudiante[] m, int low, int high, Integer type, String field) {
        if (low < high) {
            int pivotIndex = partition(m, low, high, type, field);
            quicksort(m, low, pivotIndex - 1, type, field);
            quicksort(m, pivotIndex + 1, high, type, field);
        }
    }

    private int partition(Estudiante[] array, int low, int high, Integer type, String field) {
        Estudiante pivote = array[high];
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

    private void swap(Estudiante[] array, int i, int j) {
        Estudiante temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public ListaEnlazada<Estudiante> busquedaBinaria(ListaEnlazada<Estudiante> lista, String text, String campo, Integer type) throws VacioExceptions {
        ListaEnlazada<Estudiante> listaOrdenada = ordenarLista(lista, campo);

        ListaEnlazada<Estudiante> marc = new ListaEnlazada<>();
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

    private int busquedaBinaria1(ListaEnlazada<Estudiante> lista, String text, String campo) throws VacioExceptions {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Estudiante mid = lista.get(indice);
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

    private boolean getForm(Estudiante matricula, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "id":
                return Integer.toString(matricula.getId()).equalsIgnoreCase(text);
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

    private ListaEnlazada<Estudiante> ordenarLista(ListaEnlazada<Estudiante> lista, String campo) throws VacioExceptions {
        ListaEnlazada<Estudiante> listaOrdenada = quicksort(lista, 0, campo);
        System.out.println(listaOrdenada.print());  
        return listaOrdenada;
    }

    public Estudiante busquedaBinaria2(ListaEnlazada<Estudiante> lista, String text, String campo, Integer type) throws VacioExceptions {
        ListaEnlazada<Estudiante> listaOrdenada = ordenarLista(lista, campo);
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);

        if (index != -1) {
            return listaOrdenada.get(index);
        } else {
            System.out.println("Elemento no encontrado");
            return null;
        }
    }

    public static void main(String[] args) throws VacioExceptions {
//        ListaEnlazada<Integer> ids = new ListaEnlazada();
//        ids.add(3);
//        Estudiante estudiante = new Estudiante(3,"Daniel", "Dorn", "luis33","2023-2030","9827728282","7267277272", "Loja", "Mestiza", true, ids);

        ControllerEstudiante c = new ControllerEstudiante();
        c.busquedaBinaria2(c.list_All(), "2", "id", 0);
//        c.save(estudiante);
        //  System.out.println(c.busquedaBinaria(c.list_All(), "", "ciclo", "quicksort", 0));

    }
}
