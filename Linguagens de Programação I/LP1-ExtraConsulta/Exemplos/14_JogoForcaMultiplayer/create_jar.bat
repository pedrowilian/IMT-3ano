@echo off
echo ====================================
echo   Criando JARs - Jogo Forca
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe HangmanServer.jar HangmanServer HangmanServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - HangmanServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar HangmanServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe HangmanClientGUI.jar HangmanClientGUI HangmanClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - HangmanClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar HangmanClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
