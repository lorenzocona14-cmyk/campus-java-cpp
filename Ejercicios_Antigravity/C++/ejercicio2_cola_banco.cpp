#include <iostream>
#include <queue>
#include <string>

using namespace std;

int main() {
    // Creamos la cola para almacenar los nombres de los clientes
    queue<string> filaBanco;

    cout << "--- SISTEMA DE ATENCION EN EL BANCO [C++] ---\n\n";

    // 1. Llegan los clientes a la fila (encolar con .push())
    cout << "Llega Maria a la fila.\n";
    filaBanco.push("Maria");

    cout << "Llega Carlos a la fila.\n";
    filaBanco.push("Carlos");

    cout << "Llega Sofia a la fila.\n";
    filaBanco.push("Sofia");

    // 2. Miramos quién está al frente sin atenderlo todavía (.front() en C++)
    cout << "\n[En la cabecera] El proximo a ser atendido es: " << filaBanco.front() << "\n";

    // 3. El cajero empieza a atender (desencolar)
    // En C++: leemos el primero con .front() y luego lo quitamos con .pop()
    cout << "\n--- El cajero llama al siguiente ---\n";
    string atendido1 = filaBanco.front();
    filaBanco.pop();
    cout << "Atendiendo a: " << atendido1 << "\n";

    cout << "\n--- El cajero llama al siguiente ---\n";
    string atendido2 = filaBanco.front();
    filaBanco.pop();
    cout << "Atendiendo a: " << atendido2 << "\n";

    // 4. Verificamos quién queda
    cout << "\nProximo en espera: " << filaBanco.front() << "\n";
    cout << "Clientes restantes en fila: " << filaBanco.size() << "\n";

    return 0;
}
