@echo off
echo ====================================
echo   TESTE - Chat Mensagens Privadas
echo ====================================
echo.
echo Este script vai demonstrar o funcionamento:
echo.
echo 1. Servidor sera iniciado
echo 2. Tres clientes GUI serao abertos
echo 3. Digite nicknames DIFERENTES em cada cliente
echo    Sugestao: Alice, Bob, Carlos
echo.
echo 4. Teste os comandos:
echo    - "Ola a todos!" (broadcast)
echo    - "@Alice Oi Alice!" (privado)
echo    - "/users" (listar usuarios)
echo.
pause

echo.
echo [1/4] Iniciando servidor...
start "Servidor" run_server.bat

timeout /t 3 /nobreak > nul

echo [2/4] Abrindo Cliente 1 - Digite: Alice
start "Cliente 1" run_client_gui.bat

timeout /t 2 /nobreak > nul

echo [3/4] Abrindo Cliente 2 - Digite: Bob
start "Cliente 2" run_client_gui.bat

timeout /t 2 /nobreak > nul

echo [4/4] Abrindo Cliente 3 - Digite: Carlos
start "Cliente 3" run_client_gui.bat

echo.
echo ====================================
echo   TUDO PRONTO!
echo ====================================
echo.
echo Instrucoes:
echo 1. Digite um nickname DIFERENTE em cada janela
echo 2. Clique em "Conectar"
echo 3. Teste mensagens broadcast (sem @)
echo 4. Teste mensagens privadas (@usuario mensagem)
echo 5. Clique em "Atualizar Usuarios" para ver a lista
echo.
echo Feche esta janela quando terminar os testes.
pause
