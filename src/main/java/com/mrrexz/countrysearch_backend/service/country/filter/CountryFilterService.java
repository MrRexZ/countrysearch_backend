package com.mrrexz.countrysearch_backend.service.country.filter;

import com.mrrexz.countrysearch_backend.bean.Country;
import com.mrrexz.countrysearch_backend.bean.LatLng;
import com.mrrexz.countrysearch_backend.repository.ICountryRepository;
import com.mrrexz.countrysearch_backend.service.location.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryFilterService implements ICountryFilterService {


    @Autowired
    ICountryRepository countryRepository;
    @Autowired
    ILocationService locationService;


    @Override
    public List<Country> getMatchingCountry(String countryToSearch, List<Country> countriesToMatch) {
        return countriesToMatch.stream().filter(country -> {
            String countryName = country.getCountryName();
            if (countryName.length() < countryToSearch.length()) {
                return false;
            }
            return country.getCountryName().substring(0, countryToSearch.length()).equalsIgnoreCase(countryToSearch);
        }).collect(Collectors.toList());

    }

    @Override
    public List<Country> getSortedCountries(List<Country> countriesToSort) {
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
