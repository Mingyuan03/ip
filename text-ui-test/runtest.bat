@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
REM beware to check current and future refactoring which may inadvertently modify file hierarchy
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\steadylah\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, ERRORLEVEL == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
REM Main file modified from Duke.java to SteadyLah.java, hence the name change below
java -classpath ..\bin SteadyLah < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
