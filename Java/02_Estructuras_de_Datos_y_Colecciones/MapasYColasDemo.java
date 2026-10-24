import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Lección 03 del Módulo de Colecciones:
 * Mapas Modernos (Idiomas Funcionales) y Colas/Pilas de Alto Rendimiento.
 */
public class MapasYColasDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. HASHMAP Y OPERACIONES MODERNAS (Java 8+) ===");
        Map<String, Integer> inventario = new HashMap<>();
        inventario.put("Laptop", 10);
        inventario.put("Teclado", 50);

        // getOrDefault: Evita retornar 'null' si la clave no existe
        int stockMouse = inventario.getOrDefault("Mouse", 0);
        System.out.println("Stock de Mouse (inexistente): " + stockMouse);

        // computeIfAbsent: Calcula e inserta el valor SOLO si la clave no está presente
        inventario.computeIfAbsent("Monitor", k -> 15);
        System.out.println("Inventario tras computeIfAbsent: " + inventario);

        // merge: Ideal para conteo de frecuencias o acumulación
        String texto = "java c++ java python java c++ rust";
        Map<String, Integer> frecuencias = new HashMap<>();
        for (String palabra : texto.split(" ")) {
            // Si no existe, guarda 1. Si existe, suma 1 al valor anterior.
            frecuencias.merge(palabra, 1, Integer::sum);
        }
        System.out.println("Frecuencia de palabras: " + frecuencias);

        System.out.println("\n=== 2. TREEMAP: MAPA ORDENADO POR CLAVES ===");
        // Mantiene las entradas ordenadas por clave usando un árbol Red-Black O(log n)
        Map<String, Double> cotizaciones = new TreeMap<>(frecuencias.keySet().stream()
                .collect(HashMap::new, (m, v) -> m.put(v, 1.0), HashMap::putAll));
        cotizaciones.put("Bitcoin", 65000.0);
        cotizaciones.put("Ethereum", 3500.0);
        cotizaciones.put("Cardano", 0.50);
        System.out.println("Cotizaciones ordenadas alfabéticamente: " + cotizaciones);

        System.out.println("\n=== 3. PILAS Y COLAS DE ALTO RENDIMIENTO (ArrayDeque) ===");
        // REGLA DE ORO DE JAVA: NUNCA USES java.util.Stack (es obsoleta y sincronizada).
        // Usa siempre ArrayDeque como Pila (Stack LIFO) o Cola (Queue FIFO).

        // Como Cola FIFO (First-In, First-Out):
        Deque<String> colaAtencion = new ArrayDeque<>();
        colaAtencion.offer("Ticket #1");
        colaAtencion.offer("Ticket #2");
        colaAtencion.offer("Ticket #3");

        System.out.println("Atendiendo FIFO: " + colaAtencion.poll()); // Ticket #1
        System.out.println("Atendiendo FIFO: " + colaAtencion.poll()); // Ticket #2

        // Como Pila LIFO (Last-In, First-Out):
        Deque<String> historialUndo = new ArrayDeque<>();
        historialUndo.push("Acción 1: Escribir");
        historialUndo.push("Acción 2: Pegar imagen");
        historialUndo.push("Acción 3: Borrar texto");

        System.out.println("Deshaciendo LIFO: " + historialUndo.pop()); // Acción 3

        System.out.println("\n=== 4. COLA DE PRIORIDAD (PriorityQueue / Min-Heap) ===");
        // Siempre entrega primero el elemento con mayor prioridad (por defecto el menor)
        PriorityQueue<Integer> tareasUrgencia = new PriorityQueue<>();
        tareasUrgencia.offer(5); // Prioridad baja
        tareasUrgencia.offer(1); // Máxima prioridad
        tareasUrgencia.offer(3); // Media

        System.out.print("Procesando por prioridad: ");
        while (!tareasUrgencia.isEmpty()) {
            System.out.print(tareasUrgencia.poll() + " "); // Imprime 1, luego 3, luego 5
        }
        System.out.println();
    }
}
