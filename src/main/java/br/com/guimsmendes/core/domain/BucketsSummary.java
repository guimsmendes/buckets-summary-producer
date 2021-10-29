package br.com.guimsmendes.core.domain;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.hibernate.internal.util.collections.CollectionHelper.isEmpty;

@Data
@RegisterForReflection
@MongoEntity(collection = "bucketsSummary")
public class BucketsSummary {

    private ObjectId id;
    private String bucket;
    private Integer quantity;
    private Status status;
    private List<Step> steps;
    private LocalDateTime created;
    private LocalDateTime updated;

    public void buildSummary(StepType initialStepType, Status status) {
        if(nonNull(this.steps) && !isEmpty(this.steps)){
            Step step = this.steps.get(this.steps.size()-1);
            step.setEndStep();
        } else {
            this.steps = new ArrayList<>();
        }
        addStep(initialStepType);
        this.status = status;
    }

    public void addStep(StepType stepType) {
        this.steps.add(Step.builder()
                .stepType(stepType)
                .initStep(LocalDateTime.now()).build());
    }

}
