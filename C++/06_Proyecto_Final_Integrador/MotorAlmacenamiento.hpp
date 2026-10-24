#pragma once

#include "Registro.hpp"
#include <unordered_map>
#include <memory>
#include <vector>
#include <string>
#include <stdexcept>

// Excepción personalizada
class ClaveNoEncontradaException : public std::runtime_error {
public:
    explicit ClaveNoEncontradaException(const std::string& clave)
        : std::runtime_error("La clave '" + clave + "' no existe en FastKV.") {}
};

/**
 * Motor de Almacenamiento FastKV en Memoria con persistencia RAII.
 */
class MotorAlmacenamiento {
private:
    std::unordered_map<std::string, std::shared_ptr<Registro>> tablaHash;

public:
    MotorAlmacenamiento() = default;

    // Métodos CRUD
    void insertar(std::string clave, std::string valor);
    std::string obtener(const std::string& clave);
    bool eliminar(const std::string& clave);
    bool existe(const std::string& clave) const;
    size_t cantidad() const;

    // Métodos Analíticos con STL y Lambdas
    std::vector<std::string> buscarPorPrefijo(const std::string& prefijo) const;
    std::vector<std::pair<std::string, size_t>> obtenerClavesMasConsultadas(size_t topN) const;

    // Persistencia RAII con <fstream>
    void guardarSnapshot(const std::string& rutaArchivo) const;
    void cargarSnapshot(const std::string& rutaArchivo);
};
