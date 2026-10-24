# Módulo 01: Programación Orientada a Objetos (POO) en Java

## 🎯 Objetivos de Aprendizaje
1. Dominar los 4 pilares: **Encapsulamiento, Abstracción, Herencia y Polimorfismo**.
2. Entender la jerarquía raíz `java.lang.Object` y los métodos `equals()`, `hashCode()` y `toString()`.
3. Distinguir con precisión quirúrgica cuándo usar una **Clase Abstracta** frente a una **Interface**.
4. Conocer las herramientas del Java moderno: **Records** (para DTOs inmutables) y **Sealed Classes** (para jerarquías controladas).

---

## 🏛️ Los 4 Modificadores de Acceso en Java

A diferencia de C++ (que tiene 3: public, protected, private), Java tiene **4 niveles de visibilidad**:

| Modificador | Misma Clase | Mismo Paquete | Subclase (otro paquete) | Todo el Mundo |
| :--- | :---: | :---: | :---: | :---: |
| `private` |  SÍ | NO | NO | NO |
| *(default / package-private)* | SÍ | SÍ | NO | NO |
| `protected` | SÍ | SÍ | SÍ | NO |
| `public` | SÍ | SÍ | SÍ | SÍ |

---

## ⚖️ Clase Abstracta vs Interface

| Criterio | Clase Abstracta | Interface |
| :--- | :--- | :--- |
| **Relación semántica** | *"Es un"* (Is-A). Modela identidad compartida. | *"Capaz de / Cumple con"* (Can-Do). Modela capacidades. |
| **Herencia múltiple** | NO (Java solo permite heredar de 1 clase). | SÍ (Una clase puede implementar múltiples interfaces). |
| **Estado (variables de instancia)** | Puede tener atributos con cualquier visibilidad. | Solo constantes (`public static final`). |
| **Constructores** | SÍ (llamados con `super()` desde la subclase). | NO tiene constructores. |
| **Métodos con cuerpo** | SÍ (métodos ordinarios con código). | SÍ (desde Java 8 con `default` y `static`, Java 9 `private`). |

---

## 📂 Archivos de Código
- [`CuentaBancariaDemo.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/01_Programacion_Orientada_a_Objetos/CuentaBancariaDemo.java): Encapsulamiento, invariantes de clase y buenas prácticas.
- [`PolimorfismoEInterfacesDemo.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/01_Programacion_Orientada_a_Objetos/PolimorfismoEInterfacesDemo.java): Herencia, vinculación dinámica, `super`, e interfaces.
- [`RecordsYSealedDemo.java`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/01_Programacion_Orientada_a_Objetos/RecordsYSealedDemo.java): Records inmutables y jerarquías selladas modernas.
- [`Ejercicios.md`](file:///c:/Users/User/Desktop/Aprendiendo%20a%20programar%20Java%20y%20C++/Java/01_Programacion_Orientada_a_Objetos/Ejercicios.md)
