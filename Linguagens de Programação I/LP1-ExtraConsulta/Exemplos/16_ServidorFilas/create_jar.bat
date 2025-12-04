@echo off
echo ====================================
echo   Criando JARs - Servidor Filas
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe QueueServer.jar QueueServer QueueServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - QueueServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar QueueServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe QueueClientGUI.jar QueueClientGUI QueueClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - QueueClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar QueueClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
