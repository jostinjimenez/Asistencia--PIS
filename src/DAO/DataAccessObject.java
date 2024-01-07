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
        return list_All().getSize();
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
    public Boolean delete(Object data) {
        ListaEnlazada<T> list = list_All();

        // Buscar el objeto en la lista utilizando el método equals
        T objectToDelete = (T) data;
        boolean found = false;
        int index = 0;

        for (T item : list) {
            if (item.equals(objectToDelete)) {
                found = true;
                break;
            }
            index++;
        }

        if (found) {
            try {
                // Agregar impresión de debug
                System.out.println("Index to delete: " + index);

                // Eliminar el objeto de la lista
                list.delete(index);

                // Actualizar el archivo XML
                this.xStream.alias(list.getClass().getName(), ListaEnlazada.class);
                this.xStream.toXML(list, new FileOutputStream(URL));

                return true;
            } catch (Exception e) {
                // Agregar impresión de debug
                System.out.println("Error deleting object: " + e.getMessage());
                return false;
            }
        } else {
            // Agregar impresión de debug
            System.out.println("Object not found in the list.");
            return false; // El objeto no se encontró en la lista
        }
    }
}
