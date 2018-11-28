package com.mrrexz.countrysearch_backend.controller;


import com.mrrexz.countrysearch_backend.bean.Country;
import com.mrrexz.countrysearch_backend.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "getCountries", method = RequestMethod.GET)
    public @ResponseBody List<Country> getMatchingSortedCountries(@RequestParam("search") String countryToSearch) {
        List<Country> matchingSortedCountry = countryService.getMatchingSortedClosestCountries(countryToSearch);
        return matchingSortedCountry;
    }



}
