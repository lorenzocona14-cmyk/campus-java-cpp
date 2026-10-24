import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Record inmutable que representa una transacción financiera.
 */
public record Transaccion(
    String id,
    String cuentaOrigen,
    String cuentaDestino,
    TipoTransaccion tipo,
    double monto,
    LocalDateTime fechaHora
) {
    public enum TipoTransaccion {
        DEPOSITO,
        RETIRO,
        TRANSFERENCIA
    }

    public Transaccion {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto de la transacción debe ser mayor a 0");
        }
    }

    public String aFormatoCsv() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s,%s,%s,%s,%.2f,%s",
            id, cuentaOrigen, cuentaDestino != null ? cuentaDestino : "N/A",
            tipo, monto, fechaHora.format(fmt));
    }
}
