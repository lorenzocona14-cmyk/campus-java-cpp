/**
 * Lección 01: El primer paso en C++.
 * 
 * En C++, a diferencia de Java, las funciones NO necesitan estar dentro de una clase.
 * El punto de entrada es la función global 'main'.
 */

#include <iostream> // Biblioteca estándar de entrada y salida (input/output stream)

/**
 * 'int main' es el punto de entrada que invoca el Sistema Operativo.
 * 
 * Parámetros opcionales:
 * - int argc: Cantidad de argumentos recibidos por consola (Argument Count).
 * - char* argv[]: Arreglo de cadenas de caracteres estilo C (Argument Vector).
 * 
 * Retorna:
 * - int: Código de salida al S.O. (0 significa éxito, cualquier otro número indica error).
 */
int main(int argc, char* argv[]) {
    // std::cout es el flujo de salida estándar (consola).
    // << es el operador de inserción en el flujo (stream insertion operator).
    std::cout << "==================================================" << '\n';
    std::cout << "  ¡Bienvenido al Reto de 30 Días de Programación!" << '\n';
    std::cout << "  Estás ejecutando C++ compilado a CÓDIGO NATIVO." << '\n';
    std::cout << "==================================================" << '\n';

    // Imprimir argumentos si se pasaron por línea de comandos
    if (argc > 1) {
        std::cout << "Argumentos recibidos por consola (" << argc << "):" << '\n';
        for (int i = 0; i < argc; ++i) {
            std::cout << "  argv[" << i << "]: " << argv[i] << '\n';
        }
    } else {
        std::cout << "Programa ejecutado como: " << argv[0] << '\n';
        std::cout << "(Tip: Ejecuta con argumentos: .\\01_hola_mundo.exe arg1 arg2)" << '\n';
    }

    // En C++ moderno, si no escribes 'return 0;', el compilador lo asume automáticamente al final de main.
    return 0;
}
