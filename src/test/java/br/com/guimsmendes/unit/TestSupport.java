package br.com.guimsmendes.unit;

import br.com.guimsmendes.core.domain.BucketsSummary;
import br.com.guimsmendes.core.domain.Status;
import br.com.guimsmendes.core.domain.StepType;
import br.com.guimsmendes.core.domain.User;
import br.com.guimsmendes.entrypoint.model.SummaryTagProducerRequest;
import br.com.guimsmendes.entrypoint.model.UserRequest;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public abstract class TestSupport {

    protected BucketsSummary mockBucketsSummary() {
        var summary = new BucketsSummary();
        summary.setId(new ObjectId("617b3c935c5ff236ca5b4918"));
        summary.setBucket("guardRails");
        summary.setQuantity(1200);
        summary.buildSummary(StepType.TAG_CREATED, Status.WAITING);
        summary.setCreated(LocalDateTime.now());
        summary.setUpdated(LocalDateTime.now());
        return summary;
    }

    protected User mockUser(){
        var user = new User();
        user.setUsername("user");
        user.setPassword("password");
        user.setId(1L);
        user.setRole("user");
        return user;
    }

    protected UserRequest mockUserRequest(){
        return new UserRequest("test", "test");
    }

    protected SummaryTagProducerRequest mockSummaryTagProducerRequest(){
        return new SummaryTagProducerRequest("i-mkp-hot", "guardRails",1200);
    };
}
