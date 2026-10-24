/**
 * Lección 03 de POO en C++:
 * Herencia, Polimorfismo en Tiempo de Ejecución y la Importancia del Destructor Virtual.
 */

#include <iostream>
#include <vector>

// Clase Base Abstracta (equivalente a una Interface en Java)
class Figura {
public:
    // Constructor
    Figura() {
        std::cout << "  [+] Constructor Base Figura" << '\n';
    }

    // ⚠️ REGLA DE ORO DE HERENCIA EN C++:
    // El destructor de la clase base DEBE ser 'virtual'.
    // Si no es virtual, al hacer 'delete punteroBase' sólo se llamaría al destructor de Figura,
    // dejando los recursos de la clase Derivada sin liberar (Memory Leak silencioso).
    virtual ~Figura() {
        std::cout << "  [-] Destructor Base Figura" << '\n';
    }

    // Función Virtual Pura (= 0): Convierte a la clase en Abstracta.
    // Obliga a las clases hijas a implementarla.
    virtual double calcularArea() const = 0;

    virtual void describir() const {
        std::cout << "Soy una figura geométrica genérica." << '\n';
    }
};

class Circulo : public Figura {
private:
    double radio;

public:
    Circulo(double r) : radio(r) {
        std::cout << "  [+] Constructor Derivado Circulo (radio=" << radio << ")" << '\n';
    }

    ~Circulo() override {
        std::cout << "  [-] Destructor Derivado Circulo" << '\n';
    }

    // 'override' le pide al compilador verificar que realmente estamos sobreescribiendo un método virtual
    double calcularArea() const override {
        return 3.1415926535 * radio * radio;
    }

    void describir() const override {
        std::cout << "Soy un Círculo con radio: " << radio << '\n';
    }
};

class Rectangulo : public Figura {
private:
    double ancho;
    double alto;

public:
    Rectangulo(double a, double h) : ancho(a), alto(h) {
        std::cout << "  [+] Constructor Derivado Rectangulo (" << ancho << "x" << alto << ")" << '\n';
    }

    ~Rectangulo() override {
        std::cout << "  [-] Destructor Derivado Rectangulo" << '\n';
    }

    double calcularArea() const override {
        return ancho * alto;
    }

    void describir() const override {
        std::cout << "Soy un Rectángulo de " << ancho << " x " << alto << '\n';
    }
};

int main() {
    std::cout << "=== 1. POLIMORFISMO A TRAVÉS DE PUNTEROS BASE ===" << '\n';
    // Para lograr polimorfismo dinámico en C++, DEBEMOS usar punteros o referencias.
    // Si guardas objetos directamente por valor en un vector de Figuras, ocurre 'Object Slicing' (rebanado).
    std::vector<Figura*> formas;
    formas.push_back(new Circulo(5.0));
    formas.push_back(new Rectangulo(4.0, 6.0));

    std::cout << "\n=== 2. INVOCACIÓN POLIMÓRFICA (DESPACHO VIRTUAL VIA VTABLE) ===" << '\n';
    for (const Figura* f : formas) {
        f->describir();
        std::cout << "  Área calculada: " << f->calcularArea() << '\n';
    }

    std::cout << "\n=== 3. DESTRUCCIÓN POLIMÓRFICA SEGURA (GRACIAS A 'virtual ~Figura') ===" << '\n';
    for (Figura* f : formas) {
        // Al ser virtual el destructor, primero se llama al destructor del hijo (Circulo/Rectangulo)
        // y luego al del padre (Figura).
        delete f;
    }
    formas.clear();

    return 0;
}
