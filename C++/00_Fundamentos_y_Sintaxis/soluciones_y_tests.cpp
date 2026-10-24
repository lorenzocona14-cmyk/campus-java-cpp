/**
 * Soluciones de Referencia y Suite de Tests Automatizados: Módulo 00 de C++.
 * 
 * Compilar y ejecutar:
 *   ..\compilar_cpp.bat 00_Fundamentos_y_Sintaxis\soluciones_y_tests.cpp
 */

#include <iostream>
#include <string>
#include <cassert>
#include <cmath>

// ==========================================
// SOLUCIÓN RETO 1: Swap por Referencia (&)
// ==========================================
void intercambiar(int& a, int& b) {
    int temp = a;
    a = b;
    b = temp;
}

// ==========================================
// SOLUCIÓN RETO 2: Inversor In-Place
// ==========================================
void invertirCadena(std::string& texto) {
    if (texto.empty()) return;
    size_t i = 0;
    size_t j = texto.length() - 1;

    while (i < j) {
        char temp = texto[i];
        texto[i] = texto[j];
        texto[j] = temp;
        i++;
        j--;
    }
}

// ==========================================
// SOLUCIÓN RETO 3: Cálculos Geométricos
// ==========================================
namespace Geometria {
    constexpr double PI = 3.141592653589793;

    double areaCirculo(double radio) {
        return PI * radio * radio;
    }

    double perimetroCirculo(double radio) {
        return 2.0 * PI * radio;
    }

    double hipotenusa(double catetoA, double catetoB) {
        return std::sqrt(catetoA * catetoA + catetoB * catetoB);
    }
}

// ==========================================
// SUITE DE TESTS AUTOMATIZADOS
// ==========================================
int main() {
    std::cout << "==================================================" << '\n';
    std::cout << "   TESTS AUTOMATIZADOS: MÓDULO 00 (C++)          " << '\n';
    std::cout << "==================================================" << '\n';

    int testsPasados = 0;
    int totalTests = 0;

    // Test 1: Intercambio por Referencia
    totalTests++;
    int x = 100, y = 200;
    intercambiar(x, y);
    if (x == 200 && y == 100) {
        std::cout << "  [OK] Test 1: intercambiar(a, b) modifico exitosamente la memoria original." << '\n';
        testsPasados++;
    } else {
        std::cerr << "  [FAIL] Test 1: Fallo al intercambiar variables." << '\n';
    }

    // Test 2: Inversión In-Place
    totalTests++;
    std::string palabra = "Estructuras";
    invertirCadena(palabra);
    if (palabra == "sarutcurtsE") {
        std::cout << "  [OK] Test 2: invertirCadena() in-place funciona al 100%." << '\n';
        testsPasados++;
    } else {
        std::cerr << "  [FAIL] Test 2: Fallo al invertir cadena. Obtenido: " << palabra << '\n';
    }

    // Test 3: Geometría
    totalTests++;
    double hipo = Geometria::hipotenusa(3.0, 4.0); // Debe ser 5.0 (Triángulo 3-4-5)
    double areaC = Geometria::areaCirculo(2.0);
    if (std::abs(hipo - 5.0) < 0.0001 && std::abs(areaC - (Geometria::PI * 4.0)) < 0.0001) {
        std::cout << "  [OK] Test 3: Calculos de Geometria con precision verificados." << '\n';
        testsPasados++;
    } else {
        std::cerr << "  [FAIL] Test 3: Fallo en cálculos geométricos." << '\n';
    }

    std::cout << "\n--------------------------------------------------" << '\n';
    std::cout << "RESULTADO FINAL: " << testsPasados << " / " << totalTests 
              << " TESTS PASADOS (" << (testsPasados * 100 / totalTests) << "%)" << '\n';
    std::cout << "--------------------------------------------------" << '\n';

    return (testsPasados == totalTests) ? 0 : 1;
}
