import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Punto de entrada del Proyecto Final de Java.
 * Simula operaciones financieras y muestra la integración completa.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("   🏛️ CRYPTOBANK CORE ENGINE - SISTEMA BANCARIO DIGITAL    ");
        System.out.println("==========================================================");

        BilleteraDigital core = new BilleteraDigital();

        // 1. Alta de cuentas
        Cuenta cta1 = new Cuenta("CBU-001", "Facundo Gomez", 10000.0);
        Cuenta cta2 = new Cuenta("CBU-002", "Valeria Diaz", 5000.0);
        Cuenta cta3 = new Cuenta("CBU-003", "Empresa Tech SA", 150000.0);

        core.registrarCuenta(cta1);
        core.registrarCuenta(cta2);
        core.registrarCuenta(cta3);

        System.out.println("\n[+] Cuentas registradas exitosamente:");
        core.obtenerTodasLasCuentas().forEach(c -> System.out.println("    " + c));

        // 2. Operaciones transaccionales
        System.out.println("\n[+] Procesando transacciones...");
        core.depositar("CBU-001", 2500.0);
        core.retirar("CBU-002", 1200.0);
        core.transferir("CBU-003", "CBU-001", 15000.0);
        core.transferir("CBU-001", "CBU-002", 3000.0);

        // 3. Manejo de excepciones controlado
        System.out.println("\n[!] Probando intento de sobregiro:");
        try {
            core.retirar("CBU-002", 50000.0); // No tiene tanto dinero
        } catch (IllegalStateException e) {
            System.out.println("    [Capturado]: " + e.getMessage());
        }

        // 4. Reporte de Saldos Actualizados
        System.out.println("\n=== ESTADO FINAL DE CUENTAS ===");
        core.obtenerTodasLasCuentas().forEach(c -> System.out.println("    " + c));

        // 5. Analítica Financiera Avanzada con Stream API
        System.out.println("\n=== ANALÍTICA FINANCIERA (STREAM API) ===");
        System.out.printf("Volumen total transaccionado: $%.2f%n", core.calcularVolumenTotalTransaccionado());

        System.out.println("\nConteo por tipo de transacción:");
        core.contarTransaccionesPorTipo().forEach((tipo, cantidad) -> 
            System.out.printf("  - %-15s: %d operaciones%n", tipo, cantidad)
        );

        System.out.println("\nTop 2 transacciones de mayor valor:");
        List<Transaccion> top2 = core.obtenerMayoresTransacciones(2);
        top2.forEach(tx -> System.out.printf("  - [%s] Monto: $%.2f (%s -> %s)%n",
            tx.id(), tx.monto(), tx.cuentaOrigen(), tx.cuentaDestino()));

        // 6. Persistencia y Auditoría con NIO.2
        Path rutaAuditoria = Path.of("auditoria_transacciones.csv");
        try {
            System.out.println("\n[+] Exportando auditoría a disco: " + rutaAuditoria.toAbsolutePath());
            core.exportarAuditoria(rutaAuditoria);

            System.out.println("Archivo generado con éxito. Contenido verificado:");
            Files.readAllLines(rutaAuditoria).forEach(linea -> System.out.println("   | " + linea));

            // Limpieza del archivo demo
            Files.deleteIfExists(rutaAuditoria);
            System.out.println("\n[v] Auditoría verificada y prueba completada con éxito.");

        } catch (IOException e) {
            System.err.println("Error al persistir auditoría: " + e.getMessage());
        }
    }
}
