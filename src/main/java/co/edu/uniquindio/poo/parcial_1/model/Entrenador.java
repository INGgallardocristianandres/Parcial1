package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Representa a un entrenador del gimnasio. Hereda los datos básicos de
 * Persona y agrega su especialidad y la tarifa que cobra por hora.
 */
public class Entrenador extends Persona {

    private Especialidad especialidad;
    private double tarifaHora;

    /**
     * Constructor para la creación de un entrenador.
     */
    public Entrenador(String id, String nombre, String telefono, String correo,
                      Especialidad especialidad, double tarifaHora) {
        super(id, nombre, telefono, correo);
        this.especialidad = especialidad;
        this.tarifaHora = tarifaHora;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public double getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(double tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    @Override
    public String toString() {
        return "Entrenador{" + super.toString()
                + ", especialidad=" + especialidad
                + ", tarifaHora=" + tarifaHora + "}";
    }
}