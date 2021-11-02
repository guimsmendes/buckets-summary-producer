package br.com.guimsmendes.unit.usecase.mapper;

import br.com.guimsmendes.core.domain.BucketsSummary;
import br.com.guimsmendes.core.mapper.BucketsSummaryMapper;
import br.com.guimsmendes.core.mapper.BucketsSummaryMapperImpl;
import br.com.guimsmendes.unit.TestSupport;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class BucketsSummaryMapperTest extends TestSupport {

    @InjectMock
    BucketsSummaryMapper bucketsSummaryMapper;

    @BeforeEach
    public void init() throws IOException {
        bucketsSummaryMapper = new BucketsSummaryMapperImpl();
    }

    @Test
    void toBucketsSummaryValidTest(){
        var summary = mockSummaryTagProducerRequest();
        var bucketsSummary = bucketsSummaryMapper.toBucketsSummary(summary);

        assertEquals(summary.getBucket(), bucketsSummary.getBucket());
        assertEquals(summary.getQuantity(), bucketsSummary.getQuantity());
        assertNotNull(bucketsSummary.getId());
        assertNotNull(bucketsSummary.getCreated());
        assertNotNull(bucketsSummary.getUpdated());
    }

    @Test
    void toBucketsSummaryNull() { assertNotNull(bucketsSummaryMapper.toBucketsSummary(null)); }
}
