@Bean
public Step secondStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
return new StepBuilder("second step", jobRepository)
.<Customer,Customer>chunk(10,transactionManager)
.reader(reader())
.writer(writer())
.build();
}

here i had <String,String> earlier as i was reading and writing <Customer,Customer>
remember the typecheck to adhere to actual values