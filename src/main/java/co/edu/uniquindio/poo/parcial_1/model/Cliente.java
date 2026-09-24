package co.edu.uniquindio.poo.parcial_1.model;
import java.time.LocalDate;

/**
 * Representa a un cliente del gimnasio. Hereda los datos básicos de Persona
 * y agrega la edad y la fecha de registro.
 */
public class Cliente extends Persona {
    private int edad;
    private LocalDate fechaRegistro;

    /**
     * Constructor para la creación de un cliente.
     */
    public Cliente(String id, String nombre, String telefono, String correo,
                   int edad, LocalDate fechaRegistro) {
        super(id, nombre, telefono, correo);
        this.edad = edad;
        this.fechaRegistro = fechaRegistro;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Cliente{" + super.toString()
                + ", edad=" + edad
                + ", fechaRegistro=" + fechaRegistro + "}";
    }
}
