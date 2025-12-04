@echo off
echo ====================================
echo   Iniciando Chat com 3 Clientes
echo ====================================
echo.
echo Abrindo servidor...
start run_server.bat

timeout /t 2 /nobreak > nul

echo Abrindo Cliente 1...
start run_client_gui.bat
timeout /t 1 /nobreak > nul

echo Abrindo Cliente 2...
start run_client_gui.bat
timeout /t 1 /nobreak > nul

echo Abrindo Cliente 3...
start run_client_gui.bat

echo.
echo Chat iniciado! Digite nomes diferentes em cada cliente.
pause
