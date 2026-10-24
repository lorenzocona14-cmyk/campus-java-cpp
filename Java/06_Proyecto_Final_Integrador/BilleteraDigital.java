import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Gestor del Core Bancario y Billetera Digital.
 */
public class BilleteraDigital {
    private final Map<String, Cuenta> cuentasPorCbu = new HashMap<>();
    private final List<Transaccion> historialTransacciones = new ArrayList<>();
    private int contadorTx = 0;

    public void registrarCuenta(Cuenta cuenta) {
        if (cuentasPorCbu.containsKey(cuenta.getCbu())) {
            throw new IllegalArgumentException("Ya existe una cuenta registrada con el CBU: " + cuenta.getCbu());
        }
        cuentasPorCbu.put(cuenta.getCbu(), cuenta);
    }

    public Cuenta obtenerCuenta(String cbu) {
        Cuenta cuenta = cuentasPorCbu.get(cbu);
        if (cuenta == null) {
            throw new NoSuchElementException("No se encontró ninguna cuenta con CBU: " + cbu);
        }
        return cuenta;
    }

    public Transaccion depositar(String cbu, double monto) {
        Cuenta cta = obtenerCuenta(cbu);
        cta.acreditar(monto);

        Transaccion tx = new Transaccion(
            generarIdTransaccion(),
            cbu,
            null,
            Transaccion.TipoTransaccion.DEPOSITO,
            monto,
            LocalDateTime.now()
        );
        historialTransacciones.add(tx);
        return tx;
    }

    public Transaccion retirar(String cbu, double monto) {
        Cuenta cta = obtenerCuenta(cbu);
        cta.debitar(monto);

        Transaccion tx = new Transaccion(
            generarIdTransaccion(),
            cbu,
            null,
            Transaccion.TipoTransaccion.RETIRO,
            monto,
            LocalDateTime.now()
        );
        historialTransacciones.add(tx);
        return tx;
    }

    public Transaccion transferir(String cbuOrigen, String cbuDestino, double monto) {
        if (cbuOrigen.equals(cbuDestino)) {
            throw new IllegalArgumentException("La cuenta de origen y destino no pueden ser iguales.");
        }
        Cuenta origen = obtenerCuenta(cbuOrigen);
        Cuenta destino = obtenerCuenta(cbuDestino);

        // Operación atómica de transferencia
        origen.debitar(monto);
        destino.acreditar(monto);

        Transaccion tx = new Transaccion(
            generarIdTransaccion(),
            cbuOrigen,
            cbuDestino,
            Transaccion.TipoTransaccion.TRANSFERENCIA,
            monto,
            LocalDateTime.now()
        );
        historialTransacciones.add(tx);
        return tx;
    }

    // --- MÓDULO ANALÍTICO CON STREAM API ---
    public double calcularVolumenTotalTransaccionado() {
        return historialTransacciones.stream()
            .mapToDouble(Transaccion::monto)
            .sum();
    }

    public List<Transaccion> obtenerMayoresTransacciones(int topN) {
        return historialTransacciones.stream()
            .sorted(Comparator.comparingDouble(Transaccion::monto).reversed())
            .limit(topN)
            .toList();
    }

    public Map<Transaccion.TipoTransaccion, Long> contarTransaccionesPorTipo() {
        return historialTransacciones.stream()
            .collect(Collectors.groupingBy(Transaccion::tipo, Collectors.counting()));
    }

    public List<Cuenta> obtenerTodasLasCuentas() {
        return new ArrayList<>(cuentasPorCbu.values());
    }

    // --- PERSISTENCIA CON NIO.2 ---
    public void exportarAuditoria(Path rutaArchivo) throws IOException {
        List<String> lineas = new ArrayList<>();
        lineas.add("ID,ORIGEN,DESTINO,TIPO,MONTO,FECHA_HORA");
        for (Transaccion tx : historialTransacciones) {
            lineas.add(tx.aFormatoCsv());
        }
        Files.write(rutaArchivo, lineas, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private synchronized String generarIdTransaccion() {
        contadorTx++;
        return String.format("TX-%06d", contadorTx);
    }
}
