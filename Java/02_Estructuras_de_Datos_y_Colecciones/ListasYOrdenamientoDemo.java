import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Lección 01 del Módulo de Colecciones:
 * ArrayList vs LinkedList y Ordenamiento Avanzado con Comparable y Comparator.
 */
public class ListasYOrdenamientoDemo {

    // Modelo de Estudiante para practicar ordenamiento
    static class Estudiante implements Comparable<Estudiante> {
        private final String nombre;
        private final double promedio;
        private final int edad;

        public Estudiante(String nombre, double promedio, int edad) {
            this.nombre = nombre;
            this.promedio = promedio;
            this.edad = edad;
        }

        public String getNombre() { return nombre; }
        public double getPromedio() { return promedio; }
        public int getEdad() { return edad; }

        // Orden Natural (por promedio descendente)
        @Override
        public int compareTo(Estudiante otro) {
            // Compara de mayor a menor promedio
            return Double.compare(otro.promedio, this.promedio);
        }

        @Override
        public String toString() {
            return String.format("%-10s | Promedio: %4.1f | Edad: %2d", nombre, promedio, edad);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 1. ARRAYLIST VS LINKEDLIST ===");
        // ArrayList: Implementado sobre un array contiguo.
        // Excelente localidad de caché (cache locality) y acceso O(1) por get(i).
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("C++");
        arrayList.add("Go");
        System.out.println("Elemento en índice 1 (ArrayList): " + arrayList.get(1));

        // LinkedList: Nodos doblemente enlazados en memoria no contigua.
        // Bueno para inserciones/eliminaciones constantes en los extremos O(1),
        // pero ineficiente para acceso aleatorio O(n) y con sobrecarga de punteros.
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.addFirst("Inicio");
        linkedList.addLast("Fin");
        System.out.println("Primer elemento: " + linkedList.getFirst() + " | Último: " + linkedList.getLast());

        System.out.println("\n=== 2. LISTAS INMUTABLES (Java 9+) ===");
        // List.of crea listas no modificables que rechazan nulos y modificaciones
        List<String> inmutable = List.of("Spring", "Quarkus", "Micronaut");
        try {
            inmutable.add("OtroFramework"); // Lanza UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Confirmado: List.of() produce una lista verdaderamente inmutable.");
        }

        System.out.println("\n=== 3. ORDENAMIENTO: NATURAL (Comparable) ===");
        List<Estudiante> aula = new ArrayList<>();
        aula.add(new Estudiante("Carlos", 8.5, 22));
        aula.add(new Estudiante("Ana", 9.8, 20));
        aula.add(new Estudiante("Beatriz", 7.2, 25));
        aula.add(new Estudiante("David", 8.5, 19));

        // Ordenamiento por el método compareTo definido en Estudiante
        Collections.sort(aula);
        System.out.println("--- Orden Natural (Por Promedio Descendente) ---");
        aula.forEach(System.out::println);

        System.out.println("\n=== 4. ORDENAMIENTO PERSONALIZADO (Comparator Moderno) ===");
        // Ordenar alfabéticamente por Nombre
        aula.sort(Comparator.comparing(Estudiante::getNombre));
        System.out.println("--- Por Nombre Alfabético ---");
        aula.forEach(System.out::println);

        // Ordenar con criterios encadenados: Primero por promedio desc, luego por menor edad
        aula.sort(
            Comparator.comparingDouble(Estudiante::getPromedio).reversed()
                      .thenComparingInt(Estudiante::getEdad)
        );
        System.out.println("\n--- Criterio Compuesto (Promedio DESC, luego Edad ASC) ---");
        aula.forEach(System.out::println);
    }
}
