set projectLocation=D:\Prashant\Automation\Demo
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\login.xml
pause
