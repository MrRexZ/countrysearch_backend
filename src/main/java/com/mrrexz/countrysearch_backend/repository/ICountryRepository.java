package com.mrrexz.countrysearch_backend.repository;

import com.mrrexz.countrysearch_backend.bean.Country;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ICountryRepository {

    List<Country> getAllCountry();
}
