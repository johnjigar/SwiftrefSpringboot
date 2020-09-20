# SwiftrefSpringboot

# Build 
mvn clean package -DskipTests

# Docker deployment 
docker build -t kakarla/swiftref:tagname .

# Docker run 
docker run -p 5000:5000 --env AWS_ACCESS_KEY_ID=id --env AWS_SECRET_ACCESS_KEY=secret kakarla/swiftref:tagname

# Jenkins
Test 1
