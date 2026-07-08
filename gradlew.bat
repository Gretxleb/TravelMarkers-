@echo off
set DIR=%~dp0
if "%~1"=="" (
  echo Gradle Wrapper not available. Please install Android Studio or Gradle.
  exit /b 1
)

where java >nul 2>nul
if errorlevel 1 (
  echo Java not found. Install JDK 17.
  exit /b 1
)

set JAVA_EXE=java.exe
set JAVA_HOME=%JAVA_HOME%
if not "%JAVA_HOME%"=="" (
  set JAVA_EXE=%JAVA_HOME%\bin\java.exe
)

if not exist "%JAVA_EXE%" (
  echo Java executable not found at %JAVA_EXE%
  exit /b 1
)

"%JAVA_EXE%" -cp "%DIR%\gradle\wrapper\gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain %*
