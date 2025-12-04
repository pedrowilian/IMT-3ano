@echo off
echo ====================================
echo   Servidor de Agenda
echo ====================================
echo.

echo [1/3] Compilando servidor...
javac AgendaServer.java

echo [2/3] Compilando cliente console...
javac AgendaClient.java

echo [3/3] Compilando cliente GUI...
javac AgendaClientGUI.java

echo.
echo Compilacao concluida!
pause
