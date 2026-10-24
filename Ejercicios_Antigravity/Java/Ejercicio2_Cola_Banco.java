import java.util.LinkedList;
import java.util.Queue;

public class Ejercicio2_Cola_Banco {
    public static void main(String[] args) {
        // En Java, Queue es una interfaz y comúnmente se usa LinkedList como implementación
        Queue<String> filaBanco = new LinkedList<>();

        System.out.println("--- SISTEMA DE ATENCION EN EL BANCO ---\n");

        // 1. Llegan los clientes a la fila (encolar con .offer() o .add())
        System.out.println("Llega María a la fila.");
        filaBanco.offer("María");

        System.out.println("Llega Carlos a la fila.");
        filaBanco.offer("Carlos");

        System.out.println("Llega Sofía a la fila.");
        filaBanco.offer("Sofía");

        // 2. Miramos quién es el primero en la fila sin atenderlo todavía (.peek())
        System.out.println("\n[En la cabecera] El próximo a ser atendido es: " + filaBanco.peek());

        // 3. El cajero empieza a atender (desencolar con .poll())
        System.out.println("\n--- El cajero llama al siguiente ---");
        String atendido1 = filaBanco.poll(); // Extrae y devuelve el primero de la fila
        System.out.println("Atendiendo a: " + atendido1);

        System.out.println("\n--- El cajero llama al siguiente ---");
        String atendido2 = filaBanco.poll();
        System.out.println("Atendiendo a: " + atendido2);

        // 4. Verificamos quién queda
        System.out.println("\nQuedan en la fila: " + filaBanco);
        System.out.println("Próximo en espera: " + filaBanco.peek());
    }
}
