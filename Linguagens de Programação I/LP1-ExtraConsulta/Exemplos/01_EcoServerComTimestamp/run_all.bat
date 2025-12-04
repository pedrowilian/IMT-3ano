@echo off
echo ====================================
echo   Iniciando Servidor e Clientes
echo ====================================
echo.
echo Abrindo servidor...
start run_server.bat

timeout /t 2 /nobreak > nul

echo Abrindo cliente GUI 1...
start run_client_gui.bat

timeout /t 1 /nobreak > nul

echo Abrindo cliente GUI 2...
start run_client_gui.bat

echo.
echo Tudo iniciado! Feche esta janela quando terminar.
pause
