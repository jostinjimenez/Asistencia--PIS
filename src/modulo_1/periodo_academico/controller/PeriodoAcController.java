package modulo_1.periodo_academico.controller;

import DAO.DataAccessObject;
import model.PeriodoAcademico;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import java.io.FileOutputStream;
import java.lang.reflect.Field;

import static modulo_1.inicio_sesion.controller.util.Utilidades.getField;


public class PeriodoAcController extends DataAccessObject<PeriodoAcademico> {
    // Atributos
    private ListaEnlazada<PeriodoAcademico> periodoAcademicos;
    private PeriodoAcademico periodoAc = new PeriodoAcademico();
    private Integer index = -1;

    // Constructor
    public PeriodoAcController() {
        super(PeriodoAcademico.class);
        this.periodoAcademicos = new ListaEnlazada<>();
    }

    // Getters y Setters
    public ListaEnlazada<PeriodoAcademico> getPeriodoAcademicos() {
        if (periodoAcademicos.isEmpty()) {
            periodoAcademicos = this.list_All();
        }
        return periodoAcademicos;
    }

    public void setPeriodoAcademicos(ListaEnlazada<PeriodoAcademico> periodoAcademicos) {
        this.periodoAcademicos = periodoAcademicos;
    }

    public PeriodoAcademico getPeriodoAc() {
        return periodoAc;
    }

    public void setPeriodoAc(PeriodoAcademico periodoAc) {
        this.periodoAc = periodoAc;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    // Metodos
    public Boolean save() {
        this.periodoAc.setId(generarID());
        return save(periodoAc);
    }

    public Boolean update(Integer index) {
        return update(periodoAc, index);
    }

    public Boolean delete(Integer idPeriodoAc) {
        for (int i = 0; i < periodoAcademicos.getSize(); i++) {
            PeriodoAcademico pa = null;
            try {
                pa = periodoAcademicos.get(i);
            } catch (VacioExceptions e) {
                throw new RuntimeException(e);
            }
            if (pa.getId().equals(idPeriodoAc)) {
                try {
                    pa.setEstado(false);
                    this.xStream.toXML(periodoAcademicos, new FileOutputStream(URL));
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
    public ListaEnlazada<PeriodoAcademico> ordenarQS(ListaEnlazada<PeriodoAcademico> lista, Integer type, String field) throws Exception {
        PeriodoAcademico[] periodos = lista.toArray();
        Field faux = getField(PeriodoAcademico.class, field);
        if (faux != null) {
            quickSort(periodos, 0, periodos.length - 1, type, field);
        } else {
            throw new Exception("El atributo no existe");
        }
        return lista.toList(periodos);
    }

    private void quickSort(PeriodoAcademico[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        if (primero < ultimo) {
            int pi = partition(p, primero, ultimo, type, field);

            quickSort(p, primero, pi - 1, type, field);
            quickSort(p, pi + 1, ultimo, type, field);
        }
    }

    private int partition(PeriodoAcademico[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        PeriodoAcademico pivot = p[ultimo];
        int i = (primero - 1);

        for (int j = primero; j < ultimo; j++) {
            if (p[j].compareTo(pivot, field, type)) {
                i++;
                PeriodoAcademico temp = p[i];
                p[i] = p[j];
                p[j] = temp;
            }
        }

        PeriodoAcademico aux = p[i + 1];
        p[i + 1] = p[ultimo];
        p[ultimo] = aux;

        return i + 1;
    }

    public PeriodoAcademico buscarId(ListaEnlazada<PeriodoAcademico> lista, Integer id) throws Exception {
        ListaEnlazada<PeriodoAcademico> lo = ordenarQS(lista, 0, "id");

        int left = 0;
        int right = lo.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            PeriodoAcademico midPeriodo = lo.get(mid);

            if (midPeriodo.getId().equals(id)) {
                return midPeriodo;
            }

            if (midPeriodo.getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public ListaEnlazada<PeriodoAcademico> buscarAnio(ListaEnlazada<PeriodoAcademico> lista, Integer anio) throws Exception {
        ListaEnlazada<PeriodoAcademico> result = new ListaEnlazada<>();
        for (int i = 0; i < lista.getSize(); i++) {
            PeriodoAcademico pa = lista.get(i);
            if (pa.getAnio().equals(anio)) {
                result.add(pa);
            }
        }
        return result;
    }

    public ListaEnlazada<PeriodoAcademico> buscarFechaInicio(ListaEnlazada<PeriodoAcademico> lista, String fechaInicio) throws Exception {
        ListaEnlazada<PeriodoAcademico> result = new ListaEnlazada<>();
        for (int i = 0; i < lista.getSize(); i++) {
            PeriodoAcademico pa = lista.get(i);
            if (pa.getFechaInicio().equals(fechaInicio)) {
                result.add(pa);
            }
        }
        return result;
    }

    public ListaEnlazada<PeriodoAcademico> buscarFechaFin(ListaEnlazada<PeriodoAcademico> lista, String fechaFin) throws Exception {
        ListaEnlazada<PeriodoAcademico> result = new ListaEnlazada<>();
        for (int i = 0; i < lista.getSize(); i++) {
            PeriodoAcademico pa = lista.get(i);
            if (pa.getFechaFin().equals(fechaFin)) {
                result.add(pa);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        PeriodoAcController pc = new PeriodoAcController();

        System.out.println("Ordenamiento por QuickSort");
        System.out.println("--------------------------------");
        try {

            System.out.println(pc.buscarAnio(pc.getPeriodoAcademicos(), 2024).print());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

