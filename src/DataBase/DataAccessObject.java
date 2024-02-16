package DataBase;

import tda_listas.ListaEnlazada;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase adaptadora para los metodos de guardar, modificar, listar y buscar por id desde la Base de datos
 *
 * @author infierno
 */
public class DataAccessObject<T> implements TransferObject<T> {
    /**
     * Obejto Conexion
     */
    private Connection connection;
    /**
     * Class del modelo a usar
     */
    private Class clazz;

    /**
     * Constructor de la clase
     *
     * @param clazz El objeto de la clase del modelo Ejemplo: Persona.class
     */
    public DataAccessObject(Class clazz) {
        this.connection = new Connection();
        this.clazz = clazz;
    }

    /**
     * Metodo que permite guardar
     *
     * @param obj El objeto del modelo lleno
     * @return La llave primaria generada por el motor de base de datos (se sugiere construir la tabla de base de datos con la generacion de id auto incementable)
     * @throws Exception Cuando no se puede guardar en la base de datos
     */
    @Override
    public Integer save(T obj) throws Exception {
        String query = queryInsert(obj);
        Integer idGenerado = -1;

        try (PreparedStatement statement = connection.getConnection().prepareStatement(query)) {
            statement.executeUpdate();

            // Recupera el valor de la secuencia después de la inserción
            try (Statement seqStatement = connection.getConnection().createStatement()) {
                ResultSet resultSet = seqStatement.executeQuery("SELECT INCREMENTO.CURRVAL FROM dual");
                if (resultSet.next()) {
                    idGenerado = resultSet.getInt(1);
                }
            }
        } finally {
            connection.getConnection().close();
            connection.setConnection(null);
        }
        return idGenerado;
    }

    /**
     * Metodo que permite guardar
     *
     * @param obj El objeto del modelo lleno
     * @return True si se guardo, False si no se guardo
     * @throws Exception Cuando no se puede guardar en la base de datos
     */
    public Boolean saveB(T obj) throws Exception {
        String query = queryInsert(obj);
        Boolean band = false;

        try (PreparedStatement statement = connection.getConnection().prepareStatement(query)) {
            int affectedRows = statement.executeUpdate();
            band = affectedRows > 0;
        } finally {
            connection.getConnection().close();
            connection.setConnection(null);
        }
        return band;
    }


    /**
     * Metodo que permite modificar un registro en la base de datos, para modificar se debe primero consultar el Objeto haciendo uso del metodo Obtener
     *
     * @param obj El objeto del modelo a modificar
     * @throws Exception Alguna Excepcion si no modifica
     */
    @Override
    public void update(T obj) throws Exception {
        String query = queryUpdate(obj);
        Statement st = connection.getConnection().createStatement();
        st.executeUpdate(query);
        connection.getConnection().close();
        connection.setConnection(null);
    }

