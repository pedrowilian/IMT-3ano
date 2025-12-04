@echo off
echo ====================================
echo   Iniciando Servidor e Cliente
echo ====================================
echo.
start run_server.bat
timeout /t 2 /nobreak > nul
start run_client_gui.bat
echo.
pause
