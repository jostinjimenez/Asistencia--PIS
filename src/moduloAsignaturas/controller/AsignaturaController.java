package moduloAsignaturas.controller;

import DataBase.DataAccessObject;
import model.Matricula;
import tda_listas.ListaEnlazada;
import tda_listas.Nodo;
import tda_listas.exceptions.VacioExceptions;

import java.sql.*;
import java.util.Comparator;
import java.util.Iterator;

import model.Asignatura;

public class AsignaturaController extends DataAccessObject<Asignatura> {

    private Asignatura asignatura = new Asignatura();
    private ListaEnlazada<Asignatura> asignaturas;

    public AsignaturaController() {
        super(Asignatura.class);
        asignaturas = new ListaEnlazada<>();
    }

    public ListaEnlazada<Asignatura> getAsignaturas() {
        if (asignaturas.isEmpty()) {
            asignaturas = this.list_All();
        }
        return asignaturas;
    }

    public Asignatura getAsignatura() {
        if (asignatura == null) {
            asignatura = new Asignatura();
        }
        return asignatura;
    }

    public void setAsignaturas(ListaEnlazada<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Integer save() throws Exception {
        return super.save(this.asignatura);
    }

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

    public int buscar(String criterioBusqueda, Comparator<Asignatura> comparador, String criterio) {
        Nodo<Asignatura> current = (Nodo<Asignatura>) asignaturas.getHead();

        int index = 0;

        while (current != null) {
            Asignatura asignatura = current.getData();

            if ("nombre".equalsIgnoreCase(criterio) && asignatura.getNombre().equals(criterioBusqueda)) {
                return index;
            } else if ("codigo".equalsIgnoreCase(criterio) && asignatura.getCodigo_materia().equals(criterioBusqueda)) {
                return index;
            }

            current = current.getNext();
            index++;
        }

        System.out.println("No se encontró ninguna asignatura con el criterio proporcionado.");
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

    private boolean getForm(Asignatura matricula, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "id":
                return Integer.toString(matricula.getId()).equalsIgnoreCase(text);
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

    private ListaEnlazada<Asignatura> ordenarLista(ListaEnlazada<Asignatura> lista, String campo) throws VacioExceptions {
        ListaEnlazada<Asignatura> listaOrdenada = quicksort(lista, 0, campo);
        return listaOrdenada;
    }

    public Asignatura busquedaBinaria2(ListaEnlazada<Asignatura> lista, String text, String campo, Integer type) throws VacioExceptions {
        ListaEnlazada<Asignatura> listaOrdenada = ordenarLista(lista, campo);
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);

        if (index != -1) {
            return listaOrdenada.get(index);
        } else {
            System.out.println("Elemento no encontrado");
            return null;
        }
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
