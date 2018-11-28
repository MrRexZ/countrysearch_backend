package com.mrrexz.countrysearch_backend.service.country.filter;

import com.mrrexz.countrysearch_backend.bean.Country;
import com.mrrexz.countrysearch_backend.service.location.LocationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;

public class CountryFilterTest {

    @InjectMocks
    private CountryFilterService countryFilterService;

    @Mock
    private LocationService locationService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNameMatcherWorksWithMoreThan1Items() {
        List<Country> oriList = new LinkedList<Country>() {
            {
                add(new Country("Indonesia", 0, 0));
                add(new Country("America", 0, 0));
                add(new Country("India", 0, 0));
                add(new Country("", -1, 0));
                add(new Country("Austria", 0, 0));
                add(new Country("Y", 0, 0));
            }
        };

        List<Country> expectedList = new LinkedList<Country>() {
            {
                add(new Country("Indonesia", 0, 0));
                add(new Country("India", 0, 0));
            }
        };

        String searchQuery = "ind";
        List<Country> matchedCountry = countryFilterService.getMatchingCountry(searchQuery, oriList);
        Assert.assertEquals(expectedList, matchedCountry);

    }

    @Test
    public void testNameMatcherWorksWith1Item() {
        List<Country> oriList = new LinkedList<Country>() {
            {
                add(new Country("Austria", 0, 0));
            }
        };
        List<Country> expectedList = new LinkedList<Country>();
        String searchQuery = "ind";
        List<Country> matchedCountry = countryFilterService.getMatchingCountry(searchQuery, oriList);
        Assert.assertEquals(expectedList, matchedCountry);
    }

    @Test
    public void testNameMatcherWorksWith0Item() {
        List<Country> oriList = new LinkedList<Country>();
        List<Country> expectedList = new LinkedList<Country>();
        String searchQuery = "ind";
        List<Country> matchedCountry = countryFilterService.getMatchingCountry(searchQuery, oriList);
        Assert.assertEquals(expectedList, matchedCountry);
    }

    @Test
    public void testNameMatcherWorksWithNull() {
        List<Country> expectedList = new LinkedList<Country>();
        String searchQuery = "ind";
        List<Country> matchedCountry = countryFilterService.getMatchingCountry(searchQuery, null);
        Assert.assertEquals(expectedList, matchedCountry);
    }
}
