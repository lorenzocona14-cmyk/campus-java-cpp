# Módulo 02: Estructuras de Datos y Java Collections Framework (JCF)

## 🎯 Objetivos de Aprendizaje
1. Dominar la jerarquía fundamental del **Java Collections Framework**: `Collection` (`List`, `Set`, `Queue`) y `Map`.
2. Analizar la complejidad algorítmica ($O(1)$, $O(\log n)$, $O(n)$) para tomar decisiones de arquitectura fundamentadas.
3. Entender la mecánica interna de `HashMap`: buckets, factor de carga (*load factor* 0.75) y colisiones con árboles rojo-negro.
4. Comprender y respetar el sagrado contrato entre `equals()` y `hashCode()`.

---

## 🗺️ Mapa Mental del Framework

```
                          java.lang.Iterable<E>
                                    │
                                    ▼
                         java.util.Collection<E>
                       /            │            \
                      /             │             \
                     ▼              ▼              ▼
                 List<E>         Set<E>         Queue<E>
               ┌─────────┐     ┌─────────┐      ┌──────────────┐
               │ArrayList│     │HashSet  │      │ArrayDeque    │
               │Link.List│     │TreeSet  │      │PriorityQueue │
               └─────────┘     └─────────┘      └──────────────┘

                               Map<K, V>  (No hereda de Collection)
                               ┌─────────┐
                               │HashMap  │
                               │TreeMap  │
                               └─────────┘
```

---

## 📊 Matriz de Complejidad Temporal (Big-O)

| Estructura | Acceso por Índice | Búsqueda | Inserción / Eliminación | Orden Garantizado |
| :--- | :---: | :---: | :---: | :---: |
| **`ArrayList`** | **$O(1)$** | $O(n)$ | $O(1)$ al final / $O(n)$ en medio | Orden de inserción |
| **`LinkedList`** | $O(n)$ | $O(n)$ | **$O(1)$** en extremos | Orden de inserción |
| **`HashSet`** | N/A | **$O(1)$** prom. | **$O(1)$** prom. | Sin orden |
| **`TreeSet`** | N/A | **$O(\log n)$** | **$O(\log n)$** | Orden natural / Comparator |
| **`HashMap`** | N/A | **$O(1)$** prom. | **$O(1)$** prom. | Sin orden |
| **`TreeMap`** | N/A | **$O(\log n)$** | **$O(\log n)$** | Ordenado por clave |

---

## ⚠️ El Contrato Sagrado: `equals()` y `hashCode()`
Si dos objetos son iguales según `equals(Object o)`:
$$\text{obj1.equals(obj2)} \implies \text{obj1.hashCode() == obj2.hashCode()}$$
Si rompes esta regla, los objetos desaparecerán de los `HashSet` y `HashMap`, provocando fugas de memoria o búsquedas fallidas.

---

## 📂 Archivos de Código
- [`ListasYOrdenamientoDemo.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/02_Estructuras_de_Datos_y_Colecciones/ListasYOrdenamientoDemo.java)
- [`ConjuntosYContratoHashDemo.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/02_Estructuras_de_Datos_y_Colecciones/ConjuntosYContratoHashDemo.java)
- [`MapasYColasDemo.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/02_Estructuras_de_Datos_y_Colecciones/MapasYColasDemo.java)
- [`Ejercicios.md`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/02_Estructuras_de_Datos_y_Colecciones/Ejercicios.md)
