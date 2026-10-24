#include <iostream>
#include <stack>
#include <string>

using namespace std;

int main() {
    // Creamos la pila para almacenar las acciones (strings)
    stack<string> historialAcciones;

    cout << "--- SIMULADOR DE TEXTO Y DESHACER (Ctrl+Z) [C++] ---\n\n";

    // 1. El usuario realiza acciones (vamos apilando con .push())
    cout << "Escribiendo: 'Hola'\n";
    historialAcciones.push("Escribir 'Hola'");

    cout << "Escribiendo: 'Mundo'\n";
    historialAcciones.push("Escribir 'Mundo'");

    cout << "Aplicando formato: 'Negrita'\n";
    historialAcciones.push("Formato Negrita");

    // 2. Miramos cuál es la última acción sin borrarla (.top() en C++)
    cout << "\n[Espiando] La ultima accion realizada fue: " << historialAcciones.top() << "\n";

    // 3. El usuario presiona Ctrl+Z dos veces
    // IMPORTANTE EN C++: .pop() borra el elemento pero NO lo devuelve.
    // Para ver qué sacamos, primero leemos con .top() y luego llamamos a .pop().
    cout << "\n--- Presionando Ctrl+Z ---\n";
    string deshecho1 = historialAcciones.top(); // 1. Leemos el de arriba
    historialAcciones.pop();                     // 2. Lo eliminamos
    cout << "Se deshizo: " << deshecho1 << "\n";

    cout << "\n--- Presionando Ctrl+Z otra vez ---\n";
    string deshecho2 = historialAcciones.top();
    historialAcciones.pop();
    cout << "Se deshizo: " << deshecho2 << "\n";

    // 4. Verificamos qué queda en el historial
    cout << "\nAccion actual en el tope: " << historialAcciones.top() << "\n";
    cout << "Elementos restantes en la pila: " << historialAcciones.size() << "\n";

    return 0;
}
