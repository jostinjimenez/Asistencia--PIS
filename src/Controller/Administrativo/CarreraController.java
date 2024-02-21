package Controller.Administrativo;

import Controller.DataBase.DataAccessObject;
import model.*;
import Controller.tda_listas.ListaEnlazada;
import Controller.tda_listas.exceptions.VacioExceptions;

import java.lang.reflect.Field;

import static Controller.Util.Utilidades.getField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controlador para la entidad Carrera. Extiende de DataAccessObject para
 * manejar operaciones de base de datos.
 */
public class CarreraController extends DataAccessObject<Carrera> {

    // Atributos
    private ListaEnlazada<Carrera> carreras;
    private Carrera carrera = new Carrera();
    private Integer index = -1;

    /**
     * Constructor del controlador. Inicializa la lista de carreras.
     */
    public CarreraController() {
        super(Carrera.class);
        this.carreras = new ListaEnlazada<>();
    }

    /**
     * Obtiene la lista de carreras. Si la lista está vacía, se obtienen todas
     * las carreras de la base de datos.
     *
     * @return Lista de carreras.
     */
    public ListaEnlazada<Carrera> getCarreras() {
        if (carreras.isEmpty()) {
            carreras = this.list_All();
        }
        return carreras;
    }

    /**
     * Establece la lista de carreras.
     *
     * @param carreras Lista de carreras.
     */
    public void setCarreras(ListaEnlazada<Carrera> carreras) {
        this.carreras = carreras;
    }

    /**
     * Obtiene la carrera actual. Si la carrera es nula, se crea una nueva
     * carrera.
     *
     * @return Carrera actual.
     */
    public Carrera getCarrera() {
        return carrera != null ? carrera : new Carrera();
    }

    /**
     * Establece la carrera actual.
     *
     * @param carrera Carrera actual.
     */
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    /**
     * Obtiene el índice actual.
     *
     * @return Índice actual.
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Establece el índice actual.
     *
     * @param index Índice actual.
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * Guarda la carrera actual en la base de datos.
     *
     * @return ID de la carrera guardada.
     * @throws Exception Si ocurre un error al guardar la carrera.
     */
    public Integer save() throws Exception {
        return super.save(this.carrera, "SQC_CARRERA");
    }

    /**
     * Actualiza la carrera actual en la base de datos.
     *
     * @return Verdadero si la actualización fue exitosa, falso en caso
     * contrario.
     */
    public Boolean update() {
        try {
            update(this.carrera);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ListaEnlazada<Carrera> ordenarQS(ListaEnlazada<Carrera> lista, Integer type, String field) throws Exception {
        Carrera[] carrerass = lista.toArray();
        Field faux = getField(Carrera.class, field);
        if (faux != null) {
            quickSort(carrerass, 0, carrerass.length - 1, type, field);
        } else {
            throw new Exception("El atributo no existe");
        }
        return lista.toList(carrerass);
    }

    private void quickSort(Carrera[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        if (primero < ultimo) {
            int pi = partition(p, primero, ultimo, type, field);

            quickSort(p, primero, pi - 1, type, field);
            quickSort(p, pi + 1, ultimo, type, field);
        }
    }

    private int partition(Carrera[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        Carrera pivot = p[ultimo];
        int i = (primero - 1);

        for (int j = primero; j < ultimo; j++) {
            if (p[j].compareTo(pivot, field, type)) {
                i++;
                Carrera temp = p[i];
                p[i] = p[j];
                p[j] = temp;
            }
        }

        Carrera aux = p[i + 1];
        p[i + 1] = p[ultimo];
        p[ultimo] = aux;

        return i + 1;
    }

    public ListaEnlazada<Carrera> busquedaBinaria(ListaEnlazada<Carrera> lista, String text, String campo) throws Exception {
        ListaEnlazada<Carrera> listaOrdenada = ordenarQS(lista, 0, campo);

        ListaEnlazada<Carrera> marc = new ListaEnlazada<>();
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);
        if (index != -1) {
            while (index < listaOrdenada.getSize() && getForm(listaOrdenada.get(index), text, campo)) {
                marc.add(listaOrdenada.get(index));
                index++;
            }

        } else {
            System.out.println("Elemento no encontrado");
        }

        return marc;
    }

    private int busquedaBinaria1(ListaEnlazada<Carrera> lista, String text, String campo) throws VacioExceptions {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Carrera mid = lista.get(indice);
            int resultado = mid.comparar(mid, text, campo);
            if (resultado == 0) {
                int izquierda = indice - 1;
                while (izquierda >= 0 && getForm(lista.get(izquierda), text, campo)) {
                    indice = izquierda;
                    izquierda--;
                }
                return indice;
            } else if (resultado < 0) {
                sup = indice - 1;
            } else {
                infe = indice + 1;
            }
        }

        return -1;
    }

    public Carrera busquedaBinaria2(ListaEnlazada<Carrera> lista, String text, String campo) throws Exception {
        ListaEnlazada<Carrera> listaOrdenada = ordenarQS(lista, 0, campo);
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);

        if (index != -1) {
            return listaOrdenada.get(index);
        } else {
            System.out.println("Elemento no encontrado");
            return null;
        }
    }

    private boolean getForm(Carrera carrera, String text, String campo) {
        return switch (campo.toLowerCase()) {
            case "nombre" ->
                carrera.getNombre().equalsIgnoreCase(text);
            case "area_estudio" ->
                carrera.getArea_estudio().equalsIgnoreCase(text);
            case "id" ->
                Integer.toString(carrera.getId()).equalsIgnoreCase(text);
            case "codigo" ->
                carrera.getCodigo().equalsIgnoreCase(text);
            default ->
                throw new IllegalArgumentException("Campo de comparación no válido");
        };
    }

    public ListaEnlazada<Carrera> buscarPorCodigo(String codigo) {
        ListaEnlazada<Carrera> carreras = new ListaEnlazada<>();
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT ID, NOMBRE, CODIGO, AREA_ESTUDIO, MODALIDAD  FROM CARRERA WHERE CODIGO = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, codigo);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Carrera carrera = new Carrera();
                        carrera.setId(resultSet.getInt("ID"));
                        carrera.setCodigo(resultSet.getString("CODIGO"));
                        carrera.setArea_estudio(resultSet.getString("AREA_ESTUDIO"));
                        carrera.setModalidad(resultSet.getString("MODALIDAD"));
                        carrera.setNombre(resultSet.getString("NOMBRE"));
                        carreras.add(carrera);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return carreras;
    }

    public ListaEnlazada<Carrera> buscarPorNombre(String nombre) {
        ListaEnlazada<Carrera> carreras = new ListaEnlazada<>();
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {

            String sql = "SELECT ID, NOMBRE, CODIGO, AREA_ESTUDIO, MODALIDAD  FROM CARRERA WHERE NOMBRE = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nombre);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Carrera carrera = new Carrera();
                        carrera.setId(resultSet.getInt("ID"));
                        carrera.setCodigo(resultSet.getString("CODIGO"));
                        carrera.setArea_estudio(resultSet.getString("AREA_ESTUDIO"));
                        carrera.setModalidad(resultSet.getString("MODALIDAD"));
                        carrera.setNombre(resultSet.getString("NOMBRE"));
                        carreras.add(carrera);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return carreras;
    }

}
