@echo off
echo ====================================
echo   Criando JARs - Ranking
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe RankingServer.jar RankingServer RankingServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - RankingServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar RankingServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe RankingClientGUI.jar RankingClientGUI RankingClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - RankingClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar RankingClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
