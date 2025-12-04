@echo off
echo Iniciando Leilao com 3 participantes...
start run_server.bat
timeout /t 2 /nobreak > nul
start run_client_gui.bat
timeout /t 1 /nobreak > nul
start run_client_gui.bat
timeout /t 1 /nobreak > nul
start run_client_gui.bat
echo Leilao iniciado!
pause
