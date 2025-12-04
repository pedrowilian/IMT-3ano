@echo off
echo ====================================
echo   Iniciando Clock Server
echo ====================================
echo.
start run_server.bat
timeout /t 2 /nobreak > nul

echo Abrindo 2 clientes para ver hora sincronizada...
start run_client_gui.bat
timeout /t 1 /nobreak > nul
start run_client_gui.bat

echo.
pause
