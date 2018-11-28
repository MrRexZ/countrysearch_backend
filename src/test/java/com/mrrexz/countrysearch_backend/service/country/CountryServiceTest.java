package com.mrrexz.countrysearch_backend.service.country;

import com.mrrexz.countrysearch_backend.bean.Country;
import com.mrrexz.countrysearch_backend.repository.CountryRepository;
import com.mrrexz.countrysearch_backend.repository.ICountryRepository;
import com.mrrexz.countrysearch_backend.service.country.filter.CountryFilterService;
import com.mrrexz.countrysearch_backend.service.country.filter.ICountryFilterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

import static com.mrrexz.countrysearch_backend.service.country.testdata.CountryTestData.america;
import static com.mrrexz.countrysearch_backend.service.country.testdata.CountryTestData.india;
import static com.mrrexz.countrysearch_backend.service.country.testdata.CountryTestData.indonesia;
import static org.mockito.Mockito.when;

public class CountryServiceTest {

    @InjectMocks
    CountryService countryService;
    @Mock
    CountryRepository countryRepository;
    @Mock
    CountryFilterService countryFilterService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMatchAndSortReturnsCorrectResultWithMultiInput() {
        List<Country> oriList = new LinkedList<Country>() {
            {
                add(india);
                add(new Country("", -1, 0));
                add(indonesia);
                add(new Country("Y", 0, 0));
                add(america);
            }
        };

        List<Country> matchedList = new LinkedList<Country>() {
            {
                add(india);
                add(indonesia);
            }
        };


        List<Country> sortedMatchedList = new LinkedList<Country>() {
            {
                add(indonesia);
                add(india);
            }
        };

        List<String> expectedList = new LinkedList<String>() {
            {
                add(indonesia.getCountryName());
                add(india.getCountryName());
            }
        };
        String searchQuery = "in";
        when(countryRepository.getAllCountry()).thenReturn(oriList);
        when(countryFilterService.getMatchingCountry(searchQuery, oriList)).thenReturn(matchedList);
        when(countryFilterService.getSortedCountries(matchedList)).thenReturn(sortedMatchedList);
        List<String> actualList = countryService.getMatchingSortedClosestCountriesName(searchQuery);
        Assert.assertEquals(expectedList, actualList);

    }
}
