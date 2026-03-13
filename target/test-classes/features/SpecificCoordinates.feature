
Feature: Weather API validation

  @smoke @regression @weatherbasedoncoordinates @analysis
  Scenario Outline: Validate weather using coordinates
    Given the weather API endpoint
    When I request weather for latitude <lat> and longitude <lon>
    Then the response status should be 200

    Examples:
      | lat        | lon        |
      | -33.865143 | 151.209900 |
      | 40.712776  | -74.005974 |
      | 51.507351  | -0.127758  |
