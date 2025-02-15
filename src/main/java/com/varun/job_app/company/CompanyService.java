package com.varun.job_app.company;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanies();

    Company getCompany(Long id);

    void addCompany(Company company);

    boolean updateCompany(Long id, Company company);

    boolean removeCompany(Long id);
}
