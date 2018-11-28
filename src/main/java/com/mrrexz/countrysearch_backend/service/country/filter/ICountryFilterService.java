package com.mrrexz.countrysearch_backend.service.country.filter;

import com.mrrexz.countrysearch_backend.model.Country;

import java.util.List;

public interface ICountryFilterService {

    List<Country> getMatchingCountry(String countryToSearch, List<Country> countries);
    List<Country> getSortedCountries(List<Country> countriesToSort);
}
