# P6-Full-Stack-reseau-dev MDD application

MDD is a social network when users can post articles about software development, subscribe/unsubscribe to topics and post comments on each article. The application consists of combining the front-end and back-end part using an API.

## DB/SQL

### Technologies

- MySQL Workbench 8.0.34

### Installation

- Install [MySQL workbench](https://www.mysql.com/products/workbench/).
- connect to mysql with your credentials,
- create a database `CREATE DATABASE mddapi;`,
- import and excute the SQL scripts `mdd.sql` and `mdd-samples.sql` in the project directory : \Developpez-une-application-full-stack-complete\back\src\main\resources\ .

## Front-end

### Technologies

- Angular 17.1.1
- Node 20.10.0
- Npm 10.4.0

### Installation

First install a node version manager like [nvm](https://github.com/nvm-sh/nvm#installing-and-updating).

Execute `nvm install 20.10.0` to install Node and npm.

Execute `npm install -g @angular/17.1.1` to install Angular.

Run `npm install` to install all the dependencies.

### Run

To run the app, execute `npm run start`.

In a browser go to `http://localhost:4200` to use the app (IMPORTANT: start the back-end before !).

### Use

Begin by register yourself on the app and login.

Some topics are already available for subscribing in at `http://localhost:4200/topics`.

Go to `http://localhost:4200/posts`, you can now publish an article selecting a topic.

## Back-end

### Technologies

- Spring Boot 3.2.1
- Java 17
- Maven 3.8.6

### Installation

First install [Java](https://www.oracle.com/fr/java/technologies/downloads/#java17) according your operating system.

Then install [Maven](https://maven.apache.org/install.html).

### Run

- Clone this project https://github.com/OpenClassrooms-Student-Center/Developpez-une-application-full-stack-complete.git
- Enter the 'back' folder
- In the root directory execute `mvn clean verify` in order to compile, test and package the archive.
- Type `mvn install`
- You can use an app like Postman or Swagger to test the endpoints.
- To see the API documentation, when this app is launched, in your web browser go to `http://localhost:9000/api/swagger-ui/index.html`

## Author

Fatma Zammel
