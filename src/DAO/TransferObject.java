package DAO;

import tda_listas.ListaEnlazada;

public interface TransferObject<T> {
    public Boolean save(T data);
    
    public Boolean delete(Integer id);

    public Boolean update(T data, Integer index);

    public ListaEnlazada<T> list_All();

    public T find(Integer id);
}
