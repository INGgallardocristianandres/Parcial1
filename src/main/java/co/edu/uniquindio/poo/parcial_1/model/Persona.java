package co.edu.uniquindio.poo.parcial_1.model;

public class Persona {
    /**
     * Clase abstracta que representa a una persona dentro del sistema.
     * Es la superclase de Cliente y Entrenador.
     */
    protected String id;
    protected String nombre;
    protected String telefono;
    protected String correo;
    /**
     * Constructor base para la creación de una persona.
     */
    public Persona (String id, String nombre,String telefono, String correo){
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getId() {
        return correo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    @Override
    public String toString() {
        return "id=" + id + ", nombre=" + nombre + ", " +
                "telefono=" + telefono + ", correo=" + correo;
    }
}
