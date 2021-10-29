package br.com.guimsmendes.core.mapper;

import br.com.guimsmendes.core.domain.BucketsSummary;
import br.com.guimsmendes.entrypoint.model.SummaryTagProducerRequest;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

import java.time.LocalDateTime;

@Mapper(componentModel = "cdi", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT, imports = {ObjectId.class, LocalDateTime.class})
public interface BucketsSummaryMapper {

    @Mapping(target = "id", expression = "java(new ObjectId())")
    @Mapping(target = "created", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updated", expression = "java(LocalDateTime.now())")
    BucketsSummary toBucketsSummary(SummaryTagProducerRequest summaryTagProducerRequest);
}
