package com.mrrexz.countrysearch_backend.util.network;

import com.google.gson.*;
import com.mrrexz.countrysearch_backend.bean.Country;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class CountryDeserializer implements JsonDeserializer<List<Country>> {
    @Override
    public List<Country> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray countries =  jsonObject.getAsJsonArray("countries");

        List<Country> countryList = new LinkedList<>();
        for (int i = 0 ; i < countries.size() ; i++) {
            JsonObject countryObj = countries.get(i).getAsJsonObject();
            String name = countryObj.get("name").getAsString();
            double latitude = countryObj.get("lat").getAsDouble();
            double longitude = countryObj.get("lng").getAsDouble();
            Country country = new Country(name, latitude, longitude);
            countryList.add(country);
        }
        return countryList;
    }
}
