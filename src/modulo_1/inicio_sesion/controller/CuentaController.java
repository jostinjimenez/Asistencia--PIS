package modulo_1.inicio_sesion.controller;

import DataBase.DataAccessObject;

import model.*;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import javax.swing.*;

public class CuentaController extends DataAccessObject<Cuenta> {

    // Atributos
    private ListaEnlazada<Cuenta> cuentas;
    private Cuenta cuenta = new Cuenta();
    private Integer index = -1;

    // Constructor
    public CuentaController() {
        super(Cuenta.class);
        this.cuentas = new ListaEnlazada<>();
    }

    // Getters y Setters
    public ListaEnlazada<Cuenta> getCuentas() {
        if (cuentas.isEmpty()) {
            cuentas = this.list_All();
        }
        return cuentas;
    }

    public void setCuentas(ListaEnlazada<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public Cuenta getCuenta() {
        return cuenta != null ? cuenta : new Cuenta();
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    // Metodos
    public Integer save() throws Exception {
        return super.save(this.cuenta);
    }

    public Boolean update() {
        try {
            update(this.cuenta);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public Cuenta validarCuenta(String usuario, String clave) {
        for (Cuenta c : this.getCuentas()) {

            if (c.getCorreo_institucional().equalsIgnoreCase(usuario)) {
                if (c.getClave().equalsIgnoreCase(clave)) {
                    return c;
                } else {
                    JOptionPane.showMessageDialog(null, "Clave incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Usuario incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    public Integer identificarRolPersona(Persona p) {
        Integer idRol = null;
        for (Cuenta cuenta : getCuentas()) {
            if (cuenta.getPersona_id().equals(p.getId())) {
                idRol = getPersona(cuenta.getPersona_id()).getRol_id().intValue();
                break;
            }
        }
        return idRol;
    }

    // Buscar persona por id
    public Persona getPersona(Integer idPersona) {
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

    public Cuenta validarCuenta(String usuario, Persona persona) {
        PersonaController pc = new PersonaController();
        try {
            for (Cuenta c : this.getCuentas()) {
                Persona personaCuenta = pc.buscarID1(pc.getPersonas(), c.getPersona_id());
                if (c.getCorreo_institucional().equalsIgnoreCase(usuario)) {
                    if (personaCuenta.getDni().equalsIgnoreCase(persona.getDni())) {
                        return c;
                    } else {
                        JOptionPane.showMessageDialog(null, "La cedula no es la correcta", "Error", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }
                }
            }
        } catch (Exception e) {
        }
        JOptionPane.showMessageDialog(null, "Usuario incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    public String cifrar(String texto, int clave) {
        StringBuilder textoCifrado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (Character.isLetter(caracter)) {
                char inicio = Character.isUpperCase(caracter) ? 'A' : 'a';
                caracter = (char) (((int) caracter - inicio + clave) % 26 + inicio);
            }
            textoCifrado.append(caracter);
        }
        return textoCifrado.toString();
    }

    public String descifrar(String textoCifrado, int clave) {
        StringBuilder textoDescifrado = new StringBuilder();
        for (int i = 0; i < textoCifrado.length(); i++) {
            char caracter = textoCifrado.charAt(i);
            if (Character.isLetter(caracter)) {
                char inicio = Character.isUpperCase(caracter) ? 'Z' : 'z';
                caracter = (char) (((int) caracter - inicio - clave + 26) % 26 + inicio);
            }
            textoDescifrado.append(caracter);
        }
        return textoDescifrado.toString();
    }

}
