package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigReader;

import static org.junit.Assert.*;

public class SpecificCoordinates {

    Response response;

    public SpecificCoordinates() {
        // empty constructor required by Cucumber
    }

    @Given("the weather API endpoint")
    public void setEndpoint(){
        RestAssured.baseURI = ConfigReader.getProperty("weather.api.baseurl");
    }

    @When("I request weather for latitude {double} and longitude {double}")
    public void getWeather(double lat, double lon){

        String apiKey = ConfigReader.getProperty("weather.api.key");

        response = RestAssured
                .given()
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("key", apiKey)
                .when()
                .get();

        response.prettyPrint();
    }

    @Then("the response status should be {int}")
    public void validateStatus(int status){
        assertEquals(status, response.getStatusCode());
    }
}