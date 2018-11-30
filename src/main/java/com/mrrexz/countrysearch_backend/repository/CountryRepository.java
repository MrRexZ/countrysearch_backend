package com.mrrexz.countrysearch_backend.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mrrexz.countrysearch_backend.model.Country;
import com.mrrexz.countrysearch_backend.util.network.CountryDeserializer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


@Component
public class CountryRepository implements ICountryRepository{
    @Override
    public List<Country> getAllCountry() {
        try {
            ClassPathResource countriesJsonResource = new ClassPathResource("json/countries_metadata.json");
            String jsonContent = new String(FileCopyUtils.copyToByteArray(countriesJsonResource.getInputStream()));
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(new TypeToken<List<Country>>(){}.getType(), new CountryDeserializer())
                    .create();
            List<Country> countryList = gson.fromJson(jsonContent, new TypeToken<List<Country>>(){}.getType());
            return countryList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
