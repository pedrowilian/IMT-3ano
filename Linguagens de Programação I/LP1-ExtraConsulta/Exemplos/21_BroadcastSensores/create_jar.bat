@echo off
echo ====================================
echo   Criando JARs - Sensores
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe SensorServer.jar SensorServer SensorServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - SensorServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar SensorServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe SensorClientGUI.jar SensorClientGUI SensorClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - SensorClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar SensorClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
