@echo off
echo ====================================
echo   Criando JARs - Operacoes Matematicas
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe MathServer.jar MathServer MathServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - MathServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar MathServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe MathClientGUI.jar MathClientGUI MathClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - MathClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar MathClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
