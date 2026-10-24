/**
 * Lección 02 de POO en C++:
 * La Regla de los Tres (Rule of Three) y la Copia Profunda (Deep Copy).
 */

#include <iostream>
#include <algorithm> // std::copy

class BufferEnteros {
private:
    int* datos;
    size_t capacidad;

public:
    // 1. Constructor Ordinario
    BufferEnteros(size_t cap)
        : capacidad(cap), datos(new int[cap]{0}) {
        std::cout << "[+] Constructor: Reservados " << capacidad << " ints en el Heap en " << datos << '\n';
    }

    // 2. Destructor (Regla 1/3): Libera la memoria dinámica
    ~BufferEnteros() {
        std::cout << "[-] Destructor: Liberando memoria en " << datos << '\n';
        delete[] datos;
        datos = nullptr;
    }

    // 3. Constructor de Copia (Regla 2/3): COPIA PROFUNDA (Deep Copy)
    // Sin esto, el compilador copiaría el puntero superficialmente (ambos apuntando al mismo Heap)
    BufferEnteros(const BufferEnteros& otro)
        : capacidad(otro.capacidad), datos(new int[otro.capacidad]) {
        std::copy(otro.datos, otro.datos + capacidad, datos);
        std::cout << "[C] Constructor de Copia: Memoria duplicada en nueva dirección " << datos << '\n';
    }

    // 4. Operador de Asignación de Copia (Regla 3/3): buf1 = buf2;
    BufferEnteros& operator=(const BufferEnteros& otro) {
        std::cout << "[=] Operador de Asignación invocado." << '\n';
        // Protección contra auto-asignación (buf1 = buf1)
        if (this == &otro) {
            return *this;
        }

        // Liberar la memoria antigua que teníamos
        delete[] datos;

        // Reservar y copiar el contenido nuevo
        capacidad = otro.capacidad;
        datos = new int[capacidad];
        std::copy(otro.datos, otro.datos + capacidad, datos);

        return *this;
    }

    void establecer(size_t indice, int valor) {
        if (indice < capacidad) datos[indice] = valor;
    }

    void imprimir(const std::string& etiqueta) const {
        std::cout << etiqueta << " (Dirección: " << datos << "): [ ";
        for (size_t i = 0; i < capacidad; ++i) std::cout << datos[i] << " ";
        std::cout << "]\n";
    }
};

int main() {
    std::cout << "=== 1. CREACIÓN DEL BUFFER ORIGINAL ===" << '\n';
    BufferEnteros original(3);
    original.establecer(0, 10);
    original.establecer(1, 20);
    original.establecer(2, 30);
    original.imprimir("Original");

    std::cout << "\n=== 2. COPIA INDEPENDIENTE (CONSTRUCTOR DE COPIA) ===" << '\n';
    BufferEnteros copia = original; // Invoca constructor de copia
    copia.establecer(0, 999);       // Modificamos solo la copia

    std::cout << "Verificando independencia en memoria:" << '\n';
    original.imprimir("Original tras mutar copia");
    copia.imprimir("Copia mutada");

    std::cout << "\n=== 3. ASIGNACIÓN ENTRE OBJETOS EXISTENTES ===" << '\n';
    BufferEnteros tercero(1);
    tercero = original; // Invoca operator=
    tercero.imprimir("Tercero tras asignación");

    std::cout << "\n=== 4. FIN DEL PROGRAMA: DESTRUCCIÓN DETERMINISTA ===" << '\n';
    return 0;
}
