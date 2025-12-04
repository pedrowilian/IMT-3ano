@echo off
echo ====================================
echo   Criando JARs - Chat Mute
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe MuteServer.jar MuteServer MuteServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - MuteServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar MuteServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe MuteClientGUI.jar MuteClientGUI MuteClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - MuteClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar MuteClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
