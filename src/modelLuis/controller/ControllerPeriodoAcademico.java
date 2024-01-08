/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelLuis.controller;

/**
 *
 * @author Usuario
 */
import DAO.DataAccessObject;
import model.PeriodoAcademico;
import model.Persona;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import static modulo_1.inicio_sesion.controller.util.Utilidades.getField;

public class ControllerPeriodoAcademico extends DataAccessObject<PeriodoAcademico> {

    // Atributos
    private ListaEnlazada<PeriodoAcademico> periodoAcademicos;
    private PeriodoAcademico periodoAc = new PeriodoAcademico();
    private Integer index = -1;

    // Constructor
    public ControllerPeriodoAcademico() {
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

    public static void main(String[] args) {

        ListaEnlazada<Integer> ids = new ListaEnlazada();
        ids.add(1);
        PeriodoAcademico a = new PeriodoAcademico(1, 2018, "4/01/2024", "29/02/2024", true, ids);
        ControllerPeriodoAcademico p = new ControllerPeriodoAcademico();
        p.save();
    }

}
