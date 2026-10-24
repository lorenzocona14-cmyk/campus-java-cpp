import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Lección 02 del Módulo de Colecciones:
 * Sets (Conjuntos) y la Demostración del Desastre de Romper equals y hashCode.
 */
public class ConjuntosYContratoHashDemo {

    // Clase CON BUG: Sobrescribe equals pero OLVIDA sobrescribir hashCode
    static class ClienteBug {
        private final String dni;

        public ClienteBug(String dni) {
            this.dni = dni;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ClienteBug that = (ClienteBug) o;
            return Objects.equals(dni, that.dni);
        }

        // NO SE SOBRESCRIBE hashCode(): Hereda el de Object (basado en la dirección de memoria)
    }

    // Clase CORRECTA: Respeta el contrato
    static class ClienteCorrecto {
        private final String dni;

        public ClienteCorrecto(String dni) {
            this.dni = dni;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ClienteCorrecto that = (ClienteCorrecto) o;
            return Objects.equals(dni, that.dni);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dni); // Genera el mismo hash para el mismo DNI
        }

        @Override
        public String toString() {
            return "Cliente(" + dni + ")";
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 1. DEMOSTRACIÓN DEL DESASTRE DE EQUALS SIN HASHCODE ===");
        
        Set<ClienteBug> setBug = new HashSet<>();
        ClienteBug bug1 = new ClienteBug("12345678");
        ClienteBug bug2 = new ClienteBug("12345678");

        System.out.println("¿bug1.equals(bug2)?: " + bug1.equals(bug2)); // TRUE
        setBug.add(bug1);
        setBug.add(bug2);

        // ¡ATENCIÓN! El tamaño es 2 aunque los objetos sean idénticos conceptualmente.
        // Al tener diferente hashCode(), fueron a parar a "buckets" distintos en la tabla hash.
        System.out.println("Tamaño de setBug (¡INCORRECTO! Debería ser 1): " + setBug.size());

        System.out.println("\n=== 2. COMPORTAMIENTO CORRECTO ===");
        Set<ClienteCorrecto> setCorrecto = new HashSet<>();
        ClienteCorrecto c1 = new ClienteCorrecto("12345678");
        ClienteCorrecto c2 = new ClienteCorrecto("12345678");

        setCorrecto.add(c1);
        setCorrecto.add(c2);
        System.out.println("Tamaño de setCorrecto (CORRECTO, sin duplicados): " + setCorrecto.size());

        System.out.println("\n=== 3. HASHSET VS LINKEDHASHSET VS TREESET ===");

        // HashSet: Rápido O(1), pero NO garantiza ningún orden
        Set<String> hashSet = new HashSet<>();
        hashSet.addAll(Set.of("Z", "A", "M", "B"));
        System.out.println("HashSet (orden impredecible): " + hashSet);

        // LinkedHashSet: O(1), preserva el orden de inserción
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Z");
        linkedHashSet.add("A");
        linkedHashSet.add("M");
        linkedHashSet.add("B");
        System.out.println("LinkedHashSet (orden de inserción): " + linkedHashSet);

        // TreeSet: O(log n), mantiene los elementos ordenados naturalmente
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Z");
        treeSet.add("A");
        treeSet.add("M");
        treeSet.add("B");
        System.out.println("TreeSet (orden alfabético / natural): " + treeSet);
    }
}
