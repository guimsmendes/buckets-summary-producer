package br.com.guimsmendes.entrypoint.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Data
@RegisterForReflection
@AllArgsConstructor
@NoArgsConstructor
public class SummaryTagProducerRequest {

    @Pattern(regexp = "^[a-z-]+$")
    @NotEmpty(message= "index cannot be null or empty.")
    private String index;

    @Pattern(regexp = "[a-z]+((\\d)|([A-Z0-9][a-z0-9]+))*([A-Z])?")
    @NotEmpty(message = "Bucket cannot be null or empty.")
    private String bucket;

    @PositiveOrZero
    private Integer quantity;

}
