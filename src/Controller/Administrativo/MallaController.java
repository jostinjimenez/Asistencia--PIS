package Controller.Administrativo;

import DataBase.DataAccessObject;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import java.lang.reflect.Field;

import model.Malla;

import static Controller.Util.Utilidades.getField;

public class MallaController extends DataAccessObject<Malla> {

    private Malla malla = new Malla();
    private ListaEnlazada<Malla> mallas;
    private Integer index = -1;

    public MallaController() {
        super(Malla.class);
        this.mallas = new ListaEnlazada<>();

    }

    public ListaEnlazada<Malla> getMallas() {
        if (mallas.isEmpty()) {
            mallas = list_All();
        }
        return mallas;

    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setMallas(ListaEnlazada<Malla> mallas) {
        this.mallas = mallas;
    }

    public void setMalla(Malla malla) {
        this.malla = malla;
    }

    public Malla getMalla() {
        if (malla == null) {
            malla = new Malla();
        }
        return malla;
    }

    public Integer save() throws Exception {
        return super.save(this.malla, "SQC_MALLA");
    }

    public Boolean update() {
        try {
            update(this.malla);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ListaEnlazada<Malla> ordenarQS(ListaEnlazada<Malla> lista, Integer type, String field) throws Exception {
        Malla[] carrerass = lista.toArray();
        Field faux = getField(Malla.class, field);
        if (faux != null) {
            quickSort(carrerass, 0, carrerass.length - 1, type, field);
        } else {
            throw new Exception("El atributo no existe");
        }
        return lista.toList(carrerass);
    }

    private void quickSort(Malla[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        if (primero < ultimo) {
            int pi = partition(p, primero, ultimo, type, field);

            quickSort(p, primero, pi - 1, type, field);
            quickSort(p, pi + 1, ultimo, type, field);
        }
    }

    private int partition(Malla[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        Malla pivot = p[ultimo];
        int i = (primero - 1);

        for (int j = primero; j < ultimo; j++) {
            if (p[j].compareTo(pivot, field, type)) {
                i++;
                Malla temp = p[i];
                p[i] = p[j];
                p[j] = temp;
            }
        }

        Malla aux = p[i + 1];
        p[i + 1] = p[ultimo];
        p[ultimo] = aux;

        return i + 1;
    }

    public ListaEnlazada<Malla> busquedaBinaria(ListaEnlazada<Malla> lista, String text, String campo) throws Exception {
        ListaEnlazada<Malla> listaOrdenada = ordenarQS(lista, 0, campo);

        ListaEnlazada<Malla> marc = new ListaEnlazada<>();
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

    private int busquedaBinaria1(ListaEnlazada<Malla> lista, String text, String campo) throws VacioExceptions {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Malla mid = lista.get(indice);
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

    public Malla busquedaBinaria2(ListaEnlazada<Malla> lista, String text, String campo) throws Exception {
        ListaEnlazada<Malla> listaOrdenada = ordenarQS(lista, 0, campo);
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);

        if (index != -1) {
            return listaOrdenada.get(index);
        } else {
            System.out.println("Elemento no encontrado");
            return null;
        }
    }

    private boolean getForm(Malla mallaa, String text, String campo) {
        return switch (campo.toLowerCase()) {
            case "descripcion" -> mallaa.getDescripcion().equalsIgnoreCase(text);
            case "id" -> Integer.toString(mallaa.getId()).equalsIgnoreCase(text);
            case "nro_asignaturas" -> Integer.toString(mallaa.getNro_asignaturas()).equalsIgnoreCase(text);
            case "codigo" -> mallaa.getCodigo().equalsIgnoreCase(text);

            default -> throw new IllegalArgumentException("Campo de comparación no válido");
        };
    }

}
