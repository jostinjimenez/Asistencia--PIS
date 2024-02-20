package Controller.Administrativo;

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
    public Integer save() throws Exception {
        return super.save(this.rol, "SQC_ROL");
    }

    public Boolean update() {
        try {
            update(this.rol);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        RolController rc = new RolController();

        // Prueba de update
//        rc.setRol(rc.find(5));
//        rc.getRol().setNombre("Prueba");
//        rc.update();

        // Prueba de delete
        if (rc.delete(5)) {
            System.out.println("Eliminado");
        } else {
            System.out.println("No eliminado");
        }


    }
}

