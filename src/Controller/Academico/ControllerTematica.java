package Controller.Academico;

import Controller.DataBase.DataAccessObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Tematica;
import Controller.tda_listas.ListaEnlazada;

/**
 * Controlador para la entidad Tematica.
 * Extiende de DataAccessObject para manejar operaciones de base de datos.
 */
public class ControllerTematica extends DataAccessObject<Tematica> {

    private Tematica tematica = new Tematica();
    private ListaEnlazada<Tematica> lista = new ListaEnlazada<>();
    private Integer index = -1;

    /**
     * Constructor del controlador.
     * Inicializa la tematica y la lista de tematicas.
     */
    public ControllerTematica() {
        super(Tematica.class);
    }

    /**
     * Obtiene la tematica actual.
     * Si la tematica es nula, se crea una nueva tematica.
     * @return Tematica actual.
     */
    public Tematica getTematica() {
        if (tematica == null) {
            tematica = new Tematica();
        }
        return tematica;
    }

    /**
     * Establece la tematica actual.
     * @param tematica Tematica actual.
     */
    public void setAsistencia(Tematica tematica) {
        this.tematica = tematica;
    }

    /**
     * Obtiene la lista de tematicas.
     * Si la lista está vacía, se obtienen todas las tematicas de la base de datos.
     * @return Lista de tematicas.
     */
    public ListaEnlazada<Tematica> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;
    }

    /**
     * Establece la lista de tematicas.
     * @param lista Lista de tematicas.
     */
    public void setLista(ListaEnlazada<Tematica> lista) {
        this.lista = lista;
    }

    /**
     * Obtiene el índice actual.
     * @return Índice actual.
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Establece el índice actual.
     * @param index Índice actual.
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * Guarda la tematica actual en la base de datos.
     * @return Verdadero si la tematica fue guardada exitosamente, falso en caso contrario.
     * @throws Exception Si ocurre un error al guardar la tematica.
     */
    public Boolean save() throws Exception {
        return super.saveB(this.tematica);
    }

    /**
     * Actualiza la tematica actual en la base de datos.
     * @return Verdadero si la actualización fue exitosa, falso en caso contrario.
     */
    public Boolean update() {
        try {
            update(this.tematica);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Busca una tematica en la base de datos por su nombre.
     * @param texto Nombre de la tematica a buscar.
     * @return La tematica encontrada, o null si no se encontró ninguna tematica con ese nombre.
     */
    public Tematica buscarTematica(String texto) {
        Tematica tematica = null;
        System.out.println("Aqui esta el error");
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT NOMBRE, FECHA, ID "
                    + "FROM TEMATICA "
                    + "WHERE NOMBRE = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, texto);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        tematica = new Tematica();
                        tematica.setId(resultSet.getInt("ID"));
                        tematica.setNombre(resultSet.getString("NOMBRE"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage(), e);
        }
        return tematica;
    }

}