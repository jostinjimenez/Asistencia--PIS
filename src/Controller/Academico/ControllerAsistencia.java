/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Academico;

import DataBase.DataAccessObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Asignatura;
import model.Asistencia;
import model.Cursa;
import model.Persona;
import tda_listas.ListaEnlazada;

/**
 * Controlador para la entidad Asistencia.
 * Extiende de DataAccessObject para manejar operaciones de base de datos.
 */
public class ControllerAsistencia extends DataAccessObject<Asistencia> {

    private Asistencia asistencia = new Asistencia();
    private ListaEnlazada<Asistencia> lista = new ListaEnlazada<>();
    private Integer index = -1;

    /**
     * Constructor del controlador.
     * Inicializa la asistencia y la lista de asistencias.
     */
    public ControllerAsistencia() {
        super(Asistencia.class);
    }

    /**
     * Obtiene la asistencia actual.
     * Si la asistencia es nula, se crea una nueva asistencia.
     * @return Asistencia actual.
     */
    public Asistencia getAsistencia() {
        if (asistencia == null) {
            asistencia = new Asistencia();
        }
        return asistencia;
    }

    /**
     * Establece la asistencia actual.
     * @param asitencia Asistencia actual.
     */
    public void setAsistencia(Asistencia asitencia) {
        this.asistencia = asitencia;
    }

    /**
     * Obtiene la lista de asistencias.
     * Si la lista está vacía, se obtienen todas las asistencias de la base de datos.
     * @return Lista de asistencias.
     */
    public ListaEnlazada<Asistencia> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;
    }

    /**
     * Establece la lista de asistencias.
     * @param lista Lista de asistencias.
     */
    public void setLista(ListaEnlazada<Asistencia> lista) {
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
     * Guarda la asistencia actual en la base de datos.
     * @return Verdadero si la asistencia fue guardada exitosamente, falso en caso contrario.
     * @throws Exception Si ocurre un error al guardar la asistencia.
     */
    public Boolean save() throws Exception {
        return super.saveB(this.asistencia);
    }

    /**
     * Guarda la asistencia actual en la base de datos y devuelve su ID.
     * @return ID de la asistencia guardada.
     * @throws Exception Si ocurre un error al guardar la asistencia.
     */
    public Integer saved() throws Exception {
        return super.save(this.asistencia, "SQC_ASISTENCIA");
    }

    /**
     * Actualiza la asistencia actual en la base de datos.
     * @return Verdadero si la actualización fue exitosa, falso en caso contrario.
     */
    public Boolean update() {
        try {
            update(this.asistencia);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ListaEnlazada buscarCiclos(String texto, String ciclo) {
        ListaEnlazada<Cursa> lista = new ListaEnlazada<>();
        Cursa cursa = null;
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT c.ID, c.PARALELO, c.ASIGNATURA_ID, c.MATRICULA_ID, c.DOCENTE_ID "
                    + "FROM CURSA c "
                    + "INNER JOIN MATRICULA m ON c.MATRICULA_ID = m.ID "
                    + "WHERE c.DOCENTE_ID = ? AND m.CICLO = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, texto);
                preparedStatement.setString(2, ciclo);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        cursa = new Cursa();
                        cursa.setId(resultSet.getInt("ID"));
                        cursa.setAsignatura_id(resultSet.getInt("ASIGNATURA_ID"));
                        cursa.setParalelo(resultSet.getString("PARALELO"));
                        cursa.setMatricula_id(resultSet.getInt("MATRICULA_ID"));
                        cursa.setDocente_id(resultSet.getInt("DOCENTE_ID"));
                        lista.add(cursa);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return lista;
    }

    public Asignatura buscarAsig(String texto) {
        Asignatura asi = null;
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT ID, NOMBRE, HORAS_TOTALES, CODIGO_MATERIA "
                    + "FROM ASIGNATURA "
                    + "WHERE ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, texto);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        asi = new Asignatura();
                        asi.setId(resultSet.getInt("ID"));
                        asi.setNombre(resultSet.getString("NOMBRE"));
                        asi.setHoras_Totales(resultSet.getInt("HORAS_TOTALES"));
                        asi.setCodigo_materia(resultSet.getString("CODIGO_MATERIA"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return asi;
    }

    public Persona buscarEstudiante(String texto) {
        Persona persona = null;
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT e.ID AS ESTUDIANTE_ID, p.ID AS ID_PERSONA,  p.NOMBRE, p.APELLIDO, p.DNI  "
                    + "FROM ESTUDIANTE e "
                    + "INNER JOIN MATRICULA m ON e.ID = m.ESTUDIANTE_ID "
                    + "INNER JOIN CURSA c ON m.ID = c.MATRICULA_ID "
                    + "INNER JOIN PERSONA p ON e.ID = p.ID "
                    + "WHERE c.ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, texto);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        persona = new Persona();
                        persona.setId(resultSet.getInt("ID_PERSONA"));
                        persona.setApellido(resultSet.getString("APELLIDO"));
                        persona.setNombre(resultSet.getString("NOMBRE"));
                        persona.setDni(resultSet.getString("DNI"));

                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return persona;
    }

    public ListaEnlazada buscarParalelos(String texto, String id) {
        ListaEnlazada<Cursa> lista = new ListaEnlazada<>();
        Cursa cursa = null;
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT c.ID, c.PARALELO, c.ASIGNATURA_ID, c.MATRICULA_ID, c.DOCENTE_ID "
                    + "FROM CURSA c "
                    + "WHERE c.PARALELO = ? AND  c.ASIGNATURA_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, texto);
                preparedStatement.setString(2, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        cursa = new Cursa();
                        cursa.setId(resultSet.getInt("ID"));
                        cursa.setAsignatura_id(resultSet.getInt("ASIGNATURA_ID"));
                        cursa.setParalelo(resultSet.getString("PARALELO"));
                        cursa.setMatricula_id(resultSet.getInt("MATRICULA_ID"));
                        cursa.setDocente_id(resultSet.getInt("DOCENTE_ID"));
                        lista.add(cursa);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return lista;
    }

}
