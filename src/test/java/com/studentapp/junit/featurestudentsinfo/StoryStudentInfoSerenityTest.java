package com.studentapp.junit.featurestudentsinfo;

import com.studentapp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@RunWith(SerenityRunner.class)
public class StoryStudentInfoSerenityTest extends TestBase {

    @Test
    public void getAllStudents() {
        SerenityRest.given().when().get("list").then().log().all().statusCode(200);
    }

    @Test
    public void thisIsAFailingTest() {
        SerenityRest.given().when().get("list").then().log().all().statusCode(500);
    }

    /* Test implementation not finished yet*/
    @Pending
    @Test
    public void thisIsAPendingTest() {
        SerenityRest.given().when().get("list").then().log().all().statusCode(200);
    }

    @Ignore
    @Test
    public void thisIsAnIgnoredTest() {
        SerenityRest.given().when().get("list").then().log().all().statusCode(200);
    }

    // This is not an assertion failig test but rather a java/code error
    @Test
    public void thisIsAnErrorTest() {
        System.out.println("This is an error: " + 5 / 0);
    }

    // When an exception is being returned
    // In JUnit an exception is returned, so you can customize the failure
    // to be compromised in serenity by adding it to serenity.properties
    @Test
    public void thisIsACompromisedTest() throws FileNotFoundException {
        File file = new File("some/path");
        FileReader fileReader = new FileReader(file);
    }

    @Manual
    @Test
    public void thisIsAManualTest() {
    }

    @Title("This test will get all the students information from the student app")
    @Test
    public void testWithTitleAnnotation() {
        SerenityRest.given().when().get("list").then().log().all().statusCode(200);
    }

}
