package br.com.guimsmendes.integ.entrypoint;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;


import javax.ws.rs.core.Response;

import static io.restassured.http.ContentType.JSON;

@QuarkusTest
public class SummaryTagProducerControllerTest {

    @Test
    void createValidSummaryTag() {
        createSummaryTestCase("{\"index\":\"i-mkp-hot\",\"bucket\":\"guardRail\",\"quantity\":1000}", Response.Status.CREATED);
    }

    @Test
    void createInvalidIndexSummaryTag() {
        createSummaryTestCase("{\"index\":\"IMKPHOT\",\"bucket\":\"guardRail\",\"quantity\":1000}", Response.Status.BAD_REQUEST);
    }

    @Test
    void createEmptyIndexSummaryTag() {
        createSummaryTestCase("{\"index\":\"\",\"bucket\":\"guardRail\",\"quantity\":1000}", Response.Status.BAD_REQUEST);
    }

    @Test
    void createInvalidBucketSummaryTag() {
        createSummaryTestCase("{\"index\":\"i-mkp-hot\",\"bucket\":\"GUARDRAIL\",\"quantity\":1000}", Response.Status.BAD_REQUEST);
    }

    @Test
    void createEmptyBucketSummaryTag() {
        createSummaryTestCase("{\"index\":\"i-mkp-hot\",\"bucket\":\"\",\"quantity\":1000}", Response.Status.BAD_REQUEST);
    }

    @Test
    void createInvalidQuantitySummaryTag() {
        createSummaryTestCase("{\"index\":\"i-mkp-hot\",\"bucket\" :\"guardRails\",\"quantity\":-1}", Response.Status.BAD_REQUEST);
    }

    private void createSummaryTestCase(String payload, Response.Status httpStatus) {
        RestAssured.given().auth().basic("guimsmendes", "123456").contentType(JSON).body(payload)
                .post("/summary/tag-producer").then().statusCode(httpStatus.getStatusCode());

    }
}
