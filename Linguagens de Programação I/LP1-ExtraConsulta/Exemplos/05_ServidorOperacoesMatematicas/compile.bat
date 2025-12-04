@echo off
echo ====================================
echo   Servidor Operacoes Matematicas
echo ====================================
echo.

echo [1/3] Compilando servidor...
javac MathServer.java

echo [2/3] Compilando cliente console...
javac MathClient.java

echo [3/3] Compilando cliente GUI...
javac MathClientGUI.java

echo.
echo Compilacao concluida!
pause
