package br.com.guimsmendes.integ.entrypoint;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.http.ContentType.JSON;

@QuarkusTest
public class UserResourceTest {

    @Test
    void createValidUser() {
        createUserTestCase("{\"username\":\"test\",\"password\" :\"test\"}", Response.Status.NO_CONTENT);
    }

    @Test
    void createInvalidUsername() {
        createUserTestCase("{\"username\":\"\",\"password\" :\"test\"}", Response.Status.BAD_REQUEST);
    }

    @Test
    void createInvalidPassword() {
        createUserTestCase("{\"username\":\"test\",\"password\" :\"\"}", Response.Status.BAD_REQUEST);
    }

    private void createUserTestCase(String payload, Response.Status httpStatus) {
        RestAssured.given().contentType(JSON).body(payload)
                .post("/users").then().statusCode(httpStatus.getStatusCode());

    }
}
