# 🏆 Proyecto Integrador C++: FastKV Storage Engine

> **"Un verdadero ingeniero de sistemas en C++ diseña con RAII, minimiza las copias con semántica de movimiento, garantiza cero fugas de memoria con punteros inteligentes y explota al máximo la STL."**

---

## 🎯 Especificación del Proyecto

El objetivo de este proyecto es construir **FastKV**, un motor de base de datos Clave-Valor en memoria (estilo Redis/RocksDB minimalista) que integra:
1. **Punteros Inteligentes:** Almacenamiento y gestión del ciclo de vida de registros con `std::shared_ptr` y `std::unique_ptr`.
2. **Semántica de Movimiento:** Inserciones $O(1)$ sin duplicación de strings en memoria vía `std::move`.
3. **STL y Plantillas:** Tabla Hash (`std::unordered_map`) de acceso $O(1)$, algoritmos de `<algorithm>` para analítica (filtrado por prefijo, claves más consultadas).
4. **Manejo Robusto de Excepciones:** Jerarquía de errores propios (`ClaveNoEncontradaException`).
5. **Persistencia RAII en Disco:** Guardado de instantáneas (*snapshots*) y recuperación de datos mediante `<fstream>`.

---

## 🚀 Cómo Compilar y Ejecutar

Desde la terminal en esta carpeta:
```powershell
# Compilar todos los archivos fuente
& "C:\MinGW\bin\g++.exe" -std=c++14 -Wall -Wextra -static -static-libgcc -static-libstdc++ main.cpp MotorAlmacenamiento.cpp -o FastKV.exe

# Ejecutar el binario generado
.\FastKV.exe
```

O utilizando el script batch global:
```powershell
..\compilar_cpp.bat 06_Proyecto_Final_Integrador\main.cpp
```
