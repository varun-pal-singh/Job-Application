package com.varun.job_app.job.Impl;

import com.varun.job_app.company.Company;
import com.varun.job_app.company.CompanyService;
import com.varun.job_app.job.Job;
import com.varun.job_app.job.JobRepository;
import com.varun.job_app.job.JobService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.smartcardio.CommandAPDU;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{
    private final JobRepository jobRepository;
    private final CompanyService companyService;

    public JobServiceImpl(JobRepository jobRepository, CompanyService companyService) {
        this.jobRepository = jobRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Job> getJobs() {                    // get all jobs
        return jobRepository.findAll();
    }

    @Override
    public Job getJob(Long id) {                    // get a job
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean addJob(Job newJob) {       // post a job
//        Company company = newJob.getCompany();
//        if (company == null)    return false;
//        Long companyId = company.getId();
//        if (companyId == null || companyService.getCompany(companyId) == null)   return false;
        jobRepository.save(newJob);
        return true;
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {    // update a job
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isEmpty())  return false;

        Job curJob = jobOptional.get();

        if (updatedJob.getTitle() != null)          curJob.setTitle(updatedJob.getTitle());
        if (updatedJob.getDescription() != null)    curJob.setDescription(updatedJob.getDescription());
        if (updatedJob.getMinSalary() != null)      curJob.setMinSalary(updatedJob.getMinSalary());
        if (updatedJob.getMaxSalary() != null)      curJob.setMaxSalary(updatedJob.getMaxSalary());
        if (updatedJob.getLocation() != null)       curJob.setLocation(updatedJob.getLocation());
        if (updatedJob.getCompany() != null)        curJob.setCompany(updatedJob.getCompany());

        jobRepository.save(curJob);
        return true;
    }

    @Override
    public boolean removeJob(Long id) {             // delete a job
        Job job = jobRepository.findById(id).orElse(null);
        if (job == null)    return false;
        Company company = job.getCompany();
        company.getJobs().remove(job);
        jobRepository.deleteById(id);
        return true;
    }
}