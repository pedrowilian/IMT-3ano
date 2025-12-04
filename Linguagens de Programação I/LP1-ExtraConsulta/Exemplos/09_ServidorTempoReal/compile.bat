@echo off
echo ====================================
echo   Servidor Tempo Real
echo ====================================
echo.

echo [1/3] Compilando servidor...
javac ClockServer.java

echo [2/3] Compilando cliente console...
javac ClockClient.java

echo [3/3] Compilando cliente GUI...
javac ClockClientGUI.java

echo.
echo Compilacao concluida!
pause
