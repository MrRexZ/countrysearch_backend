package com.mrrexz.countrysearch_backend.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.mrrexz.countrysearch_backend.bean.LatLng;
import com.mrrexz.countrysearch_backend.util.config.Config;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


@Service
public class LocationService implements ILocationService {

    @Override
    public LatLng getServerLatLng() throws IOException {
        JsonObject serverLocationJson = getCurrentLocationJSON();
        double latitude = serverLocationJson.get("latitude").getAsDouble();
        double longitude = serverLocationJson.get("longitude").getAsDouble();
        return new LatLng(latitude, longitude);

    }

    private JsonObject getCurrentLocationJSON() throws IOException {
        URL url = new URL(Config.LOCATOR_API);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        InputStream stream = connection.getInputStream();
        JsonObject json;

        JsonElement element = new JsonParser().parse(
                new InputStreamReader(stream)
        );
        json = element.getAsJsonObject();
        return json;

    }
}
