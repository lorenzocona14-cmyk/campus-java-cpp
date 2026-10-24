/**
 * Lección 03 del Módulo STL:
 * Algoritmos Estándar (<algorithm>) y Expresiones Lambda en C++.
 */

#include <iostream>
#include <vector>
#include <algorithm> // std::sort, std::find_if, std::transform, std::count_if
#include <numeric>   // std::accumulate

struct Producto {
    std::string nombre;
    double precio;
    bool enStock;
};

int main() {
    std::vector<Producto> inventario = {
        {"Laptop Pro", 2500.0, true},
        {"Cable HDMI", 15.0, true},
        {"Monitor 4K", 600.0, false},
        {"Mouse Gamer", 85.0, true},
        {"Auriculares", 150.0, true}
    };

    std::cout << "=== 1. std::sort CON EXPRESIÓN LAMBDA ===" << '\n';
    // Ordenar de menor a mayor precio usando un lambda de comparación
    std::sort(inventario.begin(), inventario.end(), [](const Producto& a, const Producto& b) {
        return a.precio < b.precio;
    });

    std::cout << "Productos ordenados por precio ASC:" << '\n';
    for (const auto& p : inventario) {
        std::cout << "  - " << p.nombre << ": $" << p.precio << '\n';
    }

    std::cout << "\n=== 2. std::find_if: BÚSQUEDA CONDICIONAL ===" << '\n';
    // Buscar el primer producto agotado (enStock == false)
    auto itAgotado = std::find_if(inventario.begin(), inventario.end(), [](const Producto& p) {
        return !p.enStock;
    });

    if (itAgotado != inventario.end()) {
        std::cout << "Primer producto sin stock: " << itAgotado->nombre << '\n';
    }

    std::cout << "\n=== 3. CAPTURA EXTERNA EN LAMBDAS ([&] y [=]) ===" << '\n';
    double presupuestoMaximo = 200.0;
    // Captura 'presupuestoMaximo' por valor [=] o explícitamente [presupuestoMaximo]
    int accesibles = std::count_if(inventario.begin(), inventario.end(), [presupuestoMaximo](const Producto& p) {
        return p.precio <= presupuestoMaximo;
    });
    std::cout << "Cantidad de productos por debajo de $" << presupuestoMaximo << ": " << accesibles << '\n';

    std::cout << "\n=== 4. std::accumulate: REDUCCIÓN Y TOTALES ===" << '\n';
    // Suma el precio de todos los productos en stock
    double sumaTotalEnStock = std::accumulate(
        inventario.begin(), inventario.end(), 0.0,
        [](double acumulado, const Producto& p) {
            return p.enStock ? (acumulado + p.precio) : acumulado;
        }
    );
    std::cout << "Valor total de productos disponibles en stock: $" << sumaTotalEnStock << '\n';

    return 0;
}
