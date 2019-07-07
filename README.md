<h1>Integration Server</h1>
<h2>SOAP Client</h2>
<p>Generation classes</p>
<p>${JAVA_HOME}/bin/xjc -wsdl http://localhost:8080/ws/countries.wsdl -d ${project-directory}/src/main/java -p com.alekseysamoylov.integration.soap</p>
