package com.mrrexz.countrysearch_backend.service.country;

import com.mrrexz.countrysearch_backend.bean.Country;
import com.mrrexz.countrysearch_backend.repository.ICountryRepository;
import com.mrrexz.countrysearch_backend.service.country.filter.ICountryFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService implements ICountryService {
    @Autowired
    ICountryRepository countryRepository;
    @Autowired
    ICountryFilterService countryAutoCompleteService;


    @Override
    public List<String> getMatchingSortedClosestCountriesName(String countryToSearch) {
        return getMatchingSortedClosestCountries(countryToSearch).stream().map(country -> country.getCountryName()).collect(Collectors.toList());
    }

    private List<Country> getMatchingSortedClosestCountries(String countryToSearch) {
        List<Country> allCountries = countryRepository.getAllCountry();
        List<Country> matchingCountries = countryAutoCompleteService.getMatchingCountry(countryToSearch, allCountries);
        List<Country> matchingSortedCountries = countryAutoCompleteService.getSortedCountries(matchingCountries);
        return matchingSortedCountries;
    }
}
