#include "MotorAlmacenamiento.hpp"
#include <fstream>
#include <algorithm>
#include <sstream>

void MotorAlmacenamiento::insertar(std::string clave, std::string valor) {
    if (clave.empty()) {
        throw std::invalid_argument("La clave no puede estar vacía.");
    }
    // std::make_shared maneja el ciclo de vida del registro automáticamente
    auto registro = std::make_shared<Registro>(clave, std::move(valor));
    tablaHash[std::move(clave)] = std::move(registro);
}

std::string MotorAlmacenamiento::obtener(const std::string& clave) {
    auto it = tablaHash.find(clave);
    if (it == tablaHash.end()) {
        throw ClaveNoEncontradaException(clave);
    }
    return it->second->getValor();
}

bool MotorAlmacenamiento::eliminar(const std::string& clave) {
    return tablaHash.erase(clave) > 0;
}

bool MotorAlmacenamiento::existe(const std::string& clave) const {
    return tablaHash.find(clave) != tablaHash.end();
}

size_t MotorAlmacenamiento::cantidad() const {
    return tablaHash.size();
}

std::vector<std::string> MotorAlmacenamiento::buscarPorPrefijo(const std::string& prefijo) const {
    std::vector<std::string> resultados;
    for (const auto& par : tablaHash) {
        // Si la clave empieza con el prefijo
        if (par.first.rfind(prefijo, 0) == 0) {
            resultados.push_back(par.first);
        }
    }
    std::sort(resultados.begin(), resultados.end());
    return resultados;
}

std::vector<std::pair<std::string, size_t>> MotorAlmacenamiento::obtenerClavesMasConsultadas(size_t topN) const {
    std::vector<std::pair<std::string, size_t>> pares;
    pares.reserve(tablaHash.size());

    for (const auto& par : tablaHash) {
        pares.emplace_back(par.first, par.second->getAccesos());
    }

    // Ordenamiento descendente por número de accesos usando un lambda
    std::sort(pares.begin(), pares.end(), [](const auto& a, const auto& b) {
        return a.second > b.second;
    });

    if (pares.size() > topN) {
        pares.resize(topN);
    }

    return pares;
}

void MotorAlmacenamiento::guardarSnapshot(const std::string& rutaArchivo) const {
    std::ofstream archivo(rutaArchivo);
    if (!archivo.is_open()) {
        throw std::runtime_error("No se pudo abrir el archivo para guardar snapshot: " + rutaArchivo);
    }

    for (const auto& par : tablaHash) {
        // Formato simple: CLAVE=VALOR
        // Registro::getValor sin const no lo usamos aquí, usamos un accessor seguro o leemos directamente
        archivo << par.first << "=" << par.second->getValor() << '\n';
    }
}

void MotorAlmacenamiento::cargarSnapshot(const std::string& rutaArchivo) {
    std::ifstream archivo(rutaArchivo);
    if (!archivo.is_open()) {
        throw std::runtime_error("No se pudo abrir el archivo de snapshot para lectura: " + rutaArchivo);
    }

    tablaHash.clear();
    std::string linea;

    while (std::getline(archivo, linea)) {
        if (linea.empty()) continue;
        auto posSeparador = linea.find('=');
        if (posSeparador != std::string::npos) {
            std::string clave = linea.substr(0, posSeparador);
            std::string valor = linea.substr(posSeparador + 1);
            insertar(std::move(clave), std::move(valor));
        }
    }
}
