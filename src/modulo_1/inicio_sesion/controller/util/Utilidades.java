package modulo_1.inicio_sesion.controller.util;

import model.Persona;
import modulo_1.inicio_sesion.controller.PersonaController;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Utilidades {

    public static Field getField(Class clazz, String attribute) {
        Field[] fields = clazz.getFields();         // cuando hay herencia
        Field resp = null;
        for (Field field : fields) {
            if (attribute.equalsIgnoreCase(field.getName())) {
                resp = field;
            }
        }
        fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (attribute.equalsIgnoreCase(field.getName())) {
                resp = field;
            }
        }
        return resp;
    }

    public static String getFieldValue(Object obj, String field) throws Exception {
        Field declaredField = getField(Persona.class, field);
        declaredField.setAccessible(true);
        System.out.println(declaredField.get(obj).toString());
        return declaredField.get(obj).toString();
    }

    public static Object getData(Object clas, String attribute) {
        Class clazz = clas.getClass();
        Field field = getField(clazz, attribute);
        Object obj = null;

        if (field != null) {
            String aux = "get" + capitalize(attribute);
            Method method = null;
            for (Method m : clazz.getMethods()) {
                if (m.getName().equalsIgnoreCase(aux)) {
                    method = m;
                    break;
                }
            }
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.getName().equalsIgnoreCase(aux)) {
                    method = m;
                    break;
                }
            }
            if (method != null) {
                try {
                    obj = method.invoke(clas);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    public static String capitalize(String str) {
        char[] caracteres = str.toCharArray();
        String aux = String.valueOf(caracteres[0]).toUpperCase();
        caracteres[0] = aux.charAt(0);
        return new String(caracteres);
    }

    public static Persona getPersonaStatic(Integer idPersona) {
        PersonaController personaController = new PersonaController();
        ListaEnlazada<Persona> personas = personaController.getPersonas();
        try {
            personas = personaController.ordenarQS(personas, 0, "id");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int left = 0;
        int right = personas.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Persona midPersona = null;
            try {
                midPersona = personas.get(mid);
            } catch (VacioExceptions e) {
                e.printStackTrace();
            }

            if (midPersona.getId().equals(idPersona)) {
                return midPersona;
            }
            if (midPersona.getId() < idPersona) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}
