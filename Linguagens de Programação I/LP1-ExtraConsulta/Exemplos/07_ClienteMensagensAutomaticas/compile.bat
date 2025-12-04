@echo off
echo ====================================
echo   Cliente Mensagens Automaticas
echo ====================================
echo.

echo [1/3] Compilando servidor...
javac PingPongServer.java

echo [2/3] Compilando cliente console...
javac AutoPingClient.java

echo [3/3] Compilando cliente GUI...
javac AutoPingClientGUI.java

echo.
echo Compilacao concluida!
pause
