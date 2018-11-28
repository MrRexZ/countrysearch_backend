package com.mrrexz.countrysearch_backend.service.country.filter;

import com.mrrexz.countrysearch_backend.bean.Country;
import com.mrrexz.countrysearch_backend.bean.LatLng;
import com.mrrexz.countrysearch_backend.service.country.testdata.CountryTestData;
import com.mrrexz.countrysearch_backend.service.location.ILocationService;
import com.mrrexz.countrysearch_backend.service.location.LocationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import static com.mrrexz.countrysearch_backend.service.country.testdata.CountryTestData.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CountryFilterTest {

    @InjectMocks
    private CountryFilterService countryFilterService;

    @Mock
    private LocationService locationService = mock(LocationService.class);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testNameMatcherWorksWithMoreThan1Items() {
        List<Country> oriList = new LinkedList<Country>() {
            {
                add(indonesia);
                add(america);
                add(india);
                add(new Country("", -1, 0));
                add(new Country("Y", 0, 0));
            }
        };

        List<Country> expectedList = new LinkedList<Country>() {
            {
                add(indonesia);
                add(india);
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
                add(singapore);
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


    @Test
    public void testCoorSortWorksWithMoreThan1Items() {
        List<Country> oriList = new LinkedList<Country>() {
            {
                add(america);
                add(indonesia);
                add(uk);
                add(singapore);
            }
        };

        List<Country> expectedList = new LinkedList<Country>() {
            {
                add(indonesia);
                add(singapore);
                add(uk);
                add(america);
            }
        };

        LatLng serverLatLngTest = new LatLng(4.695135, 96.749397);
        when(locationService.getServerLatLngCache()).thenReturn(serverLatLngTest);
        when(locationService.getDistance(anyDouble(), anyDouble(), anyDouble(), anyDouble())).thenCallRealMethod();
        List<Country> actualCountryList = countryFilterService.getSortedCountries(oriList);
        Assert.assertEquals(expectedList, actualCountryList);
    }

    @Test
    public void testCoorSortWorksWith0Items() {

    }

    @Test
    public void testCoorSortWorksWithNullInput() {

    }
}