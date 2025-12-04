@echo off
echo ====================================
echo   Criando JARs - Contagem Conexoes
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe ConnectionCounterServer.jar ConnectionCounterServer ConnectionCounterServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - ConnectionCounterServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar ConnectionCounterServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe ConnectionCounterClientGUI.jar ConnectionCounterClientGUI ConnectionCounterClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - ConnectionCounterClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar ConnectionCounterClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
