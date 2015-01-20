package org.restlet.example.contact.api.db;

import org.restlet.example.contact.api.core.exception.BadParameterException;
import org.restlet.example.contact.api.core.exception.NotFoundException;
import org.restlet.example.contact.api.model.Company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Manuel Boillod
 */
public class CompanyDb {

    public static CompanyDb INSTANCE = new CompanyDb();

    private AtomicInteger idGenerator = new AtomicInteger();

    private Map<Integer, Company> companies = Collections.synchronizedMap(new LinkedHashMap<Integer, Company>());

    public List<Company> getCompanies() {
        return new ArrayList<>(companies.values());
    }

    public Company getCompany(Integer companyId) {
        return companies.get(companyId);
    }

    public Company addCompany(Company company) {
        if (company.getId() == null) {
            company.setId(idGenerator.incrementAndGet());
        } else {
            Company existing = getCompany(company.getId());
            if (existing != null) {
                throw new BadParameterException("Company with id '" + company.getId() + "' already exists");
            }
        }
        companies.put(company.getId(), company);
        return company;
    }

    public Company updateCompany(Integer companyId, Company company) {
        Company existing = getCompany(companyId);
        if (existing == null) {
            throw new NotFoundException("No company with id '" + companyId + "'");
        }

        companies.put(companyId, company);
        return company;
    }

    public boolean deleteCompany(Integer companyId) {
        Company existing = companies.remove(companyId);
        return existing != null;
    }
}
