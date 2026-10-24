/**
 * Lección 01 de POO en C++:
 * Encapsulamiento, Lista de Inicialización y el Sagrado Principio RAII.
 */

#include <iostream>
#include <string>

class ArchivoSimuladoRAII {
private:
    std::string nombreArchivo;
    bool estaAbierto;

public:
    // Constructor: Adquiere el recurso (abre el archivo)
    // Usamos 'Lista de Inicialización' (: campo(valor)), que es más eficiente que asignar dentro del cuerpo {}
    ArchivoSimuladoRAII(const std::string& nombre)
        : nombreArchivo(nombre), estaAbierto(true) {
        std::cout << "  [RAII Constructor]: Abriendo archivo '" << nombreArchivo << "' y adquiriendo descriptor de SO." << '\n';
    }

    // Método de operación
    void escribir(const std::string& texto) {
        if (!estaAbierto) {
            std::cout << "  [Error]: Intento de escribir en archivo cerrado." << '\n';
            return;
        }
        std::cout << "  [Escribiendo en " << nombreArchivo << "]: \"" << texto << "\"" << '\n';
    }

    // Destructor (~): Se ejecuta de forma DETERMINISTA y OBLIGATORIA
    // en el microsegundo exacto en que el objeto sale del ámbito { }
    ~ArchivoSimuladoRAII() {
        std::cout << "  [RAII Destructor]: Cerrando archivo '" << nombreArchivo << "' y liberando descriptor de SO." << '\n';
        estaAbierto = false;
    }
};

int main() {
    std::cout << "=== 1. INICIO DEL PROGRAMA ===" << '\n';

    {
        std::cout << "\n--- Entrando a un bloque de ámbito interno { } ---" << '\n';
        // 'doc' vive ÚNICAMENTE dentro de estas llaves
        ArchivoSimuladoRAII doc("reporte_ventas.log");
        doc.escribir("Transacción #100 procesada.");
        doc.escribir("Transacción #101 procesada.");
        std::cout << "--- A punto de salir del bloque de ámbito interno ---" << '\n';
    } // <-- ¡AQUÍ el compilador destruye 'doc' automáticamente!

    std::cout << "\n=== 2. FUERA DEL BLOQUE ===" << '\n';
    std::cout << "Observa arriba cómo el destructor se ejecutó antes de esta línea sin intervención manual." << '\n';

    return 0;
}
