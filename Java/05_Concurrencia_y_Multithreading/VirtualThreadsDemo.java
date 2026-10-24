import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Lección 02 de Concurrencia:
 * Virtual Threads (Project Loom en Java 21+).
 * 
 * Lanzaremos 10,000 hilos concurrentes simulando operaciones de I/O
 * (llamadas HTTP o consultas a base de datos).
 * ¡Con hilos tradicionales de S.O. esto colapsaría la memoria de tu PC!
 */
public class VirtualThreadsDemo {

    public static void main(String[] args) {
        final int NUMERO_DE_TAREAS = 10_000;
        AtomicInteger tareasCompletadas = new AtomicInteger(0);

        System.out.println("=== LANZANDO " + NUMERO_DE_TAREAS + " VIRTUAL THREADS ===");
        Instant inicio = Instant.now();

        // En Java 21+, ExecutorService implementa AutoCloseable.
        // El bloque try-with-resources espera automáticamente a que TODAS las tareas finalicen.
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUMERO_DE_TAREAS; i++) {
                final int idTarea = i;
                executor.submit(() -> {
                    try {
                        // Simulamos una latencia de red de 100 milisegundos
                        Thread.sleep(100);
                        tareasCompletadas.incrementAndGet();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        } // Aquí se produce un join implícito de todas las tareas

        Instant fin = Instant.now();
        Duration duracion = Duration.between(inicio, fin);

        System.out.println("¡Todas las tareas finalizaron!");
        System.out.println("Tareas completadas con éxito: " + tareasCompletadas.get());
        System.out.println("Tiempo total transcurrido: " + duracion.toMillis() + " ms");
        System.out.println("Observa cómo 10,000 hilos durmiendo 100ms se completaron en apenas ~" 
                           + (duracion.toMillis() / 1000.0) + " segundos sin agotar la memoria.");
    }
}
