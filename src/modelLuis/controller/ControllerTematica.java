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
import model.Tematica;
import tda_listas.ListaEnlazada;

/**
 *
 * @author Usuario
 */
public class ControllerTematica extends DataAccessObject<Tematica> {

    private Tematica tematica = new Tematica();
    private ListaEnlazada<Tematica> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public ControllerTematica() {
        super(Tematica.class);
    }

    public Tematica getTematica() {
        if (tematica == null) {
            tematica = new Tematica();
        }
        return tematica;
    }

    public void setAsistencia(Tematica tematica) {
        this.tematica = tematica;
    }

    public ListaEnlazada<Tematica> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;

    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Tematica> lista) {
        this.lista = lista;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    // Metodos
    public Boolean save() throws Exception {
        return super.saveB(this.tematica);
    }

    public Boolean update() {
        try {
            update(this.tematica);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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
