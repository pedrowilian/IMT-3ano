@echo off
echo ====================================
echo   Criando JARs - Chat Nickname
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe NicknameChatServer.jar NicknameChatServer NicknameChatServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - NicknameChatServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar NicknameChatServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe NicknameChatClientGUI.jar NicknameChatClientGUI NicknameChatClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - NicknameChatClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar NicknameChatClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
