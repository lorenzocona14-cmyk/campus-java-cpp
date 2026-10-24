import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Lección 01 del Módulo Funcional:
 * Lambdas, Method References e Interfaces Funcionales Estándar.
 */
public class LambdasYFuncionalesDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. PREDICATE: EVALUACIÓN BOOLEANA ===");
        // Predicado: evalúa si una cadena tiene al menos 5 caracteres
        Predicate<String> esLarga = texto -> texto.length() >= 5;
        Predicate<String> contieneA = texto -> texto.toLowerCase().contains("a");

        // Composición de predicados con 'and', 'or', 'negate'
        Predicate<String> valida = esLarga.and(contieneA);

        System.out.println("¿'Java' es válida?: " + valida.test("Java")); // false (longitud < 5)
        System.out.println("¿'Programación' es válida?: " + valida.test("Programación")); // true

        System.out.println("\n=== 2. FUNCTION: TRANSFORMACIÓN DE DATOS ===");
        // Transforma un String a su longitud entera
        Function<String, Integer> longitud = String::length; // Method reference equivalente a s -> s.length()
        Function<Integer, String> formatear = n -> "Longitud calculada: " + n;

        // Composición de funciones con 'andThen'
        Function<String, String> procesar = longitud.andThen(formatear);
        System.out.println(procesar.apply("Modern Java"));

        System.out.println("\n=== 3. CONSUMER: ACCIÓN CON EFECTO SECUNDARIO ===");
        // Recibe un valor y no retorna nada (void)
        Consumer<String> imprimirAlerta = mensaje -> System.out.println("🚨 ALERTA: " + mensaje);
        imprimirAlerta.accept("Memoria al 85% de capacidad.");

        System.out.println("\n=== 4. SUPPLIER: PROVEEDOR PEREZOSO DE VALORES ===");
        // No recibe nada, produce un valor solo cuando se llama a .get()
        Supplier<Double> numeroAleatorio = Math::random;
        System.out.println("Valor generado bajo demanda: " + numeroAleatorio.get());

        System.out.println("\n=== 5. DEFINIR TU PROPIA INTERFAZ FUNCIONAL ===");
        OperacionMatematica multiplicar = (a, b) -> a * b;
        OperacionMatematica potencia = (a, b) -> Math.pow(a, b);

        System.out.println("Multiplicación (5 * 4): " + multiplicar.ejecutar(5, 4));
        System.out.println("Potencia (2 ^ 8): " + potencia.ejecutar(2, 8));
    }

    @FunctionalInterface
    interface OperacionMatematica {
        double ejecutar(double a, double b);
    }
}
