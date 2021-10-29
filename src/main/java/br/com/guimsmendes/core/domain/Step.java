package br.com.guimsmendes.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Step {

    private StepType stepType;
    private LocalDateTime initStep;
    private LocalDateTime endStep;

    public void setEndStep(){
        this.endStep = LocalDateTime.now();
    }
}
