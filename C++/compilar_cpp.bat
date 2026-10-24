@echo off
setlocal enabledelayedexpansion
chcp 65001 > nul
cls

echo ======================================================
echo    ⚡ COMPILADOR Y EJECUTOR RÁPIDO DE C++ (GCC/MinGW)
echo ======================================================
echo.

if "%~1"=="" (
    echo [!] Uso: compilar_cpp.bat ^<Ruta_Al_Archivo.cpp^>
    echo.
    echo Ejemplo:
    echo   .\compilar_cpp.bat 00_Fundamentos_y_Sintaxis\01_hola_mundo.cpp
    echo.
    pause
    exit /b 1
)

:: Asegurar que las herramientas y DLLs de MinGW estén en el PATH de este proceso
set "PATH=C:\MinGW\bin;C:\MinGW\mingw32\bin;%PATH%"

set "FILE=%~1"
set "DIR=%~dp1"
set "FILENAME=%~n1"
set "OUTPUT=%DIR%%FILENAME%.exe"

echo [+] Compilando con GCC:
echo     Fuente:  %FILE%
echo     Salida:  %OUTPUT%
echo     Norma:   C++14 (-std=c++14 -Wall -Wextra -static)
echo ------------------------------------------------------
echo.

g++ -std=c++14 -Wall -Wextra -static -static-libgcc -static-libstdc++ "%FILE%" -o "%OUTPUT%"

if %errorlevel% neq 0 (
    echo.
    echo [X] Error de compilacion detectado.
    pause
    exit /b %errorlevel%
)

echo [v] Compilacion exitosa. Ejecutando programa...
echo ======================================================
echo.

"%OUTPUT%"

echo.
echo ======================================================
echo [v] Ejecucion finalizada con exito.
echo.
pause
