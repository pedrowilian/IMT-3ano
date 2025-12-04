@echo off
echo ====================================
echo   Chat com Nickname
echo ====================================
echo.

echo [1/3] Compilando servidor...
javac NicknameChatServer.java

echo [2/3] Compilando cliente console...
javac NicknameChatClient.java

echo [3/3] Compilando cliente GUI...
javac NicknameChatClientGUI.java

echo.
echo Compilacao concluida!
pause
