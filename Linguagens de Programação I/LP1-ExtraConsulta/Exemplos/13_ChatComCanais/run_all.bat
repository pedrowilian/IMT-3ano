@echo off
start run_server.bat
timeout /t 2 /nobreak > nul
start run_client_gui.bat
timeout /t 1 /nobreak > nul
start run_client_gui.bat
timeout /t 1 /nobreak > nul
start run_client_gui.bat
pause
