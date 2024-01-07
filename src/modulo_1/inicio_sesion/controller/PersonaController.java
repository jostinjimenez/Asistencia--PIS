package modulo_1.inicio_sesion.controller;

import DAO.DataAccessObject;
import model.Cuenta;
import model.Persona;
import model.Rol;
import modulo_1.inicio_sesion.controller.util.Utilidades;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import java.io.FileOutputStream;
import java.lang.reflect.Field;

import static modulo_1.inicio_sesion.controller.util.Utilidades.getField;

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
        ListaEnlazada<Persona> personasActivas = new ListaEnlazada<>();
        for (Persona persona : personas) {
            if (persona.isActivo()) {
                personasActivas.add(persona);
            }
        }
        return personasActivas;
    }

    public void setPersonas(ListaEnlazada<Persona> personas) {
        this.personas = personas;
    }

    public Persona getPersona() {
        return persona != null ? persona : new Persona();
    }

    public Persona getPersonaID(Integer id) {
        for (int i = 0; i < personas.getSize(); i++) {
            Persona persona = null;
            try {
                persona = personas.get(i);
            } catch (VacioExceptions e) {
                throw new RuntimeException(e);
            }
            if (persona.getId().equals(id)) {
                return persona;
            }
        }
        return null;
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
    public Boolean save() {
        this.persona.setId(generarID());
        return save(persona);
    }

    public Boolean update(Integer index) {
        return update(persona, index);
    }

    public Boolean delete(Integer idPersona) {
        for (int i = 0; i < personas.getSize(); i++) {
            Persona persona = null;
            try {
                persona = personas.get(i);
            } catch (VacioExceptions e) {
                throw new RuntimeException(e);
            }
            if (persona.getId().equals(idPersona)) {
                try {
                    persona.setActivo(false);
                    this.xStream.toXML(personas, new FileOutputStream(URL));
                    return true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            }
        }
        return false;
    }

    // Ordenar por QuickSort
    public ListaEnlazada<Persona> ordenarQS(ListaEnlazada<Persona> lista, Integer type, String field) throws Exception {
        long startTime = System.nanoTime();
        Persona[] personas = lista.toArray();
        Field faux = getField(Persona.class, field);
        if (faux != null) {
            quickSort(personas, 0, personas.length - 1, type, field);
        } else {
            throw new Exception("El atributo no existe");
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Tiempo de ejecución de ordenarQS: " + duration + " nanosegundos");
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
    public ListaEnlazada<Persona> buscarRol(ListaEnlazada<Persona> lista, String field, Rol rol) throws Exception {
        ListaEnlazada<Persona> lo = this.ordenarQS(lista, 0, field);
        Persona[] p = lo.toArray();
        ListaEnlazada<Persona> result = new ListaEnlazada<>();

        int left = 0;
        int right = lista.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (p[mid].getIdRol().intValue() == rol.getId().intValue()) {
                result.add(p[mid]);

                int temp = mid - 1;
                while (temp >= left && p[temp].getIdRol().intValue() == rol.getId().intValue()) {
                    result.add(p[temp]);
                    temp--;
                }

                temp = mid + 1;
                while (temp <= right && p[temp].getIdRol().intValue() == rol.getId().intValue()) {
                    result.add(p[temp]);
                    temp++;
                }
                return result;
            }
            if (p[mid].getIdRol().intValue() < rol.getId().intValue()) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public ListaEnlazada<Persona> buscarRolCombinado(ListaEnlazada<Persona> lista, String field, Rol rol) throws Exception {
        long startTime = System.nanoTime();

        lista = this.ordenarQS(lista, 0, field);

        int n = lista.getSize();
        int segmento = (int) Math.sqrt(n);
        Persona[] personas = lista.toArray();
        ListaEnlazada<Persona> result = new ListaEnlazada<>();

        int i;
        for (i = 0; i < n; i += segmento) {
            if (personas[Math.min(i + segmento, n) - 1].getIdRol().intValue() >= rol.getId().intValue()) {
                break;
            }
        }

        if (i >= n) {
            return result;
        }

        int lo = i;
        int hi = Math.min(i + segmento, n);
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (personas[mid].getIdRol().intValue() == rol.getId().intValue()) {
                result.add(personas[mid]);

                int aux = mid - 1;
                while (aux >= lo && personas[aux].getIdRol().intValue() == rol.getId().intValue()) {
                    result.add(personas[aux]);
                    aux--;
                }

                aux = mid + 1;
                while (aux < hi && personas[aux].getIdRol().intValue() == rol.getId().intValue()) {
                    result.add(personas[aux]);
                    aux++;
                }
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                System.out.println("Tiempo de ejecución: " + duration + " nanosegundos");
                return result;
            } else if (personas[mid].getIdRol().intValue() < rol.getId().intValue()) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Tiempo de ejecución: " + duration + " nanosegundos");
        return result;
    }


    public static void main(String[] args) {
        PersonaController pc = new PersonaController();

        System.out.println("Ordenamiento por QuickSort");
        System.out.println("--------------------------------");
        try {
            System.out.println(pc.ordenarQS(pc.getPersonas(), 0, "apellido").print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
