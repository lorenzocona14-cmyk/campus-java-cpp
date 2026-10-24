#include "operaciones.hpp"
#include <stdexcept>

namespace Matematicas {

    long long factorial(int n) {
        if (n < 0) {
            throw std::invalid_argument("El factorial no está definido para números negativos.");
        }
        long long resultado = 1;
        for (int i = 2; i <= n; ++i) {
            resultado *= i;
        }
        return resultado;
    }

    double potencia(double base, int exponente) {
        double resultado = 1.0;
        int expPositivo = exponente < 0 ? -exponente : exponente;

        for (int i = 0; i < expPositivo; ++i) {
            resultado *= base;
        }

        return exponente < 0 ? (1.0 / resultado) : resultado;
    }

    bool esPar(int numero) {
        return (numero % 2) == 0;
    }

}
