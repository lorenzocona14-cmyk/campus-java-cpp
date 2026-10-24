import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Lección 02 del Módulo Funcional:
 * La Stream API en profundidad (Transformación, Agrupamiento y Reducción).
 */
public class StreamsApiDemo {

    public record Producto(String nombre, String categoria, double precio, int stock) {}

    public static void main(String[] args) {
        List<Producto> catalogo = List.of(
            new Producto("MacBook Pro", "Computación", 2499.99, 12),
            new Producto("Dell XPS 15", "Computación", 1899.00, 8),
            new Producto("Monitor 4K LG", "Periféricos", 499.50, 25),
            new Producto("Teclado Mecánico", "Periféricos", 120.00, 40),
            new Producto("Mouse Inalámbrico", "Periféricos", 45.00, 60),
            new Producto("Silla Ergonómica", "Mobiliario", 350.00, 15),
            new Producto("Escritorio Elevable", "Mobiliario", 600.00, 5)
        );

        System.out.println("=== 1. FILTRADO, TRANSFORMACIÓN Y RECOLECCIÓN ===");
        // Obtener los nombres de todos los productos de Computación con precio > $2000
        List<String> computacionPremium = catalogo.stream()
            .filter(p -> p.categoria().equals("Computación"))
            .filter(p -> p.precio() > 2000)
            .map(Producto::nombre)
            .toList(); // En Java 16+, toList() crea una lista inmutable directamente

        System.out.println("Productos de computación premium: " + computacionPremium);

        System.out.println("\n=== 2. AGRUPAMIENTO POR CATEGORÍA (groupingBy) ===");
        // Agrupar la lista en un Map<String, List<Producto>> según la categoría
        Map<String, List<Producto>> porCategoria = catalogo.stream()
            .collect(Collectors.groupingBy(Producto::categoria));

        porCategoria.forEach((categoria, productos) -> {
            System.out.println("📂 Categoría: " + categoria);
            productos.forEach(p -> System.out.printf("   - %-20s | $%.2f%n", p.nombre(), p.precio()));
        });

        System.out.println("\n=== 3. CÁLCULO ESTADÍSTICO COMPLETO (summarizingDouble) ===");
        // Obtiene suma, promedio, conteo, mínimo y máximo en una sola pasada
        DoubleSummaryStatistics stats = catalogo.stream()
            .collect(Collectors.summarizingDouble(Producto::precio));

        System.out.printf("Total productos: %d%n", stats.getCount());
        System.out.printf("Suma de precios: $%.2f%n", stats.getSum());
        System.out.printf("Precio promedio: $%.2f%n", stats.getAverage());
        System.out.printf("Precio mínimo:   $%.2f%n", stats.getMin());
        System.out.printf("Precio máximo:   $%.2f%n", stats.getMax());

        System.out.println("\n=== 4. REDUCCIÓN PERSONALIZADA (reduce) ===");
        // Calcular el valor total del inventario: sum(precio * stock)
        double valorTotalInventario = catalogo.stream()
            .map(p -> p.precio() * p.stock())
            .reduce(0.0, Double::sum);

        System.out.printf("Valor total del inventario: $%.2f%n", valorTotalInventario);
    }
}
