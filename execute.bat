@ECHO OFF
cd .\build\install\ex01BolanosJosue
START java -Dserver.port=7001 -classpath lib/* com.distribuida.Servidor
START java -Dserver.port=8001 -classpath lib/* com.distribuida.Servidor
START java -Dserver.port=9001 -classpath lib/* com.distribuida.Servidor
START java -Dserver.port=9191 -classpath lib/* com.distribuida.Servidor