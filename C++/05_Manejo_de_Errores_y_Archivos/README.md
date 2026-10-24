# Módulo 05: Manejo de Errores, Excepciones y Persistencia con <fstream>

## 🎯 Objetivos de Aprendizaje
1. Comprender el mecanismo de excepciones de C++: `throw`, `try` y `catch`.
2. Conocer la jerarquía estándar encabezada por **`std::exception`** (`<stdexcept>`).
3. Entender el **Stack Unwinding (Desenrollado de la Pila)**: cómo C++ destruye ordenadamente todos los objetos en el camino al lanzar una excepción, garantizando cero fugas si se usa RAII.
4. Aprender por qué las excepciones **SIEMPRE deben capturarse por referencia constante (`const std::exception&`)** para evitar *Object Slicing*.
5. Manipular archivos en disco usando los flujos RAII de **`<fstream>`** (`std::ifstream` y `std::ofstream`).

---

## 🏛️ Jerarquía de Excepciones Estándar

```
                        std::exception
                              │
             ┌────────────────┴────────────────┐
             ▼                                 ▼
     std::logic_error                  std::runtime_error
  (Errores detectables antes:        (Errores en tiempo de ejecución:
   std::invalid_argument,             std::overflow_error,
   std::out_of_range)                 std::system_error)
```

---

## 📁 Persistencia RAII con `<fstream>`

En C++, **NO necesitas llamar a `archivo.close()` manualmente**.
La clase `std::ofstream` y `std::ifstream` cierran el archivo y vacían el búfer automáticamente en su destructor cuando salen del ámbito `{ }`.

---

## 📂 Archivos de Código
- [`01_excepciones_robustas.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/05_Manejo_de_Errores_y_Archivos/01_excepciones_robustas.cpp)
- [`02_archivos_fstream_raii.cpp`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/05_Manejo_de_Errores_y_Archivos/02_archivos_fstream_raii.cpp)
- [`Ejercicios.md`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/05_Manejo_de_Errores_y_Archivos/Ejercicios.md)
