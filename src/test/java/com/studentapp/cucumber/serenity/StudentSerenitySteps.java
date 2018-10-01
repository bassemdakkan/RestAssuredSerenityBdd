package com.studentapp.cucumber.serenity;

import com.studentapp.model.Student;
import com.studentapp.utils.ReusableSpecifications;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class StudentSerenitySteps {

    @Step("Create a new student with firstName:{0}, lastName:{1}, email:{2}, programme:{3}, courses:{4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courses) {

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return SerenityRest.rest().given()
                //.contentType(ContentType.JSON) // specify content type or use reusable request specifications
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .when()
                .body(student)
                .post().then();
    }

    @Step("Get all the info of the students with firstName:{0}")
    public HashMap<String, Object> getStudentInfoByFirstName(String firstName) {
        HashMap<String, Object> value = SerenityRest.rest().given()
                .when()
                .get("list")
                .then()
                .statusCode(200)
                .extract()
                .path("findAll{it.firstName=='" + firstName + "'}.get(0)");
        return value;
    }

    @Step("Update student id {0} to firstName={1}, lastName={2}, email={3}, programme={4}, courses={5}")
    public ValidatableResponse updateStudent(String studentId, String firstName, String lastName, String email, String programme, List<String> courses) {

        Student student = new Student();
        student.setFirstName(firstName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return SerenityRest.rest().given()
                //.contentType(ContentType.JSON) OR
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .when()
                .body(student)
                .put(studentId).then();
    }

    @Step("Delete student info with ID {0}")
    public ValidatableResponse deleteStudent(String studentId){
        return SerenityRest.rest().delete(studentId).then();
    }

    @Step("Get the student info with ID {0}")
    public ValidatableResponse getStudentInfoById(String id) {
        return SerenityRest.rest().given().get(id).then();
    }
}