/**
 * Lección 01 del Módulo STL:
 * Plantillas de Funciones y Clases Genéricas (Templates).
 */

#include <iostream>
#include <string>
#include <stdexcept>

// 1. Plantilla de Función (Function Template)
// El compilador genera una versión en código máquina distinta para cada tipo 'T' que uses
template <typename T>
T obtenerMaximo(T a, T b) {
    return (a > b) ? a : b;
}

// 2. Plantilla de Clase (Class Template) con parámetro de tipo y parámetro no de tipo (size_t)
template <typename T, size_t Capacidad>
class PilaFija {
private:
    T elementos[Capacidad];
    size_t tope;

public:
    PilaFija() : tope(0) {}

    void apilar(const T& elemento) {
        if (tope >= Capacidad) {
            throw std::overflow_error("¡Pila llena! Capacidad máxima alcanzada.");
        }
        elementos[tope++] = elemento;
    }

    T desapilar() {
        if (estaVacia()) {
            throw std::underflow_error("¡Pila vacía! No hay elementos.");
        }
        return elementos[--tope];
    }

    bool estaVacia() const {
        return tope == 0;
    }

    size_t tamano() const {
        return tope;
    }
};

int main() {
    std::cout << "=== 1. TEMPLATES DE FUNCIONES ===" << '\n';
    std::cout << "Máximo entre enteros (10, 25):     " << obtenerMaximo(10, 25) << '\n';
    std::cout << "Máximo entre decimales (3.14, 2.71): " << obtenerMaximo(3.14, 2.71) << '\n';
    std::cout << "Máximo entre strings:               " << obtenerMaximo(std::string("Zeta"), std::string("Alfa")) << '\n';

    std::cout << "\n=== 2. TEMPLATES DE CLASES: PILA FIJA EN EL STACK ===" << '\n';
    // Instanciamos una PilaFija para 3 enteros:
    PilaFija<int, 3> pilaEnteros;
    pilaEnteros.apilar(100);
    pilaEnteros.apilar(200);
    pilaEnteros.apilar(300);

    std::cout << "Desapilando enteros: ";
    while (!pilaEnteros.estaVacia()) {
        std::cout << pilaEnteros.desapilar() << " ";
    }
    std::cout << '\n';

    // Instanciamos una PilaFija para cadenas de texto:
    PilaFija<std::string, 2> pilaStrings;
    pilaStrings.apilar("Hola");
    pilaStrings.apilar("Mundo");

    std::cout << "Desapilando cadenas: ";
    while (!pilaStrings.estaVacia()) {
        std::cout << pilaStrings.desapilar() << " ";
    }
    std::cout << '\n';

    return 0;
}
