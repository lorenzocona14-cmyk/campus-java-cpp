@echo off
setlocal enabledelayedexpansion
chcp 65001 > nul
cls

echo ======================================================
echo    ☕ COMPILADOR Y EJECUTOR RÁPIDO DE JAVA
echo ======================================================
echo.

if "%~1"=="" (
    echo [!] Uso: compilar_y_ejecutar.bat ^<Ruta_Al_Archivo.java^>
    echo.
    echo Ejemplo:
    echo   .\compilar_y_ejecutar.bat 00_Fundamentos_y_Sintaxis\HolaMundo.java
    echo.
    pause
    exit /b 1
)

set FILE=%~1
set DIR=%~dp1
set FILENAME=%~n1

echo [+] Ejecutando: %FILE%
echo ------------------------------------------------------
echo.

java "%FILE%"

echo.
echo ------------------------------------------------------
echo [v] Ejecucion finalizada con exito.
echo.
pause
