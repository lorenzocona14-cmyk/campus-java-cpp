/**
 * Lección 02 del Módulo de Memoria:
 * Arrays como Punteros Degradados y Aritmética de Punteros.
 */

#include <iostream>

int main() {
    std::cout << "=== 1. DEGRADACIÓN DE ARRAYS A PUNTEROS ===" << '\n';
    int numeros[5] = {10, 20, 30, 40, 50};

    // El nombre del array 'numeros' se degrada automáticamente a un puntero al primer elemento (&numeros[0])
    int* ptr = numeros;

    std::cout << "Dirección de numeros[0]: " << &numeros[0] << '\n';
    std::cout << "Dirección de 'numeros':    " << numeros << " (idéntica)" << '\n';
    std::cout << "Dirección en 'ptr':        " << ptr << '\n';

    std::cout << "\n=== 2. ARITMÉTICA DE PUNTEROS ===" << '\n';
    // Cuando sumas 1 a un puntero, NO le sumas 1 byte.
    // Le sumas 1 * sizeof(TipoDeDato). Si es un int (4 bytes), avanza 4 bytes en RAM.
    for (int i = 0; i < 5; ++i) {
        std::cout << "Elemento [" << i << "]: "
                  << "Dirección: " << (ptr + i) 
                  << " | Valor con *(ptr + " << i << "): " << *(ptr + i)
                  << " | Equivalente arr[" << i << "]: " << numeros[i] << '\n';
    }

    std::cout << "\n=== 3. RECORRIDO DE ARRAY USANDO UN PUNTERO MÓVIL ===" << '\n';
    int* iterador = numeros; // Apunta al inicio
    int* fin = numeros + 5;  // Apunta justo una posición después del último elemento (Past-the-end)

    std::cout << "Valores iterados por puntero: ";
    while (iterador < fin) {
        std::cout << *iterador << " ";
        iterador++; // Avanza al siguiente entero
    }
    std::cout << '\n';

    std::cout << "\n=== 4. RESTA DE PUNTEROS ===" << '\n';
    // La resta de dos punteros devuelve la cantidad de ELEMENTOS entre ellos, no bytes.
    std::ptrdiff_t distancia = fin - numeros;
    std::cout << "Cantidad de elementos entre fin e inicio: " << distancia << '\n';

    return 0;
}
