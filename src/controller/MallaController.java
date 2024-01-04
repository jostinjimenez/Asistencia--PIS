package controller;

import tda_listas.ListaEnlazada;
import DAO.DataAccessObject;
import model.Malla;

public class MallaController extends DataAccessObject<Malla> {

    private Malla malla = new Malla();
    private ListaEnlazada<Malla> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public MallaController() {
        super(Malla.class);
    }

    public Malla getMalla() {
        if (malla == null) {
            malla = new Malla();
        }
        return malla;
    }

    public void setMalla(Malla malla) {
        this.malla = malla;
    }

    public Boolean saved() {
        boolean isSaved = save(malla);
        if (isSaved) {
            lista = getLista(); // Actualizar la lista después de guardar
        }
        return isSaved;
    }

    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        Integer length = list_All().getSize() + 1;
        Integer pos = Integer.toString(length).length();
        for (int i = 0; i < (10 - pos); i++) {
            code.append("0");
        }
        code.append(length.toString());
        return code.toString();
    }

    public ListaEnlazada<Malla> getLista() {
        lista = list_All(); // Actualizar la lista desde la base de datos
        return lista;
    }

    public Boolean update(Integer i) {
        return update(malla, i);
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Malla> lista) {
        this.lista = lista;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }
}