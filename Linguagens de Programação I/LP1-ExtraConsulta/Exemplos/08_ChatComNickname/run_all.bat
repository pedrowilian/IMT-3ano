@echo off
echo ====================================
echo   Iniciando Chat com Nickname
echo ====================================
echo.
start run_server.bat
timeout /t 2 /nobreak > nul

echo Abrindo 3 clientes...
start run_client_gui.bat
timeout /t 1 /nobreak > nul
start run_client_gui.bat
timeout /t 1 /nobreak > nul
start run_client_gui.bat

echo.
echo Digite um nickname diferente em cada cliente!
pause
