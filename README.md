# Rest-Web-server
The project consists of the creation of a Rest Web Server with the function of translator from English to Italian.
The [Rest_Web_service.pdf](Rest_Web_service.pdf "Rest _Web_service.pdf") file describes all the technologies used to develop it.
The *Translator.exe* and *Translator.jar* files are the two executable files created for the project presentation.
The web server was located on the sp-server.sytes.net site, but currently the site is down so to make the code work you need to use a new one.
Inside the folders there is all the code used for the creation of the web server.

## Requirements
The project was developed with java 1.8 on Eclipse.
Php, javascript, and css were used for the Web part.

### Server 
The [Server](Server "Server")  folder contains all the files that are needed for the server part of our Web server. Being the web server developed through Tomcat, we created the Server folder to get a .war file to use on Tomcat. So in addition to the .java files we have inserted make files (to be executed in alphabetical order) that allow you to create a .war file. The server comes, for convenience, contacted in port *8080*  (Tomcat's default port).

### Web authentication
[This](WebAuthentication "This") folder contains the entire "web" part of the project. In fact, through the files in php and javascript, and with the help of a database, there is the possibility to register to the site (same site where the server part is present, but at port *80*) and get the key API in order to use the available clients. You can also return to the site with your credentials in order to get your API key back. Each key API is personal and randomly generated. A password recovery service has also been implemented.

### Client
Within the [Client](Client "Client") folder there are folders containing the .class files, the libraries used, and the client source code. In addition to these folders there are makefiles that allow you to create the executable for the client. Make files are to be used in this order:
1.  makeclean.bat
2.  makedirs.bat
3.  makeclient.bat

You can then launch the client with via the runclient.bat file. The client, that is launched via terminal, allows you to send a word in English to the server and then get its translation in Italian.

### Graphic Client 
Through the extension package Window Builder for Eclipse, we created a graphics window for using the client. The [Graphic Client](GraphicClient "Graphic Client") folder is therefore an eclipse project and then no makefiles have been created.
From this eclipse porget the Translater.jar file was exported and, subsequently, we converted the .jar executable to a .exe executable via the Launch4j program. Both programs are not functional because the site sp-server.sytes.net is down.

