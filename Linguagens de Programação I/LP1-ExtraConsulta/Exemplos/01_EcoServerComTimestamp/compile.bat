@echo off
echo ====================================
echo   Eco Server com Timestamp
echo ====================================
echo.

REM Compilar
echo [1/3] Compilando servidor...
javac EcoServer.java
if %errorlevel% neq 0 (
    echo Erro ao compilar servidor!
    pause
    exit /b 1
)

echo [2/3] Compilando cliente console...
javac EcoClient.java
if %errorlevel% neq 0 (
    echo Erro ao compilar cliente!
    pause
    exit /b 1
)

echo [3/3] Compilando cliente GUI...
javac EcoClientGUI.java
if %errorlevel% neq 0 (
    echo Erro ao compilar cliente GUI!
    pause
    exit /b 1
)

echo.
echo Compilacao concluida com sucesso!
echo.
pause
