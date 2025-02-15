package com.varun.job_app.company.impl;

import com.varun.job_app.company.Company;
import com.varun.job_app.company.CompanyRepository;
import com.varun.job_app.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isEmpty())  return false;

        Company curCompany = companyOptional.get();

        if (updatedCompany.getName() != null)           curCompany.setName(updatedCompany.getName());
        if (updatedCompany.getDescription() != null)    curCompany.setDescription(updatedCompany.getDescription());
        if (updatedCompany.getJobs() != null)           curCompany.setJobs(updatedCompany.getJobs());
        if (updatedCompany.getReviews() != null)        curCompany.setReviews(updatedCompany.getReviews());

        companyRepository.save(curCompany);
        return true;
    }

    @Override
    public boolean removeCompany(Long id) {
        if (!companyRepository.existsById(id))  return false;
        companyRepository.deleteById(id);
        return true;
    }
}
