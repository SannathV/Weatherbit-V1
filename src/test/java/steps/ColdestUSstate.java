package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigReader;

import java.util.*;

import static org.junit.Assert.assertFalse;

public class ColdestUSstate {

    List<String> states;
    Map<String, Double> stateTemps = new HashMap<>();

    @Given("a metadata file containing US states")
    public void a_metadata_file_containing_us_states() {

        states = Arrays.asList(
                "Texas",
                "California",
                "Florida",
                "Alaska",
                "Colorado"
        );
    }

    @When("I retrieve the temperature for each state")
    public void i_retrieve_the_temperature_for_each_state() {

        String apiKey = ConfigReader.getProperty("weather.api.key");

        for(String state : states){

            Response response = RestAssured
                    .given()
                    .queryParam("city", state)
                    .queryParam("key", apiKey)
                    .get(ConfigReader.getProperty("weather.api.baseurl"));

            Double temp = response.jsonPath().getDouble("data[0].temp");

            if(temp != null){
                stateTemps.put(state, temp);
            }
        }
    }

    @Then("the system should return the coldest state")
    public void the_system_should_return_the_coldest_state() {

        String coldestState = Collections.min(
                stateTemps.entrySet(),
                Map.Entry.comparingByValue()
        ).getKey();

        System.out.println("Coldest US state: " + coldestState);
    }

}
