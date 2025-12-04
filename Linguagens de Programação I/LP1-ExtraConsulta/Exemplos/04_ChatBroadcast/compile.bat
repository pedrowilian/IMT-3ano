@echo off
echo ====================================
echo   Chat Broadcast
echo ====================================
echo.

echo [1/3] Compilando servidor...
javac ChatServer.java

echo [2/3] Compilando cliente console...
javac ChatClient.java

echo [3/3] Compilando cliente GUI...
javac ChatClientGUI.java

echo.
echo Compilacao concluida!
pause
