#include <iostream>
#include <stack>
#include <string>

using namespace std;

int main() {
    string palabraOriginal = "ANTIGRAVITY";
    cout << "Palabra original: " << palabraOriginal << "\n";

    // Creamos una pila de caracteres (char)
    stack<char> pilaLetras;

    // Paso 1: Apilamos cada letra (las metemos una por una)
    for (char letra : palabraOriginal) {
        pilaLetras.push(letra);
    }

    // Paso 2: Desapilamos una por una. Al ser LIFO, salen en orden inverso
    string palabraInvertida = "";
    while (!pilaLetras.empty()) {
        palabraInvertida += pilaLetras.top(); // Tomamos la de arriba
        pilaLetras.pop();                     // La sacamos de la pila
    }

    cout << "Palabra invertida con Pila: " << palabraInvertida << "\n";

    return 0;
}
