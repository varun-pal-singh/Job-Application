package com.varun.job_app.job;

import com.varun.job_app.job.Impl.JobServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getJobs(){                                             // get all jobs
        List<Job> jobs = jobService.getJobs();
        if (jobs == null || jobs.isEmpty())   return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id){                               // get a job
        Job job = jobService.getJob(id);
        if (job == null)    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addJob(@RequestBody Job job){                            // post a job
        boolean isJobAdded = jobService.addJob(job);
        if (!isJobAdded)    return new ResponseEntity<>("plz add a company first", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("job added successfully", HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,
                                            @RequestBody Job job){   // update a job
        boolean jobUpdated = jobService.updateJob(id, job);
        if (!jobUpdated)    return new ResponseEntity<>("job doesn't exists", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("job updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeJob(@PathVariable Long id){                         // delete a job
        boolean isJobDeleted = jobService.removeJob(id);
        if (!isJobDeleted)  return new ResponseEntity<>("job doesn't exists", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("job deleted successfully", HttpStatus.OK);
    }

}
