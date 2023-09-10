package com.example.batch.SpringBatch.config;
import com.example.batch.SpringBatch.dto.Customer;
import com.example.batch.SpringBatch.readermapper.CustomerMapper;
import com.example.batch.SpringBatch.tasklet.FilePrintingTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableBatchProcessing
public class SampleJob{

    @Bean
    public Job firstJob(JobRepository jobRepository, Step secondStep) {
//        Sample Job as of Spring Batch 5
        return new JobBuilder("second job", jobRepository)
                .start(secondStep)
                .build();
    }

//    @Bean
//    public Step firstStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
//        return new StepBuilder("first step", jobRepository)
//                .tasklet(filePrintingTaskletBean(), transactionManager)
//                .build();
//    }

    @Bean
    public Step secondStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("second step", jobRepository)
                .<Customer,Customer>chunk(10,transactionManager)
                .reader(reader())
                .writer(writer())
                .build();
    }

    @Bean
    public FlatFileItemReader<Customer> reader(){
        Resource resource = new FileSystemResource("src/main/resources/addresses.csv");
        FlatFileItemReader<Customer> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        DefaultLineMapper<Customer> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(new CustomerMapper());
        flatFileItemReader.setLineMapper(defaultLineMapper);
        return flatFileItemReader;
    }

    @Bean
    public FlatFileItemWriter<Customer> writer(){
        WritableResource resource = new FileSystemResource("src/main/resources/addressOutput.txt");
        FlatFileItemWriter<Customer> flatFileItemWriter = new FlatFileItemWriter<>();
        flatFileItemWriter.setResource(resource);
        flatFileItemWriter.setLineAggregator(new PassThroughLineAggregator<>());
        return flatFileItemWriter;
    }

    @Bean
    public CustomerMapper customerMapper(){
        return new CustomerMapper();
    }

    @Bean
    public FilePrintingTasklet filePrintingTaskletBean(){
        FilePrintingTasklet filePrintingTasklet = new FilePrintingTasklet();
        filePrintingTasklet.setHelloFromTasklet("mera kaddu khale harsh");
        filePrintingTasklet.setTimes(10);
        return filePrintingTasklet;
    }

}
