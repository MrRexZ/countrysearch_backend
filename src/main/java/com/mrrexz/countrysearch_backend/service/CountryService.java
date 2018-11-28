package com.mrrexz.countrysearch_backend.service;

import com.mrrexz.countrysearch_backend.bean.Country;
import com.mrrexz.countrysearch_backend.repository.CountryRepository;
import com.mrrexz.countrysearch_backend.repository.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService implements ICountryService {

    @Autowired
    ICountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.getAllCountry();
    }
}
