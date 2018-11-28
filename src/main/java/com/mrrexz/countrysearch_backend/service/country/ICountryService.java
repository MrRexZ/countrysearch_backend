package com.mrrexz.countrysearch_backend.service.country;

import java.util.List;

public interface ICountryService {

    List<String> getMatchingSortedClosestCountriesName(String countryToSearch);
}
