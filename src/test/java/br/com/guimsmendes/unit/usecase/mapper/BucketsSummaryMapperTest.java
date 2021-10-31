package br.com.guimsmendes.unit.usecase.mapper;

import br.com.guimsmendes.core.domain.BucketsSummary;
import br.com.guimsmendes.core.mapper.BucketsSummaryMapper;
import br.com.guimsmendes.core.mapper.BucketsSummaryMapperImpl;
import br.com.guimsmendes.entrypoint.model.SummaryTagProducerRequest;
import br.com.guimsmendes.unit.TestSupport;
import br.com.six2six.fixturefactory.Fixture;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;

import static br.com.guimsmendes.template.FixtureFactoryTemplateLoader.VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BucketsSummaryMapperTest extends TestSupport {

    @InjectMock
    BucketsSummaryMapper bucketsSummaryMapper;

    @BeforeEach
    @Override
    public void init() throws IOException {
        bucketsSummaryMapper = new BucketsSummaryMapperImpl();
    }

    @Test
    void toBucketsSummaryValidTest(){
        SummaryTagProducerRequest summary = Fixture.from(SummaryTagProducerRequest.class).gimme(VALID.name());

        BucketsSummary bucketsSummary = bucketsSummaryMapper.toBucketsSummary(summary);

        assertEquals(bucketsSummary.getBucket(), "guardRails");
        assertEquals(bucketsSummary.getQuantity(), 1200);
        assertNotNull(bucketsSummary.getCreated());
        assertNotNull(bucketsSummary.getUpdated());
    }

    @Test
    void toBucketsSummaryNull() { assertNotNull(bucketsSummaryMapper.toBucketsSummary(null)); }
}
