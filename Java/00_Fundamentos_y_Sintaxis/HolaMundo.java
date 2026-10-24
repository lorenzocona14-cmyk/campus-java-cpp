/**
 * Lección 01: El primer paso en Java.
 * 
 * En Java, toda línea ejecutable DEBE pertenecer a una clase.
 * El nombre del archivo debe coincidir exactamente con el nombre de la clase pública.
 */
public class HolaMundo {

    /**
     * El método 'main' es el punto de entrada que la máquina virtual (JVM) busca.
     * 
     * - public: Visible por la JVM fuera del paquete.
     * - static: Pertenece a la clase, no a una instancia (se llama sin hacer 'new HolaMundo()').
     * - void: No devuelve ningún valor.
     * - String[] args: Argumentos opcionales pasados desde la consola.
     */
    public static void main(String[] args) {
        // System.out es el flujo estándar de salida (consola).
        // println imprime la cadena y añade un salto de línea (\n).
        System.out.println("==================================================");
        System.out.println("  ¡Bienvenido al Reto de 30 Días de Programación!");
        System.out.println("  Estás ejecutando Java en la JVM.");
        System.out.println("==================================================");

        // Imprimir argumentos de consola si se proporcionaron
        if (args.length > 0) {
            System.out.println("Argumentos recibidos:");
            for (int i = 0; i < args.length; i++) {
                System.out.println("  [" + i + "]: " + args[i]);
            }
        } else {
            System.out.println("No se pasaron argumentos de consola. (Tip: Prueba 'java HolaMundo.java arg1 arg2')");
        }
    }
}
