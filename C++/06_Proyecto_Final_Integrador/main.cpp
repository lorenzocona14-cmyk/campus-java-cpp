/**
 * Punto de Entrada de FastKV:
 * Simulación y Verificación del Motor de Almacenamiento en C++.
 */

#include <iostream>
#include <cstdio> // std::remove
#include "MotorAlmacenamiento.hpp"

int main() {
    std::cout << "==========================================================" << '\n';
    std::cout << "   ⚡ FASTKV STORAGE ENGINE - MOTOR CLAVE-VALOR EN C++    " << '\n';
    std::cout << "==========================================================" << '\n';

    MotorAlmacenamiento db;

    // 1. Inserción de Datos (con semántica de movimiento O(1))
    std::cout << "\n[+] Insertando pares clave-valor en FastKV..." << '\n';
    db.insertar("usuario:100:nombre", "Lionel Messi");
    db.insertar("usuario:100:email",  "messi@intermiami.com");
    db.insertar("usuario:101:nombre", "Cristiano Ronaldo");
    db.insertar("usuario:101:email",  "cr7@alnassr.com");
    db.insertar("config:max_threads", "64");
    db.insertar("config:timeout_ms",  "5000");

    std::cout << "Total de registros almacenados: " << db.cantidad() << '\n';

    // 2. Consultas y simulación de accesos
    std::cout << "\n[+] Simulando consultas frecuentes..." << '\n';
    std::cout << "Consulta 'usuario:100:nombre': " << db.obtener("usuario:100:nombre") << '\n';
    std::cout << "Consulta 'usuario:100:nombre': " << db.obtener("usuario:100:nombre") << '\n';
    std::cout << "Consulta 'usuario:100:nombre': " << db.obtener("usuario:100:nombre") << '\n';
    std::cout << "Consulta 'config:max_threads': " << db.obtener("config:max_threads") << '\n';

    // 3. Manejo de Excepciones de Clave Inexistente
    std::cout << "\n[!] Probando consulta de clave inexistente:" << '\n';
    try {
        db.obtener("clave:fantasma");
    } catch (const ClaveNoEncontradaException& e) {
        std::cout << "    [Capturado con éxito]: " << e.what() << '\n';
    }

    // 4. Búsqueda por Prefijo con STL
    std::cout << "\n=== BÚSQUEDA POR PREFIJO ('usuario:100') ===" << '\n';
    auto clavesUsuario = db.buscarPorPrefijo("usuario:100");
    for (const auto& k : clavesUsuario) {
        std::cout << "  - Clave: " << k << " => Valor: " << db.obtener(k) << '\n';
    }

    // 5. Analítica de Claves Más Frecuentes
    std::cout << "\n=== TOP CLAVES MÁS CONSULTADAS ===" << '\n';
    auto topClaves = db.obtenerClavesMasConsultadas(3);
    for (const auto& par : topClaves) {
        std::cout << "  - " << par.first << " (" << par.second << " lecturas)" << '\n';
    }

    // 6. Persistencia RAII con Snapshot
    const std::string archivoSnapshot = "fastkv_dump.rdb";
    std::cout << "\n[+] Guardando Snapshot a disco: " << archivoSnapshot << '\n';
    db.guardarSnapshot(archivoSnapshot);

    std::cout << "\n[+] Instanciando un SEGUNDO motor vacío para probar restauración..." << '\n';
    MotorAlmacenamiento dbNueva;
    std::cout << "Cantidad antes de restaurar: " << dbNueva.cantidad() << '\n';

    dbNueva.cargarSnapshot(archivoSnapshot);
    std::cout << "Cantidad tras restaurar:     " << dbNueva.cantidad() << '\n';
    std::cout << "Valor recuperado en el nuevo motor: " << dbNueva.obtener("usuario:101:nombre") << '\n';

    // Limpieza de prueba
    std::remove(archivoSnapshot.c_str());
    std::cout << "\n[v] Snapshot temporal eliminado y prueba completada con éxito." << '\n';

    return 0;
}
