package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigReader;

import java.util.*;

import static org.junit.Assert.assertFalse;

public class MajorInternationalCities {
    List<String> internationalCities;
    Map<String, Double> internationalTemps = new HashMap<>();
    Map<String, Response> cityResponses = new HashMap<>(); // store responses for validation

    public MajorInternationalCities() {
        // required empty constructor
    }
    @Given("a list of major international cities")
    public void loadInternationalCities() {

        RestAssured.baseURI = ConfigReader.getProperty("weather.api.baseurl");

        internationalCities = Arrays.asList(
                "London,UK",
                "New York,US",
                "Tokyo,JP",
                "Sydney,AU",
                "Dubai,AE"
        );
    }


    @When("I request weather data for those cities")
    public void retrieveWeatherForInternationalCities() {

        String apiKey = ConfigReader.getProperty("weather.api.key");

        for(String city : internationalCities){

            Response response = RestAssured
                    .given()
                    .queryParam("city", city)
                    .queryParam("key", apiKey)
                    .when()
                    .get();

            // Save the response for validation steps
            cityResponses.put(city, response);
            Double temp = response.jsonPath().getDouble("data[0].temp");

            if(temp != null){
                internationalTemps.put(city, temp);
            }
        }
    }



    @Then("the system should successfully retrieve weather data")
    public void verifyWeatherDataRetrieved() {

        assertFalse(internationalTemps.isEmpty());

        System.out.println("Weather data retrieved for the following cities:");

        for(Map.Entry<String, Double> entry : internationalTemps.entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    // Validate API response status codes
    @Then("the API response status should be 200 for all cities")
    public void validateApiStatusCodes() {
        for (Map.Entry<String, Response> entry : cityResponses.entrySet()) {
            entry.getValue().then().statusCode(200);
            System.out.println("Status 200 verified for " + entry.getKey());
        }
    }


}

