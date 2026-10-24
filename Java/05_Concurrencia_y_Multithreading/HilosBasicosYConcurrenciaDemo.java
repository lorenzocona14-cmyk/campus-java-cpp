import java.util.concurrent.atomic.AtomicInteger;

/**
 * Lección 01 de Concurrencia:
 * Condiciones de carrera, sincronización y variables atómicas.
 */
public class HilosBasicosYConcurrenciaDemo {

    // 1. Contador NO SEGURO: provisto para mostrar la condición de carrera
    static class ContadorInseguro {
        private int valor = 0;

        // La operación valor++ NO es atómica. En bytecode son 3 instrucciones:
        // 1. Leer valor
        // 2. Incrementar temporal
        // 3. Escribir valor de vuelta
        public void incrementar() {
            valor++;
        }

        public int getValor() { return valor; }
    }

    // 2. Contador SEGURO con synchronized
    static class ContadorSincronizado {
        private int valor = 0;

        public synchronized void incrementar() {
            valor++;
        }

        public synchronized int getValor() { return valor; }
    }

    // 3. Contador SEGURO con AtomicInteger (Alto rendimiento, Lock-Free vía hardware CAS)
    static class ContadorAtomico {
        private final AtomicInteger valor = new AtomicInteger(0);

        public void incrementar() {
            valor.incrementAndGet();
        }

        public int getValor() { return valor.get(); }
    }

    public static void main(String[] args) throws InterruptedException {
        final int NUM_HILOS = 10;
        final int INCREMENTOS_POR_HILO = 10_000;
        final int VALOR_ESPERADO = NUM_HILOS * INCREMENTOS_POR_HILO; // 100,000

        System.out.println("=== 1. DEMOSTRACIÓN DE CONDICIÓN DE CARRERA (RACE CONDITION) ===");
        ContadorInseguro inseguro = new ContadorInseguro();
        ejecutarConcurrente(() -> {
            for (int i = 0; i < INCREMENTOS_POR_HILO; i++) inseguro.incrementar();
        }, NUM_HILOS);
        System.out.println("Valor esperado: " + VALOR_ESPERADO);
        System.out.println("Valor REAL obtenido (con fallos por concurrencia): " + inseguro.getValor());

        System.out.println("\n=== 2. SOLUCIÓN CON SYNCHRONIZED ===");
        ContadorSincronizado sincro = new ContadorSincronizado();
        ejecutarConcurrente(() -> {
            for (int i = 0; i < INCREMENTOS_POR_HILO; i++) sincro.incrementar();
        }, NUM_HILOS);
        System.out.println("Valor obtenido con synchronized (Consistente): " + sincro.getValor());

        System.out.println("\n=== 3. SOLUCIÓN CON ATOMICINTEGER (LOCK-FREE) ===");
        ContadorAtomico atomico = new ContadorAtomico();
        ejecutarConcurrente(() -> {
            for (int i = 0; i < INCREMENTOS_POR_HILO; i++) atomico.incrementar();
        }, NUM_HILOS);
        System.out.println("Valor obtenido con AtomicInteger (Óptimo): " + atomico.getValor());
    }

    // Método utilitario para lanzar N hilos y esperar a que todos terminen (join)
    private static void ejecutarConcurrente(Runnable tarea, int hilosCount) throws InterruptedException {
        Thread[] hilos = new Thread[hilosCount];
        for (int i = 0; i < hilosCount; i++) {
            hilos[i] = new Thread(tarea);
            hilos[i].start();
        }
        for (Thread h : hilos) {
            h.join(); // Espera a que el hilo 'h' complete su trabajo
        }
    }
}
