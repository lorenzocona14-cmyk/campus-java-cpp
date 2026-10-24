import java.util.ArrayList;
import java.util.List;

/**
 * Soluciones de Referencia y Suite de Pruebas Automatizadas para el Módulo 01 de Java (POO).
 * 
 * Ejecutar:
 *   java SolucionesYTests.java
 */
public class SolucionesYTests {

    // ==========================================
    // SOLUCIÓN RETO 1: Jerarquía de Vehículos y Peaje
    // ==========================================
    public abstract static class Vehiculo {
        private final String placa;
        private final int ejes;

        public Vehiculo(String placa, int ejes) {
            this.placa = placa;
            this.ejes = ejes;
        }

        public String getPlaca() { return placa; }
        public int getEjes() { return ejes; }

        public abstract double calcularTarifaPeaje();
    }

    public static class Auto extends Vehiculo {
        public Auto(String placa) { super(placa, 2); }
        @Override
        public double calcularTarifaPeaje() { return 150.0; }
    }

    public static class Camion extends Vehiculo {
        public Camion(String placa, int ejes) { super(placa, ejes); }
        @Override
        public double calcularTarifaPeaje() {
            int ejesExtra = Math.max(0, getEjes() - 2);
            return 150.0 + (ejesExtra * 75.0);
        }
    }

    public static class Moto extends Vehiculo {
        private final boolean esElectrica;

        public Moto(String placa, boolean esElectrica) {
            super(placa, 2);
            this.esElectrica = esElectrica;
        }

        @Override
        public double calcularTarifaPeaje() {
            return esElectrica ? 25.0 : 50.0; // 50% de descuento si es eléctrica
        }
    }

    // ==========================================
    // SOLUCIÓN RETO 2: Record Producto Inmutable
    // ==========================================
    public record Producto(String codigo, String nombre, double precio, int stock) {
        public Producto {
            if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo");
            if (stock < 0) throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        public boolean tieneStock() {
            return stock > 0;
        }
    }

    // ==========================================
    // SOLUCIÓN RETO 3: Gestor de Notificaciones
    // ==========================================
    public interface CanalNotificacion {
        String enviar(String destinatario, String mensaje);
    }

    public static class EmailNotificacion implements CanalNotificacion {
        @Override
        public String enviar(String dest, String msg) { return "EMAIL a " + dest + ": " + msg; }
    }

    public static class SmsNotificacion implements CanalNotificacion {
        @Override
        public String enviar(String dest, String msg) { return "SMS a " + dest + ": " + msg; }
    }

    public static class GestorCampanas {
        private final List<CanalNotificacion> canales = new ArrayList<>();

        public void registrarCanal(CanalNotificacion c) { canales.add(c); }

        public List<String> despachar(String destinatario, String mensaje) {
            List<String> logs = new ArrayList<>();
            for (CanalNotificacion canal : canales) {
                logs.add(canal.enviar(destinatario, mensaje));
            }
            return logs;
        }
    }

    // ==========================================
    // SUITE DE TESTS AUTOMATIZADOS
    // ==========================================
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   🧪 TESTS AUTOMATIZADOS: MÓDULO 01 (POO JAVA)  ");
        System.out.println("==================================================");

        int testsPasados = 0;
        int totalTests = 0;

        // Test 1: Peaje Polimórfico
        totalTests++;
        List<Vehiculo> colaPeaje = List.of(
            new Auto("AAA-111"),               // 150
            new Camion("BBB-222", 4),           // 150 + 2*75 = 300
            new Moto("CCC-333", false),         // 50
            new Moto("DDD-444", true)           // 25
        );
        double totalPeaje = colaPeaje.stream().mapToDouble(Vehiculo::calcularTarifaPeaje).sum();
        if (totalPeaje == 525.0) {
            System.out.println("  [✓] Test 1: Cálculo de peaje polimórfico exacto ($525.0).");
            testsPasados++;
        } else {
            System.err.println("  [X] Test 1: Fallo en cálculo de peaje. Obtenido: " + totalPeaje);
        }

        // Test 2: Validación de Record Producto
        totalTests++;
        Producto p1 = new Producto("P01", "Mouse", 25.0, 10);
        Producto p2 = new Producto("P02", "Teclado", 50.0, 0);
        boolean excepcionCapturada = false;
        try {
            new Producto("P03", "Invalido", -10.0, 5);
        } catch (IllegalArgumentException e) {
            excepcionCapturada = true;
        }

        if (p1.tieneStock() && !p2.tieneStock() && excepcionCapturada) {
            System.out.println("  [✓] Test 2: Invariantes y métodos de Record Producto verificados.");
            testsPasados++;
        } else {
            System.err.println("  [X] Test 2: Fallo en validaciones de Record.");
        }

        // Test 3: Despacho Polimórfico de Canales
        totalTests++;
        GestorCampanas gestor = new GestorCampanas();
        gestor.registrarCanal(new EmailNotificacion());
        gestor.registrarCanal(new SmsNotificacion());
        List<String> logs = gestor.despachar("usuario@test.com", "Oferta");

        if (logs.size() == 2 && logs.get(0).startsWith("EMAIL") && logs.get(1).startsWith("SMS")) {
            System.out.println("  [✓] Test 3: Despacho polimórfico de notificaciones exitoso.");
            testsPasados++;
        } else {
            System.err.println("  [X] Test 3: Fallo en despacho de notificaciones.");
        }

        System.out.println("\n--------------------------------------------------");
        System.out.printf("RESULTADO FINAL: %d / %d TESTS PASADOS (%d%%)%n", 
            testsPasados, totalTests, (testsPasados * 100 / totalTests));
        System.out.println("--------------------------------------------------");
    }
}
