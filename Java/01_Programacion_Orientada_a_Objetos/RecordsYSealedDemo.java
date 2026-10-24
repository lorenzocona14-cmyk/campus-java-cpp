/**
 * Lección 03 de POO:
 * Java Moderno: Records (Java 16+) y Clases Selladas (Sealed Classes - Java 17+).
 * 
 * Los Records eliminan todo el código repetitivo (*boilerplate*) para clases de datos inmutables.
 * Las Clases Selladas permiten crear jerarquías cerradas y exhaustivas (tipos de suma / algebraic data types).
 */
public class RecordsYSealedDemo {

    // ==========================================
    // 1. RECORDS: Clases Inmutables de Datos
    // ==========================================
    // Genera automáticamente:
    // - Campos 'private final'
    // - Constructor canónico con todos los campos
    // - Getters con el nombre del campo (ej: id(), nombre(), email())
    // - toString(), equals() y hashCode() basados en el contenido
    public record Usuario(int id, String nombre, String email) {
        
        // Constructor compacto para validaciones:
        public Usuario {
            if (id <= 0) {
                throw new IllegalArgumentException("El ID debe ser positivo.");
            }
            if (nombre == null || nombre.isBlank()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }
            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("El formato de correo es inválido.");
            }
        }

        // Método personalizado adicional en el record
        public String obtenerFirma() {
            return nombre + " <" + email + ">";
        }
    }

    // ==========================================
    // 2. SEALED INTERFACES (Jerarquías Selladas)
    // ==========================================
    // Solo las clases permitidas explícitamente en 'permits' pueden implementar esta interfaz
    public sealed interface EstadoPago permits PagoPendiente, PagoAprobado, PagoRechazado {}

    public record PagoPendiente(String codigoTransaccion, double monto) implements EstadoPago {}
    public record PagoAprobado(String codigoTransaccion, double monto, String numeroAutorizacion) implements EstadoPago {}
    public record PagoRechazado(String codigoTransaccion, String motivo) implements EstadoPago {}

    // ==========================================
    // EJECUCIÓN Y PATTERN MATCHING
    // ==========================================
    public static void main(String[] args) {
        System.out.println("=== 1. DEMOSTRACIÓN DE RECORDS ===");
        Usuario u1 = new Usuario(1, "Carlos Tevez", "carlos@gmail.com");
        Usuario u2 = new Usuario(1, "Carlos Tevez", "carlos@gmail.com");

        System.out.println("ToString automático: " + u1);
        System.out.println("Getter u1.nombre(): " + u1.nombre());
        System.out.println("Getter u1.email(): " + u1.email());
        System.out.println("¿u1.equals(u2) por valor?: " + u1.equals(u2)); // true!

        System.out.println("\n=== 2. PATTERN MATCHING CON TIPOS SELLADOS ===");
        EstadoPago p1 = new PagoPendiente("TRX-100", 250.0);
        EstadoPago p2 = new PagoAprobado("TRX-101", 1200.0, "AUTH-998822");
        EstadoPago p3 = new PagoRechazado("TRX-102", "Fondos insuficientes en la tarjeta");

        procesarPago(p1);
        procesarPago(p2);
        procesarPago(p3);
    }

    // Al ser sellada, el compilador verifica la exhaustividad del switch.
    // Si olvidamos manejar un caso permitido, el compilador arroja un error.
    public static void procesarPago(EstadoPago estado) {
        String resultado = switch (estado) {
            case PagoPendiente p -> "⏳ Transacción " + p.codigoTransaccion() + " esperando confirmación por $" + p.monto();
            case PagoAprobado a -> "✅ Transacción " + a.codigoTransaccion() + " APROBADA con código: " + a.numeroAutorizacion();
            case PagoRechazado r -> "❌ Transacción " + r.codigoTransaccion() + " RECHAZADA. Motivo: " + r.motivo();
        };
        System.out.println(resultado);
    }
}
