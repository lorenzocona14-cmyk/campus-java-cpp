# ⚡ C++ Masterclass: De Cero a Ingeniero de Sistemas en 30 Días

> **"En C++, no hay red de seguridad ni máquina virtual intermedia. Tienes el poder absoluto de controlar cada byte de la memoria y cada ciclo de reloj del procesador. Un gran poder conlleva una gran disciplina técnica."**
> — *Profesor Titular de C++*

---

## 🏛️ 1. El Pipeline de Compilación de C++: ¿Cómo Funciona?

A diferencia de Java, C++ no genera bytecode para una máquina virtual. Se traduce **directamente a lenguaje máquina nativo** que el silicio de tu procesador entiende:

```
[ Código Fuente: main.cpp ]  [ Cabeceras: math.hpp ]
               │                          │
               └───────────┬──────────────┘
                           ▼
             1. PREPROCESADOR (cpp)
             - Expande directivas #include (copia y pega texto)
             - Reemplaza #define y macros
             - Evalúa compilación condicional (#ifdef, #endif)
                           │
                           ▼  (Código fuente expandido: .i)
             2. COMPILADOR (g++ / clang / cl)
             - Análisis sintáctico, semántico y optimización
             - Genera código ensamblador específico de la arquitectura
                           │
                           ▼  (Código ensamblador: .s)
             3. ENSAMBLADOR (as)
             - Convierte instrucciones asm a código máquina binario
                           │
                           ▼  (Archivos objeto binarios: .o / .obj)
             4. ENLAZADOR (Linker - ld)
             - Une todos los archivos .o con la librería estándar (libstdc++)
             - Resuelve referencias a funciones externas y puntos de entrada
                           │
                           ▼
            [ Archivo Ejecutable Nativo: programa.exe ]
```

---

## 🧠 2. El Modelo Mental de la Memoria en C++

Cuando tu programa corre, el Sistema Operativo le asigna un espacio de direcciones virtual dividido en:

```
Direcciones Altas (0xFFFFFFFF...)
┌─────────────────────────────────────────────────────────────┐
│ 1. STACK (Pila de llamadas)                                 │
│    - Rápido como un rayo (solo mueve el puntero de pila RSP)│
│    - Variables locales y parámetros de funciones            │
│    - Tamaño limitado (~1MB a 8MB). Si te pasas: StackOverflow│
│    - Liberación AUTOMÁTICA al salir del ámbito { }          │
├─────────────────────────────────────────────────────────────┤
│                          ▼  Crece hacia abajo               │
│                                                             │
│                          ▲  Crece hacia arriba              │
├─────────────────────────────────────────────────────────────┤
│ 2. HEAP (Montículo / Memoria Dinámica)                      │
│    - Reservado con 'new' o 'malloc', liberado con 'delete'  │
│    - Tamaño enorme (limitado solo por la RAM de tu PC)      │
│    - Si olvidas liberarlo: MEMORY LEAK (fuga de memoria)    │
│    - En C++ Moderno se gestiona con Punteros Inteligentes   │
├─────────────────────────────────────────────────────────────┤
│ 3. BSS y DATA (Variables globales y estáticas)              │
├─────────────────────────────────────────────────────────────┤
│ 4. TEXT (Código máquina compilado de tus funciones)        │
└─────────────────────────────────────────────────────────────┘
Direcciones Bajas (0x00000000...)
```

---

## 🗺️ 3. Hoja de Ruta de los Módulos de Aprendizaje

| Módulo | Contenido Principal | Objetivo Pedagógico |
| :--- | :--- | :--- |
| [`00_Fundamentos_y_Sintaxis`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/00_Fundamentos_y_Sintaxis) | Tipos fundamentales, `sizeof`, `const`, `constexpr`, funciones por referencia, modularización `.hpp`/`.cpp`. | Dominar la base del lenguaje y la separación de interfaces e implementación. |
| [`01_Gestion_de_Memoria_Punteros_y_Referencias`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/01_Gestion_de_Memoria_Punteros_y_Referencias) | Punteros crudos (`*`, `&`), aritmética de punteros, memoria dinámica en Heap (`new`/`delete`), memory leaks y dangling pointers. | Dominar el hardware: manipular direcciones de memoria con total seguridad. |
| [`02_Programacion_Orientada_a_Objetos_y_RAII`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/02_Programacion_Orientada_a_Objetos_y_RAII) | Clases, constructores/destructores, el principio sagrado **RAII**, La Regla de los Tres, herencia y polimorfismo dinámico (`virtual`/`override`/`vtable`). | Aprender la filosofía fundamental que hace a C++ confiable y veloz. |
| [`03_Punteros_Inteligentes_y_Semantica_Movimiento`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/03_Punteros_Inteligentes_y_Semantica_Movimiento) | C++ Moderno (C++11/14): `std::unique_ptr`, `std::shared_ptr`, `std::weak_ptr`, Move Semantics (`std::move`, rvalues `&&`), Regla de los Cinco. | Programar en C++ sin volver a escribir `delete` manualmente jamás. |
| [`04_STL_Plantillas_y_Algoritmos`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/04_STL_Plantillas_y_Algoritmos) | Templates genéricos, `std::vector`, `std::unordered_map`, iteradores, algoritmos de `<algorithm>`, Lambdas en C++. | Explotar la biblioteca estándar de máxima velocidad y abstracción cero coste. |
| [`05_Manejo_de_Errores_y_Archivos`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/05_Manejo_de_Errores_y_Archivos) | Excepciones (`try`, `catch`, `throw`, `std::exception`), Streams de archivos con `<fstream>` (`ifstream`, `ofstream`, binario vs texto). | Persistencia en disco robusta utilizando RAII para garantizar el cierre. |
| [`06_Proyecto_Final_Integrador`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/C++/06_Proyecto_Final_Integrador) | **FastKV:** Motor de Almacenamiento Clave-Valor en Memoria con Persistencia RAII y Smart Pointers. | Integrar todo lo aprendido en un software de sistemas real de alto rendimiento. |

---

## ⚡ 4. Cómo Compilar y Ejecutar en tu Terminal

Tienes instalado MinGW GCC en `C:\MinGW\bin\g++.exe`.

### Opción A: Usando el script automatizado
```powershell
.\compilar_cpp.bat 00_Fundamentos_y_Sintaxis\01_hola_mundo.cpp
```

### Opción B: Directo con g++
```powershell
& "C:\MinGW\bin\g++.exe" -std=c++14 -Wall -Wextra Archivo.cpp -o Programa.exe
.\Programa.exe
```

¡Empecemos el viaje hacia el dominio del silicio! Sigue los módulos paso a paso.
