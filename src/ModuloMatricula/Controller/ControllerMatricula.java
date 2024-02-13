package ModuloMatricula.Controller;

import DataBase.DataAccessObject;
import model.Matricula;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import java.sql.*;

/**
 * @author Usuario
 */
public class ControllerMatricula extends DataAccessObject<Matricula> {

    private Matricula matricula = new Matricula();
    private ListaEnlazada<Matricula> lista = new ListaEnlazada<>();
    private Integer index = -1;

    public ControllerMatricula() {
        super(Matricula.class);
    }

    public Matricula getMatricula() {
        if (matricula == null) {
            matricula = new Matricula();
        }
        return matricula;
    }

    public void setAsistencia(Matricula matricula) {
        this.matricula = matricula;
    }

    public ListaEnlazada<Matricula> getLista() {
        if (lista.isEmpty()) {
            lista = list_All();
        }
        return lista;

    }

    public Integer save() throws Exception {
        return super.save(this.matricula);
    }

    public Boolean update() {
        try {
            update(this.matricula);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Matricula> lista) {
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

    public ListaEnlazada<Matricula> quicksort(ListaEnlazada<Matricula> lista, Integer type, String field) throws VacioExceptions {

        Matricula[] m = lista.toArray();
        quicksort(m, 0, m.length - 1, type, field);
        lista.toList(m);
        return lista;
    }

    private void quicksort(Matricula[] m, int low, int high, Integer type, String field) {
        if (low < high) {
            int pivotIndex = partition(m, low, high, type, field);
            quicksort(m, low, pivotIndex - 1, type, field);
            quicksort(m, pivotIndex + 1, high, type, field);
        }
    }

    private int partition(Matricula[] array, int low, int high, Integer type, String field) {
        Matricula pivote = array[high];
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

    private void swap(Matricula[] array, int i, int j) {
        Matricula temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public Matricula busquedaBinaria2(ListaEnlazada<Matricula> lista, String text, String campo) throws VacioExceptions {
        ListaEnlazada<Matricula> listaOrdenada = ordenarLista(lista, campo);
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);
        if (index != -1) {
            return listaOrdenada.get(index);
        } else {
            System.out.println("Elemento no encontrado");
            return null;
        }
    }

    public ListaEnlazada<Matricula> busquedaBinaria(ListaEnlazada<Matricula> lista, String text, String campo, String tipo, Integer type) throws VacioExceptions {
        ListaEnlazada<Matricula> listaOrdenada = ordenarLista(lista, campo);

        ListaEnlazada<Matricula> marc = new ListaEnlazada<>();
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

    private int busquedaBinaria1(ListaEnlazada<Matricula> lista, String text, String campo) throws VacioExceptions {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Matricula mid = lista.get(indice);
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

    private boolean getForm(Matricula matricula, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "ciclo":
                return Integer.toString(matricula.getCiclo()).equalsIgnoreCase(text);
            case "id":
                return Integer.toString(matricula.getId()).equalsIgnoreCase(text);
            case "id_estudiante":
                return Integer.toString(matricula.getEstudiante_id()).equalsIgnoreCase(text);
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

    private ListaEnlazada<Matricula> ordenarLista(ListaEnlazada<Matricula> lista, String campo) throws VacioExceptions {
        ListaEnlazada<Matricula> listaOrdenada = new ListaEnlazada<>();
        listaOrdenada = quicksort(lista, 0, campo);
        return listaOrdenada;
    }

    public ListaEnlazada<Matricula> buscarPorEstudiante(String dniONombre) {
        ListaEnlazada<Matricula> matriculas = new ListaEnlazada<>();
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT * FROM MATRICULA JOIN ESTUDIANTE ON MATRICULA.ESTUDIANTE_ID = ESTUDIANTE.ID JOIN AXLMD.PERSONA P on P.ID = ESTUDIANTE.ID WHERE P.NOMBRE = ? OR P.DNI = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, dniONombre);
                preparedStatement.setString(2, dniONombre);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Matricula matricula = new Matricula();
                        matricula.setId(resultSet.getInt("ID"));
                        matricula.setCiclo(resultSet.getInt("CICLO"));
                        matricula.setEstudiante_id(resultSet.getInt("ESTUDIANTE_ID"));
                        matricula.setPeriodoacademico_id(resultSet.getInt("PERIODOACADEMICO_ID"));
                        matricula.setFechamatricula(resultSet.getDate("FECHAMATRICULA"));
                        matricula.setEstado_matricula(resultSet.getString("ESTADO_MATRICULA"));
                        matricula.setCarrera_id(resultSet.getInt("CARRERA_ID"));
                        matriculas.add(matricula);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return matriculas;
    }

}
