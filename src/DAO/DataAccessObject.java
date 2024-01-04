package DAO;

import com.thoughtworks.xstream.XStream;
import tda_listas.ListaEnlazada;

import java.io.FileOutputStream;
import java.io.FileReader;
import tda_listas.exceptions.VacioExceptions;

public class DataAccessObject<T> implements TransferObject {

    protected XStream xStream;
    private Class<T> clazz;
    protected String URL;

    private static Integer idCounter;

    public DataAccessObject(Class<T> clazz) {
        this.clazz = clazz;
        xStream = Connection.getxStream();
        URL = Connection.getURL() + this.clazz.getSimpleName() + ".json";

        // Inicializar idCounter la primera vez que se crea una instancia de la clase
        if (idCounter == null) {
            idCounter = (new ListaEnlazada<T>()).getSize() + 1;
        }
    }

    @Override
    public Boolean save(Object data) {
        ListaEnlazada<T> list = list_All();
        list.add((T) data);

        try {
            // Obtener el último ID utilizado desde la base de datos
            Integer lastUsedId = getLastUsedIdFromDatabase();

            // Incrementar el ID antes de asignarlo al nuevo objeto
            data.getClass().getMethod("setId", Integer.class).invoke(data, lastUsedId + 1);

            // Incrementar el contador de ID para el próximo uso
            idCounter = lastUsedId + 1;

            this.xStream.toXML(list, new FileOutputStream(URL));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

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

    public Integer generarID() {
        return idCounter;  // Devolver el próximo ID en lugar del tamaño de la lista
    }

    @Override
    public Boolean update(Object data, Integer index) {
        ListaEnlazada<T> list = list_All();

        if (index >= 0 && index < list.getSize()) {
            try {
                T existingObject = list.get(index);

                // Obtener el ID del objeto existente y asignarlo al objeto que estamos actualizando
                Integer existingId = (Integer) existingObject.getClass().getMethod("getId").invoke(existingObject);
                data.getClass().getMethod("setId", Integer.class).invoke(data, existingId);

                list.update(index, (T) data);
                this.xStream.alias(list.getClass().getName(), ListaEnlazada.class);
                this.xStream.toXML(list, new FileOutputStream(URL));
                return true;
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

    private Integer getLastUsedIdFromDatabase() throws VacioExceptions {
        // Obtener la lista actual de objetos desde el archivo JSON
        ListaEnlazada<T> list = list_All();

        // Verificar si la lista está vacía
        if (list.getSize() == 0) {
            return 0; // No hay objetos en la lista, devuelve 0 como valor predeterminado
        }

        // Obtener el último objeto de la lista
        T lastObject = list.get(list.getSize() - 1);

        try {
            // Obtener el ID del último objeto y devolverlo
            return (Integer) lastObject.getClass().getMethod("getId").invoke(lastObject);
        } catch (Exception e) {
            System.out.println("Error al obtener el último ID desde la base de datos: " + e.getMessage());
            return 0; // Devolver 0 en caso de error
        }
    }
}
