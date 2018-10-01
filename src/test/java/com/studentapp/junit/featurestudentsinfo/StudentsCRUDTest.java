package com.studentapp.junit.featurestudentsinfo;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReusableSpecifications;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.studentapp.utils.TestUtils.getRandomInt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasValue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SerenityRunner.class)
public class StudentsCRUDTest extends TestBase {

    private static String firstName = "firstName" + getRandomInt();
    private static String lastName = "lastName" + getRandomInt();
    private static String programme = "programme" + getRandomInt();
    private static String email = "test" + getRandomInt() + "@test.com";
    private static String studentId;

    @Steps
    StudentSerenitySteps steps;

    @Title("Create a new student")
    @Test
    public void createStudent() {

        List<String> courses = new ArrayList<String>();
        courses.add("Math");
        courses.add("Physics");

        steps.createStudent(firstName, lastName, email, programme, courses)
                .statusCode(201)
                .spec(ReusableSpecifications.getGenericResponseSpec());//This is a predefined set of response assertions we created in class ReusableSpecifications
    }

    @Title("Get the created student to verify it got created")
    @Test
    public void getStudent() {
        HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
        assertThat(value, hasValue(firstName));

        studentId = String.valueOf(value.get("id"));
    }

    @Title("Update the firstname of the created user and assert that it got updated")
    @Test
    public void updateStudentInfo() {

        firstName = firstName + "updated";
        List<String> courses = new ArrayList<String>();
        courses.add("MathUpdated");
        courses.add("PhysicsUpdated");

        steps.updateStudent(studentId, firstName, lastName, email, programme, courses);
        HashMap<String, Object> student = steps.getStudentInfoByFirstName(firstName);
        assertThat(student, hasValue(firstName));
    }

    @Title("Delete the created student and verify that the student is deleted")
    @Test
    public void xStudentDelete() {
        steps.deleteStudent(studentId);
        steps.getStudentInfoById(studentId).statusCode(404);
    }
}