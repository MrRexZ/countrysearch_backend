package com.mrrexz.countrysearch_backend.controller;


import com.mrrexz.countrysearch_backend.bean.Country;
import com.mrrexz.countrysearch_backend.service.ICountryService;
import com.mrrexz.countrysearch_backend.util.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    ICountryService countryService;

    @CrossOrigin(origins = Config.CLIENT_URL)
    @RequestMapping(value = "getCountries", method = RequestMethod.GET)
    public @ResponseBody List<String> getMatchingSortedCountries(@RequestParam("search") String countryToSearch) {
        List<String> matchingSortedCountriesName = countryService.getMatchingSortedClosestCountriesName(countryToSearch);
        return matchingSortedCountriesName;
    }



}
