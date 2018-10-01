package com.studentapp.junit.featurestudentsinfo;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;


//Serenity will spin up 2 threads per CPU core if you don't specify the thread parameter
// The concurrent annotation can only be used in data driven tests (parameterized tests), i.e. with SerenityParameterizedRunner
@Concurrent(threads = "4x")
@UseTestDataFrom("testdata/studentsinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentsDataDrivenTest extends TestBase {

    private String firstName;
    private String lastName;
    private String email;
    private String programme;
    private String course;

    @Steps
    StudentSerenitySteps steps;

    @Title("Data Driven test for adding multiple students to the student app")
    @Test
    public void createMultipleStudents(){

        ArrayList<String> courses = new ArrayList<>();
        courses.add(course);
        steps.createStudent(firstName, lastName, email, programme, courses).log().all()
                .statusCode(201);
    }
}