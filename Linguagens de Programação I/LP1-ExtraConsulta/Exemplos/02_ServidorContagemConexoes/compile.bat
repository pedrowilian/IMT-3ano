@echo off
echo ====================================
echo   Servidor Contagem de Conexoes
echo ====================================
echo.

echo [1/3] Compilando servidor...
javac ConnectionCounterServer.java

echo [2/3] Compilando cliente console...
javac ConnectionCounterClient.java

echo [3/3] Compilando cliente GUI...
javac ConnectionCounterClientGUI.java

echo.
echo Compilacao concluida!
pause
