package Controller.Administrativo;

import Controller.DataBase.DataAccessObject;
import model.Persona;
import Controller.tda_listas.ListaEnlazada;

import java.lang.reflect.Field;

import static Controller.Util.Utilidades.getField;
import Controller.tda_listas.exceptions.VacioExceptions;

public class PersonaController extends DataAccessObject<Persona> {

    // Atributos
    private ListaEnlazada<Persona> personas;
    private Persona persona = new Persona();
    private Integer index = -1;

    // Constructor
    public PersonaController() {
        super(Persona.class);
        this.personas = this.list_All();
    }

    // Getters y Setters
    public ListaEnlazada<Persona> getPersonas() {
        if (personas == null || personas.isEmpty()) {
            personas = this.list_All();
        }
        return personas;
    }

    public void setPersonas(ListaEnlazada<Persona> personas) {
        this.personas = personas;
    }

    public Persona getPersona() {
        return persona != null ? persona : new Persona();
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    // Metodos
    public Integer save() throws Exception {
        return super.save(this.persona, "SQC_PERSONA");
    }

    public Boolean update() {
        try {
            update(this.persona);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Ordenar por QuickSort
    public ListaEnlazada<Persona> ordenarQS(ListaEnlazada<Persona> lista, Integer type, String field) throws Exception {
        Persona[] personas = lista.toArray();
        Field faux = getField(Persona.class, field);
        if (faux != null) {
            quickSort(personas, 0, personas.length - 1, type, field);
        } else {
            throw new Exception("El atributo no existe");
        }
        return lista.toList(personas);
    }

    private void quickSort(Persona[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        if (primero < ultimo) {
            int pi = partition(p, primero, ultimo, type, field);

            quickSort(p, primero, pi - 1, type, field);
            quickSort(p, pi + 1, ultimo, type, field);
        }
    }

    private int partition(Persona[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        Persona pivot = p[ultimo];
        int i = (primero - 1);

        for (int j = primero; j < ultimo; j++) {
            if (p[j].compareTo(pivot, field, type)) {
                i++;
                Persona temp = p[i];
                p[i] = p[j];
                p[j] = temp;
            }
        }

        Persona aux = p[i + 1];
        p[i + 1] = p[ultimo];
        p[ultimo] = aux;

        return i + 1;
    }

    // Buscar por Busqueda Binaria
    public Persona busquedaBinaria2(ListaEnlazada<Persona> lista, String text, String campo) throws VacioExceptions, Exception {
        ListaEnlazada<Persona> listaOrdenada = ordenarQS(lista, 0, campo);
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);
        if (index != -1) {
            return listaOrdenada.get(index);
        } else {
            System.out.println("Elemento no encontrado");
            return null;
        }
    }

    public ListaEnlazada<Persona> busquedaBinaria(ListaEnlazada<Persona> lista, String text, String campo, String tipo, Integer type) throws VacioExceptions, Exception {
        ListaEnlazada<Persona> listaOrdenada = ordenarQS(lista, 0, campo);
        ListaEnlazada<Persona> marc = new ListaEnlazada<>();
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

    private int busquedaBinaria1(ListaEnlazada<Persona> lista, String text, String campo) throws VacioExceptions {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Persona mid = lista.get(indice);
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

    private boolean getForm(Persona persona, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "dni":
                return persona.getNombre().equalsIgnoreCase(text);
            case "id":
                return Integer.toString(persona.getId()).equalsIgnoreCase(text);
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

    private ListaEnlazada<Persona> ordenarLista(ListaEnlazada<Persona> lista, String campo) throws VacioExceptions, Exception {
        ListaEnlazada<Persona> listaOrdenada = new ListaEnlazada<>();
        listaOrdenada = ordenarQS(lista, 0, campo);
        return listaOrdenada;
    }

    public Persona buscarDni1(ListaEnlazada<Persona> lista, String txt) throws Exception {
        ListaEnlazada<Persona> lo = this.ordenarQS(lista, 0, "dni");
        Persona[] p = lo.toArray();

        int left = 0;
        int right = lista.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (p[mid].getDni().equalsIgnoreCase(txt)) {
                return p[mid];
            }
            if (p[mid].getDni().compareToIgnoreCase(txt) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Si no se encuentra ningún objeto con el DNI dado.
    }

    public Persona buscarID1(ListaEnlazada<Persona> lista, Integer id) throws Exception {
        ListaEnlazada<Persona> lo = this.ordenarQS(lista, 0, "id");
        Persona[] p = lo.toArray();

        int left = 0;
        int right = lista.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (p[mid].getId().intValue() == id.intValue()) {
                return p[mid];
            }
            if (p[mid].getId().intValue() < id.intValue()) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Si no se encuentra ningún objeto con el DNI dado.
    }
}