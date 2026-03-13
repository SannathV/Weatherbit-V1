package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigReader;

import java.util.*;

import static org.junit.Assert.assertFalse;

public class WarmestAustralianCapitalCity {
    List<String> cities;
    Map<String, Double> cityTemps = new HashMap<>();

    @Given("a list of Australian capital cities")
    public void loadCities() {

        RestAssured.baseURI = ConfigReader.getProperty("weather.api.baseurl");

        cities = Arrays.asList(
                "Sydney,AU",
                "Melbourne,AU",
                "Brisbane,AU",
                "Perth,AU",
                "Adelaide,AU",
                "Canberra,AU",
                "Hobart,AU",
                "Darwin,AU"
        );
    }
    @When("I retrieve the current temperature for each city")
    public void getTemperatures() {

        String apiKey = ConfigReader.getProperty("weather.api.key");

        for(String city : cities){

            Response response = RestAssured
                    .given()
                    .queryParam("city", city)
                    .queryParam("key", apiKey)
                    .when()
                    .get();

            Double temp = response.jsonPath().getDouble("data[0].temp");

            if(temp != null){
                cityTemps.put(city, temp);
            }
        }
    }
    @Then("the system should return the warmest capital city")
    public void findWarmestCity() {

        assertFalse(cityTemps.isEmpty());

        String warmestCity = Collections.max(
                cityTemps.entrySet(),
                Map.Entry.comparingByValue()
        ).getKey();

        System.out.println("Warmest Australian capital city: " + warmestCity);
    }
}
