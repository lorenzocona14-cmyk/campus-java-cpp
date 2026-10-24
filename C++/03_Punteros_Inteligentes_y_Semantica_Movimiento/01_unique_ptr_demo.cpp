/**
 * Lección 01 de Punteros Inteligentes:
 * std::unique_ptr: Propiedad Exclusiva, std::make_unique y Colecciones Polimórficas.
 */

#include <iostream>
#include <memory>  // Necesario para std::unique_ptr y std::make_unique
#include <vector>
#include <string>

class Servidor {
private:
    std::string nombre;

public:
    Servidor(const std::string& n) : nombre(n) {
        std::cout << "  [+] Servidor '" << nombre << "' iniciado en el Heap." << '\n';
    }

    ~Servidor() {
        std::cout << "  [-] Servidor '" << nombre << "' detenido y memoria LIBERADA AUTOMÁTICAMENTE." << '\n';
    }

    void procesarPeticion(const std::string& endpoint) const {
        std::cout << "  [*] Servidor " << nombre << " procesando: " << endpoint << '\n';
    }
};

int main() {
    std::cout << "=== 1. CREACIÓN CON std::make_unique (C++14) ===" << '\n';
    {
        // make_unique es la forma recomendada: más segura frente a excepciones y sin escribir 'new'
        std::unique_ptr<Servidor> srv1 = std::make_unique<Servidor>("Alpha-Node-01");
        srv1->procesarPeticion("/api/usuarios");

        // ❌ Esto causaría ERROR DE COMPILACIÓN (unique_ptr no se puede copiar):
        // std::unique_ptr<Servidor> copia = srv1;

        std::cout << "\n=== 2. TRANSFERENCIA DE PROPIEDAD CON std::move ===" << '\n';
        // Transferimos la propiedad a srv2. srv1 queda en nullptr.
        std::unique_ptr<Servidor> srv2 = std::move(srv1);

        if (srv1 == nullptr) {
            std::cout << "Confirmado: srv1 ha cedido la propiedad y ahora es nullptr." << '\n';
        }

        srv2->procesarPeticion("/api/pagos");
        std::cout << "Saliendo del ámbito de srv2..." << '\n';
    } // <-- Aquí se libera Servidor Alpha-Node-01 automáticamente sin escribir 'delete'!

    std::cout << "\n=== 3. VECTORES POLIMÓRFICOS MODERNOS SIN GESTIÓN MANUAL ===" << '\n';
    {
        std::vector<std::unique_ptr<Servidor>> cluster;
        cluster.push_back(std::make_unique<Servidor>("Nodo-A"));
        cluster.push_back(std::make_unique<Servidor>("Nodo-B"));

        for (const auto& s : cluster) {
            s->procesarPeticion("/healthcheck");
        }
        std::cout << "Saliendo del ámbito del vector cluster..." << '\n';
    } // <-- ¡Al destruirse el vector, destruye automáticamente todos los unique_ptr y sus nodos!

    std::cout << "\nPrograma finalizado limpiamente sin ninguna fuga de memoria." << '\n';
    return 0;
}
