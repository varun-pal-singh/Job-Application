package com.varun.job_app.job.Impl;

import com.varun.job_app.job.Job;
import com.varun.job_app.job.JobService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService<Job> {
    List<Job> jobs = new LinkedList<>();
    Long id = 1L;

    @Override
    public List<Job> getJobs() {                    // get all jobs
        return jobs;
    }

    @Override
    public Job getJob(Long id) {                    // get a job
        for (Job job : jobs){
            if (job.getId().equals(id)){
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean addJob(@NotNull Job job) {       // post a job
        job.setId(id);
        job.setTitle(job.getTitle() + id++);
        return jobs.add(job);
    }

    @Override
    public boolean updateJob(Long id, Job job) {    // update a job
        for (Job curJob : jobs){
            if (curJob.getId().equals(id)){
                curJob.setTitle(job.getTitle() + " " + id);
                curJob.setDescription(job.getDescription());
                curJob.setMinSalary(job.getMinSalary());
                curJob.setMaxSalary(job.getMaxSalary());
                curJob.setLocation(job.getLocation());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteJob(Long id) {             // delete a job
        for (Job curJob: jobs){
            if (curJob.getId().equals(id)){
                jobs.remove(curJob);
                return true;
            }
        }
        return false;
    }
}