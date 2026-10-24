/**
 * Lección 03: Estructuras de Control de Flujo en Java.
 * 
 * Incluye las estructuras clásicas y las modernas 'Switch Expressions' de Java 14+.
 */
public class ControlDeFlujo {

    public static void main(String[] args) {
        System.out.println("=== 1. CONDICIONALES: IF / ELSE IF / ELSE ===");
        int puntuacion = 85;

        if (puntuacion >= 90) {
            System.out.println("Calificación: A (Excelente)");
        } else if (puntuacion >= 80) {
            System.out.println("Calificación: B (Muy Bueno)");
        } else if (puntuacion >= 70) {
            System.out.println("Calificación: C (Aprobado)");
        } else {
            System.out.println("Calificación: F (Reprobado)");
        }

        System.out.println("\n=== 2. SWITCH CLÁSICO VS SWITCH EXPRESSIONS (Java 14+) ===");
        String diaSemana = "MIERCOLES";

        // FORMA MODERNA (Switch Expression con flechas -> y sin fallthrough accidental):
        // Puede retornar un valor y no requiere la palabra clave 'break'.
        String tipoDeDia = switch (diaSemana) {
            case "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES" -> "Día laboral";
            case "SABADO", "DOMINGO" -> "Fin de semana";
            default -> "Día inválido";
        };
        System.out.println("El " + diaSemana + " es un: " + tipoDeDia);

        // Switch Expression con bloque de código y 'yield' para retornar un valor:
        int mes = 2;
        int diasEnMes = switch (mes) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> {
                System.out.println("Calculando para Febrero (año no bisiesto)...");
                yield 28; // 'yield' retorna el valor desde un bloque switch
            }
            default -> throw new IllegalArgumentException("Mes inválido: " + mes);
        };
        System.out.println("Días en el mes " + mes + ": " + diasEnMes);

        System.out.println("\n=== 3. BUCLES: FOR, FOR-EACH, WHILE, DO-WHILE ===");

        // For tradicional
        System.out.print("For tradicional: ");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // For-each (bucle mejorado para arrays y colecciones)
        String[] lenguajes = {"Java", "C++", "Python", "Rust"};
        System.out.print("For-each: ");
        for (String lang : lenguajes) {
            System.out.print("[" + lang + "] ");
        }
        System.out.println();

        // While
        int contador = 3;
        System.out.print("While: ");
        while (contador > 0) {
            System.out.print(contador + "... ");
            contador--;
        }
        System.out.println("¡Despegue!");

        // Do-While: Se ejecuta al menos UNA vez, sin importar la condición
        int valor = 10;
        System.out.print("Do-While: ");
        do {
            System.out.print(valor + " (se ejecuta aunque 10 > 5 sea falso para seguir)");
            valor = 0;
        } while (valor > 0);
        System.out.println();

        System.out.println("\n=== 4. CONTROL AVANZADO: BREAK Y CONTINUE CON ETIQUETAS ===");
        // Las etiquetas permiten romper o continuar un bucle externo desde un bucle anidado.
        bucleExterno:
        for (int fila = 1; fila <= 3; fila++) {
            for (int col = 1; col <= 3; col++) {
                if (fila == 2 && col == 2) {
                    System.out.println("Rompiendo bucle externo en fila=" + fila + ", col=" + col);
                    break bucleExterno; // Sale del for de 'fila', no solo de 'col'
                }
                System.out.printf("(%d,%d) ", fila, col);
            }
            System.out.println();
        }
    }
}
