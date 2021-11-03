package br.com.guimsmendes.core.mapper;

import br.com.guimsmendes.core.domain.BucketsSummary;
import br.com.guimsmendes.entrypoint.model.SummaryTagProducerRequest;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-03T17:23:35-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.13 (GraalVM Community)"
)
@ApplicationScoped
public class BucketsSummaryMapperImpl implements BucketsSummaryMapper {

    @Override
    public BucketsSummary toBucketsSummary(SummaryTagProducerRequest summaryTagProducerRequest) {

        BucketsSummary bucketsSummary = new BucketsSummary();

        if ( summaryTagProducerRequest != null ) {
            bucketsSummary.setBucket( summaryTagProducerRequest.getBucket() );
            bucketsSummary.setQuantity( summaryTagProducerRequest.getQuantity() );
        }
        bucketsSummary.setId( new ObjectId() );
        bucketsSummary.setUpdated( LocalDateTime.now() );
        bucketsSummary.setCreated( LocalDateTime.now() );

        return bucketsSummary;
    }
}
