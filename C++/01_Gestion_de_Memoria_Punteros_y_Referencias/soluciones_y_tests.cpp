/**
 * Soluciones de Referencia y Suite de Tests Automatizados: Módulo 01 de C++ (Memoria y Punteros).
 * 
 * Compilar y ejecutar:
 *   ..\compilar_cpp.bat 01_Gestion_de_Memoria_Punteros_y_Referencias\soluciones_y_tests.cpp
 */

#include <iostream>

// ==========================================
// SOLUCIÓN RETO 1: Redimensionador Dinámico
// ==========================================
int* duplicarCapacidad(int* arregloViejo, int tamActual, int nuevoTam) {
    int* nuevoArreglo = new int[nuevoTam];

    // Copiar elementos usando punteros
    for (int i = 0; i < tamActual; ++i) {
        nuevoArreglo[i] = arregloViejo[i];
    }
    // Inicializar el resto en cero
    for (int i = tamActual; i < nuevoTam; ++i) {
        nuevoArreglo[i] = 0;
    }

    // Liberar la memoria antigua obligatoriamente con delete[]
    delete[] arregloViejo;

    return nuevoArreglo;
}

// ==========================================
// SOLUCIÓN RETO 2: Inversión con Dos Punteros
// ==========================================
void invertirArreglo(int* inicio, int* fin) {
    // 'fin' apunta al último elemento válido
    while (inicio < fin) {
        int temp = *inicio;
        *inicio = *fin;
        *fin = temp;

        inicio++; // Avanza a la derecha
        fin--;    // Retrocede a la izquierda
    }
}

// ==========================================
// SOLUCIÓN RETO 3: Asignación Segura en Heap
// ==========================================
// Retorna memoria en Heap, NUNCA la dirección de una variable local del Stack
int* crearEnteroSeguro(int valorInicial) {
    return new int(valorInicial);
}

// ==========================================
// SUITE DE TESTS AUTOMATIZADOS
// ==========================================
int main() {
    std::cout << "==================================================" << '\n';
    std::cout << "   TESTS AUTOMATIZADOS: MÓDULO 01 (MEMORIA C++)  " << '\n';
    std::cout << "==================================================" << '\n';

    int testsPasados = 0;
    int totalTests = 0;

    // Test 1: Redimensionamiento dinámico
    totalTests++;
    int* original = new int[2]{10, 20};
    int* ampliado = duplicarCapacidad(original, 2, 4);

    if (ampliado[0] == 10 && ampliado[1] == 20 && ampliado[2] == 0) {
        std::cout << "  [OK] Test 1: duplicarCapacidad() copió y reasignó memoria correctamente." << '\n';
        testsPasados++;
    } else {
        std::cerr << "  [FAIL] Test 1: Error en reasignación de memoria." << '\n';
    }
    delete[] ampliado; // Limpieza

    // Test 2: Inversión con dos punteros
    totalTests++;
    int datos[5] = {1, 2, 3, 4, 5};
    invertirArreglo(datos, datos + 4); // inicio=&datos[0], fin=&datos[4]

    if (datos[0] == 5 && datos[1] == 4 && datos[2] == 3 && datos[3] == 2 && datos[4] == 1) {
        std::cout << "  [OK] Test 2: invertirArreglo() con dos punteros móvil verificado." << '\n';
        testsPasados++;
    } else {
        std::cerr << "  [FAIL] Test 2: Fallo al invertir arreglo." << '\n';
    }

    // Test 3: Asignación segura en Heap
    totalTests++;
    int* seguro = crearEnteroSeguro(777);
    if (seguro != nullptr && *seguro == 777) {
        std::cout << "  [OK] Test 3: Asignación persistente en Heap comprobada sin fallos de ámbito." << '\n';
        testsPasados++;
    } else {
        std::cerr << "  [FAIL] Test 3: Error en memoria segura." << '\n';
    }
    delete seguro; // Limpieza

    std::cout << "\n--------------------------------------------------" << '\n';
    std::cout << "RESULTADO FINAL: " << testsPasados << " / " << totalTests 
              << " TESTS PASADOS (" << (testsPasados * 100 / totalTests) << "%)" << '\n';
    std::cout << "--------------------------------------------------" << '\n';

    return (testsPasados == totalTests) ? 0 : 1;
}
