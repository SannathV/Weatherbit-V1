  Feature: Warmest Australian capital city

  @smoke @regression @warmestAUcapitalcity @analysis
  Scenario: As a data analyst, programmatically identify current warmest Australian capital city
    Given a list of Australian capital cities
    When I retrieve the current temperature for each city
    Then the system should return the warmest capital city