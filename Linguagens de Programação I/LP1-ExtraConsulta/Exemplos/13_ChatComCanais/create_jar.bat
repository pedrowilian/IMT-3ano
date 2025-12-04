@echo off
echo ====================================
echo   Criando JARs - Chat Canais
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe ChannelChatServer.jar ChannelChatServer ChannelChatServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - ChannelChatServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar ChannelChatServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe ChannelChatClientGUI.jar ChannelChatClientGUI ChannelChatClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - ChannelChatClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar ChannelChatClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
