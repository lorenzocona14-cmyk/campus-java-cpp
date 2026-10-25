@echo off
chcp 65001 > nul
cls

echo ==============================================================
echo    🎓 INICIANDO CAMPUS LABORATORIO II - UDA (CON STREAMING)
echo ==============================================================
echo.
echo Verificando puertos e iniciando motor de streaming...

:: Cerrar procesos previos en el puerto 3000
for /f "tokens=5" %%a in ('netstat -aon ^| findstr :3000 ^| findstr LISTENING') do taskkill /f /pid %%a >nul 2>&1

:: Iniciar servidor en segundo plano
start "" /b python server.py
timeout /t 2 > nul

:: Abrir navegador en localhost
start http://localhost:3000

echo.
echo [✓] ¡Campus abierto exitosamente en: http://localhost:3000!
echo [✓] Para ver desde celular/tablet en tu misma red Wi-Fi: http://192.168.1.44:3000
echo [✓] Motor de Streaming Nativo ACTIVO (Sin bloqueos de YouTube ni Error 150).
echo.
echo Puedes minimizar esta consola mientras estudias en la web.
pause > nul
