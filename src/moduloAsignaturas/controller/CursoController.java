package controllerAndres;

import DAO.DataAccessObject;
import tda_listas.ListaEnlazada;
import tda_listas.Nodo;
import tda_listas.exceptions.VacioExceptions;
import java.util.Comparator;
import java.util.Iterator;
import modelAndres.Curso;

public class CursoController extends DataAccessObject<Curso> {

    private Curso curso = new Curso();
    private ListaEnlazada<Curso> lista;
    private static Integer lastUsedId = 0;

    public CursoController() {
        super(Curso.class);
        lastUsedId = generarID();
    }

    public Curso getCurso() {
        if (curso == null) {
            curso = new Curso();
        }
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public boolean validar() {
        // Verificar que el Curso no es null
        if (curso == null) {
            return false;
        }

        // Verificar que los campos del Curso no son null o vacíos
        return curso.isValid();
    }

    public Boolean saved() {
        if (validar()) {
            try {
                // Obtener los datos del formulario
                Integer nroEstudiante = curso.getNroEstudiante();
                String codCurso = curso.getCodCurso();
                String nroAula = curso.getNroAula();

                // Incrementar el lastUsedId antes de asignarlo al nuevo ID
                Integer id = ++lastUsedId;

                // Crear una nueva instancia de Curso con los valores correctos
                Curso cursoGuardar = new Curso();
                cursoGuardar.setId(id);
                cursoGuardar.setNroEstudiante(nroEstudiante);
                cursoGuardar.setCodCurso(codCurso);
                cursoGuardar.setNroAula(nroAula);

                // Guardar el Curso
                boolean isSaved = save(cursoGuardar);
                if (isSaved) {
                    lista = getLista(); // Actualizar la lista después de guardar
                }

                return isSaved;
            } catch (Exception e) {
                // Manejar la excepción aquí
                System.out.println("Error al guardar el Curso: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Error: los datos del Curso no son válidos");
            return false;
        }
    }

    public ListaEnlazada<Curso> getLista() {
        lista = list_All(); // Actualizar la lista desde la base de datos
        return lista;
    }

    public Boolean update(Integer i) {
        return update(curso, i);
    }

    public void setLista(ListaEnlazada<Curso> lista) {
        this.lista = lista;
    }

    private Integer getLastUsedIdFromDatabase() throws VacioExceptions {
        // Obtener el último ID generado por el DataAccessObject
        return generarID();
    }

    public int busquedaLineal(Curso elemento, Comparator<Curso> comparador) {
        Nodo<Curso> current = lista.getHead();
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

    public int buscar(String criterioBusqueda, Comparator<Curso> comparador, String criterio) {
        Nodo<Curso> current = (Nodo<Curso>) lista.getHead();

        int index = 0;

        while (current != null) {
            Curso curso = current.getData();

            if ("nroEstudiante".equalsIgnoreCase(criterio) && curso.getNroEstudiante().equals(Integer.parseInt(criterioBusqueda))) {
                return index;
            } else if ("codCurso".equalsIgnoreCase(criterio) && curso.getCodCurso().equalsIgnoreCase(criterioBusqueda)) {
                return index;
            } else if ("nroAula".equalsIgnoreCase(criterio) && curso.getNroAula().equalsIgnoreCase(criterioBusqueda)) {
                return index;
            }

            current = current.getNext();
            index++;
        }

        System.out.println("No se encontró ningún curso con el criterio proporcionado.");
        return -1;
    }

    public Integer getIndex() {
        Iterator<Curso> iterator = lista.iterator();
        int index = 0;

        while (iterator.hasNext()) {
            if (iterator.next() == curso) {
                return index;
            }
            index++;
        }

        return -1;
    }

    public void quicksort(ListaEnlazada<Curso> lista, Comparator<Curso> comparador, boolean ascendente) throws VacioExceptions {
        quicksortRecursivo(lista, 0, lista.getSize() - 1, comparador, ascendente);
    }

    private void quicksortRecursivo(ListaEnlazada<Curso> lista, int low, int high, Comparator<Curso> comparador, boolean ascendente) throws VacioExceptions {
        if (low < high) {
            int pivotIndex = particion(lista, low, high, comparador, ascendente);

            // Recursivamente ordenar los elementos antes y después del pivote
            quicksortRecursivo(lista, low, pivotIndex - 1, comparador, ascendente);
            quicksortRecursivo(lista, pivotIndex + 1, high, comparador, ascendente);
        }
    }

    private int particion(ListaEnlazada<Curso> lista, int low, int high, Comparator<Curso> comparador, boolean ascendente) throws VacioExceptions {
        Curso pivot = lista.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compareCursos(lista.get(j), pivot, comparador, ascendente) <= 0) {
                i++;
                swap(lista, i, j);
            }
        }

        swap(lista, i + 1, high);
        return i + 1;
    }

    private void swap(ListaEnlazada<Curso> lista, int i, int j) throws VacioExceptions {
        Curso temp = lista.get(i);
        lista.update(i, lista.get(j));
        lista.update(j, temp);
    }

    private int compareCursos(Curso c1, Curso c2, Comparator<Curso> comparador, boolean ascendente) {
        int resultado = comparador.compare(c1, c2);
        return ascendente ? resultado : -resultado;
    }
}
