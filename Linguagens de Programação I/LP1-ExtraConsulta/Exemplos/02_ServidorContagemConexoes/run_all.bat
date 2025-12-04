@echo off
echo ====================================
echo   Iniciando Servidor e Clientes
echo ====================================
echo.
echo Abrindo servidor...
start run_server.bat

timeout /t 2 /nobreak > nul

echo Abrindo 3 clientes para testar contagem...
start run_client_gui.bat
timeout /t 1 /nobreak > nul
start run_client_gui.bat
timeout /t 1 /nobreak > nul
start run_client_gui.bat

echo.
echo Clique em 'Conectar' em cada cliente!
pause
