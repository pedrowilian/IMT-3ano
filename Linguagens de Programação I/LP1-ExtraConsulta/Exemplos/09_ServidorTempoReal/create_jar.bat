@echo off
echo ====================================
echo   Criando JARs - Clock Server
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe ClockServer.jar ClockServer ClockServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - ClockServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar ClockServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe ClockClientGUI.jar ClockClientGUI ClockClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - ClockClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar ClockClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
