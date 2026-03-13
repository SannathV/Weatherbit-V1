Feature: Coldest US state

@smoke @regression @coldestUSstate
Scenario: As a logistics manager, identify current coldest US state
Given a metadata file containing US states
When I retrieve the temperature for each state
Then the system should return the coldest state