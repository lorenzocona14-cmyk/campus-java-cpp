/**
 * Lección 01 del Módulo de Memoria:
 * Anatomía de Punteros, Direcciones de Memoria y Desreferenciación.
 */

#include <iostream>

int main() {
    std::cout << "=== 1. DIRECCIONES DE MEMORIA Y EL OPERADOR & ===" << '\n';
    int variable = 42;

    // &variable obtiene la dirección física en memoria RAM donde vive 'variable'
    std::cout << "Valor de 'variable':       " << variable << '\n';
    std::cout << "Dirección de 'variable' (&): " << &variable << " (en hexadecimal)" << '\n';
    std::cout << "Tamaño de 'variable':      " << sizeof(variable) << " bytes" << '\n';

    std::cout << "\n=== 2. DECLARACIÓN DE PUNTEROS Y DESREFERENCIACIÓN (*) ===" << '\n';
    // 'ptr' es una variable que ALMACENA la dirección de memoria de un entero
    int* ptr = &variable;

    std::cout << "Valor almacenado en ptr (dirección): " << ptr << '\n';
    std::cout << "Dirección propia del puntero (&ptr):  " << &ptr << '\n';
    std::cout << "Tamaño del puntero en esta arquitectura: " << sizeof(ptr) << " bytes" << '\n';

    // Desreferenciar (*ptr): Accede al contenido de la casilla de memoria a la que apunta
    std::cout << "Valor al que apunta (*ptr):           " << *ptr << '\n';

    // Modificar la variable original A TRAVÉS del puntero
    *ptr = 999;
    std::cout << "Tras '*ptr = 999', el valor de 'variable' ahora es: " << variable << '\n';

    std::cout << "\n=== 3. EL PUNTERO NULO MODERNO (nullptr) ===" << '\n';
    // En C++ moderno (C++11), NUNCA uses NULL ni 0. Usa siempre 'nullptr'.
    // 'nullptr' tiene tipo propio 'std::nullptr_t' y evita ambigüedades con números enteros.
    int* punteroNulo = nullptr;

    if (punteroNulo == nullptr) {
        std::cout << "punteroNulo apunta a tierra (dirección 0x0). ¡Es seguro comprobarlo antes de desreferenciar!" << '\n';
    }

    // ⚠️ Desreferenciar un nullptr causa un CRASH instantáneo (Segmentation Fault):
    // *punteroNulo = 10; // ¡NUNCA HAGAS ESTO!

    return 0;
}
