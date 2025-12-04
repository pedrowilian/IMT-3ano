@echo off
echo ====================================
echo   Criando JARs - Eco Server
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe EcoServer.jar EcoServer EcoServer.class
if %ERRORLEVEL% EQU 0 (
    echo    - EcoServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar EcoServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe EcoClientGUI.jar EcoClientGUI EcoClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - EcoClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar EcoClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo   Execute: java -jar EcoServer.jar
echo            java -jar EcoClientGUI.jar
echo ====================================
pause
