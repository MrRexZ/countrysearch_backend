package com.mrrexz.countrysearch_backend.controller;


import com.mrrexz.countrysearch_backend.bean.Country;
import com.mrrexz.countrysearch_backend.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    ICountryService countryService;

    //TODO: Remove this later
    @RequestMapping("/")
    public String getAllCountries() {
        List<Country> countries = countryService.findAll();
        return "Test";
    }

}
