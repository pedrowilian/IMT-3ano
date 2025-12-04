@echo off
echo ====================================
echo   Servidor de Arquivos
echo ====================================
echo.

echo [1/3] Compilando servidor...
javac FileServer.java

echo [2/3] Compilando cliente console...
javac FileClient.java

echo [3/3] Compilando cliente GUI...
javac FileClientGUI.java

echo.
echo Compilacao concluida!
pause
