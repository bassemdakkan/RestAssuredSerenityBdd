package com.studentapp.cucumber.steps;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.HashMap;

import static com.studentapp.utils.TestUtils.getRandomInt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasValue;

public class StudentSteps {

    static String email;

    @Steps
    StudentSerenitySteps steps;

    /*
    The ^ and $ signs are to match exactly what is written in between so that cucumber won't
    mix with other steps.
    All assertions are set in the step definitions and not in the feature files
    */
    @When("^User sends a GET request to the list endpoint, they must get back a valid status code 200$")
    public void verify_status_code_200_for_listendpoint() {
        SerenityRest.rest().given()
                .when().get("list")
                .then().statusCode(200);
    }

    @When("^I create a new student by providing the information firstName (.*) lastName (.*) email (.*) programme (.*) course (.*)$")
    public void createStudent(String firstName, String lastName, String email, String programme, String course) {
        ArrayList<String> courses = new ArrayList<>();
        courses.add(course);
        email = getRandomInt() + email;

        steps.createStudent(firstName, lastName, email, programme, courses)
                .assertThat().statusCode(201);

    }

    @Then("^I verify that the student with (.*) is added$")
    public void verifyStudent(String emailId) {
        HashMap<String, Object> actualValue = steps.getStudentInfoByEmailId(emailId);
        assertThat(actualValue, hasValue(emailId));
    }
}
