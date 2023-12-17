package inicio_sesion.controller;

import DAO.DataAccessObject;
import model.Cuenta;
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
        this.rol.setId(generarID());
        return save(rol);
    }

    public Boolean update(Integer index) {
        return update(rol, index);
    }


    public static void main(String[] args) {
        RolController rc = new RolController();
        rc.getRol().setId(1);
        rc.getRol().setNombre("Administrador");
        rc.save();
        rc.getRol().setId(2);
        rc.getRol().setNombre("Estudiante");
        rc.save();
        rc.getRol().setId(3);
        rc.getRol().setNombre("Docente");
        rc.save();
    }
}

