@echo off
echo ====================================
echo   Criando JARs - Leilao
echo ====================================
echo.

echo [1/2] Criando JAR do Servidor...
jar cfe AuctionServer.jar AuctionServer AuctionServer*.class
if %ERRORLEVEL% EQU 0 (
    echo    - AuctionServer.jar criado com sucesso!
) else (
    echo    - ERRO ao criar AuctionServer.jar
)

echo.
echo [2/2] Criando JAR do Cliente GUI...
jar cfe AuctionClientGUI.jar AuctionClientGUI AuctionClientGUI.class
if %ERRORLEVEL% EQU 0 (
    echo    - AuctionClientGUI.jar criado com sucesso!
) else (
    echo    - ERRO ao criar AuctionClientGUI.jar
)

echo.
echo ====================================
echo   JARs criados!
echo ====================================
pause
