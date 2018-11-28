package com.mrrexz.countrysearch_backend.service.country.autocomplete;

import com.mrrexz.countrysearch_backend.bean.Country;

import java.util.List;

public interface ICountryAutoCompleteService {

    List<Country> getMatchingCountry(String countryToSearch, List<Country> countries);
    List<Country> getSortedCountries(List<Country> countriesToSort);
}
