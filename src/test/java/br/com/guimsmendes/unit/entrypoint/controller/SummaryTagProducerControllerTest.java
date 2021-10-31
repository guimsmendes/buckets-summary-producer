package br.com.guimsmendes.unit.entrypoint.controller;

import br.com.guimsmendes.unit.TestSupport;
import br.com.six2six.fixturefactory.Fixture;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SummaryTagProducerControllerTest extends TestSupport {

//    @InjectMock
//    private SummaryTagProducerController summaryTagProducerController;
//
//    @Mock
//    private SummaryUseCase summaryUseCase;
//
//    @Mock
//    private SummaryTagProducerMapper summaryTagProducerMapper;
//
    @Override
    public void init() throws IOException {

    }
//
//    @Test
//    void createSummaryTagValidRequest() {
//        when(summaryUseCase.createTag(any(SummaryDomain.class))).thenReturn("validId");
//        when(summaryTagProducerMapper.toSummaryDomain(any(SummaryTagProducerRequest.class))).thenReturn(Fixture.from(SummaryDomain.class).gimme(VALID.name()));
//
//        ResponseEntity<String> response = summaryTagProducerController.createSummaryTag(Fixture.from(SummaryTagProducerRequest.class).gimme(VALID.name()));
//        assertEquals(response.getStatusCodeValue(), 200);
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//        assertEquals(response.getBody(), "validId");
//
//    }
//
//    @Test
//    void createSummaryTagInvalidRequest() {
//        assertThrows(NullPointerException.class,
//                () -> summaryTagProducerController.createSummaryTag(null));
//
//    }
}
