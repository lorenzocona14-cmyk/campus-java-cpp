#pragma once

#include <string>
#include <chrono>

/**
 * Representa una entrada de datos en FastKV.
 */
class Registro {
private:
    std::string clave;
    std::string valor;
    std::chrono::system_clock::time_point timestampCreacion;
    size_t accesos;

public:
    // Constructor con semántica de movimiento para strings
    Registro(std::string k, std::string v)
        : clave(std::move(k)), valor(std::move(v)),
          timestampCreacion(std::chrono::system_clock::now()),
          accesos(0) {}

    const std::string& getClave() const { return clave; }

    const std::string& getValor() {
        accesos++;
        return valor;
    }

    void setValor(std::string nuevoValor) {
        valor = std::move(nuevoValor);
    }

    size_t getAccesos() const { return accesos; }
};
