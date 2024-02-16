/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelLuis.controller;

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
 *
 * @author Usuario
 */
public class ControllerAsistencia extends DataAccessObject<Asistencia> {

    private Asistencia asistencia = new Asistencia();
    private ListaEnlazada<Asistencia> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public ControllerAsistencia() {
        super(Asistencia.class);
    }

    public Asistencia getAsistencia() {
        if (asistencia == null) {
            asistencia = new Asistencia();
        }
        return asistencia;
    }

    public void setAsistencia(Asistencia asitencia) {
        this.asistencia = asitencia;
    }

    public ListaEnlazada<Asistencia> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;

    }

    public void setLista(ListaEnlazada<Asistencia> lista) {
        this.lista = lista;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean save() throws Exception {
        return super.saveB(this.asistencia);
    }
    
     public Integer saved() throws Exception {
        return super.save(this.asistencia);
    }

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
