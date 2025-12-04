@echo off
color 0A
echo ====================================================
echo   GERADOR DE JARs - Todos os 21 Projetos
echo ====================================================
echo.
echo Este script vai criar JARs para TODOS os projetos.
echo.
pause

cd 01_EcoServerComTimestamp
echo.
echo [1/21] Criando JARs - Eco Server...
call create_jar.bat
cd ..

cd 02_ServidorContagemConexoes
echo.
echo [2/21] Criando JARs - Contagem...
call create_jar.bat
cd ..

cd 03_ServidorReversaoTexto
echo.
echo [3/21] Criando JARs - Reversao...
call create_jar.bat
cd ..

cd 04_ChatBroadcast
echo.
echo [4/21] Criando JARs - Chat Broadcast...
call create_jar.bat
cd ..

cd 05_ServidorOperacoesMatematicas
echo.
echo [5/21] Criando JARs - Matematica...
call create_jar.bat
cd ..

cd 06_ServidorArquivos
echo.
echo [6/21] Criando JARs - Arquivos...
call create_jar.bat
cd ..

cd 07_ClienteMensagensAutomaticas
echo.
echo [7/21] Criando JARs - Auto Ping...
call create_jar.bat
cd ..

cd 08_ChatComNickname
echo.
echo [8/21] Criando JARs - Nickname...
call create_jar.bat
cd ..

cd 09_ServidorTempoReal
echo.
echo [9/21] Criando JARs - Clock...
call create_jar.bat
cd ..

cd 10_ServidorAgenda
echo.
echo [10/21] Criando JARs - Agenda...
call create_jar.bat
cd ..

cd 11_ServidorMensagensPrivadas
echo.
echo [11/21] Criando JARs - Mensagens Privadas...
call create_jar.bat
cd ..

cd 12_LeilaoTempoReal
echo.
echo [12/21] Criando JARs - Leilao...
call create_jar.bat
cd ..

cd 13_ChatComCanais
echo.
echo [13/21] Criando JARs - Canais...
call create_jar.bat
cd ..

cd 14_JogoForcaMultiplayer
echo.
echo [14/21] Criando JARs - Forca...
call create_jar.bat
cd ..

cd 15_VotacaoGrupo
echo.
echo [15/21] Criando JARs - Votacao...
call create_jar.bat
cd ..

cd 16_ServidorFilas
echo.
echo [16/21] Criando JARs - Filas...
call create_jar.bat
cd ..

cd 17_MulticastLogs
echo.
echo [17/21] Criando JARs - Logs...
call create_jar.bat
cd ..

cd 18_RankingPontos
echo.
echo [18/21] Criando JARs - Ranking...
call create_jar.bat
cd ..

cd 19_RelogioSincronizado
echo.
echo [19/21] Criando JARs - Ping...
call create_jar.bat
cd ..

cd 20_ChatSilenciamento
echo.
echo [20/21] Criando JARs - Mute...
call create_jar.bat
cd ..

cd 21_BroadcastSensores
echo.
echo [21/21] Criando JARs - Sensores...
call create_jar.bat
cd ..

echo.
echo ====================================================
echo   TODOS OS 42 JARs CRIADOS COM SUCESSO!
echo   (21 Servidores + 21 Clientes GUI)
echo ====================================================
echo.
pause
