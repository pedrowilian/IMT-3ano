@echo off
echo ====================================
echo   Criando JARs - Auto Ping
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe PingPongServer.jar PingPongServer PingPongServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - PingPongServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar PingPongServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe AutoPingClientGUI.jar AutoPingClientGUI AutoPingClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - AutoPingClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar AutoPingClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
