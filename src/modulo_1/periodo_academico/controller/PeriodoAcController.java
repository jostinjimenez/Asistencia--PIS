package modulo_1.periodo_academico.controller;

import DAO.DataAccessObject;
import model.PeriodoAcademico;
import model.Persona;
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

    public void cerrarPeriodoAc(Integer idPeriodoAc) {
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
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
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


//    public ListaEnlazada<PeriodoAcademico> buscarEstado(ListaEnlazada<PeriodoAcademico> lista, Boolean estado) throws Exception {
//        ListaEnlazada<PeriodoAcademico> lo = this.ordenarQS(lista, 0, "estado");
//        PeriodoAcademico[] p = lo.toArray();
//        ListaEnlazada<PeriodoAcademico> result = new ListaEnlazada<>();
//
//        int left = 0;
//        int right = lista.getSize() - 1;
//
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//
//            if (p[mid].getEstado().booleanValue() == estado.booleanValue()) {
//                result.add(p[mid]);
//
//                int temp = mid - 1;
//                while (temp >= left && p[temp].getEstado().booleanValue() == estado.booleanValue()) {
//                    result.add(p[temp]);
//                    temp--;
//                }
//
//                temp = mid + 1;
//                while (temp <= right && p[temp].getEstado().booleanValue() == estado.booleanValue()) {
//                    result.add(p[temp]);
//                    temp++;
//                }
//                return result;
//            }
//            if (p[mid].getEstado().booleanValue() < estado.booleanValue()) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//        return result;
//    }

    public ListaEnlazada<PeriodoAcademico> buscarFechaInicio(ListaEnlazada<PeriodoAcademico> lista, String fechaInicio) throws Exception {
        ListaEnlazada<PeriodoAcademico> lo = this.ordenarQS(lista, 0, "fechaInicio");
        PeriodoAcademico[] p = lo.toArray();
        ListaEnlazada<PeriodoAcademico> result = new ListaEnlazada<>();

        int left = 0;
        int right = lista.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (p[mid].getFechaInicio().equals(fechaInicio)) {
                result.add(p[mid]);

                int temp = mid - 1;
                while (temp >= left && p[temp].getFechaInicio().equals(fechaInicio)) {
                    result.add(p[temp]);
                    temp--;
                }

                temp = mid + 1;
                while (temp <= right && p[temp].getFechaInicio().equals(fechaInicio)) {
                    result.add(p[temp]);
                    temp++;
                }
                return result;
            }
            if (p[mid].getFechaInicio().compareTo(fechaInicio) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public ListaEnlazada<PeriodoAcademico> buscarFechaFin(ListaEnlazada<PeriodoAcademico> lista, String fechaFin) throws Exception {
        ListaEnlazada<PeriodoAcademico> lo = this.ordenarQS(lista, 0, "fechaFin");
        PeriodoAcademico[] p = lo.toArray();
        ListaEnlazada<PeriodoAcademico> result = new ListaEnlazada<>();

        int left = 0;
        int right = lista.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (p[mid].getFechaFin().equals(fechaFin)) {
                result.add(p[mid]);

                int temp = mid - 1;
                while (temp >= left && p[temp].getFechaFin().equals(fechaFin)) {
                    result.add(p[temp]);
                    temp--;
                }

                temp = mid + 1;
                while (temp <= right && p[temp].getFechaFin().equals(fechaFin)) {
                    result.add(p[temp]);
                    temp++;
                }
                return result;
            }
            if (p[mid].getFechaFin().compareTo(fechaFin) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
}

