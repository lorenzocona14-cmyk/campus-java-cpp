/**
 * Lección 01 del Módulo de Excepciones:
 * Excepciones Checked vs Unchecked, AutoCloseable y Try-With-Resources.
 */
public class ExcepcionesAvanzadasDemo {

    // 1. Excepción Checked (Hereda de Exception): Obliga al llamador a manejarla o propagarla
    public static class FondosInsuficientesException extends Exception {
        private final double montoFaltante;

        public FondosInsuficientesException(String mensaje, double montoFaltante) {
            super(mensaje);
            this.montoFaltante = montoFaltante;
        }

        public double getMontoFaltante() {
            return montoFaltante;
        }
    }

    // 2. Excepción Unchecked (Hereda de RuntimeException): Representa violaciones de precondición
    public static class ParametroInvalidoException extends RuntimeException {
        public ParametroInvalidoException(String mensaje) {
            super(mensaje);
        }
    }

    // 3. Recurso personalizado que implementa AutoCloseable para demostrar try-with-resources
    public static class ConexionTransaccional implements AutoCloseable {
        private final String idTransaccion;

        public ConexionTransaccional(String idTransaccion) {
            this.idTransaccion = idTransaccion;
            System.out.println("  [+] Conexión transaccional abierta: " + idTransaccion);
        }

        public void ejecutarOperacion() throws Exception {
            System.out.println("  [*] Ejecutando operación bancaria crítica...");
            // Simulamos un fallo aleatorio o controlado
            throw new FondosInsuficientesException("Saldo no disponible para la operación", 150.0);
        }

        @Override
        public void close() {
            System.out.println("  [-] Conexión cerrada y recursos liberados automáticamente: " + idTransaccion);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 1. PRUEBA DE TRY-WITH-RESOURCES Y AUTOCLOSEABLE ===");
        
        // try-with-resources garantiza que 'con' se cerrará INCLUSO si 'ejecutarOperacion()' falla
        try (ConexionTransaccional con = new ConexionTransaccional("TX-9988")) {
            con.ejecutarOperacion();
        } catch (FondosInsuficientesException e) {
            System.err.println("Capturada excepción de negocio: " + e.getMessage());
            System.err.println("Monto faltante para completar la transacción: $" + e.getMontoFaltante());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        }

        System.out.println("\n=== 2. ENCADENAMIENTO DE EXCEPCIONES (EXCEPTION CHAINING) ===");
        try {
            operacionDeNivelSuperior();
        } catch (RuntimeException e) {
            System.out.println("Excepción superior recibida: " + e.getMessage());
            System.out.println("Causa raíz original: " + e.getCause().getClass().getSimpleName() 
                               + " -> " + e.getCause().getMessage());
        }
    }

    public static void operacionDeNivelSuperior() {
        try {
            // Simulamos fallo de bajo nivel
            int divisionPorCero = 10 / 0;
        } catch (ArithmeticException causaRaiz) {
            // Empaquetamos el error de bajo nivel dentro de una excepción de negocio
            throw new RuntimeException("Fallo en el cálculo de métricas financieras", causaRaiz);
        }
    }
}
