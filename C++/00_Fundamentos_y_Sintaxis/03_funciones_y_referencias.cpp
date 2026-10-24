/**
 * Lección 03: Funciones, Parámetros por Defecto y Referencias en C++.
 * 
 * ¡EL CONCEPTO CLAVE!:
 * En C++, una referencia ('&') es un alias directo a la memoria original.
 */

#include <iostream>
#include <string>

// 1. Paso por Valor: Copia el argumento. Modificarlo aquí NO afecta al llamador.
void duplicarPorValor(int n) {
    n *= 2;
    std::cout << "  [Dentro de función]: " << n << '\n';
}

// 2. Paso por Referencia (&): 'n' es un ALIAS de la variable original.
// No se copia nada. Modificar 'n' modifica directamente la memoria del llamador.
void duplicarPorReferencia(int& n) {
    n *= 2;
}

// 3. Paso por Referencia Constante (const &):
// LA FORMA MÁS USADA EN C++ para objetos grandes (strings, vectores, clases).
// - No duplica la memoria en la pila (0 costo de copia).
// - El 'const' impide que la función modifique accidentalmente el dato original.
void imprimirReporte(const std::string& mensaje, int codigoEstado = 200) {
    // mensaje += "!"; // Error de compilación si descomentas esto por ser 'const'
    std::cout << "[Reporte " << codigoEstado << "]: " << mensaje << '\n';
}

// 4. Sobrecarga de Funciones (Overloading)
double calcularArea(double lado) {
    return lado * lado; // Cuadrado
}

double calcularArea(double base, double altura) {
    return base * altura; // Rectángulo
}

int main() {
    std::cout << "=== 1. PASO POR VALOR VS REFERENCIA ===" << '\n';
    int valor = 10;

    std::cout << "Valor inicial: " << valor << '\n';
    duplicarPorValor(valor);
    std::cout << "Tras duplicarPorValor: " << valor << " (NO cambió, se modificó una copia)" << '\n';

    duplicarPorReferencia(valor);
    std::cout << "Tras duplicarPorReferencia: " << valor << " (SÍ cambió: ¡acceso directo a la memoria!)" << '\n';

    std::cout << "\n=== 2. REFERENCIA CONSTANTE Y PARÁMETROS POR DEFECTO ===" << '\n';
    std::string textoLargo = "Servidor Web iniciado en puerto 8080 correctamente.";
    
    imprimirReporte(textoLargo);      // Usa codigoEstado = 200 por defecto
    imprimirReporte(textoLargo, 404);  // Sobrescribe el valor por defecto

    std::cout << "\n=== 3. SOBRECARGA DE FUNCIONES ===" << '\n';
    std::cout << "Área de cuadrado (lado 5): " << calcularArea(5.0) << '\n';
    std::cout << "Área de rectángulo (4 x 6): " << calcularArea(4.0, 6.0) << '\n';

    return 0;
}
