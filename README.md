Coverage: 63%
# Enemy Bestiary

A Spring Boot project thar handles the detailing both normal and custom enemies based of the Mario & Luigi series of games.

## Getting Started

1. Sownload and install the latest version of [Git](https://git-scm.com/downloads) for your respective platform.
2. Open a terminal (GIT Bash for Windows users) and navigate to the directory you want the code to rest on your system
3. Type in `git clone https://github.com/LamarrRD-QA/Enemy-Bestiary.git` and execute
4. You should now have a local version of this application for personal use.

### Prerequisites

- Maven - 3.x series (latest version is 3.8.1 at time of writing)
- Jave Development Kit - 11 or later (OpenJDK preferred but other distributions may work).
- MySQL - 5.7 or later.
- Selenium WebDriver (Chrome) for running integration tests.

### Installing

1. Download and install the Java Development Kit for your platform
2. Download and install Maven for your platform
3. Download and install MySQL  for your platform
4. Navigate to `src/main/resources/application-prod.yml` and open this file in your favourite text editor.
5. Edit the database location, username and password to the ones associated with your setup and save.
6. Navigate back to the root directory where this file is and open a command prompt from this directory.
6. Run `mvn clean package` to start afresh, remove any unnecessary artefacts and compile into a JAR executable.
7. Run the JAR file from the command line with `java -jar EnemyBestiary-0.0.1-SNAPSHOT.jar`


End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

Explain what these tests test, why and how to run them

```
Give an example
```

### Integration Tests 
Explain what these tests test, why and how to run them
Selenium

```
Give an example
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Lamarr Redhead-Davis** - *Project Execution* - [LamarrRD-QA](https://github.com/LamarrRD-QA/)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

[Axios](https://github.com/axios/axios)
[Bootstrap](https://getbootstrap.com/)
[Bootstrap Icons](https://icons.getbootstrap.com/)
[Enemy images/details](https://www.mariowiki.com/Mario_%26_Luigi:_Superstar_Saga_bestiary)
* Inspiration
* etc