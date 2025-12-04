@echo off
echo ====================================
echo   Servidor Reversao de Texto
echo ====================================
echo.

echo [1/3] Compilando servidor...
javac TextReverseServer.java

echo [2/3] Compilando cliente console...
javac TextReverseClient.java

echo [3/3] Compilando cliente GUI...
javac TextReverseClientGUI.java

echo.
echo Compilacao concluida!
pause
