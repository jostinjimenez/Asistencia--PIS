/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.DataBase;

import Controller.tda_listas.ListaEnlazada;

/**
 *
 * @author walter
 */
public interface TransferObject<T> {
    /**
     * Metodo que permite realizar el guardaddo
     * @param obj Objeto del modelo
     * @return El id generado producto del guardado
     */
    public Integer save(T obj, String sequenceName) throws Exception;
    /**
     * Permite modificar los datos en un repositorio de datos
     * @param obj Objeto a modificar     
     */
    public void update(T obj) throws Exception;
    /**
     * LIstado de objetos en la BD
     * @return Una ListaEnlazada
     */
    public ListaEnlazada<T> list_All();
    /**
     * Permite obtener un objeto de la base de datos a travez del Id
     * @param id El id a buscar en la base de datos
     * @return El objeto buscado, es null si no esxiste el objeto
     */
    public T find(Integer id);
    /**
     * Permite eliminar un objeto de la base de datos
     * @param id El id a eliminar
     * @return True si se elimino, False si no se elimino
     */
    public Boolean delete(Integer id);
}
