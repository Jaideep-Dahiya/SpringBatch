package com.example.batch.SpringBatch.config;
import com.example.batch.SpringBatch.tasklet.FilePrintingTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
@EnableBatchProcessing
public class SampleJob{

    @Bean
    public Job firstJob(JobRepository jobRepository, Step firstStep) {
//        Sample Job as of Spring Batch 5
        return new JobBuilder("first job", jobRepository)
                .start(firstStep)
                .build();
    }

    @Bean
    public Step firstStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("first step", jobRepository)
                .tasklet(filePrintingTaskletBean(), transactionManager)
                .build();
    }

    @Bean
    public FilePrintingTasklet filePrintingTaskletBean(){
        FilePrintingTasklet filePrintingTasklet = new FilePrintingTasklet();
        filePrintingTasklet.setHelloFromTasklet("mera kaddu khale harsh");
        filePrintingTasklet.setTimes(10);
        return filePrintingTasklet;
    }

}
