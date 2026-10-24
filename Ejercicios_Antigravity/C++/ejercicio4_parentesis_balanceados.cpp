#include <iostream>
#include <stack>
#include <string>

using namespace std;

// Verifica si los signos de apertura y cierre (), [], {} están correctamente anidados y balanceados.
bool estaBalanceado(const string& expresion) {
    stack<char> pila;

    for (char caracter : expresion) {
        // Si es apertura, lo apilamos a la espera de su cierre
        if (caracter == '(' || caracter == '[' || caracter == '{') {
            pila.push(caracter);
        }
        // Si es cierre, validamos con el tope
        else if (caracter == ')' || caracter == ']' || caracter == '}') {
            if (pila.empty()) {
                return false; // Cierre sin apertura
            }

            char apertura = pila.top();
            pila.pop();

            if (caracter == ')' && apertura != '(') return false;
            if (caracter == ']' && apertura != '[') return false;
            if (caracter == '}' && apertura != '{') return false;
        }
    }

    // Si al final no sobraron aperturas abiertas, está perfectamente balanceado
    return pila.empty();
}

int main() {
    cout << "--- VERIFICADOR DE PARENTESIS BALANCEADOS [C++] ---\n\n";

    string formulaValida = "{ [ 10 + 5 ] * ( 4 - 2 ) }";
    string formulaInvalida = "{ [ 10 + 5 ) ]";

    cout << "Formula 1: " << formulaValida << "\n";
    cout << "Esta balanceada?: " << (estaBalanceado(formulaValida) ? "SI (Correcta)" : "NO (Error de sintaxis)") << "\n\n";

    cout << "Formula 2: " << formulaInvalida << "\n";
    cout << "Esta balanceada?: " << (estaBalanceado(formulaInvalida) ? "SI (Correcta)" : "NO (Error de sintaxis)") << "\n";

    return 0;
}
