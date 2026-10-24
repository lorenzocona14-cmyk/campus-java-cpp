#include <iostream>
#include "operaciones.hpp"

int main() {
    std::cout << "=== DEMOSTRACIÓN DE MODULARIZACIÓN EN C++ ===" << '\n';
    std::cout << "Código dividido limpiamente en .hpp (cabecera) y .cpp (implementación)." << '\n';

    int n = 5;
    std::cout << "Factorial de " << n << " = " << Matematicas::factorial(n) << '\n';
    std::cout << "2 elevado a la 8 = " << Matematicas::potencia(2.0, 8) << '\n';
    std::cout << "2 elevado a la -3 = " << Matematicas::potencia(2.0, -3) << '\n';
    std::cout << "¿Es 42 un número par?: " << (Matematicas::esPar(42) ? "Sí" : "No") << '\n';

    return 0;
}
