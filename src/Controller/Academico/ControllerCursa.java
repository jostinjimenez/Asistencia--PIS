package Controller.Academico;

import Controller.DataBase.DataAccessObject;
import model.Cursa;
import Controller.tda_listas.ListaEnlazada;
import Controller.tda_listas.exceptions.VacioExceptions;

import java.sql.*;

/**
 * Controlador para la entidad Cursa.
 * Extiende de DataAccessObject para manejar operaciones de base de datos.
 */
public class ControllerCursa extends DataAccessObject<Cursa> {

    private Cursa cursa = new Cursa();
    private ListaEnlazada<Cursa> lista = new ListaEnlazada<>();
    private Integer index = -1;

    /**
     * Constructor del controlador.
     * Inicializa la cursa y la lista de cursas.
     */
    public ControllerCursa() {
        super(Cursa.class);
    }

    /**
     * Obtiene la cursa actual.
     * Si la cursa es nula, se crea una nueva cursa.
     *
     * @return Cursa actual.
     */
    public Cursa getCursa() {
        if (cursa == null) {
            cursa = new Cursa();
        }
        return cursa;
    }

    /**
     * Establece la cursa actual.
     *
     * @param cursa Cursa actual.
     */
    public void setCursa(Cursa cursa) {
        this.cursa = cursa;
    }

    /**
     * Guarda la cursa actual en la base de datos.
     *
     * @return ID de la cursa guardada.
     * @throws Exception Si ocurre un error al guardar la cursa.
     */
    public Integer save() throws Exception {
        return super.save(this.cursa, "SQC_CURSA");
    }

    /**
     * Actualiza la cursa actual en la base de datos.
     *
     * @return Verdadero si la actualización fue exitosa, falso en caso contrario.
     */
    public Boolean update() {
        try {
            update(this.cursa);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene la lista de cursas.
     * Si la lista está vacía, se obtienen todas las cursas de la base de datos.
     *
     * @return Lista de cursas.
     */
    public ListaEnlazada<Cursa> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;
    }

    /**
     * Establece la lista de cursas.
     *
     * @param lista Lista de cursas.
     */
    public void setLista(ListaEnlazada<Cursa> lista) {
        this.lista = lista;
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
     * Ordena la lista de cursas utilizando el algoritmo de ordenamiento rápido (quicksort).
     *
     * @param lista Lista de cursas a ordenar.
     * @param type  Tipo de ordenamiento.
     * @param field Campo por el cual se ordenará.
     * @return Lista de cursas ordenada.
     * @throws VacioExceptions Si la lista está vacía.
     */
    public ListaEnlazada<Cursa> quicksort(ListaEnlazada<Cursa> lista, Integer type, String field) throws VacioExceptions {
        Cursa[] m = lista.toArray();
        quicksort(m, 0, m.length - 1, type, field);
        lista.toList(m);
        return lista;
    }

    private void quicksort(Cursa[] m, int low, int high, Integer type, String field) {
        if (low < high) {
            int pivotIndex = partition(m, low, high, type, field);
            quicksort(m, low, pivotIndex - 1, type, field);
            quicksort(m, pivotIndex + 1, high, type, field);
        }
    }

    private int partition(Cursa[] array, int low, int high, Integer type, String field) {
        Cursa pivote = array[high];
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

    private void swap(Cursa[] array, int i, int j) {
        Cursa temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public Cursa busquedaBinaria2(ListaEnlazada<Cursa> lista, String text, String campo, Integer type) throws VacioExceptions {
        ListaEnlazada<Cursa> listaOrdenada = ordenarLista(lista, campo);
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);

        if (index != -1) {
            return listaOrdenada.get(index);
        } else {
            System.out.println("Elemento no encontrado");
            return null;
        }
    }

    public ListaEnlazada<Cursa> busquedaBinaria(ListaEnlazada<Cursa> lista, String text, String campo, String tipo, Integer type) throws VacioExceptions {
        ListaEnlazada<Cursa> listaOrdenada = ordenarLista(lista, campo);

        ListaEnlazada<Cursa> marc = new ListaEnlazada<>();
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

    private int busquedaBinaria1(ListaEnlazada<Cursa> lista, String text, String campo) throws VacioExceptions {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Cursa mid = lista.get(indice);
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

    private boolean getForm(Cursa cursa, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "paralelo":
                return cursa.getParalelo().equalsIgnoreCase(text);
            case "id_matricula":
                return Integer.toString(cursa.getMatricula_id()).equalsIgnoreCase(text);
            case "id_docente":
                return Integer.toString(cursa.getDocente_id()).equalsIgnoreCase(text);
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }

    }

    private ListaEnlazada<Cursa> ordenarLista(ListaEnlazada<Cursa> lista, String campo) throws VacioExceptions {
        ListaEnlazada<Cursa> listaOrdenada = quicksort(lista, 0, campo);
        return listaOrdenada;
    }

    public ListaEnlazada<Cursa> buscarCursaPorDocente(Integer id) {
        ListaEnlazada<Cursa> lista = new ListaEnlazada<>();
        Cursa cursa = null;
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = """
                    SELECT
                        C.ID, C.PARALELO, C.MATRICULA_ID, C.DOCENTE_ID, C.ASIGNATURA_ID
                    FROM
                        CURSA C JOIN DOCENTE D ON C.DOCENTE_ID = D.ID
                    WHERE
                        D.ID = ?""";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
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
