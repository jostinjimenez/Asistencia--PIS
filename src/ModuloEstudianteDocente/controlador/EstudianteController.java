package ModuloEstudianteDocente.controlador;

import java.io.IOException;

import DataBase.DataAccessObject;

import java.lang.reflect.Field;

import model.Estudiante;
import modulo_1.inicio_sesion.controller.PersonaController;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import static modulo_1.inicio_sesion.controller.util.Utilidades.getField;

public class EstudianteController extends DataAccessObject<Estudiante> {
    // Atributos
    private ListaEnlazada<Estudiante> estudiantes;
    private Estudiante estudiante = new Estudiante();
    private Integer index = -1;

    // Constructor
    public EstudianteController() {
        super(Estudiante.class);
        this.estudiantes = new ListaEnlazada<>();
    }

    // Getters y Setters
    public ListaEnlazada<Estudiante> getEstudiantes() {
        if (estudiantes.isEmpty()) {
            estudiantes = this.list_All();
        }
        return estudiantes;
    }

    public void setEstudiantes(ListaEnlazada<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    // Metodos
    public Boolean save() throws Exception {
        return super.saveB(this.estudiante);
    }

    public Boolean update() throws IOException {
        try {
            update(this.estudiante);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
}
