@echo off
echo ====================================
echo   Criando JARs - Reversao Texto
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe TextReverseServer.jar TextReverseServer TextReverseServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - TextReverseServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar TextReverseServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe TextReverseClientGUI.jar TextReverseClientGUI TextReverseClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - TextReverseClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar TextReverseClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
