@echo off
echo ====================================
echo   Criando JARs - Votacao
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe VotingServer.jar VotingServer VotingServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - VotingServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar VotingServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe VotingClientGUI.jar VotingClientGUI VotingClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - VotingClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar VotingClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