    /**
     * Metodo que permite sacar los datos de la base de datos
     *
     * @return Un Objeto de la ListaEnlazada con los datos llenos
     */
    @Override
    public ListaEnlazada<T> list_All() {
        ListaEnlazada<T> lista = new ListaEnlazada<>();
        try {
            Statement stmt = connection.getConnection().createStatement();
            String query = "SELECT * FROM " + clazz.getSimpleName().toLowerCase();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                lista.add(llenarObjeto(rs));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    /**
     * Permite obtener un objeto de la base de datos a travez del Id
     *
     * @param id El id a buscar en la base de datos
     * @return El objeto buscado, es null si no esxiste el objeto
     */
    @Override
    public T find(Integer id) {
        T data = null;
        try {
            Statement stmt = connection.getConnection().createStatement();
            String query = "select * from " + clazz.getSimpleName().toLowerCase() + " where id = " + id;
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                data = llenarObjeto(rs);
            }
        } catch (Exception e) {
        }
        return data;
    }

    /**
     * Permite eliminar un objeto de la base de datos
     *
     * @param id El id a eliminar
     * @return True si se elimino, False si no se elimino
     */
    @Override
    public Boolean delete(Integer id) {
        try {
            Statement stmt = connection.getConnection().createStatement();
            String query = "DELETE FROM " + clazz.getSimpleName().toLowerCase() + " WHERE id = " + id;
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //--------------ESTO ES DEL CRUD NO MODIFICAR AL MENOS QUE LO AMERITE------
    private T llenarObjeto(ResultSet rs) {
        T data = null;
        try {
            data = (T) clazz.getDeclaredConstructor().newInstance();
            for (Field f : clazz.getDeclaredFields()) {
                String atributo = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                fijarDatos(f, rs, data, atributo);
            }
            for (Field f : clazz.getSuperclass().getDeclaredFields()) {
                String atributo = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                fijarDatos(f, rs, data, atributo);
            }

        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
        return data;
    }

    private void fijarDatos(Field f, ResultSet rs, T data, String atributo) {
        try {
            if (f.getName().startsWith("$")) {
                // Ignora los campos internos generados automáticamente por el compilador de Java
                return;
            }
            Method m = null;
            //System.out.println("Procesando atributo: " + atributo); // Agrega esta línea
            if (f.getType().getSimpleName().equalsIgnoreCase("String")) {
                m = clazz.getMethod("set" + atributo, String.class);
                m.invoke(data, rs.getString(atributo));
            }

            if (f.getType().getSimpleName().equalsIgnoreCase("Integer")) {
                m = clazz.getMethod("set" + atributo, Integer.class);
                m.invoke(data, rs.getInt(atributo));
            }

            if (f.getType().getSimpleName().equalsIgnoreCase("Double")) {
                m = clazz.getMethod("set" + atributo, Double.class);
                m.invoke(data, rs.getDouble(atributo));
            }

            if (f.getType().getSimpleName().equalsIgnoreCase("Boolean")) {
                m = clazz.getMethod("set" + atributo, Boolean.class);
                m.invoke(data, rs.getBoolean(atributo));
            }

            if (f.getType().getSimpleName().equalsIgnoreCase("Date")) {
                m = clazz.getMethod("set" + atributo, Date.class);
                m.invoke(data, rs.getDate(atributo));
            }

            if (f.getType().isEnum()) {
                m = clazz.getMethod("set" + atributo, (Class<Enum>) f.getType());
                m.invoke(data, Enum.valueOf((Class<Enum>) f.getType(), rs.getString(atributo)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private HashMap<String, Object> obtenerObjeto(T obj) {
        HashMap<String, Object> mapa = new HashMap<>();
        try {
            for (Field f : clazz.getDeclaredFields()) {
                Method m = null;
                String atributo = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                m = clazz.getMethod("get" + atributo);
                Object aux = m.invoke(obj);
                if (aux != null) {
                    mapa.put(atributo.toLowerCase(), aux);
                }
            }

            for (Field f : clazz.getSuperclass().getDeclaredFields()) {
                Method m = null;
                String atributo = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                m = clazz.getMethod("get" + atributo);
                Object aux = m.invoke(obj);
                if (aux != null) {
                    mapa.put(atributo.toLowerCase(), aux);
                }

            }
        } catch (Exception e) {
            System.out.println("No se pudo tener dato");
        }
        return mapa;
    }

    private String queryInsert(T obj) {
        HashMap<String, Object> mapa = obtenerObjeto(obj);
        String query = "INSERT INTO " + clazz.getSimpleName().toLowerCase() + " (";
        for (Map.Entry<String, Object> entry : mapa.entrySet()) {
            query += entry.getKey() + ",";
        }
        query = query.substring(0, query.length() - 1);
        query += ") VALUES (";
        for (Map.Entry<String, Object> entry : mapa.entrySet()) {
            if (entry.getValue().getClass().getSuperclass().getSimpleName().equalsIgnoreCase("Number")) {
                query += entry.getValue() + ", ";
            } else if (entry.getValue().getClass().getSimpleName().equalsIgnoreCase("Boolean")) {
                query += ((Boolean) entry.getValue() ? "1" : "0") + ", ";
            } else if (entry.getValue().getClass().getSimpleName().equalsIgnoreCase("Date")) {
                SimpleDateFormat formato = new SimpleDateFormat("dd-MMM-yyyy");
                query += "'" + formato.format(entry.getValue()) + "'" + ", ";
            } else {
                query += "'" + entry.getValue().toString() + "'" + ", ";
            }
        }
        query = query.substring(0, query.length() - 2);
        query += ")";
        return query;
    }

    private String queryUpdate(T obj) {
        HashMap<String, Object> mapa = obtenerObjeto(obj);
        String query = "UPDATE " + clazz.getSimpleName().toLowerCase() + " SET ";
        Integer id = 0;
        for (Map.Entry<String, Object> entry : mapa.entrySet()) {
            if (entry.getKey().toString().equalsIgnoreCase("id")) {
                id = (Integer) entry.getValue();
            } else {
                query += entry.getKey() + " = ";
                if (entry.getValue().getClass().getSuperclass().getSimpleName().equalsIgnoreCase("Number")) {
                    query += entry.getValue() + ", ";
                } else if (entry.getValue().getClass().getSimpleName().equalsIgnoreCase("Boolean")) {
                    query += ((Boolean) entry.getValue() ? "1" : "0") + ", ";
                } else if (entry.getValue().getClass().getSimpleName().equalsIgnoreCase("Date")) {
                    SimpleDateFormat formato = new SimpleDateFormat("dd-MMM-yyyy");
                    query += "'" + formato.format(entry.getValue()) + "'" + ", ";
                } else {
                    query += "'" + entry.getValue().toString() + "'" + ", ";
                }
            }
        }
        query = query.substring(0, query.length() - 2);
        query += " WHERE id = " + id;
        return query;
    }
}
