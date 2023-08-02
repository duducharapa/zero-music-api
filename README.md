# Zero Music API

<div style="text-align: center; margin-bottom: 10px;">
    <img src="https://img.shields.io/badge/category-portfolio-blue" alt="Project category">
    <img src="https://img.shields.io/badge/status-working-green" alt="Project status">
</div>
<div style="text-align: center; margin-bottom: 10px;">
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17">
    <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot 3">
</div>

This API is part of a portfolio project called **Zero Music**: a music player focused on anime musics.

---

## Technologies
Beside the use of **Java 17** as language and **Spring Boot 3** as framework, here are some of others technologies envolved on project:

### Spring ecosystem
- **Spring Data + Hibernate** (Entity management)
- **Spring Validation** (Request data validation)
### Integrated services
- **Splunk Enterprise** (Centered application logging)
- **Localstack S3** (Simulated [AWS S3](https://aws.amazon.com/pt/s3/) resource)
### Others
- **Mockito** (Unit testing)
- **Springdoc** ([Swagger and OpenAPI](https://swagger.io/) implementation)
- **H2** (In-memory database)
- **Docker and Docker-compose** (System deployment portability)

---

## Installation
The prerequisites to install the environment are:
- Docker and Docker-compose
- awslocal or your own AWS S3 bucket

If you have your own bucket on AWS S3, change the credentials and S3 URL on environment variables of **api** service on ```docker-compose.yml```.

### Docker-compose
Just run the following command on root directory:

> docker-compose up -d

After all containers are started, if you don't have a AWS S3 bucket, use the ```awslocal``` to create one using the command below:

> awslocal s3api create-bucket --bucket zero-music-bucket

Now, your application is completely up to run! 

---

## Using the API
You can choose any HTTP client to communicate with API using [this link](http://localhost:9000/), but here are some options provided by application:

- Use the **Swagger UI** try functionality
- Use the **Insomnia** client (import the config inside ```.insomnia/``` directory)

The documentation about the endpoints avaliable can be acessed by Swagger's application [using this link](http://localhost:9000/swagger-ui/index.html).
