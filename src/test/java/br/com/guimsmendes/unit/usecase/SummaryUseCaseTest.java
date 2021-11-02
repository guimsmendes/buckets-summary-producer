package br.com.guimsmendes.unit.usecase;

import br.com.guimsmendes.unit.TestSupport;
import br.com.guimsmendes.core.SummaryUseCase;
import br.com.guimsmendes.core.domain.BucketsSummary;
import br.com.guimsmendes.core.domain.Status;
import br.com.guimsmendes.core.domain.StepType;
import br.com.guimsmendes.dataprovider.repository.BucketsSummaryRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@QuarkusTest
public class SummaryUseCaseTest extends TestSupport {

    @Inject
    SummaryUseCase summaryUseCase;

    @InjectMock
    private BucketsSummaryRepository bucketsSummaryRepository;

    @Test
    void createValidTag() {
        var summaryTagProducerRequest = mockSummaryTagProducerRequest();
        doNothing().when(bucketsSummaryRepository).persist(any(BucketsSummary.class));

        ArgumentCaptor<BucketsSummary> bucketsSummaryArgumentCaptor = ArgumentCaptor.forClass(BucketsSummary.class);

        ObjectId tag = summaryUseCase.createTag(summaryTagProducerRequest);

        verify(bucketsSummaryRepository, timeout(100)).persist(bucketsSummaryArgumentCaptor.capture());
        var bucketsSummary = bucketsSummaryArgumentCaptor.getValue();

        Assertions.assertNotNull(tag);
        Assertions.assertNotNull(bucketsSummary.getCreated());
        Assertions.assertNotNull(bucketsSummary.getUpdated());
        assertEquals(summaryTagProducerRequest.getBucket(), bucketsSummary.getBucket());
        assertEquals(summaryTagProducerRequest.getQuantity(), bucketsSummary.getQuantity());
        assertEquals(Status.WAITING, bucketsSummary.getStatus());
        assertEquals(StepType.TAG_CREATED, bucketsSummary.getSteps().get(0).getStepType());
    }

    @Test
    void listByValidId(){
        when(bucketsSummaryRepository.findById(any(ObjectId.class))).thenReturn(mockBucketsSummary());

        var bucketsSummary = summaryUseCase.getBucketsSummary("617b3c935c5ff236ca5b4918");

        assertEquals(new ObjectId("617b3c935c5ff236ca5b4918"), bucketsSummary.getId());
        assertEquals("guardRails", bucketsSummary.getBucket());
        assertEquals(1200, bucketsSummary.getQuantity());
    }
}
