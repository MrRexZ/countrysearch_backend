package com.mrrexz.countrysearch_backend.service;

import com.mrrexz.countrysearch_backend.bean.Country;
import com.mrrexz.countrysearch_backend.bean.LatLng;
import com.mrrexz.countrysearch_backend.repository.ICountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService implements ICountryService {

    @Autowired
    ICountryRepository countryRepository;
    @Autowired
    ILocationService locationService;

    public List<Country> getMatchingSortedClosestCountries(String countryToSearch) {
        List<Country> allCountries = countryRepository.getAllCountry();
        List<Country> matchingCountries = getMatchingCountry(countryToSearch, allCountries);
        List<Country> matchingSortedCountries = getSortedCountries(matchingCountries);
        return matchingSortedCountries;
    }

    @Override
    public List<String> getMatchingSortedClosestCountriesName(String countryToSearch) {
        return getMatchingSortedClosestCountries(countryToSearch).stream().map(country -> country.getCountryName()).collect(Collectors.toList());
    }

    private List<Country> getMatchingCountry(String countryToSearch, List<Country> countries) {
        return countries.stream().filter(country -> {
            String countryName = country.getCountryName();
            if (countryName.length() < countryToSearch.length()) {
                return false;
            }
            return country.getCountryName().substring(0, countryToSearch.length()).equalsIgnoreCase(countryToSearch);
        }).collect(Collectors.toList());

    }

    private List<Country> getSortedCountries(List<Country> countriesToSort) {
        LatLng serverLatLng;
        serverLatLng = locationService.getServerLatLngCache();
        if (serverLatLng == null) {
            return countriesToSort;
        }
        return countriesToSort.stream().sorted((country, anotherCountry) -> {
            double distanceToFirstCountry = locationService.getDistance(serverLatLng.getLatitude(), serverLatLng.getLongitude(), country.getLatitude(), country.getLongitude());
            double distanceToSecondCountry = locationService.getDistance(serverLatLng.getLatitude(), serverLatLng.getLongitude(), anotherCountry.getLatitude(), anotherCountry.getLongitude());
            if (distanceToFirstCountry - distanceToSecondCountry > 0) {
                return -1;
            } else if (distanceToFirstCountry - distanceToSecondCountry == 0) {
                return 0;
            } else {
                return 1;
            }
        }).collect(Collectors.toList());

    }
}
