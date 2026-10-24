# ⚡ C++ Cheat Sheet: Guía Rápida de Bolsillo

> **Mantén este archivo abierto mientras programas para consultar sintaxis de memoria y punteros en 5 segundos.**

---

## 📌 1. Punteros, Referencias y Memoria

| Sintaxis | Significado | ¿Copia datos? | ¿Modifica original? | ¿Puede ser `nullptr`? |
| :--- | :--- | :---: | :---: | :---: |
| `void f(int x)` | Paso por valor | ✅ SÍ | ❌ NO | ❌ NO |
| `void f(int& x)` | Paso por referencia | ❌ NO | ✅ SÍ | ❌ NO |
| `void f(const int& x)`| Referencia constante (**Óptimo**) | ❌ NO | ❌ NO (Solo lectura) | ❌ NO |
| `void f(int* p)` | Puntero a entero | ❌ NO | ✅ SÍ (`*p = 10`) | ✅ SÍ |

```cpp
int a = 10;
int* ptr = &a;    // &a obtiene la dirección física de 'a'
*ptr = 20;        // *ptr desreferencia y modifica 'a'
int& ref = a;     // ref es un alias directo a 'a' (no requiere *)
```

---

## 📦 2. Stack vs Heap (Memoria Dinámica)

```cpp
// 1. En el STACK (Pila): Rápido, automático, tamaño limitado
int stackVar = 50; 

// 2. En el HEAP (Montículo): Manual, enorme, requiere liberación OBLIGATORIA
int* p = new int(50);
delete p;
p = nullptr; // Buena práctica: evita puntero colgante (dangling pointer)

// 3. Arreglo dinámico en Heap:
int* arr = new int[100];
delete[] arr; // ¡OBLIGATORIO usar delete[] con corchetes!
arr = nullptr;
```

---

## 🛡️ 3. El Principio Sagrado RAII

```cpp
class ArchivoRAII {
    std::ofstream file;
public:
    ArchivoRAII(const std::string& ruta) : file(ruta) {} // Adquisición en constructor
    ~ArchivoRAII() { /* file se cierra solo en el destructor determinista */ }
};
```

---

## 🧩 4. Punteros Inteligentes Modernos (`#include <memory>`)

```cpp
// 1. unique_ptr: Propiedad exclusiva (Cero sobrecarga, sin delete manual)
std::unique_ptr<MiClase> u1 = std::make_unique<MiClase>("Param");
u1->metodo();
// Transferir propiedad:
std::unique_ptr<MiClase> u2 = std::move(u1); // u1 queda en nullptr

// 2. shared_ptr: Propiedad compartida con conteo de referencias
std::shared_ptr<MiClase> s1 = std::make_shared<MiClase>();
std::shared_ptr<MiClase> s2 = s1; // use_count() pasa a 2

// 3. weak_ptr: Observador débil sin propiedad (rompe ciclos circulares)
std::weak_ptr<MiClase> w = s1;
if (auto bloqueado = w.lock()) {
    bloqueado->metodo(); // Promovido temporalmente a shared_ptr
}
```

---

## ⚖️ 5. Plantilla Rápida: La Regla de los Tres / Cinco

```cpp
class Recurso {
    int* ptr;
public:
    // Destructor:
    ~Recurso() { delete ptr; }
    // Constructor de Copia (Deep Copy):
    Recurso(const Recurso& otro) : ptr(new int(*otro.ptr)) {}
    // Operador de Asignación de Copia:
    Recurso& operator=(const Recurso& otro) {
        if (this == &otro) return *this;
        delete ptr;
        ptr = new int(*otro.ptr);
        return *this;
    }
    // Constructor de Movimiento (C++11 Move Semantics O(1)):
    Recurso(Recurso&& otro) noexcept : ptr(otro.ptr) { otro.ptr = nullptr; }
};
```

---

## 🏹 6. Expresiones Lambda en C++

```cpp
// [captura](parámetros) -> tipo_retorno { cuerpo }
int factor = 10;

// [=] captura por copia
auto porCopia = [=](int x) { return x * factor; };

// [&] captura por referencia (permite modificar variables locales externas)
auto porRef = [&](int x) { factor += x; };

// Cláusula de ordenamiento en std::sort:
std::sort(v.begin(), v.end(), [](const auto& a, const auto& b) {
    return a.precio < b.precio;
});
```

---

## ⚡ 7. Flags de Compilación Recomendados (GCC/Clang)

```powershell
# Flags de desarrollo estricto (atrapan el 95% de bugs en compilación):
g++ -std=c++14 -Wall -Wextra -pedantic -static -static-libgcc -static-libstdc++ main.cpp -o main.exe

# Flag de máxima optimización para producción:
g++ -std=c++14 -O3 -DNDEBUG main.cpp -o main.exe
```
