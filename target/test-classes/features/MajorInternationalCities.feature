Feature: Weather for major international cities

  @smoke @regression @majorinternationalcities @analysis
  Scenario: As a frequent flyer, retrieve and verify current weather data for a list of multiple major international cities
    Given a list of major international cities
    When I request weather data for those cities
    Then the system should successfully retrieve weather data
    Then the API response status should be 200 for all cities




