package br.com.guimsmendes.unit.usecase;

import br.com.guimsmendes.core.SummaryUseCase;
import br.com.guimsmendes.dataprovider.repository.BucketsSummaryRepository;
import br.com.guimsmendes.unit.TestSupport;
import br.com.six2six.fixturefactory.Fixture;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SummaryUseCaseTest extends TestSupport {

    @InjectMocks
    private SummaryUseCase summaryUseCase;

    @Mock
    private BucketsSummaryRepository bucketsSummaryRepository;

    @Override
    public void init() throws IOException {

    }

//    @Test
//    void createTagEmptySteps() {
//        when(bucketsSummaryRepository.(any(SummaryDomain.class))).thenReturn(Optional.of("validId"));
//
//        String tag = summaryUseCase.createTag(Fixture.from(SummaryDomain.class).gimme(PRE_PROCESSING.name()));
//        assertEquals(tag, "validId");
//
//    }
//
//    @Test
//    void unableToCreateTag() {
//        when(summaryGateway.postBucketSummary(any(SummaryDomain.class))).thenReturn(Optional.empty());
//
//        assertThrows(UseCaseException.UnableToCreateTag.class,
//                () -> summaryUseCase.createTag(Fixture.from(SummaryDomain.class).gimme(PRE_PROCESSING.name())));
//
//    }
}
