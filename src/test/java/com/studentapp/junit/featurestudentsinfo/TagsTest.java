package com.studentapp.junit.featurestudentsinfo;

import com.studentapp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTagValuesOf;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class TagsTest extends TestBase {

    // ---------------------- CONCURRENCY PROBLEM WITH TAGS --------------------------
    // NOTICED A CORRUPTED REPORT AND MIXED TESTS WHEN USING FORKS AND PARALLELISM WITH TAGS; CHECK IT OUT

    @WithTag(type = "feature", name = "negativeTests") // SAME AS @WithTag("feature:negativeTests") OR @WithTag("negativeTests") SINCE DEFAULT IS FEATURE
    @Title("Provide a 405 status code when incorrect HTTP method is used to access a resource")
    @Test
    public void invalidRestVerb() {
        SerenityRest.rest().given()
                .when()
                .post("list")
                .then().statusCode(405).log().all();
    }

    // THIS IS TO ADD MULTIPLE TAGS TO ONE TEST, SUCH AS: SMOKE AND REGRESSION
    @WithTagValuesOf({"smoke", "feature:positiveTests"}) //@WithTags({@WithTag("feature:positiveTests"), @WithTag("smoke")})
    @Title("Verify that we're getting a correct status code 200 with a valid get request")
    @Test
    public void verifyStatusCode200() {
        SerenityRest.rest().given()
                .when()
                .get("list")
                .then().statusCode(200);
    }

    @WithTag(type = "feature", name = "negativeTests")
    @Title("Verify that the app returns status code 400 when we try to access an invalid resource")
    @Test
    public void invalidResourceCall() {
        SerenityRest.rest().given().when()
                .get("kkjjhh")
                .then().statusCode(400).log().all();
    }

}