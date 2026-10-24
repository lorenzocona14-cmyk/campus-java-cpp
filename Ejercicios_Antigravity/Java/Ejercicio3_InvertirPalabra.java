import java.util.Stack;

public class Ejercicio3_InvertirPalabra {
    public static void main(String[] args) {
        String palabraOriginal = "ANTIGRAVITY";
        System.out.println("Palabra original: " + palabraOriginal);

        // Creamos una pila de caracteres (Character)
        Stack<Character> pilaLetras = new Stack<>();

        // Paso 1: Apilamos cada letra de la palabra (de izquierda a derecha)
        for (int i = 0; i < palabraOriginal.length(); i++) {
            pilaLetras.push(palabraOriginal.charAt(i));
        }

        // Paso 2: Desapilamos una por una. Como la pila es LIFO, saldrán al revés
        StringBuilder palabraInvertida = new StringBuilder();
        while (!pilaLetras.isEmpty()) {
            palabraInvertida.append(pilaLetras.pop());
        }

        System.out.println("Palabra invertida con Pila: " + palabraInvertida.toString());
    }
}
