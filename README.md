# project-search-service

The project-search-service service is used to create/update/search/delete the projectRecord on ElasticSearch.

# Prerequisties
kakfa should up and run on 9092

ElasticSearch should up and run on 9206

Please use the ENV variable to change the above configurations

Please use spring profiles to use ENV specific configurations

# To build the project
Use eclipse/intellij or 

manually build the project using maven -> "**mvn clean compile package**" from the project directory

# To start the project manually
java -jar -Dspring.profiles.active=dev ./target/app.jar

# To know more about APIs
use swagger API -> http://localhost:4203/swagger-ui.html


Also, can download image (santhoshas1990/project-search-service:latest) from dokcer hub
