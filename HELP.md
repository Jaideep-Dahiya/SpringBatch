# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.3/maven-plugin/reference/html/#build-image)
* [Spring Batch](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/index.html#howto.batch)

### Guides
The following guides illustrate how to use some features concretely:

* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)


# FIRST COMMIT (basic tasklet)
1. schema-mysql.sql file taken from spring batch core and inserted to docker from dockerfile
2. docker exec -i -t container-name-here sh | command before for interactive access
3. mysql -u root -p password for checking things

# SECOND COMMIT (added restart-ability)
1. added unique identifying job parameters so that it can run again and again

# THIRD COMMIT (flat file reader writer)
1. added csv file reading and text file output