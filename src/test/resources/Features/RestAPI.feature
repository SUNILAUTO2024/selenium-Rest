@regression
Feature: Test All APIs

  @api
  Scenario:TC_01_List_All_Objects
    Given endpoint "https://api.restful-api.dev/objects"
    And user send the get request
    Then status code should equal to 200
    Then user validate response

  @api
  Scenario:TC_02_List_Objects_Id
    Given endpoint "https://api.restful-api.dev/objects?id=3&id=5&id=10"
    And user send the get request
    Then status code should equal to 200
    Then user validate response

  @api
  Scenario:TC_03_List_Single_Objects_by_Id
    Given endpoint "https://api.restful-api.dev/objects/7"
    And user send the get request
    Then status code should equal to 200
    Then user validate response

  @api_List
 Scenario Outline:TC_03_List_Single_Objects_by_Id
    Given endpoint "<Endpoint>"
    And user send the get request
    Then status code should equal to "<Status_Code>"
    Then user validate response with JasonPath "<Jason_Path>" and Jason_Value "<Jason_Value>"
    Then user validated
    Examples:
 |     Endpoint                                           |Status_Code    | Jason_Path   |Jason_Value  |Jpath2|JValue|
 |     https://api.restful-api.dev/objects                |200            |[0].id        |1            |     t| d    |
 |     https://api.restful-api.dev/objects?id=3&id=5&id=10|200            |[0].data.color|Cloudy White |g     |s     |
 |     https://api.restful-api.dev/objects/7              |200            |name  |Apple MacBook Pro 16 |fd    |ss    |

  @api_Add
  Scenario:TC_04_Add object
    Given endpoint "https://api.restful-api.dev/objects"
    And request file "Add.jason"
    And user perform post operation
    Then status code should equal to 200
    Then user validate response