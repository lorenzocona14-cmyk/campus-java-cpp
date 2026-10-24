/**
 * Lección 02: Tipos de Datos Primitivos, Wrappers y Operadores en Java.
 * 
 * Java es un lenguaje fuertemente tipado (statically typed).
 * Cada variable debe tener un tipo declarado antes de usarse.
 */
public class TiposDeDatosYOperadores {

    public static void main(String[] args) {
        System.out.println("=== 1. LOS 8 TIPOS PRIMITIVOS EN JAVA ===");

        // 1. Enteros
        byte  unByte = 127;                  // 8 bits:  -128 a 127
        short unShort = 32_000;              // 16 bits: -32,768 a 32,767 (los guiones bajos mejoran legibilidad)
        int   unEntero = 2_147_483_647;      // 32 bits: -2^31 a 2^31 - 1 (tipo estándar para enteros)
        long  unLong = 9_223_372_036_854_775_807L; // 64 bits: requiere sufijo 'L' o 'l'

        // 2. Punto Flotante (IEEE 754)
        float  unFloat = 3.1415927f;         // 32 bits: requiere sufijo 'f' o 'F'
        double unDouble = 3.141592653589793; // 64 bits: doble precisión (estándar para decimales)

        // 3. Carácter (Unicode UTF-16)
        char unCaracter = 'A';               // 16 bits: almacena un único carácter o código Unicode
        char letraUnicode = '\u0040';        // '@'

        // 4. Booleano
        boolean esVerdadero = true;          // true o false (NO es un número ni 0 ni 1)

        System.out.printf("byte: %d | short: %d | int: %d | long: %d%n", unByte, unShort, unEntero, unLong);
        System.out.printf("float: %f | double: %f | char: %c (%c) | boolean: %b%n%n", 
                          unFloat, unDouble, unCaracter, letraUnicode, esVerdadero);

        System.out.println("=== 2. OVERFLOW (DESBORDAMIENTO DE ENTEROS) ===");
        // Si sumas 1 al valor máximo de un int, da la vuelta al mínimo negativo:
        int maxInt = Integer.MAX_VALUE;
        int overflowInt = maxInt + 1;
        System.out.println("Integer.MAX_VALUE: " + maxInt);
        System.out.println("Integer.MAX_VALUE + 1: " + overflowInt + " (¡Cuidado con el desbordamiento!)");

        System.out.println("\n=== 3. CASTING (CONVERSIÓN DE TIPOS) ===");
        // Casting Implícito (Ensanchamiento / Widening): seguro, automático de menor a mayor tamaño
        int miEntero = 42;
        double miDouble = miEntero; // int -> double
        System.out.println("Conversión implícita (int -> double): " + miDouble);

        // Casting Explícito (Estrechamiento / Narrowing): puede haber pérdida de datos
        double pi = 3.99999;
        int piTruncado = (int) pi; // Trunca la parte decimal, no redondea
        System.out.println("Conversión explícita (double -> int): " + piTruncado);

        System.out.println("\n=== 4. WRAPPERS (CLASES ENVOLVENTES) Y AUTOBOXING ===");
        // Primitivo -> Objeto (Autoboxing automático)
        Integer enteroObjeto = 100; // Equivalente interno a Integer.valueOf(100)
        // Objeto -> Primitivo (Unboxing automático)
        int primitivoExtraido = enteroObjeto; // Equivalente a enteroObjeto.intValue()
        
        System.out.println("Objeto Integer: " + enteroObjeto);
        System.out.println("Primitivo extraído: " + primitivoExtraido);
        System.out.println("Parsear String a int: " + Integer.parseInt("2026"));

        System.out.println("\n=== 5. INFERENCIA DE TIPO CON 'var' (Java 10+) ===");
        // El compilador infiere el tipo a partir de la expresión del lado derecho
        var mensaje = "Java infiere que esto es String";
        var precio = 19.99; // double
        System.out.println("mensaje (" + mensaje.getClass().getSimpleName() + "): " + mensaje);
        System.out.println("precio: " + precio);

        System.out.println("\n=== 6. TEXT BLOCKS (Java 15+) ===");
        // Cadenas multilínea limpias sin concatenación de \n
        String json = """
            {
                "lenguaje": "Java",
                "version": 25,
                "paradigmas": ["POO", "Funcional"]
            }
            """;
        System.out.println(json);
    }
}
