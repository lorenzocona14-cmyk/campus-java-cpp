import java.util.Objects;

/**
 * Entidad Cuenta con encapsulamiento estricto.
 */
public class Cuenta {
    private final String cbu;
    private final String titular;
    private double saldo;

    public Cuenta(String cbu, String titular, double saldoInicial) {
        if (cbu == null || cbu.isBlank()) throw new IllegalArgumentException("CBU inválido");
        if (titular == null || titular.isBlank()) throw new IllegalArgumentException("Titular inválido");
        if (saldoInicial < 0) throw new IllegalArgumentException("El saldo inicial no puede ser negativo");

        this.cbu = cbu;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public String getCbu() { return cbu; }
    public String getTitular() { return titular; }
    public synchronized double getSaldo() { return saldo; }

    public synchronized void acreditar(double monto) {
        if (monto <= 0) throw new IllegalArgumentException("Monto a acreditar debe ser positivo");
        this.saldo += monto;
    }

    public synchronized void debitar(double monto) {
        if (monto <= 0) throw new IllegalArgumentException("Monto a debitar debe ser positivo");
        if (monto > this.saldo) {
            throw new IllegalStateException(String.format(
                "Saldo insuficiente en cuenta %s. Saldo: $%.2f | Requerido: $%.2f",
                cbu, saldo, monto));
        }
        this.saldo -= monto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(cbu, cuenta.cbu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cbu);
    }

    @Override
    public String toString() {
        return String.format("Cuenta[%s | Titular: %-15s | Saldo: $%.2f]", cbu, titular, saldo);
    }
}
