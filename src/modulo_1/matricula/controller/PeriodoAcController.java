package modulo_1.matricula.controller;

import DAO.DataAccessObject;
import model.PeriodoAcademico;
import model.Persona;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import java.io.FileOutputStream;


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
}

