/**
 * Lección 02 del Módulo de Archivos:
 * Entrada y Salida con <fstream> y Gestión Automática RAII.
 */

#include <iostream>
#include <fstream>  // std::ifstream, std::ofstream
#include <string>
#include <cstdio>   // std::remove

int main() {
    const std::string nombreArchivo = "configuracion_sistema.txt";

    std::cout << "=== 1. ESCRITURA EN ARCHIVO (std::ofstream) ===" << '\n';
    {
        // El constructor de std::ofstream abre el archivo
        std::ofstream archivoSalida(nombreArchivo);

        if (!archivoSalida.is_open()) {
            std::cerr << "Error al abrir el archivo para escritura." << '\n';
            return 1;
        }

        archivoSalida << "PUERTO=8080" << '\n';
        archivoSalida << "HOST=127.0.0.1" << '\n';
        archivoSalida << "MODO=PRODUCCION" << '\n';
        archivoSalida << "MAX_CONEXIONES=1000" << '\n';

        std::cout << "Datos de configuración escritos exitosamente." << '\n';
        std::cout << "Saliendo del ámbito: el destructor cerrará el archivo y vaciará el búfer." << '\n';
    } // <-- Aquí se cierra automáticamente archivoSalida

    std::cout << "\n=== 2. LECTURA LÍNEA A LÍNEA (std::ifstream) ===" << '\n';
    {
        std::ifstream archivoEntrada(nombreArchivo);

        if (!archivoEntrada.is_open()) {
            std::cerr << "Error al abrir el archivo para lectura." << '\n';
            return 1;
        }

        std::string linea;
        std::cout << "Contenido leído desde '" << nombreArchivo << "':" << '\n';
        while (std::getline(archivoEntrada, linea)) {
            std::cout << "  [Par Clave/Valor]: " << linea << '\n';
        }
    } // <-- Aquí se cierra automáticamente archivoEntrada

    std::cout << "\n=== 3. LIMPIEZA DE ARCHIVO DE PRUEBA ===" << '\n';
    if (std::remove(nombreArchivo.c_str()) == 0) {
        std::cout << "Archivo '" << nombreArchivo << "' eliminado limpiamente." << '\n';
    }

    return 0;
}
