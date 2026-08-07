@echo off

:: Se non c'e` una cartella bin, la creo
if not exist bin mkdir bin

del "bin\*.class"

:: -cp compilo i file presenti nella cartella lib\
:: -d l'output lo metto nella cartella bin
javac -cp ".;lib\*" -d bin src\*.java

:: -cp compilo i file prenseti nella cartella bin e lib
:: Eseguo il main
java -cp "bin;lib\*" TheKnife