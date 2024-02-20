package Controller.Academico;

import DataBase.DataAccessObject;
import model.Docente;
import model.Matricula;
import tda_listas.ListaEnlazada;
import tda_listas.Nodo;
import tda_listas.exceptions.VacioExceptions;

import java.sql.*;
import java.util.Comparator;
import java.util.Iterator;

import model.Asignatura;

/**
 * Controlador para la entidad Asignatura.
 * Extiende de DataAccessObject para manejar operaciones de base de datos.
 */
public class AsignaturaController extends DataAccessObject<Asignatura> {

    private Asignatura asignatura = new Asignatura();
    private ListaEnlazada<Asignatura> asignaturas;

    /**
     * Constructor del controlador.
     * Inicializa la lista de asignaturas.
     */
    public AsignaturaController() {
        super(Asignatura.class);
        asignaturas = new ListaEnlazada<>();
    }

    /**
     * Obtiene la lista de asignaturas.
     * Si la lista está vacía, se obtienen todas las asignaturas de la base de datos.
     * @return Lista de asignaturas.
     */
    public ListaEnlazada<Asignatura> getAsignaturas() {
        if (asignaturas.isEmpty()) {
            asignaturas = this.list_All();
        }
        return asignaturas;
    }

    /**
     * Obtiene la asignatura actual.
     * Si la asignatura es nula, se crea una nueva asignatura.
     * @return Asignatura actual.
     */
    public Asignatura getAsignatura() {
        if (asignatura == null) {
            asignatura = new Asignatura();
        }
        return asignatura;
    }

