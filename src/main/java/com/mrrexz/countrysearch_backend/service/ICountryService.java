package com.mrrexz.countrysearch_backend.service;

import com.mrrexz.countrysearch_backend.bean.Country;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICountryService {

    public List<Country> findAll();

    List<Country> getMatchingSortedClosestCountries(String cityToSearch);
}
