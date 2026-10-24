/**
 * Soluciones de Referencia y Suite de Pruebas Automatizadas para el Módulo 00 de Java.
 * 
 * Puedes ejecutar este archivo para autoevaluar tu comprensión de los 3 retos:
 *   java SolucionesYTests.java
 */
public class SolucionesYTests {

    // ==========================================
    // SOLUCIÓN RETO 1: Números Primos Eficientes
    // ==========================================
    public static boolean esPrimo(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static int contarPrimosHasta(int limite) {
        int contador = 0;
        for (int i = 1; i <= limite; i++) {
            if (esPrimo(i)) contador++;
        }
        return contador;
    }

    public static long sumarPrimosHasta(int limite) {
        long suma = 0;
        for (int i = 1; i <= limite; i++) {
            if (esPrimo(i)) suma += i;
        }
        return suma;
    }

    // ==========================================
    // SOLUCIÓN RETO 2: Conversor con Switch Expressions
    // ==========================================
    public static double convertirTemperatura(double valor, char de, char a) {
        char origen = Character.toUpperCase(de);
        char destino = Character.toUpperCase(a);

        if (origen == destino) return valor;

        // Primero convertimos a Celsius como unidad pivote
        double enCelsius = switch (origen) {
            case 'C' -> valor;
            case 'F' -> (valor - 32.0) * (5.0 / 9.0);
            case 'K' -> valor - 273.15;
            default -> throw new IllegalArgumentException("Unidad de origen no soportada: " + origen);
        };

        // Luego convertimos de Celsius a la unidad destino
        double resultado = switch (destino) {
            case 'C' -> enCelsius;
            case 'F' -> (enCelsius * (9.0 / 5.0)) + 32.0;
            case 'K' -> enCelsius + 273.15;
            default -> throw new IllegalArgumentException("Unidad de destino no soportada: " + destino);
        };

        return Math.round(resultado * 100.0) / 100.0;
    }

    // ==========================================
    // SOLUCIÓN RETO 3: Paso por Valor con Jugador
    // ==========================================
    public static class Jugador {
        private final String nombre;
        private int fichas;

        public Jugador(String nombre, int fichas) {
            this.nombre = nombre;
            this.fichas = fichas;
        }

        public String getNombre() { return nombre; }
        public int getFichas() { return fichas; }

        public boolean apostar(int cantidad) {
            if (cantidad <= 0 || cantidad > fichas) return false;
            this.fichas -= cantidad;
            return true;
        }
    }

    public static void intentarReasignarJugador(Jugador j) {
        j = new Jugador("Trampita", 1_000_000);
    }

    // ==========================================
    // SUITE DE TESTS AUTOMATIZADOS (Test Runner)
    // ==========================================
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   🧪 EJECUTANDO TESTS AUTOMATIZADOS: MÓDULO 00   ");
        System.out.println("==================================================");

        int testsPasados = 0;
        int totalTests = 0;

        // Test 1.1: Primos conocidos
        totalTests++;
        if (esPrimo(2) && esPrimo(13) && esPrimo(97) && !esPrimo(1) && !esPrimo(4) && !esPrimo(100)) {
            System.out.println("  [✓] Test 1.1: Clasificador esPrimo() funciona correctamente.");
            testsPasados++;
        } else {
            System.err.println("  [X] Test 1.1: Fallo en esPrimo().");
        }

        // Test 1.2: Primos hasta 100 (existen exactamente 25 números primos y su suma es 1060)
        totalTests++;
        if (contarPrimosHasta(100) == 25 && sumarPrimosHasta(100) == 1060) {
            System.out.println("  [✓] Test 1.2: Conteo (25) y Suma (1060) de primos hasta 100 correcto.");
            testsPasados++;
        } else {
            System.err.println("  [X] Test 1.2: Conteo o suma de primos incorrecta.");
        }

        // Test 2.1: Conversor de Temperatura
        totalTests++;
        boolean t21 = convertirTemperatura(0.0, 'C', 'F') == 32.0;
        boolean t22 = convertirTemperatura(100.0, 'C', 'K') == 373.15;
        boolean t23 = convertirTemperatura(212.0, 'F', 'C') == 100.0;
        if (t21 && t22 && t23) {
            System.out.println("  [✓] Test 2.1: Conversión de Temperatura con Switch Expressions precisa.");
            testsPasados++;
        } else {
            System.err.println("  [X] Test 2.1: Fallo en conversiones de temperatura.");
        }

        // Test 3.1: Mutación interna vs Reasignación (Paso por valor)
        totalTests++;
        Jugador jugador = new Jugador("Lucas", 500);
        boolean apostoOk = jugador.apostar(200);
        boolean noPudoApostarDeMas = !jugador.apostar(1000);
        intentarReasignarJugador(jugador);

        if (apostoOk && noPudoApostarDeMas && jugador.getFichas() == 300 && jugador.getNombre().equals("Lucas")) {
            System.out.println("  [✓] Test 3.1: Paso por valor comprobado (estado mutado, variable no reasignada).");
            testsPasados++;
        } else {
            System.err.println("  [X] Test 3.1: Fallo en la lógica del Jugador o paso de parámetros.");
        }

        System.out.println("\n--------------------------------------------------");
        System.out.printf("RESULTADO FINAL: %d / %d TESTS PASADOS (%d%%)%n", 
            testsPasados, totalTests, (testsPasados * 100 / totalTests));
        System.out.println("--------------------------------------------------");
    }
}
