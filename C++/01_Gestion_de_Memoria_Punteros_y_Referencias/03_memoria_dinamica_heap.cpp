/**
 * Lección 03 del Módulo de Memoria:
 * Memoria Dinámica en el Heap: 'new', 'delete', 'new[]' y 'delete[]'.
 */

#include <iostream>

int main() {
    std::cout << "=== 1. ASIGNACIÓN DINÁMICA DE UN ELEMENTO EN EL HEAP ===" << '\n';

    // 'new' reserva memoria en el Heap (Montículo) y retorna la dirección
    int* enteroEnHeap = new int(42);

    std::cout << "Dirección en el Heap: " << enteroEnHeap << '\n';
    std::cout << "Valor almacenado:     " << *enteroEnHeap << '\n';

    // LIBERACIÓN OBLIGATORIA: Si no llamas a 'delete', se produce un MEMORY LEAK
    delete enteroEnHeap;

    // BUENA PRÁCTICA CRÍTICA: Convertir en nullptr para evitar 'Dangling Pointer'
    enteroEnHeap = nullptr;
    std::cout << "Memoria liberada y puntero restablecido a nullptr." << '\n';

    std::cout << "\n=== 2. ASIGNACIÓN DINÁMICA DE ARRAYS (new[] y delete[]) ===" << '\n';
    // El tamaño puede ser una variable definida en tiempo de ejecución (no posible en Stack estático)
    int cantidad = 4;
    int* arregloDinamico = new int[cantidad]{100, 200, 300, 400};

    std::cout << "Elementos del arreglo en Heap: ";
    for (int i = 0; i < cantidad; ++i) {
        std::cout << arregloDinamico[i] << " ";
    }
    std::cout << '\n';

    // ⚠️ REGLA DE ORO DE C++:
    // Si reservaste con 'new[]', DEBES liberar con 'delete[]'.
    // Usar 'delete' simple en un array causa Comportamiento Indefinido (Undefined Behavior).
    delete[] arregloDinamico;
    arregloDinamico = nullptr;
    std::cout << "Arreglo liberado con delete[] exitosamente." << '\n';

    std::cout << "\n=== 3. COMPARATIVA DE RENDIMIENTO: STACK VS HEAP ===" << '\n';
    std::cout << "1. Stack: Ultra rápido (1 instrucción de CPU). Tamaño fijo. Muere al salir de { }." << '\n';
    std::cout << "2. Heap:  Más lento (busca bloque libre en SO). Tamaño enorme. Vive hasta que tú lo liberes." << '\n';

    return 0;
}
