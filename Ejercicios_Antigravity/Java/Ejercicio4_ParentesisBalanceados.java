import java.util.Stack;

public class Ejercicio4_ParentesisBalanceados {

    /**
     * Verifica si los signos de apertura y cierre (), [], {} están correctamente anidados y balanceados.
     */
    public static boolean estaBalanceado(String expresion) {
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char caracter = expresion.charAt(i);

            // Si es un signo de apertura, lo apilamos (esperamos su pareja de cierre más adelante)
            if (caracter == '(' || caracter == '[' || caracter == '{') {
                pila.push(caracter);
            } 
            // Si es un signo de cierre, verificamos con el tope de la pila
            else if (caracter == ')' || caracter == ']' || caracter == '}') {
                // Si la pila está vacía, hay un cierre sin apertura -> ¡Desbalanceado!
                if (pila.isEmpty()) {
                    return false;
                }

                char apertura = pila.pop();

                // Verificamos que coincida el tipo de apertura con el de cierre
                if (caracter == ')' && apertura != '(') return false;
                if (caracter == ']' && apertura != '[') return false;
                if (caracter == '}' && apertura != '{') return false;
            }
        }

        // Si la pila quedó vacía, significa que todos los signos encontraron su pareja
        return pila.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("--- VERIFICADOR DE PARENTESIS BALANCEADOS ---\n");

        String formulaValida = "{ [ 10 + 5 ] * ( 4 - 2 ) }";
        String formulaInvalida = "{ [ 10 + 5 ) ]";

        System.out.println("Formula 1: " + formulaValida);
        System.out.println("¿Esta balanceada?: " + (estaBalanceado(formulaValida) ? "SI (Correcta)" : "NO (Error de sintaxis)"));

        System.out.println("\nFormula 2: " + formulaInvalida);
        System.out.println("¿Esta balanceada?: " + (estaBalanceado(formulaInvalida) ? "SI (Correcta)" : "NO (Error de sintaxis)"));
    }
}
