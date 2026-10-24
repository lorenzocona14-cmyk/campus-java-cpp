@echo off
chcp 65001 > nul
cls

echo ==============================================================
echo    🎓 INICIANDO CAMPUS LABORATORIO II - UDA (CON STREAMING)
echo ==============================================================
echo.
echo Liberando puertos anteriores e iniciando motor de streaming...

:: Cerrar cualquier proceso previo en el puerto 3000
for /f "tokens=5" %%a in ('netstat -aon ^| findstr :3000 ^| findstr LISTENING') do taskkill /f /pid %%a >nul 2>&1

:: Iniciar servidor en segundo plano
start "" /b python server.py
timeout /t 2 > nul

:: Abrir navegador
start http://localhost:3000

echo.
echo [v] ¡Campus abierto exitosamente en: http://localhost:3000!
echo [v] Motor de Streaming Nativo ACTIVO (Sin bloqueos de YouTube ni Error 150).
echo.
echo Puedes minimizar esta consola mientras estudias en la web.
timeout /t 5 > nul
