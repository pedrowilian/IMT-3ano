@echo off
echo ====================================
echo   Iniciando Chat com 3 Clientes
echo ====================================
echo.
echo Abrindo servidor...
start run_server.bat

timeout /t 2 /nobreak > nul

echo Abrindo Cliente 1 (Alice)...
start run_client_gui.bat
timeout /t 1 /nobreak > nul

echo Abrindo Cliente 2 (Bob)...
start run_client_gui.bat
timeout /t 1 /nobreak > nul

echo Abrindo Cliente 3 (Carlos)...
start run_client_gui.bat

echo.
echo ====================================
echo   Chat iniciado!
echo ====================================
echo.
echo Digite nicknames diferentes em cada cliente
echo Use @usuario mensagem para mensagem privada
echo Use /users para ver lista de usuarios
echo.
pause
