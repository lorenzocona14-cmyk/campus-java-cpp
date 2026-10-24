import java.util.Stack;

public class Ejercicio1_Pila_CtrlZ {
    public static void main(String[] args) {
        // Creamos la pila para almacenar las acciones (cadenas de texto)
        Stack<String> historialAcciones = new Stack<>();

        System.out.println("--- SIMULADOR DE TEXTO Y DESHACER (Ctrl+Z) ---\n");

        // 1. El usuario realiza acciones (vamos apilando con .push())
        System.out.println("Escribiendo: 'Hola'");
        historialAcciones.push("Escribir 'Hola'");

        System.out.println("Escribiendo: 'Mundo'");
        historialAcciones.push("Escribir 'Mundo'");

        System.out.println("Aplicando formato: 'Negrita'");
        historialAcciones.push("Formato Negrita");

        // 2. Miramos cuál es la última acción sin borrarla (con .peek())
        System.out.println("\n[Espiando] La última acción realizada fue: " + historialAcciones.peek());

        // 3. El usuario presiona Ctrl + Z dos veces (desapilamos con .pop())
        System.out.println("\n--- Presionando Ctrl+Z ---");
        String deshecho1 = historialAcciones.pop(); // Saca y devuelve el último
        System.out.println("Se deshizo: " + deshecho1);

        System.out.println("\n--- Presionando Ctrl+Z otra vez ---");
        String deshecho2 = historialAcciones.pop();
        System.out.println("Se deshizo: " + deshecho2);

        // 4. Verificamos qué queda en el historial
        System.out.println("\nAcción actual en el tope: " + historialAcciones.peek());
        System.out.println("¿Quedan acciones pendientes?: " + (!historialAcciones.isEmpty()));
    }
}
