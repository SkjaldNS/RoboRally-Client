# Roborally - Client Server Game with Rest API

The client and server can be run and compiled in two ways:
1. [Using maven with the terminal](#maven)
2. [Using IntelliJ IDEA](#intellij-idea)

You might also be interested in running multiple clients at the same time. This will also be explained in both the [Intellij IDEA](#intellij-idea) and [Maven](#maven).

## Maven
To both compile and run the client and the server with Maven, you need to have [Maven](https://maven.apache.org/install.html) installed first.

### How to compile and run the client
To compile and run the client the following steps are needed:
1. Download the project from the repository
2. Open the newly downloaded project in a terminal
3. Write the following commands:
```bash
cd client
mvn javafx:run
```

#### Running multiple clients
To run multiple clients at the same time, you need to open a new terminal and repeat the steps for running the client. This can be done as many times as you want.
```bash
mvn javafx:run
```

### How to compile and run the server
1. Download the project from the repository
2. Open the newly downloaded project in a terminal
3. Write the following commands:
```bash
cd server
mvn spring-boot:run
```

## IntelliJ IDEA
To compile and run the client and the server with IntelliJ IDEA, you need to have [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) installed first.

The first step for both the client and server is to download the project from the repository
## Client
### How to compile and run the client
To compile and run the client the following steps are needed:
1. In the newly downloaded project, open the client folder in IntelliJ IDEA
2. Navigate to the path src/main/java/dk/dtu/compute/se/pisd/roborally
3. Right-click on the StartRoboRally.java file and select Run 'StartRoboRally.main()'

#### Running multiple clients
To run multiple clients at the same time, you need to change the configuration of the client in IntelliJ IDEA. This can be done by following these steps:
1. Navigate to the top right corner of IntelliJ IDEA
2. Click on the dropdown menu next to the green play button
3. Click on Edit Configurations
4. For the StartRoboRally configuration, click on 'Modify options'
5. Choose the option 'Allow multiple instances' and apply the changes

After these steps, you can run the client multiple times by clicking on the green play button multiple times.

## Server
### How to run the server
To run the server the following steps are needed:
1. In the newly downloaded project, open the server folder in IntelliJ IDEA
2. Navigate to the path src/main/java/dk/dtu/roborally_server
3. Right-click on the RoborallyServerApplication.java file and select Run 'RoborallyServerApplication.main()'

