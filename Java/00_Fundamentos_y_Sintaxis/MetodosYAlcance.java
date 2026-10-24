/**
 * Lección 04: Métodos, Alcance y el Mito del Paso de Parámetros.
 * 
 * ¡CONCEPTO FUNDAMENTAL DE ENTREVISTA TÉCNICA!:
 * "Java es ESTRICTAMENTE paso por valor (Pass by Value)".
 */
public class MetodosYAlcance {

    // Clase auxiliar simple para demostrar el paso de referencias
    static class Persona {
        String nombre;

        Persona(String nombre) {
            this.nombre = nombre;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 1. MÉTODOS Y SOBRECARGA (OVERLOADING) ===");
        System.out.println("Suma de 2 enteros: " + sumar(10, 20));
        System.out.println("Suma de 3 enteros: " + sumar(10, 20, 30));
        System.out.println("Suma de doubles:   " + sumar(2.5, 4.5));

        System.out.println("\n=== 2. NÚMERO VARIABLE DE ARGUMENTOS (VARARGS) ===");
        System.out.println("Promedio de 3 notas: " + calcularPromedio(7.0, 8.5, 9.5));
        System.out.println("Promedio de 5 notas: " + calcularPromedio(10.0, 9.0, 8.0, 7.0, 6.0));

        System.out.println("\n=== 3. DEMOSTRACIÓN DEFINITIVA: PASO POR VALOR ===");
        
        // Caso A: Tipos primitivos
        int numeroOriginal = 50;
        System.out.println("Antes de modificarPrimitivo: " + numeroOriginal);
        modificarPrimitivo(numeroOriginal);
        System.out.println("Después de modificarPrimitivo: " + numeroOriginal + " (NO cambia: se copió el número)");

        // Caso B: Modificar el estado interno del objeto
        Persona p1 = new Persona("Ana");
        System.out.println("\nNombre antes de cambiarNombre: " + p1.nombre);
        cambiarNombre(p1, "Beatriz");
        System.out.println("Nombre después de cambiarNombre: " + p1.nombre + " (SÍ cambia: se mutó el objeto en el Heap)");

        // Caso C: Reasignar la referencia recibida
        Persona p2 = new Persona("Carlos");
        System.out.println("\nPersona antes de intentarReasignar: " + p2.nombre);
        intentarReasignar(p2);
        System.out.println("Persona después de intentarReasignar: " + p2.nombre + " (NO cambia: reasignar el puntero local no afecta al llamador)");
    }

    // Sobrecarga 1: dos ints
    public static int sumar(int a, int b) {
        return a + b;
    }

    // Sobrecarga 2: tres ints
    public static int sumar(int a, int b, int c) {
        return a + b + c;
    }

    // Sobrecarga 3: dos doubles
    public static double sumar(double a, double b) {
        return a + b;
    }

    // Varargs: double... es interpretado como un double[] internamente
    public static double calcularPromedio(double... notas) {
        if (notas.length == 0) return 0.0;
        double total = 0.0;
        for (double nota : notas) {
            total += nota;
        }
        return total / notas.length;
    }

    public static void modificarPrimitivo(int n) {
        n = 999; // Modifica únicamente la copia en el frame del Stack de este método
    }

    public static void cambiarNombre(Persona persona, String nuevoNombre) {
        persona.nombre = nuevoNombre; // Sigue la referencia en memoria y muta el campo
    }

    public static void intentarReasignar(Persona persona) {
        // 'persona' es una copia de la dirección de memoria.
        // Si le asignamos un nuevo objeto, sólo reasignamos la variable local.
        persona = new Persona("Daniel"); 
        System.out.println("  -> Dentro del método, 'persona' ahora se llama: " + persona.nombre);
    }
}
