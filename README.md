# public-holiday-consumer
Spring Boot Java application which consumes from the Public Holiday API made by Nager Date.

# How to run

## Prerequisites

### Java (JDK 17)

**Windows**

- Download Java from [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Set `JAVA_HOME` environment variable on `sysdm.cpl`

**Linux**

Run these commands on the terminal:

```
sudo apt update
sudo apt install openjdk-17-jdk
```

### Maven

**Windows**

- Download Maven from [Apache](https://maven.apache.org/download.cgi)
- Add the Maven instalation to `PATH` on `sysdm.cpl`

**Linux**

Run on the terminal: `sudo apt install maven`

## Running the program

After cloning the repository, open a terminal in the repository's folder and run the following command:

```
mvn install
```

After finishing the install cycle, a `target` folder will appear on the repository folder. 
Run the following command there:

```
java -jar [.jar file]
```

Replace `[.jar file]` with the generated .jar, ex. `public-holiday-0.0.1-SNAPSHOT.jar`.

After the project has launched, you may make requests through your preferred tool to the following endpoints:

- /getLastThreeHolidays/{countryCode}
- /getNumberOfHolidaysOnWeek/{year}?countryCodes=
- /getNumberOfHolidaysOnWeekWithCountries/{year}?countryCodes=
- /getCommonHolidays/{year}/{firstCountryCode}/{secondCountryCode}

The most convenient one is be through /swagger-ui/index.html, which can be directly accessed from your browser 
using this [link](http://localhost:8080/swagger-ui/index.html) with the default project configuration.

### Get last three holidays

This endpoint returns the last three holidays from a country expressed as its ISO 3166-1 alpha-2 Country Code.

Example with Spain: 

[http://localhost:8080/getLastThreeHolidays/ES](http://localhost:8080/getLastThreeHolidays/ES)

### Get number of holidays on weekdays (including country codes)

This endpoint returns the number of holidays on weekdays from a selected year and
any number of countries each expressed as its ISO 3166-1 alpha-2 country code separated by commas,
then ordered from highest to lowest vacation count and highest to lowest in country code alphabetical order.

Example in 2025 with Great Britain, Spain, Portugal, France, The Netherlands,
Argentina, Vatican City, Italy and Austria:

[http://localhost:8080/getNumberOfHolidaysOnWeekWithCountries/2025?countryCodes=GB,ES,PT,FR,NL,AR,VA,IT,DE,AT](http://localhost:8080/getNumberOfHolidaysOnWeekWithCountries/2025?countryCodes=GB,ES,PT,FR,NL,AR,VA,IT,DE,AT)

### Get raw number of holidays on weekdays

Same as the endpoint before but it just returns the holiday count for each country in descending order:

Example:

[http://localhost:8080/getNumberOfHolidaysOnWeek/2025?countryCodes=GB,ES,PT,FR,NL,AR,VA,IT,DE,AT](http://localhost:8080/getNumberOfHolidaysOnWeek/2025?countryCodes=GB,ES,PT,FR,NL,AR,VA,IT,DE,AT)

### Get common holidays between two countries

This endpoint returns the number of holidays ocurring on the same date in a given year
between two countries each expressed as its ISO 3166-1 alpha-2 country code.

Example in 2025 between Spain and the Netherlands:

[http://localhost:8080/getCommonHolidays/2025/es/nl](http://localhost:8080/getCommonHolidays/2025/es/nl)

### Errors

- When a country does not have any holidays, for the **last three holidays** endpoint and the
**common holidays between two countries** endpoint will return an empty list, as the first
endpoint does not have anything to return and for the second any common holidays between a country
and another that doesn't have holidays will always be none.

-In the **number of holidays on weekdays** and **raw number of holidays on weekdays** endpoint
the countries without holidays will be included with 0 on the count and any country code that gets an
error from the API will be included on the list with 0 holidays to be able to inform of the countries
that do have holidays instead of throwing an error.