#cd actuator-admin-ui
#mvn clean package -DskipTests
#cd ..

cd customer-service
mvn clean package -DskipTests
cd ..

cd auth-service
mvn clean package -DskipTests
cd ..