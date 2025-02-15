package com.varun.job_app.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies(){                    // get all companies
        List<Company> companies = companyService.getCompanies();
        if (companies == null || companies.isEmpty())  return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id){       // get a company
        Company company = companyService.getCompany(id);
        if (company == null)    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company){    // add a company
        companyService.addCompany(company);
        return new ResponseEntity<>("company details added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,
                                                @RequestBody Company company){
        boolean isCompanyUpdated = companyService.updateCompany(id, company);
        if (!isCompanyUpdated)  return new ResponseEntity<>("company not found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("company details updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeCompany(@PathVariable Long id){
        boolean isCompanyRemoved = companyService.removeCompany(id);
        if (!isCompanyRemoved)  return new ResponseEntity<>("company not found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("company removed successfully", HttpStatus.OK);
    }
}
