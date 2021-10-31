package br.com.guimsmendes.integ.entrypoint;

import br.com.guimsmendes.integ.IntegTestSupport;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class SummaryTagProducerControllerTest{


    @Test
    void createValidSummaryTag() {
        createSummaryTestCase("{\"index\":\"i-mkp-hot\",\"bucket\":\"guardRail\",\"quantity\":1000}", Response.Status.CREATED);
    }

    @Test
    void createInvalidIndexSummaryTag() {
        createSummaryTestCase("{\"index\":\"IMKPHOT\",\"bucket\":\"guardRail\",\"quantity\":1000}", Response.Status.BAD_REQUEST);
    }

    @Test
    void createInvalidBucketSummaryTag() {
        createSummaryTestCase("{\"index\":\"i-mkp-hot\",\"bucket\":\"GUARDRAIL\",\"quantity\":1000}", Response.Status.BAD_REQUEST);
    }

    @Test
    void createInvalidQuantitySummaryTag() {
        createSummaryTestCase("{\"index\":\"i-mkp-hot\",\"bucket\" :\"guardRails\",\"quantity\":-1}", Response.Status.BAD_REQUEST);
    }

    private void createSummaryTestCase(String payload, Response.Status http) {
        given().contentType(JSON).body(payload).post("/summary/tag-producer").then().statusCode(http.getStatusCode())
                .body(notNullValue());

    }
}
