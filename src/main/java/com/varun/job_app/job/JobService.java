package com.varun.job_app.job;

import java.util.List;

public interface JobService<T> {
    public List<T> getJobs();                   // get all jobs
    public T getJob(Long id);                   // get a job
    public boolean addJob(T job);               // post a job
    public boolean updateJob(Long id,T job);    // update a job
    public boolean deleteJob(Long id);          // delete a job
}
