package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Fábrica concreta encargada de construir objetos de tipo {@link PlanPremium}.
 * Guarda los datos del plan al momento de crear la fábrica, y
 * {@link #crearPlan()} construye la instancia con esos datos.
 */
public class PlanPremiumFactory extends PlanFactory {

    private final String codigo;
    private final String nombre;
    private final String descripcion;
    private final int duracionMeses;
    private final double valorMensual;
    private final boolean accesoVIP;
    private final boolean accesoClasesGrupales;

    public PlanPremiumFactory(String codigo, String nombre, String descripcion,
                              int duracionMeses, double valorMensual,
                              boolean accesoVIP, boolean accesoClasesGrupales) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.accesoVIP = accesoVIP;
        this.accesoClasesGrupales = accesoClasesGrupales;
    }

    /**
     * Crea un nuevo plan de entrenamiento premium con los datos guardados.
     */
    @Override
    public PlanEntrenamiento crearPlan() {
        return new PlanPremium(codigo, nombre, descripcion, duracionMeses, valorMensual,
                accesoVIP, accesoClasesGrupales);
    }
}