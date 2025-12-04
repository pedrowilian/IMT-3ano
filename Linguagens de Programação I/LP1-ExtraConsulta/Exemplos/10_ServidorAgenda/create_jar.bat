@echo off
echo ====================================
echo   Criando JARs - Servidor Agenda
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe AgendaServer.jar AgendaServer AgendaServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - AgendaServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar AgendaServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe AgendaClientGUI.jar AgendaClientGUI AgendaClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - AgendaClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar AgendaClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
