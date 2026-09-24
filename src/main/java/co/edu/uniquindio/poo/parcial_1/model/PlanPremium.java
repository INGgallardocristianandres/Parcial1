package co.edu.uniquindio.poo.parcial_1.model;

/**
 * Plan de entrenamiento premium: incluye acceso a clases grupales y a la
 * zona VIP del gimnasio.
 */
public class PlanPremium extends PlanEntrenamiento {

    private boolean accesoGrupales;
    private boolean accesoZonaVIP;

    /**
     * Crea un plan premium con valores predeterminados. Pensado para ser
     * construido a través de {@link PlanPremiumFactory}.
     */
    public PlanPremium() {
        super("PP-" + System.currentTimeMillis(),
                "Plan Premium",
                "Acceso a clases grupales y a la zona VIP",
                8,
                150000.0,
                EstadoPlan.ACTIVO);
        this.accesoGrupales = true;
        this.accesoZonaVIP = true;
    }

    /**
     * Constructor de copia, usado por {@link #clonar()}.
     */
    private PlanPremium(PlanPremium otro) {
        super(otro);
        this.accesoGrupales = otro.accesoGrupales;
        this.accesoZonaVIP = otro.accesoZonaVIP;
    }

    public boolean isAccesoGrupales() {
        return accesoGrupales;
    }

    public void setAccesoGrupales(boolean accesoGrupales) {
        this.accesoGrupales = accesoGrupales;
    }

    public boolean isAccesoZonaVIP() {
        return accesoZonaVIP;
    }

    public void setAccesoZonaVIP(boolean accesoZonaVIP) {
        this.accesoZonaVIP = accesoZonaVIP;
    }

    @Override
    public double calcularAdicional() {
        double adicional = 0.0;
        if (accesoGrupales) {
            adicional += 20000.0;
        }
        if (accesoZonaVIP) {
            adicional += 30000.0;
        }
        return adicional;
    }

    @Override
    public PlanEntrenamiento clonar() {
        return new PlanPremium(this);
    }
}