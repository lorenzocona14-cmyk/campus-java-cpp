/**
 * Lección 02: Tipos Fundamentales, Tamaños de Memoria, Constantes y Casting en C++.
 */

#include <iostream>
#include <cstdint> // Tipos de ancho fijo: int32_t, int64_t, uint8_t, etc.

// 'constexpr' evalúa la función en TIEMPO DE COMPILACIÓN si los argumentos son constantes.
// Cero coste de CPU en tiempo de ejecución.
constexpr double calcularAreaCirculo(double radio) {
    return 3.141592653589793 * radio * radio;
}

int main() {
    std::cout << "=== 1. TAMAÑOS EN MEMORIA (OPERADOR sizeof) ===" << '\n';
    std::cout << "sizeof(char):      " << sizeof(char)      << " byte  (8 bits)" << '\n';
    std::cout << "sizeof(short):     " << sizeof(short)     << " bytes (16 bits)" << '\n';
    std::cout << "sizeof(int):       " << sizeof(int)       << " bytes (32 bits típicamente)" << '\n';
    std::cout << "sizeof(long long): " << sizeof(long long) << " bytes (64 bits)" << '\n';
    std::cout << "sizeof(float):     " << sizeof(float)     << " bytes (32 bits IEEE 754)" << '\n';
    std::cout << "sizeof(double):    " << sizeof(double)    << " bytes (64 bits)" << '\n';
    std::cout << "sizeof(bool):      " << sizeof(bool)      << " byte  (true=1, false=0)" << '\n';

    std::cout << "\n=== 2. ENTEROS CON SIGNO VS SIN SIGNO ===" << '\n';
    int conSigno = -42;
    unsigned int sinSigno = 42U; // Solo valores positivos (0 a 4,294,967,295)
    std::cout << "conSigno: " << conSigno << " | sinSigno: " << sinSigno << '\n';

    // Tipos de ancho fijo garantizados por el estándar:
    std::int32_t entero32 = 100000;
    std::uint64_t enteroSinSigno64 = 9999999999ULL;
    std::cout << "int32_t: " << entero32 << " | uint64_t: " << enteroSinSigno64 << '\n';

    std::cout << "\n=== 3. INFERENCIA CON 'auto' (C++11) ===" << '\n';
    auto variableDeducida = 3.1416; // El compilador deduce double
    auto nombre = "Lenguaje C++";   // Deduce const char*
    std::cout << "Deducido: " << variableDeducida << " | " << nombre << '\n';

    std::cout << "\n=== 4. 'const' VS 'constexpr' ===" << '\n';
    const int valorTiempoEjecucion = conSigno * 2; // Inmutable una vez calculado en tiempo de ejecución
    constexpr double AREA_PI = calcularAreaCirculo(10.0); // ¡Calculado antes de que el programa arranque!
    std::cout << "const en runtime: " << valorTiempoEjecucion << '\n';
    std::cout << "constexpr en compile-time (radio=10): " << AREA_PI << '\n';

    std::cout << "\n=== 5. CASTING SEGURO CON static_cast ===" << '\n';
    // ❌ En C clásico se usaba: int x = (int)3.99; (Peligroso, puede saltarse comprobaciones de tipo)
    // ✅ En C++ moderno usamos static_cast explícito:
    double decimal = 9.85;
    int enteroTruncado = static_cast<int>(decimal);
    std::cout << "Decimal original: " << decimal << " -> static_cast<int>: " << enteroTruncado << '\n';

    return 0;
}
