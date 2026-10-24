/**
 * Lección 03 de Punteros Inteligentes:
 * Semántica de Movimiento (Move Semantics), rvalues (&&) y std::move.
 */

#include <iostream>
#include <vector>
#include <string>

class ContenedorPesado {
private:
    std::string etiqueta;
    int* datos;
    size_t tamano;

public:
    // 1. Constructor Ordinario
    ContenedorPesado(const std::string& tag, size_t n)
        : etiqueta(tag), tamano(n), datos(new int[n]{0}) {
        std::cout << "[+] Constructor: '" << etiqueta << "' reservó " << tamano << " elementos en " << datos << '\n';
    }

    // 2. Destructor
    ~ContenedorPesado() {
        if (datos != nullptr) {
            std::cout << "[-] Destructor: '" << etiqueta << "' liberando " << datos << '\n';
            delete[] datos;
        } else {
            std::cout << "[-] Destructor: '" << etiqueta << "' vacío (sus recursos fueron movidos previamente)." << '\n';
        }
    }

    // 3. Constructor de Copia (Costoso: reserva nueva memoria y clona todo)
    ContenedorPesado(const ContenedorPesado& otro)
        : etiqueta(otro.etiqueta + "_copia"), tamano(otro.tamano), datos(new int[otro.tamano]) {
        for (size_t i = 0; i < tamano; ++i) datos[i] = otro.datos[i];
        std::cout << "[C] Constructor de Copia: Duplicados " << tamano << " ints en nueva memoria " << datos << '\n';
    }

    // 4. Constructor de Movimiento (Move Constructor - C++11): ¡ROBO DE RECURSOS O(1)!
    // Recibe una referencia rvalue (&&) a un objeto temporal o marcado con std::move
    ContenedorPesado(ContenedorPesado&& otro) noexcept
        : etiqueta(otro.etiqueta + "_movido"), tamano(otro.tamano), datos(otro.datos) {
        // Robamos el puntero directamente: NO reservamos nueva memoria
        otro.datos = nullptr; // Dejamos el objeto original en un estado seguro y vacío
        otro.tamano = 0;
        std::cout << "[M] Constructor de Movimiento: ¡Recursos robados en 0 microsegundos! Puntero transferido: " << datos << '\n';
    }
};

int main() {
    std::cout << "=== 1. EXPERIMENTO: COPIA TRADICIONAL (LENTA) ===" << '\n';
    ContenedorPesado original("Dataset-Alfa", 100000);
    ContenedorPesado clon = original; // Invoca constructor de copia

    std::cout << "\n=== 2. EXPERIMENTO: MOVIMIENTO CON std::move (INSTANTÁNEO) ===" << '\n';
    // std::move convierte 'original' en un rvalue, autorizando al nuevo objeto a "robarle" sus entrañas
    ContenedorPesado nuevoDuenio = std::move(original);

    std::cout << "\n=== 3. AGREGANDO A std::vector CON std::move ===" << '\n';
    std::vector<ContenedorPesado> lista;
    // Si no usáramos std::move, vector duplicaría la memoria al reubicarse
    lista.push_back(std::move(nuevoDuenio));

    std::cout << "\n=== 4. FINALIZANDO ÁMBITO (DESTRUCCIÓN) ===" << '\n';
    return 0;
}