    /**
     * Establece la lista de asignaturas.
     * @param asignaturas Lista de asignaturas.
     */
    public void setAsignaturas(ListaEnlazada<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    /**
     * Establece la asignatura actual.
     * @param asignatura Asignatura actual.
     */
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    /**
     * Guarda la asignatura actual en la base de datos.
     * @return ID de la asignatura guardada.
     * @throws Exception Si ocurre un error al guardar la asignatura.
     */
    public Integer save() throws Exception {
        return super.save(this.asignatura, "SQC_ASIGNATURA");
    }

    /**
     * Actualiza la asignatura actual en la base de datos.
     * @return Verdadero si la actualización fue exitosa, falso en caso contrario.
     */
    public Boolean update() {
        try {
            update(this.asignatura);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int busquedaLineal(Asignatura elemento, Comparator<Asignatura> comparador) {
        Nodo<Asignatura> current = asignaturas.getHead();
        int index = 0;

        while (current != null) {
            if (comparador.compare(current.getData(), elemento) == 0) {
                return index;
            }

            current = current.getNext();
            index++;
        }

        return -1;
    }

    public ListaEnlazada<Asignatura> quicksort(ListaEnlazada<Asignatura> lista, Integer type, String field) throws VacioExceptions {

        Asignatura[] m = lista.toArray();
        quicksort(m, 0, m.length - 1, type, field);
        lista.toList(m);
        return lista;
    }

    private void quicksort(Asignatura[] m, int low, int high, Integer type, String field) {
        if (low < high) {
            int pivotIndex = partition(m, low, high, type, field);
            quicksort(m, low, pivotIndex - 1, type, field);
            quicksort(m, pivotIndex + 1, high, type, field);
        }
    }

    private int partition(Asignatura[] array, int low, int high, Integer type, String field) {
        Asignatura pivote = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j].comparar(pivote, field, type)) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(Asignatura[] array, int i, int j) {
        Asignatura temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private int busquedaBinaria1(ListaEnlazada<Asignatura> lista, String text, String campo) throws VacioExceptions {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Asignatura mid = lista.get(indice);
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

    private boolean getForm(Asignatura asignatura, String text, String campo) {
        return switch (campo.toLowerCase()) {
            case "id" ->
                Integer.toString(asignatura.getId()).equalsIgnoreCase(text);
            case "nombre" ->
                asignatura.getNombre().toLowerCase().contains(text);
            case "codigo_materia" ->
                asignatura.getCodigo_materia().toLowerCase().contains(text);
            default ->
                throw new IllegalArgumentException("Campo de comparación no válido");
        };
    }

    private ListaEnlazada<Asignatura> ordenarLista(ListaEnlazada<Asignatura> lista, String campo) throws VacioExceptions {
        ListaEnlazada<Asignatura> listaOrdenada = quicksort(lista, 0, campo);
        return listaOrdenada;
    }

    public Asignatura busquedaBinaria2(ListaEnlazada<Asignatura> lista, String text, String campo) throws VacioExceptions {
        ListaEnlazada<Asignatura> listaOrdenada = ordenarLista(lista, campo);
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);

        if (index != -1) {
            return listaOrdenada.get(index);
        } else {
            System.out.println("Elemento no encontrado");
            return null;
        }
    }

    public ListaEnlazada<Asignatura> busquedaBinaria(ListaEnlazada<Asignatura> lista, String text, String campo) throws VacioExceptions {
        ListaEnlazada<Asignatura> listaOrdenada = ordenarLista(lista, campo);

        ListaEnlazada<Asignatura> marc = new ListaEnlazada<>();
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

    public ListaEnlazada<Asignatura> buscarAsignaturasPorMatricula(Integer id) {
        ListaEnlazada<Asignatura> asignaturass = new ListaEnlazada<>();
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT A.ID, A.NOMBRE, A.CODIGO_MATERIA, A.HORAS_TOTALES, A.MALLA_ID FROM MATRICULA M JOIN CARRERA C ON M.CARRERA_ID = C.ID JOIN MALLA MA ON C.ID = MA.CARRERA_ID JOIN ASIGNATURA A ON MA.ID = A.MALLA_ID WHERE M.ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);  // Aquí está la corrección
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Asignatura asignatura1 = new Asignatura();
                        asignatura1.setId(resultSet.getInt("ID"));
                        asignatura1.setNombre(resultSet.getString("NOMBRE"));
                        asignatura1.setCodigo_materia(resultSet.getString("CODIGO_MATERIA"));
                        asignatura1.setHoras_Totales(resultSet.getInt("HORAS_TOTALES"));
                        asignatura1.setMalla_id(resultSet.getInt("MALLA_ID"));
                        asignaturass.add(asignatura1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return asignaturass;
    }

    public ListaEnlazada<Asignatura> buscarAsignaturasPorCarrera(Integer id) {
        ListaEnlazada<Asignatura> asignaturass = new ListaEnlazada<>();
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT A.ID, A.NOMBRE, A.CODIGO_MATERIA, A.HORAS_TOTALES, A.MALLA_ID FROM CARRERA C JOIN MALLA MA ON C.ID = MA.CARRERA_ID JOIN ASIGNATURA A ON MA.ID = A.MALLA_ID WHERE C.ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);  // Aquí está la corrección
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Asignatura asignatura1 = new Asignatura();
                        asignatura1.setId(resultSet.getInt("ID"));
                        asignatura1.setNombre(resultSet.getString("NOMBRE"));
                        asignatura1.setCodigo_materia(resultSet.getString("CODIGO_MATERIA"));
                        asignatura1.setHoras_Totales(resultSet.getInt("HORAS_TOTALES"));
                        asignatura1.setMalla_id(resultSet.getInt("MALLA_ID"));
                        asignaturass.add(asignatura1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return asignaturass;
    }

    public Asignatura buscarAsignaturasPorCursa(Integer id) {
        Asignatura asignatura1 = null;
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {

            String sql = """
                    SELECT
                        A.ID, A.NOMBRE, A.CODIGO_MATERIA, A.HORAS_TOTALES, A.MALLA_ID
                    FROM
                        CURSA C JOIN ASIGNATURA A ON C.ASIGNATURA_ID = A.ID
                    WHERE
                        C.ID = ?""";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        asignatura1 = new Asignatura();
                        asignatura1.setId(resultSet.getInt("ID"));
                        asignatura1.setNombre(resultSet.getString("NOMBRE"));
                        asignatura1.setCodigo_materia(resultSet.getString("CODIGO_MATERIA"));
                        asignatura1.setHoras_Totales(resultSet.getInt("HORAS_TOTALES"));
                        asignatura1.setMalla_id(resultSet.getInt("MALLA_ID"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return asignatura1;
    }
}
