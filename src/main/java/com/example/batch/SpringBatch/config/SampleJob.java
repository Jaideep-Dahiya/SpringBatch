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
@EnableBatchProcessing(dataSourceRef = "dataSource", transactionManagerRef = "batchTransactionManager")
public class SampleJob{


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/db_example");
        dataSource.setUsername("springuser");
        dataSource.setPassword("ThePassword");
        return dataSource;
    }

    @Bean
    public JdbcTransactionManager batchTransactionManager(DataSource dataSource){
        return new JdbcTransactionManager(dataSource);
    }


    @Bean
    public Job firstJob(JobRepository jobRepository, Step firstStep) {
//        Sample Job as of Spring Batch 5
        return new JobBuilder("first job", jobRepository)
                .start(firstStep)
//                .next(secondStep())
//                .next(thirdStep())
                .build();

    }

    @Bean
    public Step firstStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        System.out.println("hello");
        return new StepBuilder("first step", jobRepository)
                .tasklet(filePrintingTaskletBean(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet firstTask() {
        return (contribution, chunkContext) -> {
            System.out.println("This is first tasklet step");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public FilePrintingTasklet filePrintingTaskletBean(){
        FilePrintingTasklet filePrintingTasklet = new FilePrintingTasklet();
        filePrintingTasklet.setHelloFromTasklet("mera kaddu khale harsh");
        filePrintingTasklet.setTimes(10);
        return filePrintingTasklet;
    }

}
