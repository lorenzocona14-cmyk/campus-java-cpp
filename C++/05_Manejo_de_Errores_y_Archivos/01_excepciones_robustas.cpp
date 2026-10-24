/**
 * Lección 01 del Módulo de Errores:
 * Excepciones Estándar, Excepciones Personalizadas y Desenrollado de Pila (Stack Unwinding).
 */

#include <iostream>
#include <string>
#include <stdexcept>

// Objeto auxiliar para demostrar que su destructor se ejecuta DURANTE el desenrollado de pila
class RecursoRastreador {
private:
    std::string nombre;

public:
    RecursoRastreador(std::string n) : nombre(std::move(n)) {
        std::cout << "  [+] Recurso '" << nombre << "' creado en la pila." << '\n';
    }

    ~RecursoRastreador() {
        std::cout << "  [-] Recurso '" << nombre << "' DESTRUIDO por Stack Unwinding." << '\n';
    }
};

// Excepción de Negocio personalizada heredada de std::runtime_error
class SaldoInsuficienteException : public std::runtime_error {
private:
    double saldoActual;
    double montoRequerido;

public:
    SaldoInsuficienteException(const std::string& msg, double saldo, double requerido)
        : std::runtime_error(msg), saldoActual(saldo), montoRequerido(requerido) {}

    double getSaldoActual() const { return saldoActual; }
    double getMontoRequerido() const { return montoRequerido; }
};

void procesarRetiro(double saldo, double monto) {
    RecursoRastreador tracker("TransaccionTemporal");

    if (monto > saldo) {
        std::cout << "  [!] Lanzando excepción SaldoInsuficienteException..." << '\n';
        throw SaldoInsuficienteException("Fondos insuficientes para debitar", saldo, monto);
    }

    std::cout << "  [*] Retiro completado exitosamente." << '\n';
}

int main() {
    std::cout << "=== 1. DEMOSTRACIÓN DE EXCEPCIONES Y STACK UNWINDING ===" << '\n';

    try {
        std::cout << "Iniciando intento de retiro con saldo insuficiente (Saldo: $500, Pedido: $1200):" << '\n';
        procesarRetiro(500.0, 1200.0);
        std::cout << "Esta línea NUNCA se ejecutará." << '\n';
    }
    // ⚠️ REGLA DE ORO: Capturar SIEMPRE por referencia constante (const std::exception&)
    // Capturar por valor causa 'Object Slicing' (rebanado del objeto) y pierde los datos específicos del hijo.
    catch (const SaldoInsuficienteException& e) {
        std::cerr << "\n[EXCEPCIÓN CAPTURADA]: " << e.what() << '\n';
        std::cerr << "  Saldo disponible:  $" << e.getSaldoActual() << '\n';
        std::cerr << "  Monto solicitado:  $" << e.getMontoRequerido() << '\n';
        std::cerr << "  Déficit faltante:  $" << (e.getMontoRequerido() - e.getSaldoActual()) << '\n';
    }
    catch (const std::exception& e) {
        std::cerr << "[ERROR GENERAL]: " << e.what() << '\n';
    }

    std::cout << "\nPrograma continúa su flujo normal de ejecución de forma segura." << '\n';
    return 0;
}
