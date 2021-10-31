package br.com.guimsmendes.integ;

import br.com.guimsmendes.dataprovider.repository.BucketsSummaryRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.inject.Inject;

@QuarkusTest
public abstract class IntegTestSupport {

    protected int serverPort = 0;

    @Inject
    BucketsSummaryRepository bucketsSummaryRepository;

    @BeforeEach
    public void beforeEach() {
        bucketsSummaryRepository.deleteAll();
        RestAssured.baseURI = "http://localhost:" + serverPort;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @AfterEach
    public void afterEach() {
        bucketsSummaryRepository.deleteAll();
    }
}
