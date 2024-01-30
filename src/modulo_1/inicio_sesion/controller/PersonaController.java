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

    public ListaEnlazada<Persona> buscarNombre(ListaEnlazada<Persona> lista, String txt) throws Exception {
        ListaEnlazada<Persona> lo = this.ordenarQS(lista, 0, "nombre");
        Persona[] p = lo.toArray();
        ListaEnlazada<Persona> result = new ListaEnlazada<>();

        int left = 0;
        int right = lista.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (p[mid].getNombre().equalsIgnoreCase(txt)) {
                result.add(p[mid]);

                int temp = mid - 1;
                while (temp >= left && p[temp].getNombre().equalsIgnoreCase(txt)) {
                    result.add(p[temp]);
                    temp--;
                }

                temp = mid + 1;
                while (temp <= right && p[temp].getNombre().equalsIgnoreCase(txt)) {
                    result.add(p[temp]);
                    temp++;
                }
                return result;
            }
            if (p[mid].getNombre().compareToIgnoreCase(txt) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public ListaEnlazada<Persona> buscarApellido(ListaEnlazada<Persona> lista, String txt) throws Exception {
        ListaEnlazada<Persona> lo = this.ordenarQS(lista, 0, "apellido");
        Persona[] p = lo.toArray();
        ListaEnlazada<Persona> result = new ListaEnlazada<>();

        int left = 0;
        int right = lista.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (p[mid].getApellido().equalsIgnoreCase(txt)) {
                result.add(p[mid]);

                int temp = mid - 1;
                while (temp >= left && p[temp].getApellido().equalsIgnoreCase(txt)) {
                    result.add(p[temp]);
                    temp--;
                }

                temp = mid + 1;
                while (temp <= right && p[temp].getApellido().equalsIgnoreCase(txt)) {
                    result.add(p[temp]);
                    temp++;
                }
                return result;
            }
            if (p[mid].getApellido().compareToIgnoreCase(txt) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public ListaEnlazada<Persona> buscarDni(ListaEnlazada<Persona> lista, String txt) throws Exception {
        ListaEnlazada<Persona> lo = this.ordenarQS(lista, 0, "dni");
        Persona[] p = lo.toArray();
        ListaEnlazada<Persona> result = new ListaEnlazada<>();

        int left = 0;
        int right = lista.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (p[mid].getDni().equalsIgnoreCase(txt)) {
                result.add(p[mid]);

                int temp = mid - 1;
                while (temp >= left && p[temp].getDni().equalsIgnoreCase(txt)) {
                    result.add(p[temp]);
                    temp--;
                }

                temp = mid + 1;
                while (temp <= right && p[temp].getDni().equalsIgnoreCase(txt)) {
                    result.add(p[temp]);
                    temp++;
                }
                return result;
            }
            if (p[mid].getDni().compareToIgnoreCase(txt) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public ListaEnlazada<Persona> buscarId(ListaEnlazada<Persona> lista, Integer id) throws Exception {
        ListaEnlazada<Persona> lo = this.ordenarQS(lista, 0, "id");
        Persona[] p = lo.toArray();
        ListaEnlazada<Persona> result = new ListaEnlazada<>();

        int left = 0;
        int right = lista.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (p[mid].getId().intValue() == id.intValue()) {
                result.add(p[mid]);

                int temp = mid - 1;
                while (temp >= left && p[temp].getId().intValue() == id.intValue()) {
                    result.add(p[temp]);
                    temp--;
                }

                temp = mid + 1;
                while (temp <= right && p[temp].getId().intValue() == id.intValue()) {
                    result.add(p[temp]);
                    temp++;
                }
                return result;
            }
            if (p[mid].getId().intValue() < id.intValue()) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        PersonaController pc = new PersonaController();

        pc.getPersona().setNombre("Jostin");
        pc.getPersona().setApellido("Jimenez");
        pc.getPersona().setDni("1150696977");
        pc.getPersona().setFecha_nacimiento("Calle 1");
        pc.getPersona().setTelefono("0999999999");
        pc.getPersona().setCorreo_personal("asdasd");
        pc.getPersona().setIdRol(1);
        pc.getPersona().setActivo(true);
        pc.getPersona().setId(1);
        pc.save();
    }
}
