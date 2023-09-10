package com.example.batch.SpringBatch.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/job")
public class FirstJobTaskletCheck {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @GetMapping("/start")
    public ResponseEntity<String> startJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("ExecutionTime", new Date()) // Example parameter
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(job, jobParameters);

            BatchStatus batchStatus = jobExecution.getStatus();
            return ResponseEntity.ok("Job Status: " + batchStatus);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Job failed to start.");
        }
    }
}
