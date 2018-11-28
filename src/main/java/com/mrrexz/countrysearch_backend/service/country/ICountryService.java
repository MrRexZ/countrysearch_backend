package com.mrrexz.countrysearch_backend.service.country;

import com.mrrexz.countrysearch_backend.bean.Country;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICountryService {

    List<String> getMatchingSortedClosestCountriesName(String countryToSearch);
}
