@echo off
echo ====================================
echo   Criando JARs - Relogio Ping
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe PingServer.jar PingServer PingServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - PingServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar PingServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe PingClientGUI.jar PingClientGUI PingClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - PingClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar PingClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
