/**
 * Lección 02 de Punteros Inteligentes:
 * std::shared_ptr, Conteo de Referencias y Romper Ciclos con std::weak_ptr.
 */

#include <iostream>
#include <memory>

class NodoCiclico; // Declaración adelantada

class NodoB {
public:
    // ⚠️ Si aquí usáramos std::shared_ptr<NodoCiclico>, tendríamos una referencia circular:
    // Nodo A apunta a B, y B apunta a A. Ninguno de los dos llegaría a use_count() == 0.
    // ✅ Usamos std::weak_ptr para observar sin ser dueño del ciclo de vida:
    std::weak_ptr<NodoCiclico> companero;

    ~NodoB() {
        std::cout << "  [-] Nodo B destruido." << '\n';
    }
};

class NodoCiclico {
public:
    std::shared_ptr<NodoB> enlace;

    ~NodoCiclico() {
        std::cout << "  [-] Nodo Ciclico destruido." << '\n';
    }
};

int main() {
    std::cout << "=== 1. CONTEO DE REFERENCIAS CON std::shared_ptr ===" << '\n';
    {
        std::shared_ptr<int> sp1 = std::make_shared<int>(42);
        std::cout << "sp1 creado. use_count: " << sp1.use_count() << '\n';

        {
            std::shared_ptr<int> sp2 = sp1; // Copia permitida: incrementa el contador
            std::cout << "sp2 creado como copia de sp1. use_count: " << sp1.use_count() << '\n';
            std::cout << "Valor accesible desde sp2: " << *sp2 << '\n';
        } // sp2 sale de ámbito aquí

        std::cout << "sp2 destruido. use_count restante: " << sp1.use_count() << '\n';
    } // sp1 sale de ámbito aquí y la memoria se libera

    std::cout << "\n=== 2. PREVENCIÓN DE CICLOS DE RETENCIÓN CON std::weak_ptr ===" << '\n';
    {
        auto a = std::make_shared<NodoCiclico>();
        auto b = std::make_shared<NodoB>();

        a->enlace = b;          // a es dueño de b
        b->companero = a;       // b observa a 'a' débilmente sin incrementar su use_count

        std::cout << "a use_count: " << a.use_count() << " (esperado 1)" << '\n';
        std::cout << "b use_count: " << b.use_count() << " (esperado 2: 'b' y 'a->enlace')" << '\n';

        // Para usar un weak_ptr, debemos promoverlo temporalmente a shared_ptr con .lock()
        if (auto observador = b->companero.lock()) {
            std::cout << "El nodo observado sigue vivo y accesible." << '\n';
        }

        std::cout << "Saliendo del ámbito..." << '\n';
    } // Ambos nodos A y B se destruyen limpiamente porque weak_ptr rompió el ciclo mortal

    return 0;
}
