cmake_minimum_required(VERSION 3.10)
project(FastKV VERSION 1.0.0 LANGUAGES CXX)

# Exigir C++14 o superior
set(CMAKE_CXX_STANDARD 14)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

# Fuentes del proyecto FastKV
set(FUENTES
    main.cpp
    MotorAlmacenamiento.cpp
)

# Definir el ejecutable
add_executable(FastKV ${FUENTES})

# Añadir cabeceras al target
target_include_directories(FastKV PRIVATE ${CMAKE_CURRENT_SOURCE_DIR})

# Flags de compilación rigurosos
if(MSVC)
    target_compile_options(FastKV PRIVATE /W4 /WX-)
else()
    target_compile_options(FastKV PRIVATE -Wall -Wextra -pedantic)
    # Enlace estático en GCC/MinGW para binarios autocontenidos
    target_link_options(FastKV PRIVATE -static -static-libgcc -static-libstdc++)
endif()
