# Products
#### Single Page Application powered by Spring framework and Vue.js.

## Technologies
Back-end:
* Spring framework
* Assembly by Maven

Front-end:
* Vue.js, Vue Router, Vuex
* Vue Bootstrap
* Assembly by WebPack

## System requirements

For manual setup:
* Java 8
* Hibernate compatible relational database (_PostgreSQL_ recommended)
* Maven for building

For Docker setup (in case you already obtain a JAR):
* Docker (surprisingly :))

## Getting started
#### Manually

1. `git clone https://github.com/D3lph1/Products.git`
2. `mvn clean install`
3. `java -jar practice-0.0.1-SNAPSHOT.jar`

#### Docker
1. `git clone https://github.com/D3lph1/Products.git`
2. Change database connection host to `postgres` in the application.properties
2. Build application to obtain JAR file (see above)
3. `docker-compose up -d`

## Screenshots
![Products list](https://i.ibb.co/XV667by/products-list.png)
![Update product](https://i.ibb.co/d7FfGHQ/products-update.png)
![Add offfer](https://i.ibb.co/VTYTpJJ/add-offer.png)


## License

This is an open-sourced software licensed under the MIT license.
