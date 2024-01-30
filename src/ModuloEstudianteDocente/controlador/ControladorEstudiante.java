/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloEstudianteDocente.controlador;

import java.io.IOException;
import DAO.DataAccessObject;
import java.awt.List;
import java.io.FileOutputStream;
import model.Estudiante;
import model.Persona;
import modulo_1.inicio_sesion.controller.PersonaController;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;


/**
 *
 * @author LENOVO
 */
public class ControladorEstudiante extends DataAccessObject<Estudiante>{
    private Estudiante estudiante = new Estudiante();
    ListaEnlazada<Estudiante> estudiantes = list_All();
    Integer index = -1;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
    
    
    
    
    
    public ControladorEstudiante() {
        super(Estudiante.class);
    }

    public Estudiante getEstudiante() {
         if (estudiante == null) {
            estudiante = new Estudiante();
        }
        return estudiante;
    }

    public ListaEnlazada<Estudiante> getEstudiantes() {
        ListaEnlazada<Estudiante> personasActivas = new ListaEnlazada<>();
        for (Estudiante persona : estudiantes) {
            if (persona.isActivo()) {
                personasActivas.add(persona);
            }
        }
        return personasActivas;
    }

    public void setEstudiantes(ListaEnlazada<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Boolean save() {
        Integer id = generarID();

        estudiante.setId(id);
        Boolean result = save(estudiante);

        PersonaController pc = new PersonaController();
        pc.getPersona().setNombre(estudiante.getNombre());
        pc.getPersona().setApellido(estudiante.getApellido());
        pc.getPersona().setDni(estudiante.getDni());
        pc.getPersona().setCorreo_personal(estudiante.getCorreo_personal());
        pc.getPersona().setFecha_nacimiento(estudiante.getFecha_nacimiento());
        pc.getPersona().setTelefono(estudiante.getTelefono());
        pc.getPersona().setIdRol(2);
        pc.getPersona().setId(id);
        pc.save();

        return result;
    }

    public void update(Integer pos) throws IOException{
        this.update(estudiante, pos);
    }


    
//
//    
//    public void setLista(ListaEnlazada<Estudiante> lista) {
//        this.lista = lista;
//    }
//
//    
//    public ListaEnlazada<Estudiante> quicksort(ListaEnlazada<Estudiante> lista, Integer type, String field) throws VacioExceptions {
//
//        Estudiante[] m = lista.toArray();
//        quicksort(m, 0, m.length - 1, type, field);
//        lista.toList(m);
//        return lista;
//    }
//
//    private void quicksort(Estudiante[] m, int low, int high, Integer type, String field) {
//        if (low < high) {
//            int pivotIndex = partition(m, low, high, type, field);
//            quicksort(m, low, pivotIndex - 1, type, field);
//            quicksort(m, pivotIndex + 1, high, type, field);
//        }
//    }
//    
//    private int partition(Estudiante[] array, int low, int high, Integer type, String field) {
//        Estudiante pivote = array[high];
//        int i = low - 1;
//
//        for (int j = low; j < high; j++) {
//            if (array[j].comparar(pivote, field, type)) {
//                i++;
//                swap(array, i, j);
//            }
//        }
//
//        swap(array, i + 1, high);
//        return i + 1;
//    }
//
//    private void swap(Estudiante[] array, int i, int j) {
//        Estudiante temp = array[i];
//        array[i] = array[j];
//        array[j] = temp;
//    }
//    
//    public ListaEnlazada<Estudiante> busquedaBinaria(ListaEnlazada<Estudiante> lista, String text, String campo, Integer type) throws VacioExceptions {
//        ListaEnlazada<Estudiante> listaOrdenada = ordenarLista(lista, campo);
//
//        ListaEnlazada<Estudiante> marc = new ListaEnlazada<>();
//        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);
//        if (index != -1) {
//            while (index < listaOrdenada.getSize() && getForm(listaOrdenada.get(index), text, campo)) {
//                marc.add(listaOrdenada.get(index));
//                index++;
//            }
//
//        } else {
//            System.out.println("Elemento no encontrado");
//        }
//        return marc;
//    }
//
//    private int busquedaBinaria1(ListaEnlazada<Estudiante> lista, String text, String campo) throws VacioExceptions {
//        int infe = 0;
//        int sup = lista.getSize() - 1;
//
//        while (infe <= sup) {
//            int indice = (infe + sup) / 2;
//            Estudiante mid = lista.get(indice);
//            int resultado = mid.comparar(mid, text, campo);
//            if (resultado == 0) {
//                int izquierda = indice - 1;
//                while (izquierda >= 0 && getForm(lista.get(izquierda), text, campo)) {
//                    indice = izquierda;
//                    izquierda--;
//                }
//                return indice;
//            } else if (resultado > 0) {
//                sup = indice - 1;
//            } else {
//                infe = indice + 1;
//            }
//            }
//
//        return -1;
//    }
//
//    private boolean getForm(Estudiante matricula, String text, String campo) {
//        switch (campo.toLowerCase()) {
//            case "id":
//                return Integer.toString(matricula.getId()).equalsIgnoreCase(text);
//            default:
//                throw new IllegalArgumentException("Campo de comparación no válido");
//        }
//    }
//    
//    private ListaEnlazada<Estudiante> ordenarLista(ListaEnlazada<Estudiante> lista, String campo) throws VacioExceptions {
//        ListaEnlazada<Estudiante> listaOrdenada = quicksort(lista, 0, campo);
//        System.out.println(listaOrdenada.print());  
//        return listaOrdenada;
//    }
//
//    public Estudiante busquedaBinaria2(ListaEnlazada<Estudiante> lista, String text, String campo, Integer type) throws VacioExceptions {
//        ListaEnlazada<Estudiante> listaOrdenada = ordenarLista(lista, campo);
//        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);
//
//        if (index != -1) {
//            return listaOrdenada.get(index);
//        } else {
//            System.out.println("Elemento no encontrado");
//            return null;
//        }
//    }
//    
//    public static void main(String[] args) throws VacioExceptions {
////        ListaEnlazada<Integer> ids = new ListaEnlazada();
////        ids.add(3);
////        Estudiante estudiante = new Estudiante(3,"Daniel", "Dorn", "luis33","2023-2030","9827728282","7267277272", "Loja", "Mestiza", true, ids);
//
//        ControladorEstudiante c = new ControladorEstudiante();
//        c.busquedaBinaria2(c.list_All(), "2", "id", 0);
////        c.save(estudiante);
//        //  System.out.println(c.busquedaBinaria(c.list_All(), "", "ciclo", "quicksort", 0));
//
// 
//}

                   





    
//    public Boolean delete(Integer idPersona){
//        for (int i = 0; i < estudiantes.getSize(); i++) {
//            Estudiante estudiante = null;
//            try{
//                estudiante = estudiantes.get(i);
//            }catch (VacioExceptions e){
//                throw new RuntimeException(e);
//            }
//            if (estudiante.getId().equals(idPersona)){
//                try{
//                    estudiante.setActivo(false);
//                    this.xStream.toXML(estudiantes, new FileOutputStream(URL));
//                    return true;
//                }catch (Exception e){
//                    System.out.println(e.getMessage());
//                    return false;
//                }
//            }
//        }
//        return false;
//    }
    
    public Boolean delete(Integer pos) throws IOException, VacioExceptions {
        ListaEnlazada<Estudiante> estudiantes = list_All();

        if (pos >= 0 && pos < estudiantes.getSize()) {
            estudiantes.delete(pos);
            save(estudiantes); 
            return true;
        } else {
            return false; 
        }
    }
    
    
//    public Boolean delete(Integer index) {
//        ListaEnlazada<T> list = list_All();
//
//        if (index >= 0 && index < list.getSize()) {
//            try {
//                list.remove(index);
//                this.xStream.alias(list.getClass().getName(), ListaEnlazada.class);
//                this.xStream.toXML(list, new FileOutputStream(URL));
//                return true;
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }

    // Resto de tu código...
//}
    
//    public Boolean delete(Object data) {
//        ListaEnlazada<Estudiante> list = list_All();
//        list.delete((Estudiante)data);    //(Estudiante) data);
//        try {
//            this.xStream.toXML(list, new FileOutputStream(URL));
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }


    public static void main(String[] args) {
        ControladorEstudiante ce = new ControladorEstudiante();
        ce.getEstudiante().setNombre("Ana");
        ce.getEstudiante().setApellido("Flores");
        ce.save();
    }

}
