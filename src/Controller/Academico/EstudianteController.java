package Controller.Academico;

import java.io.IOException;

import Controller.DataBase.DataAccessObject;

import java.lang.reflect.Field;

import model.Estudiante;
import model.Persona;
import Controller.tda_listas.ListaEnlazada;
import Controller.tda_listas.exceptions.VacioExceptions;

import static Controller.Util.Utilidades.getField;

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

    public ListaEnlazada<Estudiante> ordenarQS(ListaEnlazada<Estudiante> lista, Integer type, String field) throws Exception {
        Estudiante[] estudiantess = lista.toArray();
        Field faux = getField(Persona.class, field);
        if (faux != null) {
            quickSort(estudiantess, 0, estudiantess.length - 1, type, field);
        } else {
            throw new Exception("El atributo no existe");
        }
        return lista.toList(estudiantess);
    }

    private void quickSort(Estudiante[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        if (primero < ultimo) {
            int pi = partition(p, primero, ultimo, type, field);

            quickSort(p, primero, pi - 1, type, field);
            quickSort(p, pi + 1, ultimo, type, field);
        }
    }

    private int partition(Estudiante[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        Estudiante pivot = p[ultimo];
        int i = (primero - 1);

        for (int j = primero; j < ultimo; j++) {
            if (p[j].compareTo(pivot, field, type)) {
                i++;
                Estudiante temp = p[i];
                p[i] = p[j];
                p[j] = temp;
            }
        }

        Estudiante aux = p[i + 1];
        p[i + 1] = p[ultimo];
        p[ultimo] = aux;

        return i + 1;
    }

    public ListaEnlazada<Estudiante> busquedaBinaria(ListaEnlazada<Estudiante> lista, String text, String campo) throws Exception {
        ListaEnlazada<Estudiante> listaOrdenada = ordenarQS(lista, 0, campo);

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

    public Estudiante busquedaBinaria2(ListaEnlazada<Estudiante> lista, String text, String campo) throws Exception {
        ListaEnlazada<Estudiante> listaOrdenada = ordenarQS(lista,0, campo);
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);

        if (index != -1) {
            return listaOrdenada.get(index);
        } else {
            System.out.println("Elemento no encontrado");
            return null;
        }
    }

    private boolean getForm(Estudiante est, String text, String campo) {
        return switch (campo.toLowerCase()) {
            case "id" -> Integer.toString(est.getId()).equalsIgnoreCase(text);
            case "nombre" -> est.getNombre().equalsIgnoreCase(text);
            case "apellido" -> est.getApellido().equalsIgnoreCase(text);
            case "dni" -> est.getDni().equalsIgnoreCase(text);
            default -> throw new IllegalArgumentException("Campo de comparación no válido");
        };
    }
}
