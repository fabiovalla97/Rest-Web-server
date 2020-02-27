cd src
javac -d ../service/WEB-INF/classes -cp ../service/WEB-INF/lib/*;. core/*.java
cd ..
cd service
jar cvf ../RestDict-key.war *
cd ..


