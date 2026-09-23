package co.edu.uniquindio.poo.parcial_1.model;
/**
 * Clase abstracta que define la estructura base de los servicios adicionales
 * ofrecidos por el gimnasio.

 */
public abstract class ServicioAdicional {
    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected double precio;
    protected boolean disponible;
    /**
     * Constructor base para la creación de un servicio adicional.
     */
    public ServicioAdicional(String codigo, String nombre, String descripcion, double precio, boolean disponible) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponible = disponible;
    }

    public String getCodigo() {return codigo;}
    public void setCodigo(String codigo) {this.codigo = codigo;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public double getPrecio() {return precio;}
    public void setPrecio(double precio) {this.precio = precio;}
    public boolean isDisponible() {return disponible;}
    public void setDisponible(boolean disponible) {this.disponible = disponible;}
}
