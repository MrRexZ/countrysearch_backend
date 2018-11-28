package com.mrrexz.countrysearch_backend.repository;

import com.mrrexz.countrysearch_backend.model.Country;

import java.util.List;

public interface ICountryRepository {

    List<Country> getAllCountry();
}
