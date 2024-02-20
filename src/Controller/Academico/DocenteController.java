package Controller.Academico;

import DataBase.DataAccessObject;
import model.Docente;
import tda_listas.ListaEnlazada;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;

import static Controller.Util.Utilidades.getField;

public class DocenteController extends DataAccessObject<Docente> {

    // Atributos
    private ListaEnlazada<Docente> docentes;
    private Docente docente = new Docente();
    private Integer index = -1;

    // Constructor
    public DocenteController() {
        super(Docente.class);
        this.docentes = new ListaEnlazada<>();
    }

    // Getters y Setters
    public ListaEnlazada<Docente> getDocentes() {
        if (docentes.isEmpty()) {
            docentes = this.list_All();
        }
        return docentes;
    }

    public void setDocentes(ListaEnlazada<Docente> docentes) {
        this.docentes = docentes;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    // Metodos
    public Boolean save() throws Exception {
        return super.saveB(this.docente);
    }

    public Boolean update() throws IOException {
        try {
            update(this.docente);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public ListaEnlazada<Docente> ordenarQS(ListaEnlazada<Docente> lista, Integer type, String field) throws Exception {
        Docente[] personas = lista.toArray();
        Field faux = getField(Docente.class, field);
        if (faux != null) {
            quickSort(personas, 0, personas.length - 1, type, field);
        } else {
            throw new Exception("El atributo no existe");
        }
        return lista.toList(personas);
    }

    private void quickSort(Docente[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        if (primero < ultimo) {
            int pi = partition(p, primero, ultimo, type, field);

            quickSort(p, primero, pi - 1, type, field);
            quickSort(p, pi + 1, ultimo, type, field);
        }
    }

    private int partition(Docente[] p, int primero, int ultimo, Integer type, String field) throws Exception {
        Docente pivot = p[ultimo];
        int i = (primero - 1);

        for (int j = primero; j < ultimo; j++) {
            if (p[j].compareTo(pivot, field, type)) {
                i++;
                Docente temp = p[i];
                p[i] = p[j];
                p[j] = temp;
            }
        }

        Docente aux = p[i + 1];
        p[i + 1] = p[ultimo];
        p[ultimo] = aux;

        return i + 1;
    }

    public ListaEnlazada<Docente> buscarPorNombre(String dniONombre) {
        ListaEnlazada<Docente> docentess = new ListaEnlazada<>();
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT * FROM PERSONA JOIN DOCENTE ON PERSONA.ID = DOCENTE.ID WHERE PERSONA.NOMBRE = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, dniONombre);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Docente Docente = new Docente();
                        Docente.setId(resultSet.getInt("ID"));
                        Docente.setCodigo_empleado(resultSet.getString("CODIGO_EMPLEADO"));
                        Docente.setGrado_academico(resultSet.getString("GRADO_ACADEMICO"));
                        Docente.setExperiencia(resultSet.getInt("EXPERIENCIA"));
                        docentess.add(Docente);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return docentess;
    }

    public ListaEnlazada<Docente> buscarPorApellido(String dniONombre) {
        ListaEnlazada<Docente> docentess = new ListaEnlazada<>();
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT * FROM PERSONA JOIN DOCENTE ON PERSONA.ID = DOCENTE.ID WHERE PERSONA.APELLIDO = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, dniONombre);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Docente docentee = new Docente();
                        docentee.setId(resultSet.getInt("ID"));
                        docentee.setCodigo_empleado(resultSet.getString("CODIGO_EMPLEADO"));
                        docentee.setGrado_academico(resultSet.getString("GRADO_ACADEMICO"));
                        docentee.setExperiencia(resultSet.getInt("EXPERIENCIA"));
                        docentess.add(docentee);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return docentess;
    }

    public Docente buscarPorDni(String dniONombre) {
        Docente docentee = null;
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT * FROM PERSONA JOIN DOCENTE ON PERSONA.ID = DOCENTE.ID WHERE PERSONA.DNI = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, dniONombre);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        docentee = new Docente();
                        docentee.setId(resultSet.getInt("ID"));
                        docentee.setCodigo_empleado(resultSet.getString("CODIGO_EMPLEADO"));
                        docentee.setGrado_academico(resultSet.getString("GRADO_ACADEMICO"));
                        docentee.setExperiencia(resultSet.getInt("EXPERIENCIA"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return docentee;
    }
}
