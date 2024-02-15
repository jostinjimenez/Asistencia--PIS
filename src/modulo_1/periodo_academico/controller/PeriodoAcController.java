package modulo_1.periodo_academico.controller;

import DataBase.DataAccessObject;
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
    public Integer save() throws Exception {
        return super.save(this.periodoAc);
    }

    public Boolean update(Integer index) {
        try {
            update(this.periodoAc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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

    public ListaEnlazada<PeriodoAcademico> buscarId(ListaEnlazada<PeriodoAcademico> lista, Integer id) throws Exception {
        ListaEnlazada<PeriodoAcademico> lo = this.ordenarQS(lista, 0, "id");
        PeriodoAcademico[] p = lo.toArray();
        ListaEnlazada<PeriodoAcademico> result = new ListaEnlazada<>();

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


}

