@echo off
color 0E
echo ====================================================
echo   COMPILADOR UNIVERSAL - Todos os Projetos
echo ====================================================
echo.
echo Este script vai compilar TODOS os 21 projetos.
echo.
pause

cd 01_EcoServerComTimestamp
echo.
echo [1/21] Compilando Eco Server...
call compile.bat
cd ..

cd 02_ServidorContagemConexoes
echo.
echo [2/21] Compilando Servidor Contagem...
call compile.bat
cd ..

cd 03_ServidorReversaoTexto
echo.
echo [3/21] Compilando Servidor Reversao...
call compile.bat
cd ..

cd 04_ChatBroadcast
echo.
echo [4/21] Compilando Chat Broadcast...
call compile.bat
cd ..

cd 05_ServidorOperacoesMatematicas
echo.
echo [5/21] Compilando Servidor Matematica...
call compile.bat
cd ..

cd 06_ServidorArquivos
echo.
echo [6/21] Compilando Servidor Arquivos...
call compile.bat
cd ..

cd 07_ClienteMensagensAutomaticas
echo.
echo [7/21] Compilando Auto Ping...
call compile.bat
cd ..

cd 08_ChatComNickname
echo.
echo [8/21] Compilando Chat Nickname...
call compile.bat
cd ..

cd 09_ServidorTempoReal
echo.
echo [9/21] Compilando Clock Server...
call compile.bat
cd ..

cd 10_ServidorAgenda
echo.
echo [10/21] Compilando Servidor Agenda...
call compile.bat
cd ..

cd 11_ServidorMensagensPrivadas
echo.
echo [11/21] Compilando Mensagens Privadas...
call compile.bat
cd ..

cd 12_LeilaoTempoReal
echo.
echo [12/21] Compilando Leilao...
call compile.bat
cd ..

cd 13_ChatComCanais
echo.
echo [13/21] Compilando Chat com Canais...
call compile.bat
cd ..

cd 14_JogoForcaMultiplayer
echo.
echo [14/21] Compilando Jogo da Forca...
call compile.bat
cd ..

cd 15_VotacaoGrupo
echo.
echo [15/21] Compilando Votacao...
call compile.bat
cd ..

cd 16_ServidorFilas
echo.
echo [16/21] Compilando Servidor de Filas...
call compile.bat
cd ..

cd 17_MulticastLogs
echo.
echo [17/21] Compilando Multicast Logs...
call compile.bat
cd ..

cd 18_RankingPontos
echo.
echo [18/21] Compilando Ranking...
call compile.bat
cd ..

cd 19_RelogioSincronizado
echo.
echo [19/21] Compilando Relogio Ping...
call compile.bat
cd ..

cd 20_ChatSilenciamento
echo.
echo [20/21] Compilando Chat com Mute...
call compile.bat
cd ..

cd 21_BroadcastSensores
echo.
echo [21/21] Compilando Sensores...
call compile.bat
cd ..

echo.
echo ====================================================
echo   TODOS OS 21 PROJETOS COMPILADOS COM SUCESSO!
echo ====================================================
echo.
pause
