@echo off
echo ====================================
echo   Criando JARs - Mensagens Privadas
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe PrivateMessageServer.jar PrivateMessageServer PrivateMessageServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - PrivateMessageServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar PrivateMessageServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe PrivateMessageClientGUI.jar PrivateMessageClientGUI PrivateMessageClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - PrivateMessageClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar PrivateMessageClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
