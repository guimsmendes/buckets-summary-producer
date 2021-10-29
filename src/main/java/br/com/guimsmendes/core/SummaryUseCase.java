package br.com.guimsmendes.core;

import br.com.guimsmendes.core.domain.BucketsSummary;
import br.com.guimsmendes.core.domain.Status;
import br.com.guimsmendes.core.domain.StepType;
import br.com.guimsmendes.entrypoint.model.SummaryTagProducerRequest;
import br.com.guimsmendes.dataprovider.repository.BucketsSummaryRepository;
import br.com.guimsmendes.core.mapper.BucketsSummaryMapper;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class SummaryUseCase {

    @Inject
    BucketsSummaryRepository bucketsSummaryRepository;

    @Inject
    BucketsSummaryMapper bucketsSummaryMapper;

    public ObjectId createTag(SummaryTagProducerRequest summaryTagProducerRequest) {
        log.debug(String.format("Summary tag creation request successfully received for index: %s and bucket: %s",
                summaryTagProducerRequest.getIndex(),
                summaryTagProducerRequest.getBucket()));
        var bucketsSummary = bucketsSummaryMapper.toBucketsSummary(summaryTagProducerRequest);
        bucketsSummary.buildSummary(StepType.TAG_CREATED, Status.WAITING);
        bucketsSummaryRepository.persist(bucketsSummary);
        return bucketsSummary.getId();
    }

    public BucketsSummary getBucketsSummary(String id) {
        log.debug(String.format("Request received for id: %s", id));
        return bucketsSummaryRepository.findById(new ObjectId(id));
    }

}
