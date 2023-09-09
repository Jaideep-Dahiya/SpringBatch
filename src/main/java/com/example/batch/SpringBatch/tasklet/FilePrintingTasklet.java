package com.example.batch.SpringBatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

public class FilePrintingTasklet implements Tasklet, InitializingBean {

    private String helloFromTasklet;
    private int times;


    @Override
    @Nullable
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        for(int i=0;i<times;i++){
            System.out.println(helloFromTasklet);
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(helloFromTasklet,"this must not be null");
        Assert.isTrue(times>0,"times should be > than 0");
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setHelloFromTasklet(String helloFromTasklet) {
        this.helloFromTasklet = helloFromTasklet;
    }
}
