package com.varun.job_app.job;

import java.util.List;

public interface JobService {
    List<Job> getJobs();                   // get all jobs
    Job getJob(Long id);                   // get a job
    boolean addJob(Job job);               // post a job
    boolean updateJob(Long id, Job job);    // update a job
    boolean removeJob(Long id);          // delete a job
}
