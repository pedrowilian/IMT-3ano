@echo off
echo ====================================
echo   Criando JARs - Multicast Logs
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe LogServer.jar LogServer LogServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - LogServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar LogServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe LogClientGUI.jar LogClientGUI LogClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - LogClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar LogClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
