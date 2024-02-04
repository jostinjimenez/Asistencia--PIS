package modulo_1.inicio_sesion.controller;

import DataBase.DataAccessObject;
import model.Rol;
import tda_listas.ListaEnlazada;

public class RolController extends DataAccessObject<Rol> {
    // Atributos
    private ListaEnlazada<Rol> roles;
    private Rol rol = new Rol();
    private Integer index = -1;

    // Constructor
    public RolController() {
        super(Rol.class);
        this.roles = new ListaEnlazada<>();
    }

    // Getters y Setters
    public ListaEnlazada<Rol> getRoles() {
        if (roles.isEmpty()) {
            roles = this.list_All();
        }
        return roles;
    }

    public void setRoles(ListaEnlazada<Rol> roles) {
        this.roles = roles;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    // Metodos
    public Boolean save() {
        try {
            Integer idGenerado = super.save(this.rol);
            return idGenerado != -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean update() {
        try {
            update(this.rol);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        RolController rc = new RolController();

        rc.getRol().setId(1);
        rc.getRol().setNombre("Administrador");
        rc.getRol().setDescripcion("Tiene acceso a todas las funcionalidades del sistema");

        if (rc.save()) {
            System.out.println("Rol guardado con éxito");
        } else {
            System.out.println("Error al guardar el rol");
        }
    }
}

