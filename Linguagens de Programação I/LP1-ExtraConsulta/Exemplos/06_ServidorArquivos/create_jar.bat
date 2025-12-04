@echo off
echo ====================================
echo   Criando JARs - Servidor Arquivos
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe FileServer.jar FileServer FileServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - FileServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar FileServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe FileClientGUI.jar FileClientGUI FileClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - FileClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar FileClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
