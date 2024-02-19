package modulo_1.inicio_sesion.controller;

import DataBase.DataAccessObject;

import model.*;
import tda_listas.ListaEnlazada;
import tda_listas.exceptions.VacioExceptions;

import javax.swing.*;
import java.sql.*;

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
        return super.save(this.cuenta, "SQC_CUENTA");
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
//    public Persona getPersona(Integer idPersona) {
//        PersonaController personaController = new PersonaController();
//        ListaEnlazada<Persona> personas = personaController.getPersonas();
//        try {
//            personas = personaController.ordenarQS(personas, 0, "id");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        int left = 0;
//        int right = personas.getSize() - 1;
//
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            Persona midPersona = null;
//            try {
//                midPersona = personas.get(mid);
//            } catch (VacioExceptions e) {
//                e.printStackTrace();
//            }
//
//            if (midPersona.getId().equals(idPersona)) {
//                return midPersona;
//            }
//            if (midPersona.getId() < idPersona) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//        return null;
//    }

    public Persona getPersona(Integer idPersona) {
        Persona persona = null;
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT * FROM PERSONA WHERE ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idPersona);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        persona = new Persona();
                        persona.setId(resultSet.getInt("ID"));
                        persona.setNombre(resultSet.getString("NOMBRE"));
                        persona.setApellido(resultSet.getString("APELLIDO"));
                        persona.setDni(resultSet.getString("DNI"));
                        persona.setCorreo_personal(resultSet.getString("CORREO_PERSONAL"));
                        persona.setTelefono(resultSet.getString("TELEFONO"));
                        persona.setFecha_nacimiento(resultSet.getDate("FECHA_NACIMIENTO"));
                        persona.setActivo(resultSet.getBoolean("ACTIVO"));
                        persona.setRol_id(resultSet.getInt("ROL_ID"));
                        persona.setFoto(resultSet.getString("FOTO"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return persona;
    }

    public Cuenta validarCuenta(String usuario, Persona persona) {
        PersonaController pc = new PersonaController();
        try {
            for (Cuenta c : this.getCuentas()) {
                Persona personaCuenta = pc.busquedaBinaria2(pc.getPersonas(), c.getPersona_id().toString(), "id");
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

    public Boolean getFirstKey(String clave, Integer id) {

        PersonaController pc = new PersonaController();
        try {
            for (Cuenta c : this.getCuentas()) {
                Persona personaCuenta = pc.busquedaBinaria2(pc.getPersonas(), c.getPersona_id().toString(), "id");
                String claveDescifrada = descifrar(clave, 10);
                if (personaCuenta.getDni().equalsIgnoreCase(claveDescifrada)) {
                    JOptionPane.showMessageDialog(null, "Debe modificar su clave", "Error", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {                   
                    return false;
                }
            }
        } catch (Exception e) {
        }
        JOptionPane.showMessageDialog(null, "Usuario incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    public ListaEnlazada<Cuenta> buscarCuentasPorNombre(String texto) {
        ListaEnlazada<Cuenta> cuentass = new ListaEnlazada<>();
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT C.ID, C.CORREO_INSTITUCIONAL, C.CLAVE, C.PERSONA_ID FROM CUENTA C JOIN PERSONA P ON C.PERSONA_ID = P.ID WHERE P.NOMBRE = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, texto);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Cuenta cuentaa = new Cuenta();
                        cuentaa.setId(resultSet.getInt("ID"));
                        cuentaa.setCorreo_institucional(resultSet.getString("CORREO_INSTITUCIONAL"));
                        cuentaa.setClave(resultSet.getString("CLAVE"));
                        cuentaa.setPersona_id(resultSet.getInt("PERSONA_ID"));
                        cuentass.add(cuentaa);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return cuentass;
    }

    public ListaEnlazada<Cuenta> buscarCuentasPorApellido(String texto) {
        ListaEnlazada<Cuenta> cuentass = new ListaEnlazada<>();
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT C.ID, C.CORREO_INSTITUCIONAL, C.CLAVE, C.PERSONA_ID FROM CUENTA C JOIN PERSONA P ON C.PERSONA_ID = P.ID WHERE P.APELLIDO = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, texto);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Cuenta cuentaa = new Cuenta();
                        cuentaa.setId(resultSet.getInt("ID"));
                        cuentaa.setCorreo_institucional(resultSet.getString("CORREO_INSTITUCIONAL"));
                        cuentaa.setClave(resultSet.getString("CLAVE"));
                        cuentaa.setPersona_id(resultSet.getInt("PERSONA_ID"));
                        cuentass.add(cuentaa);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return cuentass;
    }

    public Cuenta buscarCuentaPorDni(String texto) {
        Cuenta cuentaa = null;
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT C.ID, C.CORREO_INSTITUCIONAL, C.CLAVE, C.PERSONA_ID FROM CUENTA C JOIN PERSONA P ON C.PERSONA_ID = P.ID WHERE P.DNI = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, texto);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        cuentaa = new Cuenta();
                        cuentaa.setId(resultSet.getInt("ID"));
                        cuentaa.setCorreo_institucional(resultSet.getString("CORREO_INSTITUCIONAL"));
                        cuentaa.setClave(resultSet.getString("CLAVE"));
                        cuentaa.setPersona_id(resultSet.getInt("PERSONA_ID"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return cuentaa;
    }

    public ListaEnlazada<Cuenta> buscarCuentasPorRol(Integer texto) {
        ListaEnlazada<Cuenta> cuentass = new ListaEnlazada<>();
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "AXLMD", "AXLMD")) {
            String sql = "SELECT C.ID, C.CORREO_INSTITUCIONAL, C.CLAVE, C.PERSONA_ID FROM CUENTA C JOIN PERSONA P ON C.PERSONA_ID = P.ID WHERE P.ROL_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, texto);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Cuenta cuentaa = new Cuenta();
                        cuentaa.setId(resultSet.getInt("ID"));
                        cuentaa.setCorreo_institucional(resultSet.getString("CORREO_INSTITUCIONAL"));
                        cuentaa.setClave(resultSet.getString("CLAVE"));
                        cuentaa.setPersona_id(resultSet.getInt("PERSONA_ID"));
                        cuentass.add(cuentaa);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
        }
        return cuentass;
    }

}
