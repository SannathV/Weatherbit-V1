Weather API Automation Framework
This project demonstrates automated API testing for a weather service using a modern Java-based test automation stack. The framework validates weather data using REST APIs and generates detailed test reports using Serenity.

Technology Stack
Java
REST Assured
Cucumber BDD
Serenity BDD
Maven

Prerequisites
Ensure the following tools are installed before running the tests.

Java JDK 17
Apache Maven 3.8 or higher
Git (optional for cloning the repository)

Verify installation:

java -version
mvn -version

Required Maven Plugins
The project uses the following Maven plugins defined in pom.xml.

Serenity Maven Plugin – Generates Serenity test reports
Maven Surefire Plugin – Executes test cases
Maven Compiler Plugin – Compiles the Java source code

Environment Configuration
The framework reads configuration values from:

src/test/resources/serenity.properties

Example configuration:

weather.api.key=YOUR_API_KEY
weather.api.baseurl=https://api.weatherbit.io/v2.0/current
Update the API key before executing the tests.

Running the Tests
Execute the full test suite using Maven:

mvn clean verify

This command will:

Compile the project
Execute Cucumber scenarios
Run API validations using REST Assured
Generate Serenity HTML reports
Running Tests Using Tags
Tests can be filtered using Cucumber tags.

Example:

Run smoke tests:

mvn verify -Dcucumber.filter.tags="@smoke"

Run regression suite:

mvn verify -Dcucumber.filter.tags="@regression"

Run weather analysis scenarios:

mvn verify -Dcucumber.filter.tags="@analysis"

Test Reports
After test execution, the Serenity report is generated at:

target/site/serenity/index.html

The report provides:

Scenario execution results
Step-by-step API interactions
Test execution summary
Failure details (if any)

Project Structure
src/test/java
 ├── runners
 │     TestRunner.java
 │
 ├── steps
 │     WeatherSteps.java
 │     WeatherAnalysisSteps.java
 │
 └── utils

src/test/resources
 ├── features
 │     weather.feature
 │     weather_analysis.feature
 │
 └── serenity.properties
Future Improvements
Add JSON-based metadata for cities and states
Integrate with CI/CD pipelines such as GitHub Actions or Jenkins
Add schema validation for API responses
Introduce reusable API client utilities
Developed and managed by Sannath vijayakumar
reach me at vijayask24@gmail.com
