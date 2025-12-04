@echo off
echo ====================================
echo   Criando JARs - Chat Broadcast
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe ChatServer.jar ChatServer ChatServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - ChatServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar ChatServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe ChatClientGUI.jar ChatClientGUI ChatClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - ChatClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar ChatClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
