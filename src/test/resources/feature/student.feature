Feature: Testing different requests on the student application

  @SMOKE
  Scenario: Check if the student application can be accesses by users
    When User sends a GET request to the list endpoint, they must get back a valid status code 200

  Scenario Outline: Create a new student & verify is the student is added
    When I create a new student by providing the information firstName <firstName> lastName <lastName> email <email> programme <programme> course <courses>
    Then I verify that the student with <email> is added

    Examples:
      | firstName | lastName | email                   | programme           | courses |
      | Cristi    | Greyes   | cgreyes0@privacy.gov.au | information science | java    |
      | Gelya     | Allin    | gallin1@ameblo.jp       | information science | c#      |