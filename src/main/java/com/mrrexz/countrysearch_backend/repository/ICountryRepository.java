package com.mrrexz.countrysearch_backend.repository;

import com.mrrexz.countrysearch_backend.bean.Country;

import java.util.List;

public interface ICountryRepository {

    List<Country> getAllCountry();
}
