/**
 * Lección 02 del Módulo STL:
 * Contenedores Dinámicos: std::vector y std::unordered_map de Alto Rendimiento.
 */

#include <iostream>
#include <vector>
#include <unordered_map>
#include <string>

struct Persona {
    std::string nombre;
    int edad;

    Persona(std::string n, int e) : nombre(std::move(n)), edad(e) {
        std::cout << "  [Persona construida]: " << nombre << '\n';
    }
};

int main() {
    std::cout << "=== 1. std::vector: SIZE VS CAPACITY Y RESERVE ===" << '\n';
    std::vector<int> numeros;

    std::cout << "Capacidad inicial: " << numeros.capacity() << '\n';

    // reserve() pre-reserva memoria contigua en el Heap para evitar realocaciones continuas
    numeros.reserve(10);
    std::cout << "Capacidad tras reserve(10): " << numeros.capacity() 
              << " | Tamaño: " << numeros.size() << '\n';

    for (int i = 1; i <= 5; ++i) numeros.push_back(i * 10);
    std::cout << "Tamaño tras agregar 5 elementos: " << numeros.size() 
              << " | Capacidad: " << numeros.capacity() << '\n';

    std::cout << "\n=== 2. emplace_back VS push_back ===" << '\n';
    std::vector<Persona> equipo;
    equipo.reserve(2);

    // emplace_back construye el objeto DIRECTAMENTE dentro de la memoria del vector
    // evitando crear un temporal y luego moverlo/copiarlo.
    std::cout << "Usando emplace_back:" << '\n';
    equipo.emplace_back("Lucía", 28);
    equipo.emplace_back("Marcos", 34);

    std::cout << "\n=== 3. std::unordered_map: TABLA HASH O(1) ===" << '\n';
    std::unordered_map<std::string, double> precios;

    // Inserción
    precios["Laptop"] = 1200.50;
    precios["Mouse"] = 25.00;
    precios["Teclado"] = 80.00;

    // Búsqueda eficiente O(1) con .find()
    std::string productoBuscado = "Laptop";
    auto iterador = precios.find(productoBuscado);

    if (iterador != precios.end()) {
        // En iteradores de mapas: first = clave, second = valor
        std::cout << "Encontrado: " << iterador->first << " cuesta $" << iterador->second << '\n';
    } else {
        std::cout << "Producto no encontrado." << '\n';
    }

    // Iteración por todos los elementos
    std::cout << "\nCatálogo completo:" << '\n';
    for (const auto& par : precios) {
        std::cout << "  - " << par.first << ": $" << par.second << '\n';
    }

    return 0;
}
