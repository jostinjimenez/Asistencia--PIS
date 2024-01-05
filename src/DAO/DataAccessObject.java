package DAO;

import com.thoughtworks.xstream.XStream;
import java.io.FileNotFoundException;
import tda_listas.ListaEnlazada;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import tda_listas.exceptions.VacioExceptions;

public class DataAccessObject<T> implements TransferObject {

    protected XStream xStream;
    private Class<T> clazz;
    protected String URL;

    public DataAccessObject(Class<T> clazz) {
        this.clazz = clazz;
        xStream = Connection.getXstream();
        URL = Connection.getURL() + this.clazz.getSimpleName() + ".json";
    }

    @Override
    public Boolean save(Object data) {
        ListaEnlazada<T> list = list_All();
        list.add((T) data);
        try {
            this.xStream.toXML(list, new FileOutputStream(URL));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Integer generarID() {
        return list_All().getSize() + 1;
    }

    @Override
    public Boolean update(Object data, Integer index) {
        ListaEnlazada<T> list = list_All();

        if (index >= 0 && index < list.getSize()) {
            try {
                {
                    list.update(index, (T) data);
                    this.xStream.alias(list.getClass().getName(), ListaEnlazada.class);
                    this.xStream.toXML(list, new FileOutputStream(URL));
                    return true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public ListaEnlazada<T> list_All() {
        // Guardar una lista enlazada
        ListaEnlazada<T> list = new ListaEnlazada<>();
        try {
            list = (ListaEnlazada<T>) xStream.fromXML(new FileReader(URL));
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public Object find(Integer id) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        ListaEnlazada<T> list = list_All();
        try {
            if (!list.isEmpty()) {
                T entidadAEliminar = list.get(id);
                if (list.delete(id) != null) {
                    this.xStream.alias(list.getClass().getName(), ListaEnlazada.class);
                    try {
                        this.xStream.toXML(list, new FileOutputStream(URL));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                // Manejar el caso cuando la lista está vacía
                System.err.println("La lista está vacía, no se puede eliminar.");
                return false;
            }
        } catch (VacioExceptions ex) {
            // Manejar la excepción o imprimir un mensaje de depuración
            System.err.println("Error al intentar eliminar el elemento: " + ex.getMessage());
            return false;
        }
    }
}
